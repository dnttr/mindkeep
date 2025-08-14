package org.dnttr.mindkeep;

import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Getter;
import org.dnttr.mindkeep.manager.ResourceManager;
import org.dnttr.mindkeep.manager.StyleManager;

@Getter
public final class AppService {

    private final StyleManager styleManager = new StyleManager();
    private final ResourceManager resourceManager = new ResourceManager();

    @Getter
    private static AppService instance;

    public Scene run() {
        if (instance != null) {
            throw new IllegalStateException("Application already running.");
        }

        instance = this;

        this.resourceManager.load();

        var mainTheme = this.resourceManager.getAsURL("main-theme");
        this.styleManager.load(mainTheme);

        final Scene scene = new Scene(this.resourceManager.getAsFXML("main-view"), 800, 600);
        Parent root = scene.getRoot();

        scene.getStylesheets().add(mainTheme.toExternalForm());

        return scene;
    }
}
