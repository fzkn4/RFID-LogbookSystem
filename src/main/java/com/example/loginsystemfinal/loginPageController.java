package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class loginPageController {
    public static Stage labInputStage;
    public static Stage Mainstage;

    @FXML
    private MFXButton operate;

    @FXML
    private MFXButton login;
    @FXML
    public AnchorPane scenePane;

    @FXML
    public MFXButton close;

    @FXML
    void gotoValidate(MouseEvent event) throws IOException {
        validate();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        close();
    }


    @FXML
    void gotoOperate(MouseEvent event) throws IOException {
        operate();
    }
    private void close(){
        Mainstage = (Stage) scenePane.getScene().getWindow();
        Mainstage.hide();
    }


    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.F1){
            validate();
        } else if (event.getCode() == KeyCode.F2) {
            operate();
        }

    }
    private void validate() throws IOException {
        Mainstage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginScan.fxml"));
        Scene scene = new Scene(root);
        Mainstage.setScene(scene);
        Mainstage.initStyle(StageStyle.UNDECORATED);
        Mainstage.show();

        //centering window
        Functions func = new Functions();
        func.setWindowCenter(Mainstage);
    }

    private void operate() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("LabInput.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        close();
        labInputStage = stage;
        //centering window
        Functions func = new Functions();
        func.setWindowCenter(stage);
    }
}
