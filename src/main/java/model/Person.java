package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @NonNull private StringProperty surname;
    @NonNull private StringProperty name;
    @NonNull private StringProperty middleName;

    public Person(String surname,
                  String name,
                  String middleName) {
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.middleName = new SimpleStringProperty(middleName);
    }

    public Person(Person person) {
        this.surname = person.getSurname();
        this.name = person.getName();
        this.middleName = person.getMiddleName();
    }

    public StringProperty getFullName() {
        return new SimpleStringProperty(surname.get() + " " +
                name.get() + " " +
                middleName.get());
    }
}
