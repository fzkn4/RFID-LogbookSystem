package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LabLogsTable implements Initializable {
    public int getLab_id() {
        return lab_id;
    }

    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
//        selectedRow(lab_id);
        System.out.println(lab_id);
    }

    private int lab_id;
    logs_lab_data obj = new logs_lab_data();
    private ObservableList<logs_lab_data> labLogsData = FXCollections.observableArrayList();

    //lab logs table
    @FXML
    private TableView<logs_lab_data> lab_logs_table;
    @FXML
    private TableColumn<?, ?> studentDept = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> studentFname = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> studentID = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> studentLname = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> timeIn = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> timeOut = new TableColumn<>();



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

    public void selectedRow(ObservableList<logs_lab_data> data){
//        ObservableList<logs_lab_data> infoList = FXCollections.observableArrayList();
//        DBConnect connect = new DBConnect();
//        Connection connection = connect.getConnection();
//        String query = "SELECT log_labID, last_name, first_name, student_RFID, department, log_timeIn, log_timeOut FROM students INNER JOIN logs ON students.student_RFID = logs.log_studentID where log_id = "+ id;
//
//        System.out.println("lab id: " +id);
//        try {
//            PreparedStatement statement = connection.prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                int labID = resultSet.getInt("log_labID");
//                String lastName = resultSet.getString("last_name");
//                String firstName = resultSet.getString("first_name");
//                String studentRFID = resultSet.getString("student_RFID");
//                String dept = resultSet.getString("department");
//                java.sql.Timestamp logTimeIn = resultSet.getTimestamp("log_timeIn");
//                java.sql.Timestamp logTimeOut = resultSet.getTimestamp("log_timeOut");
//
//                studentLname.setCellValueFactory(new PropertyValueFactory<>("student_lastName"));
//                studentFname.setCellValueFactory(new PropertyValueFactory<>("student_firstName"));
//                studentID.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
//                studentDept.setCellValueFactory(new PropertyValueFactory<>("student_department"));
//                timeIn.setCellValueFactory(new PropertyValueFactory<>("time_in"));
//                timeOut.setCellValueFactory(new PropertyValueFactory<>("time_out"));
//                infoList.add(new logs_lab_data(labID, lastName,  firstName, studentRFID, dept, logTimeIn, logTimeOut));
//                lab_logs_table.setItems(infoList);
//            }
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        lab_logs_table.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateLabLogsTable();
        System.out.println("initial labid"+getLab_id());
    }
}
