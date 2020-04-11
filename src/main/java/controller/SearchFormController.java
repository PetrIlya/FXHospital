package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import network.RequestProcessor;
import util.factories.table.TableStructureFactory;
import view.form.SearchForm;
import view.table.OfflineTable;
import view.table.PageableTable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchFormController {
    private List<Record> foundedRecords;
    @NonNull
    private final SearchForm form;
    @NonNull
    private PageableTable table;
    private final RequestProcessor processor;

    public SearchFormController(RequestProcessor processor) {
        this.processor = processor;
        this.foundedRecords = new ArrayList<>();
        this.table = new OfflineTable(TableStructureFactory.buildTableStructure(),
                foundedRecords);
        this.form = new SearchForm(this::processSearchEvent, table);
        this.form.show();
    }

    private void processSearchEvent(ActionEvent e) {
        this.foundedRecords.clear();
        this.foundedRecords.
                addAll(this.processor.
                        searchRecordByCondition(this.form.getConditionObject()));
        this.table.hardUpdate();
        new Alert(Alert.AlertType.INFORMATION, "Founded: " + this.foundedRecords.size()).show();
    }
}
