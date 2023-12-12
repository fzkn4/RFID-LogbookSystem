package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ValidationFailed implements Initializable {

    @FXML
    private MFXButton Ok;

    @FXML
    private AnchorPane validationFailedStage;

    private void close() {
        LoginScan.validationFailed.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished( event -> close());
        delay.play();
    }
}
