package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;

@Getter
@Setter
public class Doctor extends Person {
    @NonNull private StringProperty illnessAnalyse;

    public Doctor(@NonNull StringProperty surname,
                  @NonNull StringProperty name,
                  @NonNull StringProperty middleName,
                  @NonNull StringProperty illnessAnalyse) {
        super(surname, name, middleName);
        this.illnessAnalyse = illnessAnalyse;
    }

    public Doctor(@NonNull StringProperty illnessAnalyse, @NonNull Person person) {
        super(person);
        this.illnessAnalyse = illnessAnalyse;
    }

    public Doctor(@NonNull String illnessAnalyse, Person person) {
        super(person);
        this.illnessAnalyse = new SimpleStringProperty(illnessAnalyse);
    }
}
