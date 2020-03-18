package controller;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import util.TableRecordStructureFactory;
import view.MainContainer;
import util.MenuBarFactory;
import util.ToolBarFactory;
import view.menu.table.TableControlMenu;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ApplicationContainerController {
    public static final int DEFAULT_RECORDS_PER_PAGE_VALUE = 10;

    private Stage mainWindow;

    private MainContainer mainContainer;

    private List<Record> records;
    private Integer recordsPerPage;
    private List<List<Record>> pages;

    public ApplicationContainerController(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.records = Collections.emptyList();
        this.recordsPerPage = DEFAULT_RECORDS_PER_PAGE_VALUE;
        this.mainContainer = new MainContainer(
                mainWindow,
                new TableControlMenu().getTopContainer(),
                ToolBarFactory.getInstance(),
                MenuBarFactory.getInstance(),
                TableRecordStructureFactory.buildTableStructure()
                );
        this.mainContainer.changeTableContent(records);
    }
}
