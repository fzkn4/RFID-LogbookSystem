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
import java.util.List;
import java.util.ResourceBundle;

public class RegisterAdmins implements Initializable {
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
    Stage scanWindow = new Stage();
    @FXML
    void addAdmin(ActionEvent event) throws IOException {
        String insertQuery = "INSERT INTO admins(First_Name, Last_Name, ID_Number, position, sex) VALUES(?, ?, ?, ?, ?)";
        if (Fname.getText().length() == 0 || Lname.getText().length() == 0 || pos.getText().length() == 0 || Sex.getText().length() == 0) {
            System.out.println("fill this up.");
        } else {
            scanWindow.show();
            Functions.setWindowCenter(scanWindow);
            if (checkUID()){
                AddFailed.displayFailed = "UID already exist. Please try another one.";
            }
            else{
                try {
                    PreparedStatement ps = con.prepareStatement(insertQuery);
                    ps.setString(1, capitalize(Fname.getText()));
                    ps.setString(2, capitalize(Lname.getText()));
                    ps.setString(3, uid);
                    ps.setString(4, capitalize(pos.getText()));
                    ps.setString(5, capitalize(Sex.getText()));
                    ps.execute();
                    clear();

                    //successful window shows
                    RegisterLabs.addSuccess.show();
                    //centering window
                    Functions func = new Functions();
                    func.setWindowCenter(RegisterLabs.addSuccess);

                } catch (SQLException e) {
                    e.printStackTrace();
                    clear();

                    AddFailed.displayFailed = "Something went wrong.";
                    //add faild window shows
                    RegisterLabs.addFailed.show();
                    //centering window
                    Functions func = new Functions();
                    func.setWindowCenter(RegisterLabs.addFailed);
            }
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
    }

    @FXML
    void cancel(ActionEvent event) {

    }
    private boolean checkUID() throws IOException {
        Admins obj = new Admins();
        FilteredList<Admins> filteredData = new FilteredList<>(obj.getAdminInfo()); 
//            filteredData.setPredicate(myObject -> {
//                try {
//                    return getUID().equals(myObject.getAdmin_ID());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            scanWindow.close();
//            uid = getUID();
        return (filteredData.size() == 1);
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            scanWindow.initOwner(Validation.stage);
            FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("scanToAssign.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scanWindow.setScene(scene);
            scanWindow.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            scanWindow.initModality(Modality.WINDOW_MODAL);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
