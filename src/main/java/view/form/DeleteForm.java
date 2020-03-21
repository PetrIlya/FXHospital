package view.form;

import exceptions.StageElementIsEmptyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.NonNull;
import model.Doctor;
import model.Record;
import view.table.PageableTable;

public class DeleteForm extends Form {
    private static final String ACTION_NAME = "Delete";
    @NonNull
    private final PageableTable table;
    private final HBox topContainer;

    public DeleteForm(EventHandler<ActionEvent> eventProcessor, @NonNull PageableTable table) {
        super(eventProcessor, ACTION_NAME);
        this.table = table;
        this.topContainer = new HBox();
        reconfigureForm();
    }

    private void reconfigureForm() {
        VBox container = super.getDialogContainer();
        container.getChildren().removeAll(
                super.getIllnessAnalyse(), super.getIllnessAnalyseIdtf(),
                super.getStudentMiddleName(), super.getStudentMiddleNameIdtf(),
                super.getStudentName(), super.getStudentNameIdtf());
        this.topContainer.getChildren().addAll(super.getDialogContainer(), this.table.getTopContainer());
    }

    public boolean meetsDeleteRequirements(Record record) {
        boolean equals;

        equals = firstStage(record);

        try {
            equals = firstStage(record);
            if (!equals) {
                return equals;
            } else {
                try {
                    equals = secondStage(record);
                    if (!equals) {
                        return equals;
                    } else {
                        try {
                            equals = thirdStage(record);
                            if (!equals) {
                                return equals;
                            }
                        } catch (StageElementIsEmptyException e) {
                            return equals;
                        }
                    }
                } catch (StageElementIsEmptyException e) {
                    try {
                        equals = thirdStage(record);
                        if (!equals) {
                            return equals;
                        }
                    } catch (StageElementIsEmptyException ex) {
                        return equals;
                    }
                }
            }
        } catch (StageElementIsEmptyException e) {
            try {
                equals = secondStage(record);
                if (!equals) {
                    return equals;
                } else {
                    try {
                        equals = thirdStage(record);
                        if (!equals) {
                            return equals;
                        }
                    } catch (StageElementIsEmptyException ex) {
                        return equals;
                    }
                }
            } catch (StageElementIsEmptyException ex) {
                try {
                    equals = thirdStage(record);
                    if (!equals) {
                        return equals;
                    }
                } catch (StageElementIsEmptyException exc) {
                    return equals;
                }
            }
        }

        return equals;
    }

    private boolean firstStage(Record record) throws StageElementIsEmptyException {
        if (this.getStudentSurname().getText().
                equals(EMPTY_STRING)) {
            if (!this.getStudentAddress().getText().
                    equals(EMPTY_STRING)) {
                throw new StageElementIsEmptyException();
            } else {
                return this.getStudentAddress().getText().
                        equals(record.getStudent().getAddress());
            }
        } else {
            if (this.getStudentSurname().getText().
                    equals(record.getStudent().getSurname())) {
                if (this.getStudentAddress().getText().
                        equals(EMPTY_STRING)) {
                    return true;
                } else {
                    return this.getStudentAddress().getText().
                            equals(record.getStudent().getAddress());
                }
            } else {
                return false;
            }
        }
    }

    private boolean secondStage(Record record) throws StageElementIsEmptyException {
        if (this.getBirthDate().getEditor().getText().equals(EMPTY_STRING)) {
            throw new StageElementIsEmptyException();
        } else {
            return this.getBirthDate().getValue().equals(record.getStudent().getBirthDate());
        }
    }

    private boolean thirdStage(Record record) throws StageElementIsEmptyException {
        if (this.getIllnessDate().getEditor().getText().equals(EMPTY_STRING) &&
                this.getDoctorName().getText().equals(EMPTY_STRING) &&
                this.getDoctorMiddleName().getText().equals(EMPTY_STRING) &&
                this.getDoctorSurname().getText().equals(EMPTY_STRING)) {
            throw new StageElementIsEmptyException();
        } else {
            if (getDoctorNameEquality(record.getDoctor())) {
                return true;
            } else {
                return this.getIllnessDate().getValue().equals(record.getStudent().getIllnessDate());
            }
        }
    }

    private boolean getDoctorNameEquality(Doctor doctor) {
        if (doctor.getSurname().equals(this.getDoctorSurname().getText())) {
            return true;
        } else {
            if (doctor.getName().equals(this.getDoctorName().getText())) {
                return true;
            } else {
                return doctor.getMiddleName().equals(this.getDoctorMiddleName().getText());
            }
        }
    }

    @Override
    public void show() {
        Stage window = new Stage();
        Scene root = new Scene(this.topContainer);
        window.setScene(root);
        window.show();
    }
}
