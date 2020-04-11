package view.form;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import network.ConditionObject;
import util.TableColumnNames;

@Getter
@Setter
public class Form {
    private static final String SURNAME = "Surname";
    private static final String NAME = "Name";
    private static final String MIDDLE_NAME = "Middle name";

    public static final String EMPTY_STRING = "";

    private VBox dialogContainer;

    private TextField studentSurname;
    private TextField studentName;
    private TextField studentMiddleName;

    private TextField studentAddress;
    private DatePicker birthDate;
    private DatePicker illnessDate;

    private TextField doctorSurname;
    private TextField doctorName;
    private TextField doctorMiddleName;

    private TextField illnessAnalyse;
    private Label illnessAnalyseIdtf;

    private Button actionButton;

    private Label studentSurnameIdtf;
    private Label studentNameIdtf;
    private Label studentMiddleNameIdtf;

    private Label studentAddressIdtf;
    private Label birthDateIdtf;
    private Label illnessDateIdtf;
    private Label doctorNameIdtf;
    private Label doctorSurnameIdtf;
    private Label doctorMiddleNameIdtf;

    public Form(EventHandler<ActionEvent> eventProcessor, String actionName) {
        this.dialogContainer = new VBox();
        this.actionButton = new Button(actionName);
        this.actionButton.setOnAction(eventProcessor);
        buildContainer();
        this.dialogContainer.getChildren().add(actionButton);
    }

    public void buildContainer() {
        this.studentSurnameIdtf = new Label(SURNAME);
        this.studentSurname = new TextField();

        this.studentName = new TextField();
        this.studentNameIdtf = new Label(NAME);

        this.studentMiddleName = new TextField();
        this.studentMiddleNameIdtf = new Label(MIDDLE_NAME);

        this.studentAddressIdtf = new Label(TableColumnNames.ADDRESS.getValue());
        this.studentAddress = new TextField();
        this.birthDateIdtf = new Label(TableColumnNames.BIRTH_DATE.getValue());
        this.birthDate = new DatePicker();

        this.illnessDateIdtf = new Label(TableColumnNames.ILLNESS_DATE.getValue());
        this.illnessDate = new DatePicker();
        this.doctorNameIdtf = new Label(NAME);
        this.doctorName = new TextField();
        this.doctorSurnameIdtf = new Label(SURNAME);
        this.doctorSurname = new TextField();
        this.doctorMiddleNameIdtf = new Label(MIDDLE_NAME);
        this.doctorMiddleName = new TextField();

        this.illnessAnalyseIdtf = new Label(TableColumnNames.ILLNESS_ANALYSE.getValue());
        this.illnessAnalyse = new TextField();

        buildBindings();
        configOptions();

        this.dialogContainer.getChildren().addAll(
                this.studentSurnameIdtf, this.studentSurname,
                this.studentNameIdtf, this.studentName,
                this.studentMiddleNameIdtf, this.studentMiddleName,
                this.studentAddressIdtf, this.studentAddress,
                this.birthDateIdtf, this.birthDate,
                this.illnessDateIdtf, this.illnessDate,
                this.doctorSurnameIdtf, this.doctorSurname,
                this.doctorNameIdtf, this.doctorName,
                this.doctorMiddleNameIdtf, this.doctorMiddleName,
                this.illnessAnalyseIdtf, this.illnessAnalyse
        );
    }

