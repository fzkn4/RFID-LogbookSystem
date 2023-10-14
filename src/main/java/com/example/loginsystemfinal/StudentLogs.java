package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class StudentLogs {

    @FXML
    private StackPane studentLogsStackpane;

    @FXML
    private MFXToggleButton toGraph;

    @FXML
    void showGraph(MouseEvent event) throws IOException {
        if(toGraph.isSelected()){
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsGraph.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            studentLogsStackpane.getChildren().add(root);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsTable.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            studentLogsStackpane.getChildren().add(root);
        }
    }

}
