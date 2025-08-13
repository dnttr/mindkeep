package org.dnttr.mindkeep.utils;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StyleUtils {

    public static @Nullable Color getColor(@NotNull Button button) {
        if (button.getBackground() != null && !button.getBackground().getFills().isEmpty()) {
            var paint = button.getBackground().getFills().getFirst().getFill();

            if (paint instanceof Color color) {
                return color;
            }
        }

        return null;
    }
}
