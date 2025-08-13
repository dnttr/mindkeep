package org.dnttr.mindkeep.manager;

import com.helger.commons.io.IHasInputStream;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSDeclaration;
import com.helger.css.reader.CSSReader;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

public final class StyleManager {

    @Getter
    private final HashMap<String, HashMap<String, String>> style;

    public StyleManager() {
        this.style = new HashMap<>();
    }

    public void load(final URL resource) {
        var inputStream = new IHasInputStream() {
            @Override
            public @Nullable InputStream getInputStream() {
                try {
                    return resource.openStream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean isReadMultiple() {
                return false;
            }
        };

        var sheet = CSSReader.readFromStream(inputStream, StandardCharsets.UTF_8, ECSSVersion.LATEST);

        if (sheet != null) {
            sheet.getAllStyleRules().forEach(rule ->
                    rule.getAllSelectors().forEach(selector -> {
                        final var name = selector.getAsCSSString();
                        final var properties = rule.getAllDeclarations().stream().collect(Collectors.toMap(CSSDeclaration::getProperty, declaration -> declaration.getExpression().getAsCSSString(), (a, b) -> b, HashMap::new));

                        this.style.put(name, properties);
            }));
        }
    }
}
