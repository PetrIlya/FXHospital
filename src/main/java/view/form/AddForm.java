package view.form;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import model.Doctor;
import model.Person;
import model.Record;
import model.Student;

import java.time.format.DateTimeFormatter;

public class AddForm extends Form {
    private static final String ACTION_NAME = "Add";

    public AddForm(EventHandler<ActionEvent> eventProcessor) {
        super(eventProcessor, ACTION_NAME);
        super.removeBindings();
    }

    public Record getRecord() {
        return new Record(getStudent(), getDoctor());
    }

    public Student getStudent() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        return new Student(
                this.getStudentAddress().getText(),
                this.getBirthDate().getValue(),
                this.getIllnessDate().getValue(),
                getPerson(this.getStudentSurname(),
                        this.getStudentName(),
                        this.getStudentMiddleName())
        );
    }

    public Doctor getDoctor() {
        return new Doctor(this.getIllnessAnalyse().getText(),
                getPerson(this.getDoctorSurname(),
                        this.getDoctorName(),
                        this.getDoctorMiddleName()));
    }

    public Person getPerson(TextField surname, TextField name, TextField middleName) {
        return new Person(surname.getText(),
                name.getText(),
                middleName.getText());
    }
}
