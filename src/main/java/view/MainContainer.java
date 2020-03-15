package view;

import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import model.Record;

@Getter
@Setter
public class MainContainer {
    private BorderPane mainContainer;
    private HBox tableMenuContainer;
    private ToolBar toolBarContainer;
    private MenuBar menuBarContainer;

    private TableView<Record> recordTable;

    public MainContainer() {
        this.mainContainer = new BorderPane();
        this.mainContainer.setTop(menuBarContainer);
        this.mainContainer.setLeft(toolBarContainer);
        this.mainContainer.setBottom(tableMenuContainer);
    }
}
