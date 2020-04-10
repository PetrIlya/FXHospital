package view.table;

import com.google.common.collect.Lists;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;

import java.util.List;

@Getter
@Setter
public class OfflineTable implements PageableTable {
    public static final int DEFAULT_RECORDS_PER_PAGE_VALUE = 10;
    public static final int DEFAULT_PAGE = 0;

    private final VBox topContainer;
    @NonNull
    private final List<Record> records;
    @NonNull
    private final TableView<Record> table;
    private final TableControlMenu tableControlMenu;
    private int recordsPerPage = DEFAULT_RECORDS_PER_PAGE_VALUE;
    private List<List<Record>> pages;
    private int currentPage;

    public OfflineTable(@NonNull TableView<Record> table, @NonNull List<Record> records) {
        this.topContainer = new VBox();
        this.table = table;
        this.records = records;
        this.tableControlMenu = new TableControlMenu(
                this::nextPageEvent, this::firstPageEvent,
                this::previousPageEvent, this::lastPageEvent,
                this::update);

        this.topContainer.getChildren().addAll(table,
                tableControlMenu.getTopContainer());

        this.currentPage = 0;
        this.pages = Lists.partition(records, recordsPerPage);
        if (records.size() != 0) {
            hardUpdate();
        }
    }

    @Override
    public final void update() {
        this.table.getItems().clear();
        if (this.pages.size() != 0) {
            this.pages.get(this.currentPage).forEach(this.table.getItems()::add);
        }
        updateLabelText();
    }

    @Override
    public final void hardUpdate() {
        this.pages = Lists.partition(records, recordsPerPage);
        this.currentPage = DEFAULT_PAGE;
        update();
    }

    @Override
    public final void update(ActionEvent e) {
        try {
            this.recordsPerPage = Integer.parseInt(tableControlMenu.getRecordsPerPage().getText());
        } catch (NumberFormatException ex) {
            this.recordsPerPage = DEFAULT_RECORDS_PER_PAGE_VALUE;
        }
        this.hardUpdate();


    }

    @Override
    public void nextPageEvent(ActionEvent e) {
        if (this.currentPage != pages.size() - 1) {
            this.currentPage++;
            update();
        }
    }

    @Override
    public void previousPageEvent(ActionEvent e) {
        if (this.currentPage != 0) {
            this.currentPage--;
            update();
        }
    }

    @Override
    public void firstPageEvent(ActionEvent e) {
        this.currentPage = 0;
        update();
    }

    @Override
    public void lastPageEvent(ActionEvent e) {
        this.currentPage = pages.size() - 1;
        update();
    }

    @Override
    public void updateLabelText() {
        this.tableControlMenu.getText().setText(TableControlMenu.CURRENT_PAGE +
                (this.currentPage + 1) + "/" + this.pages.size() +
                " " + TableControlMenu.AMOUNT_OF_RECORDS + this.records.size());
    }

    @Override
    public VBox getTopContainer() {
        return this.topContainer;
    }
}
