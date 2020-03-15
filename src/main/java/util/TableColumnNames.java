package util;

public enum TableColumnNames {
    STUDENT_FULL_NAME("Student's full name"),
    BIRTH_DATE("Birth date"),
    ADDRESS("Adress"),
    ILLNESS_DATE("Illness date"),
    DOCTOR_FULL_NAME("Doctor's full name"),
    ILLNESS_ANALYSE("Illness analyse");
    private final String value;

    public String getValue() {
        return value;
    }

    TableColumnNames(String value) {
        this.value = value;
    }
}