package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class logs_student_data {
    DBConnect connect = new DBConnect();
    Connection connection = connect.getConnection();
    private int labID;
    private String labName;
    private java.sql.Timestamp time_in;
    private java.sql.Timestamp time_out;

    public logs_student_data(String labName, int labID, Timestamp time_in, Timestamp time_out) {
        this.labID = labID;
        this.labName = labName;
        this.time_in = time_in;
        this.time_out = time_out;
    }

    public logs_student_data() {
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

    public int getLabID() {
        return labID;
    }

    public void setLabID(int labID) {
        this.labID = labID;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public ObservableList<logs_student_data> getStudentLogsInfo() {
        ObservableList<logs_student_data> infoList = FXCollections.observableArrayList();

        connect = new DBConnect();
        connection = connect.getConnection();

        if (connection != null) {
            String query = "SELECT labName, labID, log_timeIn, log_timeOut FROM labs INNER JOIN logs ON labs.labID = logs.log_labID";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String lab_name = resultSet.getString("labName");
                    int lab_id = resultSet.getInt("labID");
                    java.sql.Timestamp logTimeIn = resultSet.getTimestamp("log_timeIn");
                    java.sql.Timestamp logTimeOut = resultSet.getTimestamp("log_timeOut");

                    infoList.add(new logs_student_data(lab_name,  lab_id, logTimeIn, logTimeOut));
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
