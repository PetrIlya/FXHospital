package model;

import javafx.beans.property.StringProperty;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Person {
    @NonNull private StringProperty surname;
    @NonNull private StringProperty name;
    @NonNull private StringProperty middleName;

    public Person(Person person) {
        this.surname = person.getSurname();
        this.name = person.getName();
        this.middleName = person.getMiddleName();
    }
}
