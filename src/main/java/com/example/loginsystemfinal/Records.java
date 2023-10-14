package com.example.loginsystemfinal;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Records{

    @FXML
    private TableColumn<Student, String> Fname;

    @FXML
    private TableColumn<Student, String> Lname;

    @FXML
    private TableColumn<Student, String> course;

    @FXML
    private TableColumn<Student, String> department;

    @FXML
    private TableColumn<Student, String> phone;

    @FXML
    private TableColumn<Student, Integer> rfid;

    @FXML
    private TableColumn<Student, String> sex;

    @FXML
    private TableView<Student> tableRecords;

    @FXML
    private TableColumn<Student, Integer> year;

}
