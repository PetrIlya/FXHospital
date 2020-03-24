package view.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import util.factories.ButtonFactory;
import view.menu.MenuButtonTypes;

@Getter
@Setter
public class TableControlMenu {
    public static final String CURRENT_PAGE = "Current page: ";
    public static final String AMOUNT_OF_RECORDS = "Amount of records: ";

    private HBox topContainer;

    private VBox centerContainer;

    private Button previousPage;
    private Button nextPage;
    private Button lastPage;
    private Button firstPage;

    private TextField recordsPerPage;
    private Button update;

    private Label text;

    public TableControlMenu(
            EventHandler<ActionEvent> nextPageEvent,
            EventHandler<ActionEvent> firstPageEvent,
            EventHandler<ActionEvent> previousPageEvent,
            EventHandler<ActionEvent> lastPageEvent,
            EventHandler<ActionEvent> updateEvent) {
        buildButtons(nextPageEvent, firstPageEvent,
                previousPageEvent, lastPageEvent, updateEvent);
        this.centerContainer = new VBox();
        buildTextField();
        buildLabel();

        this.topContainer = new HBox(
                firstPage,
                previousPage,
                centerContainer,
                nextPage,
                lastPage,
                text);
    }

    void buildButtons(EventHandler<ActionEvent> nextPageEvent,
                      EventHandler<ActionEvent> firstPageEvent,
                      EventHandler<ActionEvent> previousPageEvent,
                      EventHandler<ActionEvent> lastPageEvent,
                      EventHandler<ActionEvent> updateEvent) {
        this.previousPage = ButtonFactory.
                buttonBuilder(previousPageEvent, MenuButtonTypes.PREVIOUS_PAGE.getValue());
        this.nextPage = ButtonFactory.
                buttonBuilder(nextPageEvent, MenuButtonTypes.NEXT_PAGE.getValue());
        this.lastPage = ButtonFactory.
                buttonBuilder(lastPageEvent, MenuButtonTypes.LAST_PAGE.getValue());
        this.firstPage = ButtonFactory.
                buttonBuilder(firstPageEvent, MenuButtonTypes.FIRST_PAGE.getValue());
        this.update = ButtonFactory.
                buttonBuilder(updateEvent, MenuButtonTypes.UPDATE.getValue());
    }

    private void buildTextField() {
        this.recordsPerPage = new TextField(Integer.toString(PageableTable.DEFAULT_RECORDS_PER_PAGE_VALUE));
        this.centerContainer.getChildren().addAll(this.recordsPerPage, this.update);
    }

    private void buildLabel() {
        this.text = new Label();
    }
}
