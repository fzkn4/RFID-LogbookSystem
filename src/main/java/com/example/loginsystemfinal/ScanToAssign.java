package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class ScanToAssign implements Initializable {
    RegisterAdmins obj = new RegisterAdmins();
    @FXML
    private MFXButton cancel;

    @FXML
    void close(ActionEvent event) {
        obj.scanWindow.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
