package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Labs {
    int lab_id;
    String lab_name;

    public int getLab_id() {
        return lab_id;
    }

    public String getLab_name() {
        return lab_name;
    }

    public Labs(int lab_id, String lab_name) {
        this.lab_id = lab_id;
        this.lab_name = lab_name;
    }
    public Labs(){

    }

    public ObservableList<Labs> getlabInfo() {
        ObservableList<Labs> infoList = FXCollections.observableArrayList();

        DBConnect connect = new DBConnect();
        Connection connection = connect.getConnection();

        if (connection != null) {
            String query = "SELECT labID, labName FROM labs";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int LabID = resultSet.getInt("labID");
                    String LabName = resultSet.getString("labName");
                    infoList.add(new Labs(LabID, LabName));
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

    //returns total number of laboratories
    public int getTotalLabs(){
        int sum = 0;
        DBConnect connect = new DBConnect();
        Connection connection = connect.getConnection();
        String query = "SELECT count(*) AS totalLabs FROM labs";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet maxIdResult = statement.executeQuery();
            if (maxIdResult.next()) {
                int maxId = maxIdResult.getInt("totalLabs");
                sum = maxId;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sum;
    }
}
