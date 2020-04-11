package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import util.factories.table.TableStructureFactory;
import view.form.DeleteForm;
import view.table.PageableTable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DeleteFormController {
    @NonNull
    private final List<Record> records;
    @NonNull
    private final DeleteForm form;
    private List<Record> deletedRecords;
    @NonNull
    private PageableTable table;
    @NonNull
    private PageableTable mainTable;

    public DeleteFormController(@NonNull List<Record> records, @NonNull PageableTable mainTable) {
        this.records = records;
        this.deletedRecords = new ArrayList<>();
        this.table = new PageableTable(TableStructureFactory.buildTableStructure(),
                deletedRecords);
        this.mainTable = mainTable;
        this.form = new DeleteForm(this::processDeleteEvent, table);
        this.form.show();
    }

    public void processDeleteEvent(ActionEvent e) {
        this.deletedRecords.clear();
        this.records.stream().
                filter(this.form::meetsDeleteRequirements).
                forEach(this.deletedRecords::add);
        this.records.removeAll(deletedRecords);
        this.table.hardUpdate();
        this.mainTable.hardUpdate();

        new Alert(Alert.AlertType.INFORMATION, "Deleted: " + this.deletedRecords.size()).show();
    }
}
