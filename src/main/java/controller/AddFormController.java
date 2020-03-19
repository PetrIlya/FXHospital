package controller;

import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import view.form.AddForm;
import view.menu.table.PageableTable;

import java.util.List;

@Getter
@Setter
public class AddFormController {
    @NonNull
    private final List<Record> records;
    @NonNull
    private final AddForm form;
    @NonNull
    private final PageableTable table;

    public AddFormController(@NonNull List<Record> records,
                             @NonNull PageableTable table) {
        this.records = records;
        this.table = table;
        this.form = new AddForm(this::processAddEvent);
        this.form.show();
    }

    private void processAddEvent(ActionEvent e) {
        this.records.add(form.getRecord());
        this.table.update();
    }
}
