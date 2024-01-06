package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class logs_data {
    DBConnect connect = new DBConnect();
    Connection connection = connect.getConnection();
    private java.sql.Timestamp time_in;
    private java.sql.Timestamp time_out;
    private int log_lab_ID;
    private String log_student_ID;
    private int log_ID;

    public logs_data(int log_ID, Timestamp time_in, Timestamp time_out, int log_lab_ID, String log_student_ID ) {
        this.time_in = time_in;
        this.time_out = time_out;
        this.log_lab_ID = log_lab_ID;
        this.log_student_ID = log_student_ID;
        this.log_ID = log_ID;
    }

    public int getLog_ID() {
        return log_ID;
    }

    public void setLog_ID(int log_ID) {
        this.log_ID = log_ID;
    }

    public logs_data() {
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

    public int getLog_lab_ID() {
        return log_lab_ID;
    }

    public void setLog_lab_ID(int log_lab_ID) {
        this.log_lab_ID = log_lab_ID;
    }

    public String getLog_student_ID() {
        return log_student_ID;
    }

    public void setLog_student_ID(String log_student_ID) {
        this.log_student_ID = log_student_ID;
    }

    public ObservableList<logs_data> getLogsInfo() {
        ObservableList<logs_data> infoList = FXCollections.observableArrayList();

        connect = new DBConnect();
        connection = connect.getConnection();

        if (connection != null) {
            String query = "select * from logs";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int logID = resultSet.getInt("log_id");
                    java.sql.Timestamp logTimeIn = resultSet.getTimestamp("log_timeIn");
                    java.sql.Timestamp logTimeOut = resultSet.getTimestamp("log_timeOut");
                    int logLabID = resultSet.getInt("log_labID");
                    String logStudentID = resultSet.getString("log_studentID");

                    infoList.add(new logs_data(logID, logTimeIn, logTimeOut, logLabID, logStudentID));
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
