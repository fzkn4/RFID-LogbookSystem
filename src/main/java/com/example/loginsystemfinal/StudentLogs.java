package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public TableView<Student> student_table;

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

//    private void Find(){
//        Student student = new Student();
//        FilteredList<Student> filteredData = new FilteredList<>(student.getStudentInfo(), p -> true);
//        //Set the filter Predicate whenever the filter changes.
//        Logs.searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(myObject -> {
//                // If filter text is empty, display all records of the students.
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                // Compare first name, last name, student id, course field in your object with filter.
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (String.valueOf(myObject.getRfid()).toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                    // Filter matches RFID Number.
//                } else if (String.valueOf(myObject.getFname()).toLowerCase().contains(lowerCaseFilter)) {
//                    return true; // Filter matches first name.
//                }else if(String.valueOf(myObject.getLname()).toLowerCase().contains(lowerCaseFilter)){
//                    return true; //Filter mathces last name.
//                }else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)){
//                    return true; //Filter matches course.
//                }else if (String.valueOf(myObject.getDept()).toLowerCase().contains(lowerCaseFilter)) {
//                    return true; //Filter matches department.
//                }
//                return false; // Does not match.
//            });
//        });
//        // Wrap the FilteredList in a SortedList.
//        SortedList<Student> sortedData = new SortedList<>(filteredData);
//        //Bind the SortedList comparator to the TableView comparator.
//        sortedData.comparatorProperty().bind(student_table.comparatorProperty());
//        //Add sorted (and filtered) data to the table.
//        student_table.setItems(sortedData);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student_table.getSelectionModel().clearSelection();
        updateTableStudent();
        //selection table row
        student_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Logs.student_name_display.setVisible(true);
                Logs.student_name_display.setText(newSelection.getFname());
            }
        });
        //removes selection when not focused
        student_table.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                student_table.getSelectionModel().clearSelection();
            }
        });
//        Find();
    }
}
