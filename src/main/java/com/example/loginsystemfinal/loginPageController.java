package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        Mainstage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginScan.fxml"));
        Scene scene = new Scene(root);
        Mainstage.setScene(scene);
        Mainstage.initStyle(StageStyle.UNDECORATED);
        Mainstage.show();
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        close();
    }


    @FXML
    void gotoOperate(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("LabInput.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        close();
        labInputStage = stage;
    }
    private void close(){
        Mainstage = (Stage) scenePane.getScene().getWindow();
        Mainstage.close();
    }
}
