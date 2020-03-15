package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.sql.Date;

@AllArgsConstructor
public class Student extends Person{
    @NonNull private StringProperty address;
    @NonNull private ObjectProperty<Date> birthDate;
    @NonNull private ObjectProperty<Date> illnessDate;

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
}
