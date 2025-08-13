package org.dnttr.mindkeep.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.HashMap;

public final class ResourceManager {

    private final HashMap<String, URL> resources = new HashMap<>();

    public void load() {
        this.resources.put("main-view", this.getResource("main-view.fxml"));
        this.resources.put("main-theme", this.getResource("main-theme.css"));
    }

    private URL getResource(String resource) {
        URL url = getClass().getResource("/" + resource);

        if (url == null) {
            throw new IllegalArgumentException("Resource not found: " + resource);
        }

        return url;
    }

    public @NotNull URL getAsURL(final String name) {
        if (!this.resources.containsKey(name)) {
            throw new IllegalArgumentException("Resource not found: " + name);
        }

        return this.resources.get(name);
    }

    public @NotNull Parent getAsFXML(final String name) {
        if (!this.resources.containsKey(name)) {
            throw new IllegalArgumentException("Resource not found: " + name);
        }

        if (!name.contains("view")) {
            throw new IllegalArgumentException("Resource is not valid: " + name);
        }

        URL url = this.resources.get(name);

        try {
            return FXMLLoader.load(url);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load resource: " + name, e);
        }
    }
}
