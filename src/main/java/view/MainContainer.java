package view;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.Record;
import util.factories.table.TableStructureFactory;
import view.table.PageableTable;

import java.util.List;

@Getter
@Setter
public class MainContainer {
    private final Scene containerScene;

    @NonNull
    private final BorderPane mainContainer;
    @NonNull
    private final PageableTable pageableTable;
    @NonNull
    private final ToolBar toolBarContainer;
    @NonNull
    private final MenuBar menuBarContainer;

    public MainContainer(Stage mainWindow,
                         MenuBar menuBarContainer, ToolBar toolBarContainer, List<Record> records) {
        this.toolBarContainer = toolBarContainer;
        this.menuBarContainer = menuBarContainer;
        this.pageableTable = new PageableTable(TableStructureFactory.buildTableStructure(), records);

        this.mainContainer = new BorderPane();
        configContainer();

        this.containerScene = new Scene(this.mainContainer);
        mainWindow.setScene(this.containerScene);
        mainWindow.show();

    }

    private void configContainer() {
        this.mainContainer.setTop(this.menuBarContainer);
        this.mainContainer.setLeft(this.toolBarContainer);
        this.mainContainer.setCenter(this.pageableTable.getTopContainer());
    }
}
