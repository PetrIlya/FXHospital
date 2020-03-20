package view.form;

import exceptions.StageElementIsEmptyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import model.Doctor;
import model.Record;

public class SearchForm extends Form {
    private static final String ACTION_NAME = "Search";

    public SearchForm(EventHandler<ActionEvent> eventProcessor) {
        super(eventProcessor, ACTION_NAME);
        reconfigureForm();
    }

    private void reconfigureForm() {
        VBox container = super.getDialogContainer();
        container.getChildren().removeAll(
                super.getIllnessAnalyse(), super.getIllnessAnalyseIdtf(),
                super.getStudentMiddleName(), super.getStudentMiddleNameIdtf(),
                super.getStudentName(), super.getStudentNameIdtf());
    }

    public boolean meetsSearchRequirements(Record record) {
        boolean equals = false;

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

    private boolean secondStage(Record record) throws StageElementIsEmptyException{
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
                if (this.getIllnessDate().getValue().equals(record.getStudent().getIllnessDate())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean getDoctorNameEquality(Doctor doctor) {
        if (doctor.getSurname().equals(this.getDoctorSurname().getText())) {
            return true;
        } else {
            if (doctor.getName().equals(this.getDoctorName().getText())) {
                return true;
            } else {
                if (doctor.getMiddleName().equals(this.getDoctorMiddleName().getText())) {
                    return true;
                }
            }
        }
        return false;
    }
}
