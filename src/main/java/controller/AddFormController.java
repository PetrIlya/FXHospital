package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import view.form.AddForm;
import view.form.SearchForm;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class AddFormController {
    @NonNull
    private final List<Record> records;
    @NonNull
    private final AddForm form;
    @NonNull
    private final TableView<Record> mainTable;

    private List<Record> currentTableRecords = Collections.emptyList();

    public AddFormController(@NonNull List<Record> records,
                             @NonNull TableView<Record> mainTable) {
        this.records = records;
        this.mainTable = mainTable;
        this.form = new AddForm(this::processAddEvent);
        this.form.show();
    }

    private void processAddEvent(ActionEvent e) {
        this.records.add(form.getRecord());
        //this.records.forEach(this.mainTable.getItems()::add);
        this.mainTable.getItems().add(form.getRecord());
    }
}
