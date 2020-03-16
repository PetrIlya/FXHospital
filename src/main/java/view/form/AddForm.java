package view.form;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;

public class AddForm extends Form {
    private static final String ACTION_NAME = "Add";

    public AddForm(EventHandler<ActionEvent> eventProcessor) {
        super(eventProcessor, ACTION_NAME);
        super.removeBindings();
    }
}
