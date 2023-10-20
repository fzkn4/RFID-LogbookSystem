package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LabInput {

    @FXML
    private MFXButton cancel;

    @FXML
    private MFXButton confirm;

    @FXML
    private MFXTextField input;

    @FXML
    void close(ActionEvent event) throws IOException {
        close();
        loginPageController.labInputStage.close();
    }

    @FXML
    void gotoOperate(ActionEvent event) throws IOException {
        operate();
    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) operate();
        if (event.getCode() == KeyCode.ESCAPE) close();
    }
    private void operate() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("Operate.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        loginPageController.labInputStage.close();
    }

    //closing current window and back to Login page.
    private void close(){
        loginPageController.labInputStage.close();
        loginPage.Mainstage.show();
    }
}
