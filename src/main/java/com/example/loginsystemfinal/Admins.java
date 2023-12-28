package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admins {
    DBConnect connect = new DBConnect();
    Connection connection = connect.getConnection();
    private String admin_fname;
    private String admin_lname;
    private String admin_ID;
    private String admin_position;
    private String admin_sex;


    public Admins(String admin_fname, String admin_lname, String admin_ID, String admin_position, String admin_sex) {
        this.admin_fname = admin_fname;
        this.admin_lname = admin_lname;
        this.admin_ID = admin_ID;
        this.admin_position = admin_position;
        this.admin_sex = admin_sex;
    }

    public Admins() {
    }

    public String getAdmin_fname() {
        return admin_fname;
    }

    public void setAdmin_fname(String admin_fname) {
        this.admin_fname = admin_fname;
    }

    public String getAdmin_lname() {
        return admin_lname;
    }

    public void setAdmin_lname(String admin_lname) {
        this.admin_lname = admin_lname;
    }

    public String getAdmin_ID() {
        return admin_ID;
    }

    public void setAdmin_ID(String admin_ID) {
        this.admin_ID = admin_ID;
    }

    public String getAdmin_position() {
        return admin_position;
    }

    public void setAdmin_position(String admin_position) {
        this.admin_position = admin_position;
    }

    public String getAdmin_sex() {
        return admin_sex;
    }

    public void setAdmin_sex(String admin_sex) {
        this.admin_sex = admin_sex;
    }

    public ObservableList<Admins> getAdminInfo() {
        ObservableList<Admins> infoList = FXCollections.observableArrayList();

        connect = new DBConnect();
        connection = connect.getConnection();

        if (connection != null) {
            String query = "SELECT First_Name, Last_Name, ID_Number, position, sex FROM admins";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String fname = resultSet.getString("First_Name");
                    String lname = resultSet.getString("Last_Name");
                    String ID = resultSet.getString("ID_Number");
                    String pos = resultSet.getString("position");
                    String s = resultSet.getString("sex");

                    infoList.add(new Admins(fname, lname, ID, pos, s));
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
