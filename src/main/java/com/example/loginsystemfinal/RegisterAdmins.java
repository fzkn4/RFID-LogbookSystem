package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.smartcardio.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class RegisterAdmins implements Initializable {
    Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    private Stage addSuccess = new Stage();
    private Stage addFailedStage = new Stage();
    String uid = "";

    private final DBConnect connects = new DBConnect();
    private final Connection con = connects.getConnection();

    @FXML
    private MFXTextField Fname;

    @FXML
    private MFXTextField Lname;

    @FXML
    private MFXTextField Sex;

    @FXML
    private MFXButton cancel;

    @FXML
    private MFXTextField pos;

    @FXML
    private MFXButton submit;
    @FXML
    private MFXTextField admin_rfid;
    public static String admin_uid = "";
    static Stage scanWindow = new Stage();

    @FXML
    void scan(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(loginPage.class.getResource("scanToAssignAdmin.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load());
        scanWindow.setScene(scene1);
        scanWindow.showAndWait();
        Functions.setWindowCenter(scanWindow);
        admin_rfid.setText(admin_uid);

    }

    @FXML
    void addAdmin(ActionEvent event) throws IOException {
        String insertQuery = "INSERT INTO admins(First_Name, Last_Name, ID_Number, position, sex) VALUES(?, ?, ?, ?, ?)";
        if (Fname.getText().length() == 0 || Lname.getText().length() == 0 || pos.getText().length() == 0 || Sex.getText().length() == 0) {
            System.out.println("fill this up.");
        } else {
            try {
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, capitalize(Fname.getText()));
                ps.setString(2, capitalize(Lname.getText()));
                ps.setString(3, admin_rfid.getText());
                ps.setString(4, capitalize(pos.getText()));
                ps.setString(5, capitalize(Sex.getText()));
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
        Fname.clear();
        Lname.clear();
        pos.clear();
        Sex.clear();
        admin_rfid.clear();
    }

    @FXML
    void cancel(ActionEvent event) {
        clear();
    }

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

                if (terminal.isCardPresent()) {
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
            //displays error message if scanner is not present.
            System.out.println("Error: " + e.toString());
            Stage scannerNotPresentStage = new Stage();
            Parent root = FXMLLoader.load(Functions.class.getResource("ScannerNotPresent.fxml"));
            Scene scene = new Scene(root);
            scannerNotPresentStage.setScene(scene);
            scannerNotPresentStage.initStyle(StageStyle.UNDECORATED);
            scannerNotPresentStage.show();
            Functions.setWindowCenter(scannerNotPresentStage);
        }
        return uid;
    }
    private String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
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
        textFieldChecker(admin_rfid, 1);
        textFieldChecker(Fname, 2);
        textFieldChecker(Lname, 3);
        textFieldChecker(pos, 4);
        textFieldChecker(Sex, 5);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submit.setDisable(true);
        checkFields();
    }
}
