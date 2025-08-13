package org.dnttr.mindkeep;

import javafx.scene.Scene;
import lombok.Getter;
import org.dnttr.mindkeep.manager.ResourceManager;
import org.dnttr.mindkeep.manager.StyleManager;

import java.net.URL;

public final class AppService {

    private final StyleManager styleManager = new StyleManager();
    private final ResourceManager resourceManager = new ResourceManager();

    @Getter
    private static AppService instance;

    public Scene run() {
        if (instance != null) {
            throw new IllegalStateException("Application already running.");
        }

        this.resourceManager.load();

        var mainTheme = this.resourceManager.getAsURL("main-theme");
        this.styleManager.load(mainTheme);

        final Scene scene = new Scene(this.resourceManager.getAsFXML("main-view"), 800, 600);
        scene.getStylesheets().add(mainTheme.toExternalForm());

        instance = this;

        return scene;
    }
}
