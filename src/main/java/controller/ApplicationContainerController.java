package controller;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import org.xml.sax.SAXException;
import util.factories.table.TableRecordStructureFactory;
import util.xml.RecordReader;
import util.xml.RecordWriter;
import view.MainContainer;
import util.factories.MenuBarFactory;
import util.factories.ToolBarFactory;
import view.menu.table.PageableTable;
import view.menu.table.TableControlMenu;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
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
                ToolBarFactory.getInstance(this::addEvent, this::saveEvent, this::loadEvent),
                records);
    }

    public void addEvent(ActionEvent e) {
        new AddFormController(records, mainContainer.getPageableTable());
    }

    public void saveEvent(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File fileToSave = fileChooser.showSaveDialog(mainWindow);

        if (fileToSave != null) {
            RecordWriter writer = new RecordWriter(fileToSave, records);
            try {
                writer.write();
            } catch (IOException | ParserConfigurationException | TransformerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadEvent(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load");

        File fileToLoad = fileChooser.showOpenDialog(this.mainWindow);

        if (fileToLoad != null) {
            RecordReader loader = new RecordReader();
            try {
                SAXParserFactory.newInstance().newSAXParser().parse(fileToLoad, loader);

                this.records.clear();
                this.records.addAll(loader.getRecords());
            } catch (SAXException | IOException | ParserConfigurationException ex) {
                ex.printStackTrace();
            }
            this.mainContainer.getPageableTable().hardUpdate();
        }
    }
}
