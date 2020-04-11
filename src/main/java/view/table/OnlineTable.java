package view.table;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Record;
import network.PackInformation;
import network.RequestProcessor;

import java.util.List;

public class OnlineTable implements PageableTable {

    private final VBox topContainer;
    private final List<Record> records;
    private final TableView<Record> table;
    private final TableControlMenu tableControlMenu;
    private final PackInformation currentPack;
    private final RequestProcessor processor;
    private int recordsPerPage = DEFAULT_RECORDS_PER_PAGE_VALUE;
    private int currentPage;

    public OnlineTable(List<Record> records,
                       TableView<Record> table,
                       PackInformation currentPack,
                       RequestProcessor processor) {
        this.topContainer = new VBox();
        this.table = table;
        this.currentPack = currentPack;
        this.processor = processor;

        records.clear();
        records.addAll(processor.
                getRecords(DEFAULT_PAGE,
                        DEFAULT_RECORDS_PER_PAGE_VALUE));


        this.records = records;
        this.tableControlMenu = new TableControlMenu(
                this::nextPageEvent, this::firstPageEvent,
                this::previousPageEvent, this::lastPageEvent,
                this::update);
        this.topContainer.getChildren().addAll(table,
                tableControlMenu.getTopContainer());
        this.currentPage = DEFAULT_PAGE;
    }

    @Override
    public final void update() {
        this.table.getItems().clear();
        this.records.clear();
        this.records.addAll(processor.getRecords(currentPage, recordsPerPage));
        this.table.getItems().addAll(records);
        updateLabelText();
    }

    @Override
    public final void hardUpdate() {
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
        if (this.currentPage + 1 != this.currentPack.getLastPageIndex(recordsPerPage)) {
            this.currentPage++;
            update();
        }
    }

    @Override
    public void previousPageEvent(ActionEvent e) {
        if (this.currentPage - 1 >= 0) {
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
        this.currentPage = this.currentPack.getLastPageIndex(recordsPerPage) - 1;
        update();
    }

    @Override
    public void updateLabelText() {
        this.tableControlMenu.
                getText().
                setText(TableControlMenu.CURRENT_PAGE +
                        (this.currentPage + 1) +
                        "/" +
                        this.currentPack.getLastPageIndex(recordsPerPage) +
                        " " +
                        TableControlMenu.AMOUNT_OF_RECORDS +
                        this.currentPack.getTotalRecordsAmount());
    }

    @Override
    public VBox getTopContainer() {
        return this.topContainer;
    }
}
