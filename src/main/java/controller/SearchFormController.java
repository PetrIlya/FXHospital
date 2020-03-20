package controller;

import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import util.factories.table.TableStructureFactory;
import view.form.SearchForm;
import view.table.PageableTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class SearchFormController {
    @NonNull
    private final List<Record> records;
    private List<Record> foundedRecords;
    @NonNull
    private final SearchForm form;
    @NonNull
    private PageableTable table;

    private List<Record> currentTableRecords = Collections.emptyList();

    public SearchFormController(@NonNull List<Record> records) {
        this.records = records;
        this.foundedRecords = new ArrayList<>();
        this.table = new PageableTable(TableStructureFactory.buildTableStructure(),
                foundedRecords);
        this.form = new SearchForm(this::processSearchEvent, table);
        this.form.show();
    }

    private void processSearchEvent(ActionEvent e) {
        this.foundedRecords.clear();
        this.records.stream().
                filter(this.form::meetsSearchRequirements).
                forEach(this.foundedRecords::add);
        this.table.hardUpdate();
    }
}
