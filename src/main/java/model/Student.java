package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
public class Student extends Person {
    @NonNull
    private StringProperty address;
    @NonNull
    private ObjectProperty<Date> birthDate;
    @NonNull
    private ObjectProperty<Date> illnessDate;

    public Student(@NonNull StringProperty surname,
                   @NonNull StringProperty name,
                   @NonNull StringProperty middleName,
                   @NonNull StringProperty address,
                   @NonNull ObjectProperty<Date> birthDate,
                   @NonNull ObjectProperty<Date> illnessDate) {
        super(surname, name, middleName);
        this.address = address;
        this.birthDate = birthDate;
        this.illnessDate = illnessDate;
    }

    public Student(@NonNull StringProperty address,
                   @NonNull ObjectProperty<Date> birthDate,
                   @NonNull ObjectProperty<Date> illnessDate,
                   @NonNull Person person) {
        super(person);
        this.address = address;
        this.birthDate = birthDate;
        this.illnessDate = illnessDate;
    }

    public String getAddress() {
        return address.get();
    }

    public Student(@NonNull String address,
                   @NonNull Date birthDate,
                   @NonNull Date illnessDate,
                   @NonNull Person person) {
        super(person);
        this.address = new SimpleStringProperty(address);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.illnessDate = new SimpleObjectProperty<>(illnessDate);
    }
}
