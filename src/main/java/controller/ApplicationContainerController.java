package controller;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import util.TableRecordStructureFactory;
import view.MainContainer;
import view.menu.MenuBarFactory;
import view.menu.ToolBarFactory;
import view.menu.table.TableControlMenu;

import java.util.List;

@Getter
@Setter
public class ApplicationContainerController {

    private Stage mainWindow;

    private MainContainer mainContainer;

    private List<Record> records;
    private IntegerProperty recordsPerPage;
    private List<List<Record>> pages;

    public ApplicationContainerController(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.mainContainer = new MainContainer(
                mainWindow,
                new TableControlMenu().getTopContainer(),
                ToolBarFactory.getInstance(),
                MenuBarFactory.getInstance(),
                TableRecordStructureFactory.buildTableStructure()
                );
    }
}
