package view.network;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private final VBox container;
    private final ComboBox<String> packNames;
    private final Button select;

    private final TextField packToAdd;
    private final Button add;

    private final TextField packToDelete;
    private final Button delete;

    public PackManagerForm(List<String> names,
                           EventHandler<ActionEvent> selectionEvent,
                           EventHandler<ActionEvent> addPackEvent,
                           EventHandler<ActionEvent> deletePackEvent) {
        this.names = names;
        this.container = new VBox();
        this.packNames = new ComboBox<>((ObservableList) names);
        this.packToAdd = new TextField();
        this.select = ButtonFactory.generateButton(selectionEvent, "Select");
        this.add = ButtonFactory.generateButton(addPackEvent, "Add new pack");

        this.packToDelete = new TextField();
        this.delete = ButtonFactory.generateButton(deletePackEvent, "Delete pack");
        configContainer();
    }

    private void configContainer() {
        this.container.
                getChildren().
                addAll(packNames, select, packToAdd, add, packToDelete, delete);
    }

    public String getPackToAdd() {
        return this.packToAdd.getText();
    }

    public String getPackToDelete() {
        return this.packToDelete.getText();
    }

    public String getCurrentSelectedPackName() {
        return this.packNames.getSelectionModel().getSelectedItem();
    }
}
