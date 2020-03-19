package view.menu.table;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import controller.ApplicationContainerController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;

import java.util.List;
import java.util.Spliterators;

@Getter
@Setter
public class PageableTable {
    private final HBox topContainer;

    @NonNull
    private final List<Record> records;
    private int recordsPerPage = ApplicationContainerController.DEFAULT_RECORDS_PER_PAGE_VALUE;
    private List<List<Record>> pages;

    @NonNull
    private final TableView<Record> table;
    private final TableControlMenu tableControlMenu;

    private int currentPage;

    public PageableTable(@NonNull TableView<Record> table, @NonNull List<Record> records) {
        this.topContainer = new HBox();
        this.table = table;
        this.records = records;
        this.tableControlMenu = new TableControlMenu(
                this::nextPageEvent, this::firstPageEvent,
                this::previousPageEvent, this::lastPageEvent);

        this.topContainer.getChildren().addAll(table,
                tableControlMenu.getTopContainer());

        this.currentPage = 0;
        this.pages = Lists.partition(records, recordsPerPage);
    }

    public final void changeCurrentPage() {
        this.table.getItems().clear();
        pages.get(this.currentPage).forEach(this.table.getItems()::add);
    }

    private void nextPageEvent(ActionEvent e) {
        this.currentPage++;
        changeCurrentPage();
    }

    private void previousPageEvent(ActionEvent e) {
        this.currentPage--;
        changeCurrentPage();
    }

    private void firstPageEvent(ActionEvent e) {
        this.currentPage = 0;
        changeCurrentPage();
    }

    private void lastPageEvent(ActionEvent e) {
        this.currentPage = pages.size() - 1;
        changeCurrentPage();
    }
}
