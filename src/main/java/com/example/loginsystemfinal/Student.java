package com.example.loginsystemfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    private DBConnect connect = new DBConnect();
    private Connection connection = connect.getConnection();
    private String rfid;
    private String fname;
    private String lname;
    private String course;
    private int year;
    private String dept;
    private String phone;
    private String sex;
    private String date_registered;

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate_registered() {
        return date_registered;
    }

    public void setDate_registered(String date_registered) {
        this.date_registered = date_registered;
    }

    public Student() {
    }

    public Student(String rfid, String fname, String lname, String course, int year, String dept, String phone, String sex, String date_registered) {
        this.rfid = rfid;
        this.fname = fname;
        this.lname = lname;
        this.course = course;
        this.year = year;
        this.dept = dept;
        this.phone = phone;
        this.sex = sex;
        this.date_registered = date_registered;
    }

    //returns total number of students
    public int getTotalStudents(){
        int total = 0;
        String query = "SELECT count(*) AS totalStudents FROM students";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet maxIdResult = statement.executeQuery();
            if (maxIdResult.next()) {
                total = maxIdResult.getInt("totalStudents");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public ObservableList<Student> getStudentInfo() {
        ObservableList<Student> infoList = FXCollections.observableArrayList();

        if (connection != null) {
            String query = "SELECT student_RFID, first_name, last_name, course, student_year, department, phone, sex, date_registered  FROM students";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String RFID = resultSet.getString("student_RFID");
                    String fname = resultSet.getString("first_name");
                    String lname = resultSet.getString("last_name");
                    String crs = resultSet.getString("course");
                    int yr = resultSet.getInt("student_year");
                    String dep = resultSet.getString("department");
                    String phn = resultSet.getString("phone");
                    String s = resultSet.getString("sex");
                    String dateR = resultSet.getString("date_registered");

                    infoList.add(new Student(RFID, fname, lname, crs, yr, dep, phn, s, dateR));
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
