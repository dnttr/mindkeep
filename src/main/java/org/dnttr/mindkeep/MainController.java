package org.dnttr.mindkeep;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private AnchorPane pane;

    @FXML
    public Button testButton;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleButtonClick() {
        System.out.println("Click");

    }
}
