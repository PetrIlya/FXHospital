package util.xml;

import com.google.common.base.Functions;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.Doctor;
import model.Record;
import model.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@RequiredArgsConstructor
public class RecordWriter {
    @NonNull
    private File file;
    @NonNull
    private List<Record> records;

    private Document document;
    private Element root;

    public synchronized void write() throws IOException, ParserConfigurationException, TransformerException {
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        this.root = document.createElement(XMLConstants.RECORDS.getValue());
        this.records.stream().map(transformRecordToElement).forEach(this.root::appendChild);

        TransformerFactory.newInstance().newTransformer().
                transform(new DOMSource(this.document),
                        new StreamResult(this.file));
    }

    private final Function<Record, Element> transformRecordToElement = this::transformRecordToElement;

    public Element transformRecordToElement(Record record) {
        Element element = this.document.createElement(XMLConstants.RECORD.getValue());
        transformStudent(element, record.getStudent());
        transformDoctor(element, record.getDoctor());

        return element;
    }

    public void transformStudent(Element element, Student student) {
        element.setAttribute(XMLConstants.STUDENT_SURNAME.getValue(),
                student.getSurname().get());
        element.setAttribute(XMLConstants.STUDENT_NAME.getValue(),
                student.getName().get());
        element.setAttribute(XMLConstants.STUDENT_MIDDLE_NAME.getValue(),
                student.getMiddleName().get());
        element.setAttribute(XMLConstants.BIRTH_DATE.getValue(),
                student.getBirthDate().get().toString());
        element.setAttribute(XMLConstants.ILLNESS_DATE.getValue(),
                student.getIllnessDate().get().toString());
    }

    public void transformDoctor(Element element, Doctor doctor) {
        element.setAttribute(XMLConstants.DOCTOR_SURNAME.getValue(),
                doctor.getSurname().get());
        element.setAttribute(XMLConstants.DOCTOR_NAME.getValue(),
                doctor.getName().get());
        element.setAttribute(XMLConstants.DOCTOR_MIDDLE_NAME.getValue(),
                doctor.getMiddleName().get());
        element.setAttribute(XMLConstants.ILLNESS_ANALYSES.getValue(),
                doctor.getIllnessAnalyse().get());
    }
}
