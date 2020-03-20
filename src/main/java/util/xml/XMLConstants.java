package util.xml;

public enum XMLConstants {
    STUDENT_SURNAME("student_surname"),
    STUDENT_NAME("student_name"),
    STUDENT_MIDDLE_NAME("student_middle_name"),
    ADDRESS("address"),
    BIRTH_DATE("birth_date"),
    ILLNESS_DATE("illness_date"),

    DOCTOR_SURNAME("doctor_surname"),
    DOCTOR_NAME("doctor_name"),
    DOCTOR_MIDDLE_NAME("doctor_middle_name"),
    ILLNESS_ANALYSES("illness_analyses"),

    RECORDS("records"),
    RECORD("record");
    private final String value;

    public String getValue() {
        return value;
    }

    XMLConstants(String value) {
        this.value = value;
    }
}