    public void buildBindings() {
        this.enableDoctorNameField = Bindings.createBooleanBinding(() -> {
                    if (this.doctorSurname.getText().equals(EMPTY_STRING) &&
                            this.doctorMiddleName.getText().equals(EMPTY_STRING) &&
                            this.illnessDate.getEditor().getText().equals(EMPTY_STRING)) {
                        return false;
                    } else {
                        this.doctorName.setText(EMPTY_STRING);
                        return true;
                    }
                },
                this.illnessDate.getEditor().textProperty(),
                this.doctorSurname.textProperty(),
                this.doctorMiddleName.textProperty());

        this.enableDoctorSurnameField = Bindings.createBooleanBinding(() -> {
                    if (this.doctorName.getText().equals(EMPTY_STRING) &&
                            this.doctorMiddleName.getText().equals(EMPTY_STRING) &&
                            this.illnessDate.getEditor().getText().equals(EMPTY_STRING)) {
                        return false;
                    } else {
                        this.doctorSurname.setText(EMPTY_STRING);
                        return true;
                    }
                },
                this.illnessDate.getEditor().textProperty(),
                this.doctorName.textProperty(),
                this.doctorMiddleName.textProperty());

        this.enableDoctorMiddleNameField = Bindings.createBooleanBinding(() -> {
                    if (this.doctorName.getText().equals(EMPTY_STRING) &&
                            this.doctorSurname.getText().equals(EMPTY_STRING) &&
                            this.illnessDate.getEditor().getText().equals(EMPTY_STRING)) {
                        return false;
                    } else {
                        this.doctorMiddleName.setText(EMPTY_STRING);
                        return true;
                    }
                },
                this.illnessDate.getEditor().textProperty(),
                this.doctorName.textProperty(),
                this.doctorSurname.textProperty());

        this.enableIllnessDateField = Bindings.createBooleanBinding(() -> {
                    if (this.doctorName.getText().equals(EMPTY_STRING) &&
                            this.doctorSurname.getText().equals(EMPTY_STRING) &&
                            this.doctorMiddleName.getText().equals(EMPTY_STRING)) {
                        return false;
                    } else {
                        this.illnessDate.getEditor().setText(EMPTY_STRING);
                        return true;
                    }
                },
                this.doctorMiddleName.textProperty(),
                this.doctorName.textProperty(),
                this.doctorSurname.textProperty());

        this.enableStudentSurnameField = Bindings.createBooleanBinding(() -> {
                    if (this.studentAddress.getText().equals(EMPTY_STRING)) {
                        return false;
                    } else {
                        this.studentSurname.setText(EMPTY_STRING);
                        return true;
                    }
                },
                this.studentAddress.textProperty());

        this.enableAddressField = Bindings.createBooleanBinding(() -> {
                    if (this.studentSurname.getText().equals(EMPTY_STRING)) {
                        return false;
                    } else {
                        this.studentAddress.setText(EMPTY_STRING);
                        return true;
                    }
                },
                this.studentSurname.textProperty());
    }

    public void configOptions() {

        this.doctorName.disableProperty().bind(enableDoctorNameField);
        this.doctorSurname.disableProperty().bind(enableDoctorSurnameField);
        this.doctorMiddleName.disableProperty().bind(enableDoctorMiddleNameField);
        this.illnessDate.disableProperty().bind(enableIllnessDateField);

        this.studentSurname.disableProperty().bind(enableStudentSurnameField);
        this.studentAddress.disableProperty().bind(enableAddressField);
    }

    private BooleanBinding enableDoctorNameField;

    private BooleanBinding enableDoctorSurnameField;

    private BooleanBinding enableDoctorMiddleNameField;

    private BooleanBinding enableIllnessDateField;

    private BooleanBinding enableStudentSurnameField;

    private BooleanBinding enableAddressField;

    public void removeBindings() {
        this.dialogContainer.getChildren().forEach(child -> {
            if (!(child instanceof Button)) {
                child.disableProperty().unbind();
            }
        });
    }

    public void show() {
        Stage window = new Stage();
        Scene root = new Scene(this.dialogContainer);
        window.setScene(root);
        window.show();
    }

    public ConditionObject getConditionObject() {
        return ConditionObject.builder().
                birthDate(this.birthDate.getEditor().getText()).
                illnessDate(this.illnessDate.getEditor().getText()).
                studentSurname(this.studentSurname.getText()).
                studentAddress(this.studentAddress.getText()).
                doctorSurname(this.doctorSurname.getText()).
                doctorName(this.doctorName.getText()).
                doctorMiddleName(this.doctorMiddleName.getText()).
                build();
    }
}