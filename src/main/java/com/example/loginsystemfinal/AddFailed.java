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

    private void closeOnDuration(){
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished( event -> {
            displayF.setText(displayFailed);
        });
        delay.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeOnDuration();
    }
}
