package com.example.loginsystemfinal;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentLogsTable implements Initializable {

    @FXML
    private TableColumn<?, ?> lab_id;

    @FXML
    private TableColumn<?, ?> lab_name;

    @FXML
    private TableView<logs_student_data> student_table_logs;

    @FXML
    private TableColumn<?, ?> time_in;

    @FXML
    private TableColumn<?, ?> time_out;
    logs_student_data obj = new logs_student_data();
    private ObservableList<logs_student_data> student_logs_data;



    private void updateTableStudent(){
        lab_name.setCellValueFactory(new PropertyValueFactory<>("labName"));
        lab_id.setCellValueFactory(new PropertyValueFactory<>("labID"));
        time_in.setCellValueFactory(new PropertyValueFactory<>("time_in"));
        time_out.setCellValueFactory(new PropertyValueFactory<>("time_out"));

        student_logs_data = obj.getStudentLogsInfo();
        student_table_logs.setItems(student_logs_data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTableStudent();
    }
}
