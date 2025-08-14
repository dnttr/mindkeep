package org.dnttr.mindkeep.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.dnttr.mindkeep.AppService;

public class RippleButton extends Button {

    private final Color base; //TODO: move to style manager, and update when neeeded

    public RippleButton() {
        super();

        this.base = Color.valueOf(AppService.getInstance()
                .getStyleManager().getStyle().get(".root").get("-mindkeep-primary-fill"));

        this.setOnMousePressed(this::createEffect);
    }

    private void createEffect(MouseEvent event) {
        double arc = 0;

        var parent = (AnchorPane) getParent();
        var clip = new Rectangle();
        var overlay = new Pane();

        overlay.setMouseTransparent(true);

        overlay.layoutXProperty().bind(this.layoutXProperty());
        overlay.layoutYProperty().bind(this.layoutYProperty());

        overlay.prefWidthProperty().bind(this.widthProperty());
        overlay.prefHeightProperty().bind(this.heightProperty());

        clip.widthProperty().bind(overlay.prefWidthProperty());
        clip.heightProperty().bind(overlay.prefHeightProperty());

        var bg = this.getBackground();

        if (bg != null && !bg.getFills().isEmpty()) {
            var radii = bg.getFills().getFirst().getRadii();

            arc = Math.max(Math.max(radii.getTopLeftHorizontalRadius(), radii.getTopLeftVerticalRadius()), 0);
        }

        clip.setArcWidth(arc * 2);
        clip.setArcHeight(arc * 2);

        overlay.setClip(clip);
        parent.getChildren().add(overlay);

        double centerX = event.getX();
        double centerY = event.getY();

        Circle ripple = new Circle(centerX, centerY, 0);

        ripple.setFill(new Color(base.getRed(), base.getGreen(), base.getBlue(), 0.35));
        ripple.setEffect(new DropShadow(2, Color.rgb(0, 0, 0, 0.2)));

        ripple.setMouseTransparent(true);
        overlay.getChildren().add(ripple);

        double w = this.getWidth();
        double h = this.getHeight();

        double maxRadius = Math.max(Math.max(centerX, w - centerX), Math.max(centerY, h - centerY)) * 1.5;

        var timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(400),
                        new KeyValue(ripple.radiusProperty(), maxRadius),
                        new KeyValue(ripple.opacityProperty(), 0)
                )
        );

        timeline.setOnFinished(e -> parent.getChildren().remove(overlay));

        timeline.play();
    }
}
