package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private MFXTextField searchInput;

    @FXML
    private TableView<Student> studentTable;
    private ObservableList<Student> studentData;

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
        sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
        //Add sorted (and filtered) data to the table.
        studentTable.setItems(sortedData);
    }

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
        Find();
    }
}
