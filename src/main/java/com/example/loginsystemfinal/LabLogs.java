package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;


public class LabLogs implements Initializable {
    @FXML
    private Text labnameDisplay;
    Labs lab = new Labs();

    private ObservableList<Labs> labData;
    @FXML
    private TableColumn<?, ?> lab_ID_col;

    @FXML
    private TableColumn<?, ?> lab_name_col;

    @FXML
    private TableView<Labs> lab_table;

    @FXML
    private MFXToggleButton toGraph;

    @FXML
    private StackPane labSummaryStack;
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

    public void updateLabTable(){
        lab_ID_col.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
        lab_name_col.setCellValueFactory(new PropertyValueFactory<>("lab_name"));

        labData = lab.getlabInfo();
        lab_table.setItems(labData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLabTable();
        lab_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                labnameDisplay.setText(newSelection.getLab_name());
            }
        });
    }
}
