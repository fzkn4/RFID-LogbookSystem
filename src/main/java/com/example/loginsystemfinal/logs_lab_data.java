package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class logs_lab_data {
    DBConnect connect = new DBConnect();
    Connection connection = connect.getConnection();

    public String getStudent_lastName() {
        return student_lastName;
    }

    public void setStudent_lastName(String student_lastName) {
        this.student_lastName = student_lastName;
    }

    public String getStudent_firstName() {
        return student_firstName;
    }

    public void setStudent_firstName(String student_firstName) {
        this.student_firstName = student_firstName;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getStudent_department() {
        return student_department;
    }

    public void setStudent_department(String student_department) {
        this.student_department = student_department;
    }

    public Timestamp getTime_in() {
        return time_in;
    }

    public void setTime_in(Timestamp time_in) {
        this.time_in = time_in;
    }

    public Timestamp getTime_out() {
        return time_out;
    }

    public void setTime_out(Timestamp time_out) {
        this.time_out = time_out;
    }

    public logs_lab_data(String student_lastName, String student_firstName, String student_ID, String student_department, Timestamp time_in, Timestamp time_out) {
        this.student_lastName = student_lastName;
        this.student_firstName = student_firstName;
        this.student_ID = student_ID;
        this.student_department = student_department;
        this.time_in = time_in;
        this.time_out = time_out;
    }

    private String student_lastName;
    private String student_firstName;
    private String student_ID;
    private String student_department;
    private java.sql.Timestamp time_in;
    private java.sql.Timestamp time_out;

    public logs_lab_data() {
    }

    public ObservableList<logs_lab_data> getLabsLogsInfo() {
        ObservableList<logs_lab_data> infoList = FXCollections.observableArrayList();

        connect = new DBConnect();
        connection = connect.getConnection();

        if (connection != null) {
            String query = "SELECT last_name, first_name, student_RFID, department, log_timeIn, log_timeOut FROM students INNER JOIN logs ON students.student_RFID = logs.log_studentID;";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    String studentRFID = resultSet.getString("student_RFID");
                    String dept = resultSet.getString("department");
                    java.sql.Timestamp logTimeIn = resultSet.getTimestamp("log_timeIn");
                    java.sql.Timestamp logTimeOut = resultSet.getTimestamp("log_timeOut");

                    infoList.add(new logs_lab_data(lastName,  firstName, studentRFID, dept, logTimeIn, logTimeOut));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Failed to establish a database connection.");
        }
        return infoList;
    }
}
