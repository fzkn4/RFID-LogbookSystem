package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class LabLogs {

    @FXML
    private StackPane labSummaryStack;

    @FXML
    private MFXToggleButton toGraph;
    Scene root1;
    Scene root2;

    @FXML
    void showGraph(MouseEvent event) throws IOException {
        if (toGraph.isSelected()){
            Parent root = FXMLLoader.load(getClass().getResource("labLogsGraph.fxml"));
            Scene scene = toGraph.getScene();
            root1 = scene;
            //adding to stack pane
            labSummaryStack.getChildren().add(root);
            Functions.remove(labSummaryStack);

        }else{
            Parent root = FXMLLoader.load(getClass().getResource("LabLogsTable.fxml"));
            Scene scene = toGraph.getScene();
            root2 = scene;
            //adding to stack pane
            labSummaryStack.getChildren().add(root);
            Functions.remove(labSummaryStack);
        }
    }

}
