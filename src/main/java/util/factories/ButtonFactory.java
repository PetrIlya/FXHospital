package util.factories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ButtonFactory {

    public static Button buttonBuilder(EventHandler<ActionEvent> handler) {
        Button button = new Button();
        button.setOnAction(handler);
        return button;
    }

    public static Button buttonBuilder(EventHandler<ActionEvent> handler, String buttonText) {
        Button button = new Button(buttonText);
        button.setOnAction(handler);
        return button;
    }

    public static Button buttonBuilder(EventHandler<ActionEvent> handler, ImageView image) {
        Button button = new Button();
        button.setGraphic(image);
        button.setOnAction(handler);
        return button;
    }
}
