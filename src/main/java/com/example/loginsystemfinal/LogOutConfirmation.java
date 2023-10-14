package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LogOutConfirmation {

    @FXML
    private MFXButton cancel;

    @FXML
    private AnchorPane confirmartionPane;

    @FXML
    private MFXButton ok;

    @FXML
    void close(ActionEvent event) {
        closeConfirmation();
    }

    @FXML
    void gotoLoginPage(ActionEvent event) {
        closeConfirmation();
        Validation.stage.close();
        loginPage.Mainstage.show();
    }

    private void closeConfirmation(){
        Stage stage = (Stage) confirmartionPane.getScene().getWindow();
        stage.close();
    }

}
