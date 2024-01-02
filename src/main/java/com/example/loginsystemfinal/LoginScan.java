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

import javax.smartcardio.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class LoginScan implements Initializable{
    public static Stage scannerNotPresentStage = new Stage();

    @FXML
    private MFXButton Cancel;

    public static String adminName;
    private String uid;
    public static Stage loginScanStage;
    public static Stage validationFailed;
    public static boolean exec = true;
    private boolean disconectReader = false;
    @FXML
    void cancel(ActionEvent event) {
        close();
    }

    private void close(){
        exec = false;
//        Functions.disconectReader = true;
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

    /* function for reading nfc tags and returning the corresponding values in it from a database. */
    /*
     * reference to this function: https://gist.github.com/mwllgr/f42ceb7051739eec85f360aa7f2c859f
     * huge thanks to: https://gist.github.com/mwllgr
     *
     */
    private String getUID() throws IOException {
        try {
            // Connect to PC/SC interface (pcscd has to run!)
            System.out.println("Connecting to PC/SC interface...");
            TerminalFactory factory = TerminalFactory.getInstance("PC/SC", null);

            // Get all terminals (card readers)
            List<CardTerminal> terminals = factory.terminals().list();
            // Connect to first terminal (card reader)
            CardTerminal terminal = terminals.get(0);
            System.out.println("Reader found: " + terminal.getName());

            // Endless loop to wait for cards
            while (true) {
                terminal.waitForCardPresent(10);

                //disconnecting connection
                if (disconectReader){
                    terminal.waitForCardAbsent(1000);
                    System.out.println("Disconnecting...");
                    break;
                }

                if (terminal.isCardPresent()){
                    // Card found, get details
                    Card card = terminal.connect("*");
                    System.out.println("Card found, retrieving UID!");

                    // Send UID request
                    CardChannel channel = card.getBasicChannel();
                    ResponseAPDU response = channel.transmit(new CommandAPDU(new byte[] {
                            (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00
                    }));

                    if (response.getSW1() == 0x63 && response.getSW2() == 0x00) {
                        System.err.println("Error");
                        break;
                    }

                    // passing uid to another variable
                    uid = bin2hex(response.getData());
                    card.disconnect(false);
                    break;
                }
            }
        } catch (Exception e) {
            //created boolean value to prevent overriding login failed window with the error message.
            LoginScan.exec = false;
            //displays error message if scanner is not present.
            System.out.println("Error: " + e.toString());
            Parent root = FXMLLoader.load(Functions.class.getResource("ScannerNotPresent.fxml"));
            Scene scene = new Scene(root);
            scannerNotPresentStage.setScene(scene);
            scannerNotPresentStage.initStyle(StageStyle.UNDECORATED);
            scannerNotPresentStage.show();
            Functions.setWindowCenter(scannerNotPresentStage);
        }
        return uid;
    }

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> {
            try {
                uid = getUID();
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
    }
}
