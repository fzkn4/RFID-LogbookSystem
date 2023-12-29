package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Function;

public class LoginScan implements Initializable {
    @FXML
    private MFXButton Cancel;

    public static String adminName;
    private String uid;
    public static Stage loginScanStage;
    public static Stage validationFailed;
    public static boolean exec = true;
    @FXML
    void cancel(ActionEvent event) {
        close();
    }

    @FXML
    void gotoValidationFailed(KeyEvent event) throws IOException {
    }

    private void close(){
        exec = false;
        Functions.disconectReader = true;
        loginPageController.Mainstage.close();
    }
    private void validation() throws IOException {
        if (validateLogin()){
            Stage validationStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Validation.fxml"));
            Scene scene = new Scene(root);
            validationStage.setScene(scene);
            validationStage.initStyle(StageStyle.UNDECORATED);
            validationStage.show();
            //centering window
            Functions func = new Functions();
            func.setWindowCenter(validationStage);

            loginPageController.Mainstage.close();
            System.out.println("You logged in as: " + adminName);

        }else if (exec){
            validationFailed = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("validationFailed.fxml"));
            Scene scene = new Scene(root);
            validationFailed.setScene(scene);
            validationFailed.initStyle(StageStyle.UNDECORATED);
            validationFailed.show();
            //centering window
            Functions func = new Functions();
            func.setWindowCenter(validationFailed);
            loginPageController.Mainstage.close();

        }else{
            loginPageController.Mainstage.close();
        }
    }
    private boolean validateLogin(){
        DBConnect connectNow = new DBConnect();
        Connection connectDB = connectNow.getConnection();

        String verifylogin = "SELECT * FROM admins WHERE ID_Number = '" + uid + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet query_result = statement.executeQuery(verifylogin);

            if(query_result.next()) {
                adminName = query_result.getString("First_Name");
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> {
            try {
                uid = Functions.getUID();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                validation();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        delay.play();
        Functions.disconectReader = false;

        //resetting values
        uid = "";
        exec = true;
    }
}
