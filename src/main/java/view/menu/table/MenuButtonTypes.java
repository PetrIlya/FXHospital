package view.menu.table;

public enum MenuButtonTypes {
    NEXT_PAGE(">"),
    PREVIOUS_PAGE("<"),
    LAST_PAGE(">>"),
    FIRST_PAGE("<<");
    private final String value;

    public String getValue() {
        return value;
    }

    MenuButtonTypes(String value) {
        this.value = value;
    }
}