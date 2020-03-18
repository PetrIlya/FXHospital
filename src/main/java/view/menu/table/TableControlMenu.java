package view.menu.table;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class TableControlMenu {
    private static final Integer DEFAULT_VALUE = 10;

    private AtomicInteger currentPage;
    private AtomicInteger recordsPerPageValue;

    private HBox topContainer;

    private Button previousPage;
    private Button nextPage;
    private Button lastPage;
    private Button firstPage;

    private TextField recordsPerPage;

    public TableControlMenu() {
        buildButtons();
        buildTextField();

        this.topContainer = new HBox(
                firstPage,
                previousPage,
                recordsPerPage,
                nextPage,
                lastPage);
    }

    void buildButtons() {
        this.previousPage = new Button(MenuButtonTypes.PREVIOUS_PAGE.getValue());
        this.nextPage = new Button(MenuButtonTypes.NEXT_PAGE.getValue());
        this.lastPage = new Button(MenuButtonTypes.LAST_PAGE.getValue());
        this.firstPage = new Button(MenuButtonTypes.FIRST_PAGE.getValue());
    }

    void buildTextField() {
        this.recordsPerPage = new TextField(DEFAULT_VALUE.toString());
    }
}
