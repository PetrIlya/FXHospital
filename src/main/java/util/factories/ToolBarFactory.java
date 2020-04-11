package util.factories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;


public class ToolBarFactory {
    public static final String ADD_IMAGE_PATH = "image/add.png";
    public static final String DELETE_IMAGE_PATH = "image/delete.png";
    public static final String SEARCH_IMAGE_PATH = "image/search.png";
    public static final String LOAD_IMAGE_PATH = "image/load.png";
    public static final String SAVE_IMAGE_PATH = "image/save.png";

    public static ToolBar getInstance() {
        ToolBar bar = new ToolBar();
        bar.setOrientation(Orientation.VERTICAL);
        generateButtons(bar);
        return bar;
    }

    private static void generateButtons(ToolBar bar) {
        Button add = new Button();
        add.setGraphic(new ImageView("image/add.png"));
        Button delete = new Button();
        delete.setGraphic(new ImageView("image/delete.png"));
        Button search = new Button();
        search.setGraphic(new ImageView("image/search.png"));
        Button load = new Button();
        load.setGraphic(new ImageView("image/load.png"));
        Button save = new Button();
        save.setGraphic(new ImageView("/image/save.png"));

        bar.getItems().addAll(add,
                delete,
                search,
                load,
                save);
    }

    public static ToolBar getInstance(EventHandler<ActionEvent> addEvent,
                                      EventHandler<ActionEvent> saveEvent,
                                      EventHandler<ActionEvent> loadEvent,
                                      EventHandler<ActionEvent> searchEvent,
                                      EventHandler<ActionEvent> deleteEvent) {
        ToolBar bar = new ToolBar();
        bar.setOrientation(Orientation.VERTICAL);
        generateButtons(bar,
                addEvent,
                saveEvent,
                loadEvent,
                searchEvent,
                deleteEvent);
        return bar;
    }

    private static void generateButtons(ToolBar bar,
                                        EventHandler<ActionEvent> addEvent,
                                        EventHandler<ActionEvent> saveEvent,
                                        EventHandler<ActionEvent> loadEvent,
                                        EventHandler<ActionEvent> searchEvent,
                                        EventHandler<ActionEvent> deleteEvent) {
        Button add = ButtonFactory.generateButton(addEvent, new ImageView("image/add.png"));
        Button delete = ButtonFactory.generateButton(deleteEvent, new ImageView("image/delete.png"));
        Button search = ButtonFactory.generateButton(searchEvent, new ImageView("image/search.png"));
        Button load = ButtonFactory.generateButton(loadEvent, new ImageView("image/load.png"));
        Button save = ButtonFactory.generateButton(saveEvent, new ImageView("/image/save.png"));
        bar.getItems().addAll(add,
                delete,
                search,
                load,
                save);
    }
}
