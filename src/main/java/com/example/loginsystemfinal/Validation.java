package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class Validation {

    public static Stage stage;
    Stage validationStage;
    @FXML
    private MFXButton ok;
    @FXML
    private AnchorPane validationScene;

    @FXML
    void gotoMain(MouseEvent event) throws IOException {
        goMain();
    }
    @FXML
    void gotoMain_key(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER){
            goMain();
        }
    }

    private void goMain() throws IOException {
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();


        validationStage = (Stage) validationScene.getScene().getWindow();
        validationStage.close();
        loginPage.Mainstage.close();
    }
}
