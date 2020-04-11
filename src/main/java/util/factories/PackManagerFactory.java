package util.factories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.network.PackManagerForm;

import java.util.List;

public class PackManagerFactory {
    public static PackManagerForm generatePackManagerForm(List<String> names,
                                                          EventHandler<ActionEvent> selectEvent,
                                                          EventHandler<ActionEvent> addEvent,
                                                          EventHandler<ActionEvent> deleteEvent) {
        return new PackManagerForm(names, selectEvent, addEvent, deleteEvent);
    }
}
