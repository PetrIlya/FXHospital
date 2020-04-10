package view.table;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

public interface PageableTable {
    VBox getTopContainer();

    void update();

    void hardUpdate();

    void update(ActionEvent e);

    void nextPageEvent(ActionEvent e);

    void previousPageEvent(ActionEvent e);

    void firstPageEvent(ActionEvent e);

    void lastPageEvent(ActionEvent e);

    void updateLabelText();
}
