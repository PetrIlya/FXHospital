package view;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;

import java.util.List;

@Getter
public class MainContainer {
    @NonNull private final BorderPane mainContainer;
    @NonNull private final HBox tableMenuContainer;
    @NonNull private final ToolBar toolBarContainer;
    @NonNull private final MenuBar menuBarContainer;
    @NonNull private final TableView<Record> recordTable;

    public MainContainer(Stage mainWindow,
                         HBox tableMenuContainer,
                         ToolBar toolBarContainer,
                         MenuBar menuBarContainer,
                         TableView<Record> recordTable) {
        this.tableMenuContainer = tableMenuContainer;
        this.toolBarContainer = toolBarContainer;
        this.menuBarContainer = menuBarContainer;
        this.recordTable = recordTable;
        this.mainContainer = new BorderPane();
        this.mainContainer.setTop(this.menuBarContainer);
        this.mainContainer.setLeft(this.toolBarContainer);
        this.mainContainer.setBottom(this.tableMenuContainer);
        this.mainContainer.setCenter(this.recordTable);

        mainWindow.setScene(new Scene(this.mainContainer));
        mainWindow.show();

    }

    public void changeTableContent(List<Record> records) {
        this.recordTable.getItems().clear();
        records.forEach(this.recordTable.getItems()::add);
    }
}
