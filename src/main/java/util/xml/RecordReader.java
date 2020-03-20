package util.xml;

import lombok.Getter;
import lombok.Setter;
import model.Doctor;
import model.Person;
import model.Record;
import model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecordReader extends DefaultHandler {
    private List<Record> records;

    @Override
    public void startDocument() {
        this.records = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals(XMLConstants.RECORD.getValue())) {
            this.records.add(buildRecord(attributes));
        }
    }

    private Record buildRecord(Attributes attributes) {
        return new Record(buildStudent(attributes),
                buildDoctor(attributes));
    }

    private Student buildStudent(Attributes attributes) {
        return new Student(attributes.getValue(XMLConstants.ADDRESS.getValue()),
                LocalDate.parse(
                        attributes.getValue(XMLConstants.BIRTH_DATE.getValue())
                ),
                LocalDate.parse(
                        attributes.getValue(XMLConstants.ILLNESS_DATE.getValue())
                ),
                buildPerson(attributes));
    }

    private Doctor buildDoctor(Attributes attributes) {
        return new Doctor(attributes.getValue(XMLConstants.ILLNESS_ANALYSES.getValue()),
                buildPerson(attributes));
    }

    private Person buildPerson(Attributes attributes) {
        return new Person(attributes.getValue(XMLConstants.STUDENT_SURNAME.getValue()),
                attributes.getValue(XMLConstants.STUDENT_NAME.getValue()),
                attributes.getValue(XMLConstants.STUDENT_MIDDLE_NAME.getValue()));
    }
}
