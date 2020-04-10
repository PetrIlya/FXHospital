package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import network.PackInformation;
import org.xml.sax.SAXException;
import util.factories.MenuBarFactory;
import util.factories.PackManagerFactory;
import util.factories.ToolBarFactory;
import util.xml.RecordReader;
import util.xml.RecordWriter;
import view.MainContainer;
import view.network.PackManagerForm;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ApplicationContainerController {

    private Stage mainWindow;

    private final MainContainer mainContainer;
    private final PackManagerForm packManagerForm;

    private final List<Record> records;

    private final List<PackInformation> packInformationList;
    private final List<String> names;

    public ApplicationContainerController(Stage mainWindow) {
        this.mainWindow = mainWindow;
        this.records = new ArrayList<>();
        //TODO: Add network connection
        this.packInformationList = new ArrayList<>();
        this.names = FXCollections.observableArrayList(this.packInformationList.
                stream().
                map(PackInformation::getName).
                collect(Collectors.toList()));
        this.packManagerForm = PackManagerFactory.
                generatePackManagerForm(names,
                        this::addPackEvent,
                        this::deletePackEvent);

        this.mainContainer = new MainContainer(
                mainWindow,
                MenuBarFactory.getInstance(),
                ToolBarFactory.getInstance(
                        this::addEvent,
                        this::saveEvent,
                        this::loadEvent,
                        this::searchEvent,
                        this::deleteEvent),
                packManagerForm,
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

    public void addPackEvent(ActionEvent e) {
        String packToAdd = this.packManagerForm.getPackToAdd();
        if (!names.contains(packToAdd) && !packToAdd.equals("")) {
            this.names.add(packToAdd);
            //TODO: complete functional
        }
    }

    public void deletePackEvent(ActionEvent e) {
        String packToDelete = this.packManagerForm.getPackToDelete();
        if (names.contains(packToDelete) && !packToDelete.equals("")) {
            this.names.remove(packToDelete);
            //TODO: complete functional
        }
    }
}
