package view.form;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class DeleteForm extends Form {
    private static final String ACTION_NAME = "Delete";

    public DeleteForm(EventHandler<ActionEvent> eventProcessor) {
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
}
