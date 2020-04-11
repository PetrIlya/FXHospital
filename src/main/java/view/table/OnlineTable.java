package view.table;

import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import lombok.NonNull;
import model.Record;
import network.PackInformation;
import network.RequestProcessor;

import java.util.List;

public class OnlineTable implements PageableTable {

    private final VBox topContainer;
    @NonNull
    private final List<Record> records;
    private final TableView<Record> table;
    private final TableControlMenu tableControlMenu;
    private final PackInformation currentPack;
    private final RequestProcessor processor;
    private int recordsPerPage = DEFAULT_RECORDS_PER_PAGE_VALUE;
    private int currentPage;

    public OnlineTable(@NonNull TableView<Record> table,
                       PackInformation currentPack,
                       RequestProcessor processor) {
        this.topContainer = new VBox();
        this.table = table;
        this.currentPack = currentPack;
        this.processor = processor;
        this.records = processor.getRecords(DEFAULT_PAGE, DEFAULT_RECORDS_PER_PAGE_VALUE);
        this.tableControlMenu = new TableControlMenu(
                this::nextPageEvent, this::firstPageEvent,
                this::previousPageEvent, this::lastPageEvent,
                this::update);
        this.topContainer.getChildren().addAll(table,
                tableControlMenu.getTopContainer());
        this.currentPage = DEFAULT_PAGE;
        if (records.size() != 0) {
            hardUpdate();
        }
    }

    @Override
    public final void update() {
        this.table.getItems().clear();
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
        //TODO Add implementation
    }

    @Override
    public void previousPageEvent(ActionEvent e) {
        //TODO Add implementation
    }

    @Override
    public void firstPageEvent(ActionEvent e) {
        this.currentPage = 0;
        update();
    }

    @Override
    public void lastPageEvent(ActionEvent e) {
        //TODO Add implementation
        update();
    }

    @Override
    public void updateLabelText() {
        //TODO Add implementation
        this.tableControlMenu.getText().setText(TableControlMenu.CURRENT_PAGE +
                (this.currentPage + 1) + "/" + DEFAULT_PAGE +
                " " + TableControlMenu.AMOUNT_OF_RECORDS + this.records.size());
    }

    @Override
    public VBox getTopContainer() {
        return this.topContainer;
    }
}
