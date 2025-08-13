import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dnttr.mindkeep.AppService;
import org.dnttr.mindkeep.manager.StyleManager;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {

    private final AppService service = new AppService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var scene = this.service.run();

        stage.setTitle("App");
        stage.setScene(scene);

        stage.show();
    }
}
