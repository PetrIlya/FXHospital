import controller.ApplicationContainerController;
import javafx.application.Application;
import javafx.stage.Stage;
import util.RecordGenerator;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ApplicationContainerController controller = new ApplicationContainerController(stage,
                RecordGenerator.generateRecords(50));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
