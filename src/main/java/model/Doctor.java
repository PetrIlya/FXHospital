package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Doctor extends Person {
    @NonNull private String illnessAnalyse;

    public Doctor(@NonNull String illnessAnalyse, Person person) {
        super(person);
        this.illnessAnalyse = illnessAnalyse;
    }
}
