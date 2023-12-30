package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentLogs implements Initializable {
    Logs logsObj = new Logs();
    private ObservableList<Student> studentData;

    Student student = new Student();
    @FXML
    private TableColumn<?, ?> student_ID;

    @FXML
    private TableColumn<?, ?> student_course;

    @FXML
    private TableColumn<?, ?> student_dept;

    @FXML
    private TableColumn<?, ?> student_fname;

    @FXML
    private TableColumn<?, ?> student_lname;

    @FXML
    private TableView<Student> student_table;

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
            //calling remove stack
            Functions.remove(studentLogsStackpane);

        }else{
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsTable.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            studentLogsStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(studentLogsStackpane);

        }
    }
    private void updateTableStudent(){
        student_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        student_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        student_ID.setCellValueFactory(new PropertyValueFactory<>("rfid"));
        student_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        student_course.setCellValueFactory(new PropertyValueFactory<>("course"));

        studentData = student.getStudentInfo();
        student_table.setItems(studentData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student_table.getSelectionModel().clearSelection();
        updateTableStudent();
        //selection table row
        student_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                logsObj.getStudent_name_display().setText(newSelection.getFname() + " " + newSelection.getLname());
                logsObj.getStudent_id_display().setText(newSelection.getRfid());
            }
        });
    }
}
