package controller;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import util.factories.table.TableRecordStructureFactory;
import view.MainContainer;
import util.factories.MenuBarFactory;
import util.factories.ToolBarFactory;
import view.menu.table.PageableTable;
import view.menu.table.TableControlMenu;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApplicationContainerController {
    public static final int DEFAULT_RECORDS_PER_PAGE_VALUE = 10;
    public static final int DEFAULT_PAGE = 0;

    private Stage mainWindow;

    private MainContainer mainContainer;

    private List<Record> records;

    private List<List<Record>> pages;
    private Integer recordsPerPage;
    private Integer currentPage;

    public ApplicationContainerController(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.records = new ArrayList<>();
        this.recordsPerPage = DEFAULT_RECORDS_PER_PAGE_VALUE;
        this.mainContainer = new MainContainer(
                mainWindow,
                MenuBarFactory.getInstance(),
                ToolBarFactory.getInstance(this::addEvent),
                records);
    }

    public void addEvent(ActionEvent e) {
        new AddFormController(records, mainContainer.getPageableTable().getTable());
    }
}
