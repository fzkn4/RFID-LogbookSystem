package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

public class Logs implements Initializable {
    public Logs() {
    }

    Parent root1;
    Scene scene1;
    Parent root2;
    Scene scene2;
    @FXML
    public MFXTextField searchInput;
    public static boolean active = true;

    @FXML
    private Text student_id_display;
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
    Labs lab = new Labs();
    @FXML
    private Text LabTotal;
    @FXML
    private Text StudentTotal;

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

    public Text getStudent_id_display() {
        return student_id_display;
    }

    public Text getStudent_name_display() {
        return student_name_display;
    }

    @FXML
    private Text student_name_display;

    @FXML
    void showLabLogs(ActionEvent event) throws IOException {
        searchInput.clear();
        //adding to stack pane
        LogsStackpane.getChildren().add(root1);

        //removing previous scene
        Functions.remove(LogsStackpane);
    }

    @FXML
    void showStudentLogs(ActionEvent event) throws IOException {
        searchInput.clear();
        //adding to stack pane
        LogsStackpane.getChildren().add(root2);

        //removing previous scene
        Functions.remove(LogsStackpane);
    }

    public MFXTextField getSearchInput() {
        return searchInput;
    }

    //searching operation using filteredlist
    private void Find(){
        FilteredList<Student> filteredData = new FilteredList<>(studentData, p -> true);
        //Set the filter Predicate whenever the filter changes.
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all records of the students.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name, last name, student id, course field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getRfid()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches RFID Number.
                } else if (String.valueOf(myObject.getFname()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }else if(String.valueOf(myObject.getLname()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter mathces last name.
                }else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches course.
                }else if (String.valueOf(myObject.getYear()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches student year.
                }else if (String.valueOf(myObject.getDept()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches department.
                }else if (String.valueOf(myObject.getPhone()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches student's phone number.
                }else if (String.valueOf(myObject.getSex()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches student sex.
                }else if (String.valueOf(myObject.getDate_registered()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches registered date.
                }
                return false; // Does not match.
            });
        });
        // Wrap the FilteredList in a SortedList.
        SortedList sortedData = new SortedList<>(filteredData);
        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(student_table.comparatorProperty());
        //Add sorted (and filtered) data to the table.
        student_table.setItems(sortedData);
    }


    @FXML
    void showGraph(MouseEvent event) throws IOException {
        if(toGraph.isSelected()){
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsGraph.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            LogsStudentStackpane.getChildren().add(root);

            //removing previous scene
            Functions.remove(LogsStudentStackpane);
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("StudentLogsTable.fxml"));
            Scene scene = toGraph.getScene();
            //adding to stack pane
            LogsStudentStackpane.getChildren().add(root);

            //removing previous scene
            Functions.remove(LogsStudentStackpane);
        }
    }

    private void load_resources() throws IOException {
         root1 = FXMLLoader.load(getClass().getResource("LabLogs.fxml"));
         scene1 = ShowLabLogs.getScene();

         root2 = FXMLLoader.load(getClass().getResource("StudentLogs.fxml"));
         scene2 = ShowStudentLogs.getScene();
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
        LabTotal.setText( String.valueOf(lab.getTotalLabs()));
        StudentTotal.setText(String.valueOf(student.getTotalStudents()));
        updateTableStudent();

        //selection table row
        student_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                    student_name_display.setText(newSelection.getFname() + " " + newSelection.getLname());
                    student_id_display.setText(newSelection.getRfid());
            }
        });
        //dynamic search initialization
        Find();
        //loading resources on start
        try {
            load_resources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
