package view.network;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import util.factories.ButtonFactory;

import java.util.List;

@Getter
public class PackManagerForm {
    private final List<String> names;
    private VBox container;
    private ComboBox<String> packNames;
    private Button select;

    private TextField newPackName;
    private Button add;

    public PackManagerForm(List<String> names) {
        //TODO: Add correct event processors
        this.names = names;
        this.container = new VBox();
        this.packNames = new ComboBox<>(FXCollections.
                observableArrayList(names));
        this.newPackName = new TextField();
        this.select = ButtonFactory.generateButton(null, "Select");
        this.add = ButtonFactory.generateButton(null, "Add new pack");
        configContainer();
    }

    private void configContainer() {
        this.container.
                getChildren().
                addAll(packNames, select, newPackName, add);
    }
}
