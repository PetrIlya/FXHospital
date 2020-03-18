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
import view.form.SearchForm;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class SearchFormController {
    @NonNull private final List<Record> records;
    @NonNull private final Stage window;
    @NonNull private final Scene scene;
    @NonNull private final SearchForm form;
    @NonNull private final TableView<Record> recordTable;

    private List<Record> currentTableRecords = Collections.emptyList();

    public SearchFormController(@NonNull List<Record> records,
                                @NonNull Stage window,
                                @NonNull Scene scene,
                                @NonNull SearchForm form,
                                @NonNull TableView<Record> recordTable) {
        this.records = records;
        this.window = window;
        this.scene = scene;
        this.form = form;
        this.recordTable = recordTable;
    }

    private final EventHandler<ActionEvent> searchEvent = e -> {

    };
}
