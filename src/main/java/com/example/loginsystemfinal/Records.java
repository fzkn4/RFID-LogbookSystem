package com.example.loginsystemfinal;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Records implements Initializable {
    Student student = new Student();

    @FXML
    private TableColumn<?, ?> Course;
    @FXML
    private TableColumn<?, ?> Date_Reg;
    @FXML
    private TableColumn<?, ?> Department;
    @FXML
    private TableColumn<?, ?> Fname;
    @FXML
    private TableColumn<?, ?> Lname;
    @FXML
    private TableColumn<?, ?> Phone;
    @FXML
    private TableColumn<?, ?> Sex;
    @FXML
    private TableColumn<?, ?> Year;
    @FXML
    private TableColumn<?, ?> rfid;

    @FXML
    private TableView<Student> studentTable;
    private ObservableList<Student> studentData;

    private void updateLabTable(){
        rfid.setCellValueFactory(new PropertyValueFactory<>("rfid"));
        Fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        Lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        Course.setCellValueFactory(new PropertyValueFactory<>("course"));
        Year.setCellValueFactory(new PropertyValueFactory<>("year"));
        Department.setCellValueFactory(new PropertyValueFactory<>("dept"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        Date_Reg.setCellValueFactory(new PropertyValueFactory<>("date_registered"));

        studentData = student.getStudentInfo();
        studentTable.setItems(studentData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLabTable();
        studentTable.setSelectionModel(null);

    }
}
