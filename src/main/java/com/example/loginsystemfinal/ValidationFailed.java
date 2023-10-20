package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ValidationFailed {

    @FXML
    private MFXButton Ok;

    @FXML
    private AnchorPane validationFailedStage;

    @FXML
    void close(ActionEvent event) {
        LoginScan.validationFailed.close();
    }

}
