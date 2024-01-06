package com.example.loginsystemfinal;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LabLogsTable implements Initializable {
    logs_lab_data obj = new logs_lab_data();
    private ObservableList<logs_lab_data> labLogsData;

    //lab logs table
    @FXML
    private TableView<logs_lab_data> lab_logs_table;
    @FXML
    private TableColumn<?, ?> studentDept;

    @FXML
    private TableColumn<?, ?> studentFname;

    @FXML
    private TableColumn<?, ?> studentID;

    @FXML
    private TableColumn<?, ?> studentLname;

    @FXML
    private TableColumn<?, ?> timeIn;

    @FXML
    private TableColumn<?, ?> timeOut;



    private void updateLabLogsTable(){
        studentLname.setCellValueFactory(new PropertyValueFactory<>("student_lastName"));
        studentFname.setCellValueFactory(new PropertyValueFactory<>("student_firstName"));
        studentID.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
        studentDept.setCellValueFactory(new PropertyValueFactory<>("student_department"));
        timeIn.setCellValueFactory(new PropertyValueFactory<>("time_in"));
        timeOut.setCellValueFactory(new PropertyValueFactory<>("time_out"));

        labLogsData = obj.getLabsLogsInfo();
        lab_logs_table.setItems(labLogsData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLabLogsTable();
    }
}
