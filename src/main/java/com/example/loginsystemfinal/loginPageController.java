package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;

public class loginPageController implements Initializable {
    public static Stage labInputStage = new Stage();
    public static Stage Mainstage = new Stage();

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
        Functions.setWindowCenter(Mainstage);
    }

    private void operate() throws IOException {
        labInputStage.show();
        close();
        //centering window
        Functions.setWindowCenter(labInputStage);
    }
    private void loadLabInput() throws IOException {
        labInputStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("LabInput.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        labInputStage.setScene(scene);
        labInputStage.initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadLabInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
