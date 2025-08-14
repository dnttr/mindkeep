package org.dnttr.mindkeep.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import org.dnttr.mindkeep.components.RippleButton;

public class MainController {

    @FXML
    private AnchorPane pane;

    @FXML
    public RippleButton button;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleButtonClick() {
        System.out.println("Click");
    }
}