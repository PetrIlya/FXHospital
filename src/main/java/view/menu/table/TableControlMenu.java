package view.menu.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import view.menu.MenuButtonTypes;

@Getter
@Setter
public class TableControlMenu {
    private HBox topContainer;

    private Button previousPage;
    private Button nextPage;
    private Button lastPage;
    private Button firstPage;

    private TextField recordsPerPage;

    public TableControlMenu(
            EventHandler<ActionEvent> nextPageEvent,
            EventHandler<ActionEvent> firstPageEvent,
            EventHandler<ActionEvent> previousPageEvent,
            EventHandler<ActionEvent> lastPageEvent) {
        buildButtons(nextPageEvent,firstPageEvent,
                previousPageEvent,lastPageEvent);
        buildTextField();

        this.topContainer = new HBox(
                firstPage,
                previousPage,
                recordsPerPage,
                nextPage,
                lastPage);
    }

    void buildButtons(EventHandler<ActionEvent> nextPageEvent,
                      EventHandler<ActionEvent> firstPageEvent,
                      EventHandler<ActionEvent> previousPageEvent,
                      EventHandler<ActionEvent> lastPageEvent) {
        this.previousPage = new Button(MenuButtonTypes.PREVIOUS_PAGE.getValue());
        this.previousPage.setOnAction(previousPageEvent);
        this.nextPage = new Button(MenuButtonTypes.NEXT_PAGE.getValue());
        this.nextPage.setOnAction(nextPageEvent);
        this.lastPage = new Button(MenuButtonTypes.LAST_PAGE.getValue());
        this.lastPage.setOnAction(lastPageEvent);
        this.firstPage = new Button(MenuButtonTypes.FIRST_PAGE.getValue());
        this.firstPage.setOnAction(firstPageEvent);
    }

    private void buildTextField() {
        this.recordsPerPage = new TextField("1");
    }
}
