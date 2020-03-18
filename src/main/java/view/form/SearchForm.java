package view.form;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import model.Record;

public class SearchForm extends Form {
    private static final String ACTION_NAME = "Search";

    public SearchForm(EventHandler<ActionEvent> eventProcessor) {
        super(eventProcessor, ACTION_NAME);
        reconfigureForm();
    }

    private void reconfigureForm() {
        VBox container = super.getDialogContainer();
        container.getChildren().removeAll(
                super.getIllnessAnalyse(), super.getIllnessAnalyseIdtf(),
                super.getStudentMiddleName(), super.getStudentMiddleNameIdtf(),
                super.getStudentName(), super.getStudentNameIdtf());
    }

    public boolean meetsSearchRequirements(Record record) {
        boolean currentStatus = false;
        return false;
    }

    private boolean firstStageCheck(Record record) {
        String data = super.getStudentSurname().getText();
        if (!data.equals(EMPTY_STRING)) {
            if (data.equals(record.getStudent().getBirthDate().get())) {
                return false;
            }
        } else {
            data = super.getStudentAddress().getText();
            if (!data.equals(EMPTY_STRING)) {
                if (!record.getStudent().getAddress().get().equals(data)) {
                    return false;
                }
            }
        }

        return false;
    }
}
