package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Operate implements Initializable{
    @FXML
    private AnchorPane operatePane;

    @FXML
    private Text time;
    @FXML
    private Text labName;
    public static String display;


    @FXML
    private MFXButton back;
    @FXML
    void gotoLogin(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        Stage operateStage = (Stage) operatePane.getScene().getWindow();
        operateStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Functions.clock(time);
        labName.setText(display.toUpperCase());
    }
    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("loginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            LabInput.operate.close();
        }
    }
}
