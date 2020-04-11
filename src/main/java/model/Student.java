package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Student extends Person {
    @NonNull
    private String address;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private LocalDate illnessDate;

    public Student(@NonNull String address,
                   @NonNull LocalDate birthDate,
                   @NonNull LocalDate illnessDate,
                   @NonNull Person person) {
        super(person);
        this.address = address;
        this.birthDate = birthDate;
        this.illnessDate = illnessDate;
    }
}
