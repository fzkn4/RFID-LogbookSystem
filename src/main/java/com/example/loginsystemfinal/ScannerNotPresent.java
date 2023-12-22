package com.example.loginsystemfinal;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScannerNotPresent implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Functions.closeOnDuration();
    }
}
