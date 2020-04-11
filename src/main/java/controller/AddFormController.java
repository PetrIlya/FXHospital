package controller;

import javafx.event.ActionEvent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import network.PackInformation;
import network.RequestProcessor;
import util.RecordGenerator;
import view.form.AddForm;
import view.table.PageableTable;

import java.util.List;

@Getter
@Setter
public class AddFormController {
    @NonNull
    private final AddForm form;
    @NonNull
    private final PageableTable table;
    @NonNull
    private final RequestProcessor processor;
    @NonNull
    private final PackInformation currentPack;
    @NonNull
    private final List<PackInformation> packInformationList;

    public AddFormController(@NonNull PageableTable table,
                             @NonNull RequestProcessor processor,
                             @NonNull PackInformation currentPack,
                             @NonNull List<PackInformation> packInformationList) {
        this.table = table;
        this.processor = processor;
        this.currentPack = currentPack;
        this.packInformationList = packInformationList;

        this.form = new AddForm(this::processAddEvent);
        this.form.show();
    }

    private void processAddEvent(ActionEvent e) {
        //TODO: Remove generator.
        this.processor.postRecord(RecordGenerator.generateRecord());
        this.currentPack.incrementRecordsAmount();
        this.packInformationList.
                stream().
                takeWhile(this.currentPack::equals).
                findFirst().
                ifPresent(PackInformation::incrementRecordsAmount);
        this.table.hardUpdate();
    }
}
