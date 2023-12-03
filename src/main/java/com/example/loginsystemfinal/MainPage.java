package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class MainPage {
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
    void showLogs(MouseEvent event) {

    }

    @FXML
    void showRecords(MouseEvent event) {

    }

    @FXML
    void showRegister(MouseEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void gotoRegister(ActionEvent event){
        try{
            selected(logsbtn, recordbtn, registerbtn);
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Scene scene = recordbtn.getScene();
            //adding to stack pane
            parentContainer.getChildren().add(root);
            //calling remove stack
            Functions.remove(parentContainer);
        }catch (Exception e) {

        }


    }
    @FXML
    void gotoLogs(ActionEvent event) throws IOException {
        selected(registerbtn, recordbtn, logsbtn);
        Parent root = FXMLLoader.load(getClass().getResource("Logs.fxml"));
        Scene scene = recordbtn.getScene();

        //adding to stack pane
        parentContainer.getChildren().add(root);
        //calling remove stack
        Functions.remove(parentContainer);
    }
    @FXML
    void gotoRecords(ActionEvent event) throws IOException {
        selected(registerbtn, logsbtn, recordbtn);
        Parent root = FXMLLoader.load(getClass().getResource("Records.fxml"));
        Scene scene = recordbtn.getScene();

        //adding to stack pane
        parentContainer.getChildren().add(root);
        //calling remove stack
        Functions.remove(parentContainer);
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

    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) goConfirmation();
    }


    @FXML
    public void initialize() {
        Functions.clock(time);
    }

}

