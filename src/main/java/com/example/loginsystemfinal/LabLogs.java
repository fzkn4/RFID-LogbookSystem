package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LabLogs implements Initializable {
    public ObservableList<logs_lab_data> getFiltered() {
        return filtered;
    }

    public void setFiltered(ObservableList<logs_lab_data> filtered) {
        this.filtered = filtered;
    }

    private ObservableList<logs_lab_data> filtered;
    private Parent tableLabLogsRoot;
    private Scene scene2;
    public static int lab_id = 0;
    public LabLogs() {
    }

    LabLogsTable labLogsTableObj = new LabLogsTable();
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


    @FXML
    private MFXTextField search;
    @FXML
    public Text labnameDisplay;
    Labs lab = new Labs();

    private ObservableList<Labs> labData;
    @FXML
    private TableColumn<?, ?> lab_ID_col;

    @FXML
    private TableColumn<?, ?> lab_name_col;

    @FXML
    private TableView<Labs> lab_table;

    public TableView<Labs> getLab_table() {
        return lab_table;
    }

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

            tableLabLogsRoot = FXMLLoader.load(getClass().getResource("LabLogsTable.fxml"));
            scene2 = toGraph.getScene();
            root2 = scene2;
            //adding to stack pane
            labSummaryStack.getChildren().add(tableLabLogsRoot);
            Functions.remove(labSummaryStack);
        }
    }
    private void load_resources() throws IOException {

    }

    private void Find(){
        Labs lab = new Labs();
        FilteredList<Labs> filteredData = new FilteredList<>(lab.getlabInfo(), p -> true);
        //Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all records of the students.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name, last name, student id, course field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getLab_id()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches RFID Number.
                } else if (String.valueOf(myObject.getLab_name()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
        });
        // Wrap the FilteredList in a SortedList.
        SortedList<Labs> sortedData = new SortedList<>(filteredData);
        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(lab_table.comparatorProperty());
        //Add sorted (and filtered) data to the table.
        lab_table.setItems(sortedData);
    }

    public void updateLabTable(){
        lab_ID_col.setCellValueFactory(new PropertyValueFactory<>("lab_id"));
        lab_name_col.setCellValueFactory(new PropertyValueFactory<>("lab_name"));

        labData = lab.getlabInfo();
        lab_table.setItems(labData);
    }
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
    public void selectedRow(TableView<logs_lab_data> table, int labID){
        logs_lab_data lab = new logs_lab_data();
        FilteredList<logs_lab_data> filteredData = new FilteredList<>(lab.getLabsLogsInfo(), p -> true);
        //Set the filter Predicate whenever the filter changes.
        filteredData.setPredicate(myObject -> {
            // If filter text is empty, display all records of the students.
            if (labID < 1) {
                return true;
            }
            // Compare id in your object with filter.
            //return true if it matches otherwise, return false.
            if ((myObject.getLab_id()) == labID) {
                return true;
            }
            return false;
        });
        // Wrap the FilteredList in a SortedList.
        SortedList<logs_lab_data> sortedData = new SortedList<>(filteredData);
        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        //Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        LabLogsTable obj = new LabLogsTable();
        obj.selectedRow(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lab_table.getSelectionModel().clearSelection();
        updateLabTable();
        updateLabLogsTable();
        //selection table row
        lab_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                labnameDisplay.setText(newSelection.getLab_name());
                selectedRow(lab_logs_table, newSelection.getLab_id());
                labLogsTableObj.setLab_id(newSelection.getLab_id());
            }else{
                labLogsTableObj.setLab_id(0);
            }
        });
//        //removes selection when not focused
//        lab_table.focusedProperty().addListener((obs, oldVal, newVal) -> {
//            if (!newVal) {
//                lab_table.getSelectionModel().clearSelection();
//            }
//        });
        Find();
        try {
            load_resources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
