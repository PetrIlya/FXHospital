package controller;

import com.github.PetrIlya.controller.FileChooser;
import com.github.PetrIlya.controller.SimpleFileChooser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Record;
import network.PackInformation;
import network.RequestProcessor;
import org.xml.sax.SAXException;
import util.factories.MenuBarFactory;
import util.factories.PackManagerFactory;
import util.factories.ToolBarFactory;
import util.xml.RecordReader;
import util.xml.RecordWriter;
import view.MainContainer;
import view.network.PackManagerForm;
import view.table.PageableTable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

@Getter
@Setter
public class ApplicationContainerController {

    private Stage mainWindow;

    private final MainContainer mainContainer;
    private final PackManagerForm packManagerForm;
    private final List<Record> records;

    private final List<PackInformation> packInformationList;
    private final PackInformation currentPack;

    private final List<String> names;

    private final RequestProcessor processor;

    public ApplicationContainerController(Stage mainWindow) throws IOException {
        this.mainWindow = mainWindow;
        this.records = new ArrayList<>();
        this.currentPack = new PackInformation("", 0);
        Properties properties = new Properties();
        properties.load(new FileReader(
                "src/main/resources/connection.properties",
                Charset.defaultCharset()));
        this.processor = new RequestProcessor(properties.getProperty("server"), "");

        this.packInformationList = new ArrayList<>();
        renewPackInformation();

        this.names = FXCollections.observableArrayList(this.packInformationList.
                stream().
                map(PackInformation::getName).
                collect(Collectors.toList()));
        this.packManagerForm = PackManagerFactory.
                generatePackManagerForm(names,
                        this::selectionEvent,
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
                records,
                processor,
                currentPack);
    }

    private void renewPackInformation() {
        this.packInformationList.clear();
        this.packInformationList.addAll(processor.getAllPacksInformation());
    }

    public void addEvent(ActionEvent e) {
        if (!this.currentPack.getName().equals("")) {
            new AddFormController(
                    mainContainer.getTable(),
                    processor,
                    currentPack,
                    packInformationList);
            renewPackInformation();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't perform action");
        }
    }

    public void searchEvent(ActionEvent e) {
        if (!this.currentPack.getName().equals("")) {
            new SearchFormController(processor);
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't perform action");
        }
    }

    public void saveEvent(ActionEvent e) {
        if (this.processor == null) {
            FileChooser chooser = new SimpleFileChooser();
            try {
                Optional<File> fileToSave = chooser.save("default");
                if (fileToSave.isPresent()) {
                    RecordWriter writer = new RecordWriter(fileToSave.get(), records);
                    try {
                        writer.write();
                    } catch (IOException | ParserConfigurationException | TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        } else {
            new Alert(Alert.AlertType.ERROR, "Can't perform action");
        }
    }

    public void loadEvent(ActionEvent e) {
        if (this.processor == null) {
            FileChooser chooser = new SimpleFileChooser();

            Optional<File> fileToLoad = chooser.load();
            // fileChooser.showOpenDialog(this.mainWindow);

            if (fileToLoad.isPresent()) {
                RecordReader loader = new RecordReader();
                try {
                    SAXParserFactory.newInstance().newSAXParser().parse(fileToLoad.get(), loader);

                    this.records.clear();
                    this.records.addAll(loader.getRecords());
                } catch (SAXException | IOException | ParserConfigurationException ex) {
                    ex.printStackTrace();
                }
                this.mainContainer.getTable().hardUpdate();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't perform action");
        }
    }

    public void deleteEvent(ActionEvent e) {
        if (!this.currentPack.getName().equals("")) {
            new DeleteFormController(processor,
                    mainContainer.getTable());
            renewPackInformation();
        } else {
            new Alert(Alert.AlertType.ERROR, "Can't perform action");
        }
    }

    public void selectionEvent(ActionEvent e) {
        String selectedPack = this.packManagerForm.getCurrentSelectedPackName();
        if (!selectedPack.equals("")) {
            int amountOfRecordsOfPack = PackInformation.amountOfRecordsOfPack(selectedPack, packInformationList);
            this.currentPack.setTotalRecordsAmount(amountOfRecordsOfPack);
            this.currentPack.setName(selectedPack);
            this.processor.setCurrentPack(selectedPack);
            this.records.clear();
            this.records.
                    addAll(this.processor.
                            getRecords(PageableTable.DEFAULT_PAGE,
                                    PageableTable.DEFAULT_RECORDS_PER_PAGE_VALUE));
            this.mainContainer.getTable().update();
        }
    }

    public void addPackEvent(ActionEvent e) {
        String packToAdd = this.packManagerForm.getPackToAdd();
        if (!names.contains(packToAdd) && !packToAdd.equals("")) {
            this.names.add(packToAdd);
            this.processor.postPack(packToAdd);
            this.packInformationList.add(new PackInformation(packToAdd, 0));
        }
    }

    public void deletePackEvent(ActionEvent e) {
        String packToDelete = this.packManagerForm.getPackToDelete();
        if (names.contains(packToDelete) && !packToDelete.equals("")) {
            this.names.remove(packToDelete);
            this.processor.deletePack(packToDelete);
            PackInformation mock = new PackInformation(packToDelete, 0);
            this.packInformationList.
                    remove(mock);
            if (this.currentPack.equals(mock)) {
                this.currentPack.setName("");
                this.currentPack.setTotalRecordsAmount(0);
                this.records.clear();
                this.mainContainer.getTable().hardUpdate();
            }
        }
    }
}
