package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainPage implements Initializable {
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
    void quit(ActionEvent event) {
        Exit();
    }
    public static void Exit(){
        System.exit(0);
    }

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
        Parent root = FXMLLoader.load(getClass().getResource("Records.fxml"));
        Scene scene = recordbtn.getScene();

        //adding to stack pane
        parentContainer.getChildren().add(root);
        //calling remove stack
        Functions.remove(parentContainer);
    }
    private void registers() throws IOException {
        selected(logsbtn, recordbtn, registerbtn);
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = recordbtn.getScene();
        //adding to stack pane
        parentContainer.getChildren().add(root);
        //calling remove stack
        Functions.remove(parentContainer);
    }
    private void logs() throws IOException {
        selected(registerbtn, recordbtn, logsbtn);
        Parent root = FXMLLoader.load(getClass().getResource("Logs.fxml"));
        Scene scene = recordbtn.getScene();

        //adding to stack pane
        parentContainer.getChildren().add(root);
        //calling remove stack
        Functions.remove(parentContainer);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Functions.clock(date, time);
        while (Validation.stage.isShowing()){
            loginPage.Mainstage.close();
        }
        admin_name.setText(LoginScan.adminName);
    }
}

