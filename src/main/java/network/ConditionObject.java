package network;

import exceptions.StageElementIsEmptyException;
import lombok.Builder;
import lombok.Data;
import model.Doctor;
import model.Record;

@Data
@Builder
public class ConditionObject {
    private static final String EMPTY_STRING = "";
    private String studentSurname;
    private String studentAddress;

    private String birthDate;

    private String doctorSurname;
    private String doctorName;
    private String doctorMiddleName;
    private String illnessDate;

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
        if (this.getStudentSurname().equals(EMPTY_STRING)) {
            if (!this.getStudentAddress().equals(EMPTY_STRING)) {
                throw new StageElementIsEmptyException();
            } else {
                return this.getStudentAddress().
                        equals(record.getStudent().getAddress());
            }
        } else {
            if (this.getStudentSurname().
                    equals(record.getStudent().getSurname())) {
                if (this.getStudentAddress().
                        equals(EMPTY_STRING)) {
                    return true;
                } else {
                    return this.getStudentAddress().
                            equals(record.getStudent().getAddress());
                }
            } else {
                return false;
            }
        }
    }

    private boolean secondStage(Record record) throws StageElementIsEmptyException {
        if (this.getBirthDate().equals(EMPTY_STRING)) {
            throw new StageElementIsEmptyException();
        } else {
            return record.getStudent().getBirthDate().toString().equals(this.getBirthDate());
        }
    }

    private boolean thirdStage(Record record) throws StageElementIsEmptyException {
        if (this.getIllnessDate().equals(EMPTY_STRING) &&
                this.getDoctorName().equals(EMPTY_STRING) &&
                this.getDoctorMiddleName().equals(EMPTY_STRING) &&
                this.getDoctorSurname().equals(EMPTY_STRING)) {
            throw new StageElementIsEmptyException();
        } else {
            if (getDoctorNameEquality(record.getDoctor())) {
                return true;
            } else {
                return record.getStudent().getIllnessDate().toString().equals(this.getIllnessDate());
            }
        }
    }

    private boolean getDoctorNameEquality(Doctor doctor) {
        if (doctor.getSurname().equals(this.getDoctorSurname())) {
            return true;
        } else {
            if (doctor.getName().equals(this.getDoctorName())) {
                return true;
            } else {
                return doctor.getMiddleName().equals(this.getDoctorMiddleName());
            }
        }
    }
}
