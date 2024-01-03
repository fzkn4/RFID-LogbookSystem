package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

public class RegisterStudent implements Initializable {
    Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    private final DBConnect connects = new DBConnect();
    private final Connection con = connects.getConnection();
    @FXML
    private Text duplicateID;

    @FXML
    private ImageView profileImage;

    @FXML
    private MFXTextField student_RFID;

    @FXML
    private MFXTextField student_course;

    @FXML
    private MFXTextField student_dept;

    @FXML
    private MFXTextField student_fname;

    @FXML
    private MFXTextField student_lname;

    @FXML
    private MFXTextField student_phone;

    @FXML
    private MFXTextField student_sex;

    @FXML
    private MFXTextField student_year;

    @FXML
    private MFXButton submit;
    public static String student_uid = "";
    private Stage addFailedStage = new Stage();

    public static Stage scanWindow = new Stage();
    private Stage addSuccess = new Stage();

    @FXML
    void scan(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(loginPage.class.getResource("scanToAssign.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load());
        scanWindow.setScene(scene1);
        scanWindow.showAndWait();
        Functions.setWindowCenter(scanWindow);
        student_RFID.setText(student_uid);

    }

    @FXML
    void add(ActionEvent event) throws IOException {
        addStudent();
        student_uid = "";
    }
    private void addStudent() throws IOException {
        //getting local date
        Date udate = new java.util.Date();
        long l = udate.getTime();
        java.sql.Date sdate = new java.sql.Date(l);

        String insertQuery = "INSERT INTO students(student_RFID, first_name, last_name, course, student_year, department, phone, sex, date_registered) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if (student_RFID.getText().length() == 0 || student_fname.getText().length() == 0 || student_lname.getText().length() == 0 || student_course.getText().length() == 0 || student_dept.getText().length() == 0 || student_sex.getText().length() == 0 || student_phone.getText().length() == 0 || student_year.getText().length() == 0) {
            System.out.println("fill this up.");
        } else{
            try {
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, student_RFID.getText());
                ps.setString(2, capitalize(student_fname.getText()));
                ps.setString(3, capitalize(student_lname.getText()));
                ps.setString(4, student_course.getText().toUpperCase());
                ps.setInt(5, Integer.parseInt(student_year.getText()));
                ps.setString(6, student_dept.getText().toUpperCase());
                ps.setString(7, student_phone.getText());
                ps.setString(8, student_sex.getText().toUpperCase());
                ps.setDate(9, sdate);
                ps.execute();
                clear();

                addSuccess = new Stage(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader2 = new FXMLLoader(loginPage.class.getResource("addedSuccessfully.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load());
                addSuccess.setScene(scene2);
                scene2.setFill(Color.TRANSPARENT);
                //successful window shows
                addSuccess.show();
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(addSuccess);
                Functions.closeOnDuration(addSuccess);

            } catch (SQLException e) {
                //reference: https://stackoverflow.com/questions/1988570/how-to-catch-a-specific-exception-in-jdbc
                if(e.getSQLState().startsWith("23")) {
                    AddFailed.displayFailed = "RFID already taken, please choose another one.";
                }else{
                    AddFailed.displayFailed = "Failed to process data.";
                }
                e.printStackTrace();
                clear();


                //add faild window shows
                addFailedStage = new Stage(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader3 = new FXMLLoader(loginPage.class.getResource("addFailed.fxml"));
                Scene scene3 = new Scene(fxmlLoader3.load());
                addFailedStage.setScene(scene3);
                scene3.setFill(Color.TRANSPARENT);
                addFailedStage.show();
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(addFailedStage);
                Functions.closeOnDuration(addFailedStage);
            }catch (Exception e){
                e.printStackTrace();

                AddFailed.displayFailed = "Something went wrong.";
                //add faild window shows
                addFailedStage = new Stage(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader3 = new FXMLLoader(loginPage.class.getResource("addFailed.fxml"));
                Scene scene3 = new Scene(fxmlLoader3.load());
                addFailedStage.setScene(scene3);
                scene3.setFill(Color.TRANSPARENT);
                addFailedStage.show();
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(addFailedStage);
            }
        }
    }

    private String capitalize(String str)
    {
        String[] words = str.split(" ");
        String string;
        str = "";
        for (String word : words) {
            if (str.length() > 0) {
                str = str + " ";
            }
            char letter = Character.toUpperCase(word.charAt(0));
            string = letter + word.substring(1);
            str += string;
        }
        return str;
    }

    private void clear(){
        student_RFID.clear();
        student_fname.clear();
        student_lname.clear();
        student_course.clear();
        student_year.clear();
        student_dept.clear();
        student_phone.clear();
        student_sex.clear();
    }

    @FXML
    void clear(ActionEvent event) {
        clear();
    }

    //function that constantly check if textfield is empty.
    //purpose of the integer index in this function is the key in hashmap.
    private void textFieldChecker(TextField textField, int index){
        map.put(index, false);
        textField.textProperty().addListener((obs, oldText, newText) -> {
            if (!textField.getText().isEmpty() || textField.getText().length() != 0) {
                map.put(index, true);
                helper();
            }else if (textField.getText().isEmpty() || textField.getText().length() == 0){
                map.put(index, false);
                helper();
            }
        });
    }

    private void helper(){
        //if all textfields are already filled up, submit button enables.
        if (map.containsValue(false)) {
            submit.setDisable(true);
        } else {
            submit.setDisable(false);
        }
    }

    private void checkFields(){
        textFieldChecker(student_RFID, 1);
        textFieldChecker(student_fname, 2);
        textFieldChecker(student_lname, 3);
        textFieldChecker(student_course, 4);
        textFieldChecker(student_year, 5);
        textFieldChecker(student_dept, 6);
        textFieldChecker(student_phone, 7);
        textFieldChecker(student_sex, 8);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit.setDisable(true);
        checkFields();
    }
}
