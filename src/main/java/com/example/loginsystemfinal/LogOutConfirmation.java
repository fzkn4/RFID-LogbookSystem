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
    void gotoLoginPage(ActionEvent event) throws IOException {
        backToLogin();
    }
    public void backToLogin() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        closeConfirmation();
        Validation.stage.close();

    }

    private void closeConfirmation(){
        Stage stage = (Stage) confirmationPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            backToLogin();
        }
         if (event.getCode() == KeyCode.ENTER) {
            closeConfirmation();
        }

    }

}
