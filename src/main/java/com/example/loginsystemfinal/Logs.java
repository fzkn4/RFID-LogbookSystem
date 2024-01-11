package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Logs implements Initializable {
    Student studentObj = new Student();

    public Logs() {
    }

    Parent root1;
    Scene scene1;
    Parent root2;
    Scene scene2;


    @FXML
    public static MFXTextField searchInput = new MFXTextField();
    public static boolean active = true;

    @FXML
    public static Text student_id_display = new Text();
    private ObservableList<Student> studentData = studentObj.getStudentInfo();

    Student student = new Student();
    Labs lab = new Labs();
    @FXML
    private Text LabTotal;
    @FXML
    private Text StudentTotal;

    @FXML
    private StackPane LogsStackpane;

    @FXML
    private MFXButton ShowLabLogs;

    @FXML
    private MFXButton ShowStudentLogs;

    @FXML
    private StackPane LogsStudentStackpane;
    @FXML
    private MFXToggleButton toGraph;

    @FXML
    public static Text student_name_display = new Text();

    @FXML
    void showLabLogs(ActionEvent event){
        searchInput.clear();
        //adding to stack pane
        try{
            LogsStackpane.getChildren().add(root1);
            //removing previous scene
            Functions.remove(LogsStackpane);
        }catch (IllegalArgumentException e){
        }
    }
    private void studentLogs(){
        searchInput.clear();
        //adding to stack pane
        try{
            LogsStackpane.getChildren().add(root2);
            //removing previous scene
            Functions.remove(LogsStackpane);
        }catch (IllegalArgumentException e){
        }
    }

    @FXML
    void showStudentLogs(ActionEvent event){
        studentLogs();
    }

    private void load_resources() throws IOException {
         root1 = FXMLLoader.load(getClass().getResource("LabLogs.fxml"));
         scene1 = ShowLabLogs.getScene();

         root2 = FXMLLoader.load(getClass().getResource("StudentLogs.fxml"));
         scene2 = ShowStudentLogs.getScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabTotal.setText( String.valueOf(lab.getTotalLabs()));
        StudentTotal.setText(String.valueOf(student.getTotalStudents()));
        try {
            load_resources();
            studentLogs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
