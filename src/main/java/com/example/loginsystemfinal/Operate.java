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
    private Text date;
    @FXML
    private Text labName;
    public static String display;


    @FXML
    private MFXButton back;
    @FXML
    void gotoLogin(ActionEvent event) throws IOException {
        loginPage.Mainstage.show();

        Stage operateStage = (Stage) operatePane.getScene().getWindow();
        operateStage.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Functions.clock(date, time);
        labName.setText(display.toUpperCase());
    }
    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            loginPage.Mainstage.show();

            Stage operateStage = (Stage) operatePane.getScene().getWindow();
            operateStage.hide();
        }
    }
}
