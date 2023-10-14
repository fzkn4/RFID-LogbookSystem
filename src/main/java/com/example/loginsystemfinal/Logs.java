package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Logs {

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
    }

    @FXML
    void showStudentLogs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentLogs.fxml"));
        Scene scene = ShowStudentLogs.getScene();
        //adding to stack pane
        LogsStackpane.getChildren().add(root);
    }



    @FXML
    void showGraph(MouseEvent event) throws IOException {
        if(toGraph.isSelected()){
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsGraph.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            LogsStudentStackpane.getChildren().add(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsTable.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            LogsStudentStackpane.getChildren().add(root);
        }
    }

}
