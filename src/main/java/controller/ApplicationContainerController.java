package controller;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import org.xml.sax.SAXException;
import util.factories.MenuBarFactory;
import util.factories.ToolBarFactory;
import util.xml.RecordReader;
import util.xml.RecordWriter;
import view.MainContainer;

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

    private Stage mainWindow;

    private MainContainer mainContainer;

    private List<Record> records;

    private List<List<Record>> pages;

    public ApplicationContainerController(Stage mainWindow, List<Record> records) {
        this.mainWindow = mainWindow;
        this.records = records;
        this.mainContainer = new MainContainer(
                mainWindow,
                MenuBarFactory.getInstance(),
                ToolBarFactory.getInstance(
                        this::addEvent,
                        this::saveEvent,
                        this::loadEvent,
                        this::searchEvent,
                        this::deleteEvent),
                records);
        System.out.println("Was here");
        System.out.println(records.size());
    }

    public ApplicationContainerController(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.records = new ArrayList<>();
        this.mainContainer = new MainContainer(
                mainWindow,
                MenuBarFactory.getInstance(),
                ToolBarFactory.getInstance(
                        this::addEvent,
                        this::saveEvent,
                        this::loadEvent,
                        this::searchEvent,
                        this::deleteEvent),
                records);
    }

    public void addEvent(ActionEvent e) {
        new AddFormController(records, mainContainer.getPageableTable());
    }

    public void searchEvent(ActionEvent e) {
        new SearchFormController(records);
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

    public void deleteEvent(ActionEvent e) {
        new DeleteFormController(records,
                mainContainer.getPageableTable());
    }
}
