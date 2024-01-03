package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainPage implements Initializable {

    @FXML
    private MFXTextField searchInput;
    Student student = new Student();
    public static Stage addFailedStage = new Stage();

    public static Stage scanWindow = new Stage();
    public static Stage addSuccess = new Stage();
    @FXML
    private Text admin_name;
    @FXML
    private Text date;
    @FXML
    private AnchorPane pane;
    @FXML
    private MFXButton close;

    @FXML
    private MFXButton logoutbtn;

    @FXML
    private MFXButton logsbtn;

    @FXML
    private MFXButton recordbtn;

    @FXML
    private MFXButton registerbtn;
    @FXML
    private StackPane parentContainer;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Text time;

    @FXML
    private TableColumn<?, ?> Course;
    @FXML
    private TableColumn<?, ?> Date_Reg;
    @FXML
    private TableColumn<?, ?> Department;
    @FXML
    private TableColumn<?, ?> Fname;
    @FXML
    private TableColumn<?, ?> Lname;
    @FXML
    private TableColumn<?, ?> Phone;
    @FXML
    private TableColumn<?, ?> Sex;
    @FXML
    private TableColumn<?, ?> Year;
    @FXML
    private TableColumn<?, ?> rfid;

    @FXML
    private TableView<Student> studentTable;
    private ObservableList<Student> studentData;

    @FXML
    void quit(ActionEvent event) {
        Exit();
    }
    public static void Exit(){
        System.exit(0);
    }


    //root and scenes
    private Parent rootLogs;
    private Parent rootRegister;
    private Parent rootRecords;
    private Scene logScene;
    private Scene registerScene;
    private Scene recordsScene;


    @FXML
    void gotoRegister(ActionEvent event) throws IOException {
        registers();
    }
    @FXML
    void gotoLogs(ActionEvent event) throws IOException {
        logs();
    }
    @FXML
    void gotoRecords(ActionEvent event) throws IOException {
        records();
    }

    private void selected(Button button1, Button button2, Button selected)
    {
        selected.setStyle("-fx-background-color:  #3e4754; -fx-text-fill: white");
        button1.setStyle("-fx-background-color: #2a313a; -fx-text-fill: white");
        button2.setStyle("-fx-background-color: #2a313a; -fx-text-fill: white");
    }

    @FXML
    void confirmation(ActionEvent event) throws IOException {
        goConfirmation();
    }

    private void goConfirmation() throws IOException {
        Stage stage = new Stage();
        stage.initOwner(Validation.stage);
        Parent root = FXMLLoader.load(MainPage.class.getResource("logOutConfirmation.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
        //centering window
        Functions func = new Functions();
        func.setWindowCenter(stage);

    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE){
            goConfirmation();
        } else if (event.getCode() == KeyCode.F1) {
            records();
        }else if (event.getCode() == KeyCode.F2) {
            registers();
        }else if (event.getCode() == KeyCode.F3) {
            logs();
        }else if (event.getCode() == KeyCode.DELETE) {
            Exit();
        }
    }

    private void records() throws IOException {
        selected(registerbtn, logsbtn, recordbtn);
        //adding to stack pane
        parentContainer.getChildren().add(rootRecords);
        //calling remove stack
        Functions.remove(parentContainer);
    }
    private void registers() throws IOException {
        selected(logsbtn, recordbtn, registerbtn);
        //adding to stack pane
        parentContainer.getChildren().add(rootRegister);
        //calling remove stack
        Functions.remove(parentContainer);
    }
    private void logs() throws IOException {
        selected(registerbtn, recordbtn, logsbtn);
        //adding to stack pane
        parentContainer.getChildren().add(rootLogs);
        //calling remove stack
        Functions.remove(parentContainer);
    }

    private void load_resources() throws IOException {
        //logs
        rootLogs = FXMLLoader.load(getClass().getResource("Logs.fxml"));
        logScene = recordbtn.getScene();
        //register
        rootRegister = FXMLLoader.load(getClass().getResource("Register.fxml"));
        registerScene = recordbtn.getScene();
        //records
        rootRecords = FXMLLoader.load(getClass().getResource("Records.fxml"));
        recordsScene = recordbtn.getScene();

        //initializing resources for addfailed, addsuccess and scantoassign popup window
//        try {
//            scanWindow.initOwner(Validation.stage);
//            FXMLLoader fxmlLoader1 = new FXMLLoader(loginPage.class.getResource("scanToAssign.fxml"));
//            Scene scene1 = new Scene(fxmlLoader1.load());
//            scanWindow.setScene(scene1);
//            scanWindow.initStyle(StageStyle.TRANSPARENT);
//            scene1.setFill(Color.TRANSPARENT);
//            scanWindow.initModality(Modality.WINDOW_MODAL);
//
//            addSuccess = new Stage(StageStyle.TRANSPARENT);
//            FXMLLoader fxmlLoader2 = new FXMLLoader(loginPage.class.getResource("addedSuccessfully.fxml"));
//            Scene scene2 = new Scene(fxmlLoader2.load());
//            addSuccess.setScene(scene2);
//            scene2.setFill(Color.TRANSPARENT);
//
//
//            addFailedStage = new Stage(StageStyle.TRANSPARENT);
//            FXMLLoader fxmlLoader3 = new FXMLLoader(loginPage.class.getResource("addFailed.fxml"));
//            Scene scene3 = new Scene(fxmlLoader3.load());
//            addFailedStage.setScene(scene3);
//            scene3.setFill(Color.TRANSPARENT);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void updateLabTable(){
        rfid.setCellValueFactory(new PropertyValueFactory<>("rfid"));
        Fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        Lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        Course.setCellValueFactory(new PropertyValueFactory<>("course"));
        Year.setCellValueFactory(new PropertyValueFactory<>("year"));
        Department.setCellValueFactory(new PropertyValueFactory<>("dept"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        Date_Reg.setCellValueFactory(new PropertyValueFactory<>("date_registered"));

        studentData = student.getStudentInfo();
        studentTable.setItems(studentData);
    }
    //searching operation using filteredlist
    private void Find(){
        FilteredList<Student> filteredData = new FilteredList<>(studentData, p -> true);
        //Set the filter Predicate whenever the filter changes.
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all records of the students.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name, last name, student id, course field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(myObject.getRfid()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches RFID Number.
                } else if (String.valueOf(myObject.getFname()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }else if(String.valueOf(myObject.getLname()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches last name.
                }else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches course.
                }else if (String.valueOf(myObject.getYear()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches student year.
                }else if (String.valueOf(myObject.getDept()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches department.
                }else if (String.valueOf(myObject.getPhone()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches student's phone number.
                }else if (String.valueOf(myObject.getSex()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches student sex.
                }else if (String.valueOf(myObject.getDate_registered()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matches registered date.
                }
                return false; // Does not match.
            });
        });
        // Wrap the FilteredList in a SortedList.
        SortedList sortedData = new SortedList<>(filteredData);
        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
        //Add sorted (and filtered) data to the table.
        studentTable.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Functions.clock(date, time);
        while (Validation.stage.isShowing()){
            loginPage.Mainstage.close();
        }
        admin_name.setText(LoginScan.adminName);
        try {
            load_resources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateLabTable();
        studentTable.setSelectionModel(null);
        Find();
    }
}

