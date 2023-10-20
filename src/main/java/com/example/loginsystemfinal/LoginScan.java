package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginScan {
    public static Stage loginScanStage;
    public static Stage validationFailed;

    @FXML
    private MFXButton Cancel;

    @FXML
    void gotoMain(MouseEvent event) {

    }

    @FXML
    void gotoMain_key(KeyEvent event) {

    }

    @FXML
    void gotoValidation(ActionEvent event) throws IOException {
        Stage validationStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Validation.fxml"));
        Scene scene = new Scene(root);
        validationStage.setScene(scene);
        validationStage.initStyle(StageStyle.UNDECORATED);
        validationStage.show();
        loginPageController.Mainstage.close();
    }

    @FXML
    void gotoValidationFailed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE){
            validationFailed = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("validationFailed.fxml"));
            Scene scene = new Scene(root);
            validationFailed.setScene(scene);
            validationFailed.initStyle(StageStyle.UNDECORATED);
            validationFailed.show();
        }
    }

}
