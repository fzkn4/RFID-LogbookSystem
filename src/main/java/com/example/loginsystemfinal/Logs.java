package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Logs implements Initializable {
    Labs lab = new Labs();
    @FXML
    private Text LabTotal;
    @FXML
    private Text StudentTotal;

    @FXML
    private StackPane LogsStackpane;

    @FXML
    private MFXButton ShowLabLogs;

    @FXML
    private MFXButton ShowStudentLogs;

    @FXML
    private StackPane LogsStudentStackpane;
    @FXML
    private MFXToggleButton toGraph;

    @FXML
    void showLabLogs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LabLogs.fxml"));
        Scene scene = ShowLabLogs.getScene();
        //adding to stack pane
        LogsStackpane.getChildren().add(root);

        //removing previous scene
        Functions.remove(LogsStackpane);
    }

    @FXML
    void showStudentLogs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentLogs.fxml"));
        Scene scene = ShowStudentLogs.getScene();
        //adding to stack pane
        LogsStackpane.getChildren().add(root);

        //removing previous scene
        Functions.remove(LogsStackpane);
    }



    @FXML
    void showGraph(MouseEvent event) throws IOException {
        if(toGraph.isSelected()){
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsGraph.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            LogsStudentStackpane.getChildren().add(root);

            //removing previous scene
            Functions.remove(LogsStudentStackpane);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsTable.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            LogsStudentStackpane.getChildren().add(root);

            //removing previous scene
            Functions.remove(LogsStudentStackpane);
        }
    }

    private void load_resources(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabTotal.setText( String.valueOf(lab.getTotalLabs()));
    }
}
