import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        var fxml = new FXMLLoader(Objects.requireNonNull(getClass().getResource("main.fxml")));

        Scene scene = new Scene(fxml.load(), 800, 600);

        stage.setTitle("App");
        stage.setScene(scene);

        stage.show();
    }
}
