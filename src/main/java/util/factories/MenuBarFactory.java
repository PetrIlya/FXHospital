package util.factories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarFactory {
    public static final String FILE = "File";
    public static final String LOAD = "Load";
    public static final String SAVE = "Save";

    public static final String EDIT = "Edit";
    public static final String ADD = "Add";
    public static final String DELETE = "Delete";
    public static final String SEARCH = "Search";


    public static MenuBar getInstance() {
        MenuBar bar = new MenuBar();
        generateButtons(bar);
        return bar;
    }

    public static void generateButtons(MenuBar bar) {
        Menu edit = new Menu(EDIT);

        MenuItem add = new MenuItem(ADD);
        MenuItem delete = new MenuItem(DELETE);
        MenuItem search = new MenuItem(SEARCH);

        edit.getItems().addAll(add, delete, search);

        Menu file = new Menu(FILE);
        MenuItem load = new MenuItem(LOAD);
        MenuItem save = new MenuItem(SAVE);

        file.getItems().addAll(save, load);

        bar.getMenus().addAll(file, edit);
    }

    public static MenuBar getInstance(EventHandler<ActionEvent> addEvent,
                                      EventHandler<ActionEvent> saveEvent,
                                      EventHandler<ActionEvent> loadEvent,
                                      EventHandler<ActionEvent> searchEvent,
                                      EventHandler<ActionEvent> deleteEvent) {
        MenuBar bar = new MenuBar();
        generateButtons(bar,
                addEvent,
                saveEvent,
                loadEvent,
                searchEvent,
                deleteEvent);
        return bar;
    }

    public static void generateButtons(MenuBar bar,
                                       EventHandler<ActionEvent> addEvent,
                                       EventHandler<ActionEvent> saveEvent,
                                       EventHandler<ActionEvent> loadEvent,
                                       EventHandler<ActionEvent> searchEvent,
                                       EventHandler<ActionEvent> deleteEvent) {
        Menu edit = new Menu(EDIT);

        MenuItem add = new MenuItem(ADD);
        add.setOnAction(addEvent);
        MenuItem delete = new MenuItem(DELETE);
        delete.setOnAction(deleteEvent);
        MenuItem search = new MenuItem(SEARCH);
        search.setOnAction(searchEvent);

        edit.getItems().addAll(add, delete, search);

        Menu file = new Menu(FILE);
        MenuItem load = new MenuItem(LOAD);
        load.setOnAction(loadEvent);
        MenuItem save = new MenuItem(SAVE);
        save.setOnAction(saveEvent);
        file.getItems().addAll(save, load);

        bar.getMenus().addAll(file, edit);
    }
}
