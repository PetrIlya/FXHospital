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
import view.network.PackManagerForm;
import view.table.OfflineTable;
import view.table.PageableTable;

import java.util.List;

@Getter
@Setter
public class MainContainer {
    private final Scene containerScene;

    @NonNull
    private final BorderPane mainContainer;
    private final @NonNull PageableTable table;
    @NonNull
    private final ToolBar toolBarContainer;
    @NonNull
    private final MenuBar menuBarContainer;
    @NonNull
    private final PackManagerForm packManagerForm;

    public MainContainer(Stage mainWindow,
                         MenuBar menuBarContainer,
                         ToolBar toolBarContainer,
                         PackManagerForm packManagerForm,
                         List<Record> records) {
        this.toolBarContainer = toolBarContainer;
        this.menuBarContainer = menuBarContainer;
        this.packManagerForm = packManagerForm;

        this.table = new OfflineTable(TableStructureFactory.buildTableStructure(), records);

        this.mainContainer = new BorderPane();
        configContainer();

        this.containerScene = new Scene(this.mainContainer);
        mainWindow.setScene(this.containerScene);
        mainWindow.show();

    }

    private void configContainer() {
        this.mainContainer.setTop(this.menuBarContainer);
        this.mainContainer.setLeft(this.toolBarContainer);
        this.mainContainer.setCenter(this.table.getTopContainer());
        this.mainContainer.setRight(this.packManagerForm.getContainer());
    }
}
