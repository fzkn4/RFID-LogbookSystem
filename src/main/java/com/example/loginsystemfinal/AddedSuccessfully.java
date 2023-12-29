package com.example.loginsystemfinal;

import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AddedSuccessfully implements Initializable {
    private void closeOnDuration(){
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished( event -> {
                MainPage.addSuccess.hide();
        });
        delay.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeOnDuration();
    }
}
