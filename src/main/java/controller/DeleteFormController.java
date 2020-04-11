package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import network.RequestProcessor;
import util.factories.table.TableStructureFactory;
import view.form.DeleteForm;
import view.table.OfflineTable;
import view.table.PageableTable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DeleteFormController {
    @NonNull
    private final DeleteForm form;
    private List<Record> deletedRecords;
    @NonNull
    private PageableTable table;
    @NonNull
    private PageableTable mainTable;
    private final RequestProcessor processor;

    public DeleteFormController(RequestProcessor processor, @NonNull PageableTable mainTable) {
        this.processor = processor;
        this.deletedRecords = new ArrayList<>();
        this.table = new OfflineTable(TableStructureFactory.buildTableStructure(),
                deletedRecords);
        this.mainTable = mainTable;
        this.form = new DeleteForm(this::processDeleteEvent, table);
        this.form.show();
    }

    public void processDeleteEvent(ActionEvent e) {
        this.deletedRecords.clear();
        this.deletedRecords.
                addAll(this.processor.
                        deleteRecordByCondition(this.form.getConditionObject()));
        this.table.hardUpdate();
        this.mainTable.hardUpdate();
        new Alert(Alert.AlertType.INFORMATION, "Deleted: " + this.deletedRecords.size()).show();
    }
}
