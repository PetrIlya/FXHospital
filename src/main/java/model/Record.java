package model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class Record {
    @NonNull private Student student;
    @NonNull private Doctor doctor;
}
