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
import util.factories.table.TableRecordStructureFactory;
import view.form.AddForm;
import view.form.SearchForm;
import view.menu.table.PageableTable;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class SearchFormController {
    @NonNull private final List<Record> records;
    @NonNull
    private final SearchForm form;
    @NonNull
    private PageableTable table;

    private List<Record> currentTableRecords = Collections.emptyList();

    public SearchFormController(@NonNull List<Record> records) {
        this.records = records;
        this.form = new SearchForm(null);
    }

    private void processSearchEvent(ActionEvent e) {
        //TODO: Add implementation
        this.form.meetsSearchRequirements(null);
    }
}
