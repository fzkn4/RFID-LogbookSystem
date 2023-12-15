package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogOutConfirmation {

    @FXML
    private MFXButton cancel;

    @FXML
    private AnchorPane confirmationPane;

    @FXML
    private MFXButton ok;

    @FXML
    void close(ActionEvent event) {
        closeConfirmation();
    }

    @FXML
    void gotoLoginPage(ActionEvent event) {
        backToLogin();
    }

    private void closeConfirmation(){
        Stage stage = (Stage) confirmationPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    void cancelKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            closeConfirmation();
        }
    }

    @FXML
    void confirmKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            backToLogin();
        }
    }
    private void backToLogin() {
        loginPage.Mainstage.show();
        closeConfirmation();
        Validation.stage.getScene().getWindow().hide();

    }

}
