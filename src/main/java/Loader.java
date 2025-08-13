import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dnttr.mindkeep.manager.StyleManager;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {

    private final StyleManager styleManager = new StyleManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        final var fxml = new FXMLLoader(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        final var resource = Objects.requireNonNull(getClass().getResource("main-theme.css"));

        Scene scene = new Scene(fxml.load(), 800, 600);

        this.styleManager.load(resource);
        scene.getStylesheets().add(resource.toExternalForm());

        stage.setTitle("App");
        stage.setScene(scene);

        stage.show();
    }
}
