package com.example.loginsystemfinal;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFailed implements Initializable {
    public static String displayFailed = "";
    @FXML
    private Text displayF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayF.setText(displayFailed);
    }
}
