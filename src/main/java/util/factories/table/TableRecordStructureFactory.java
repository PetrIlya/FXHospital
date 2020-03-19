package util.factories.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Record;
import util.TableColumnNames;

import java.time.LocalDate;

public final class TableRecordStructureFactory {
    public static TableView<Record> buildTableStructure() {
        TableColumn<Record, String> studentFullName = new TableColumn<>(
                TableColumnNames.STUDENT_FULL_NAME.getValue());
        studentFullName.setCellValueFactory(cell -> cell.getValue().getStudent().getFullName());
        TableColumn<Record, String> doctorFullName = new TableColumn<>(
                TableColumnNames.DOCTOR_FULL_NAME.getValue());
        doctorFullName.setCellValueFactory(cell -> cell.getValue().getDoctor().getFullName());

        TableColumn<Record, String> studentAddress = new TableColumn<>(
                TableColumnNames.ADDRESS.getValue());
        studentAddress.setCellValueFactory(cell -> cell.getValue().getStudent().getAddress());

        TableColumn<Record, LocalDate> studentBirthDate = new TableColumn<>(
                TableColumnNames.BIRTH_DATE.getValue());
        studentBirthDate.setCellValueFactory(cell -> cell.getValue().getStudent().getBirthDate());

        TableColumn<Record, LocalDate> studentIllnessDate = new TableColumn<>(
                TableColumnNames.ILLNESS_DATE.getValue());
        studentIllnessDate.setCellValueFactory(cell -> cell.getValue().getStudent().getIllnessDate());

        TableColumn<Record, String> doctorConclusion = new TableColumn<>(
                TableColumnNames.ILLNESS_ANALYSE.getValue());
        doctorConclusion.setCellValueFactory(cell -> cell.getValue().getDoctor().getIllnessAnalyse());
        TableView<Record> records = new TableView<>();
        records.getColumns().addAll(
                studentFullName,
                studentAddress,
                studentBirthDate,
                studentIllnessDate,
                doctorFullName,
                doctorConclusion);
        return records;
    }
}
