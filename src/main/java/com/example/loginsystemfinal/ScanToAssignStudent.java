package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.smartcardio.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ScanToAssignStudent implements Initializable {
    private String uid = "";
    private final DBConnect connects = new DBConnect();
    private final Connection con = connects.getConnection();
    private Stage scannerNotPresentStage = new Stage();
    private boolean disconnectReader = false;
    RegisterAdmins obj = new RegisterAdmins();
    @FXML
    private MFXButton cancel;

    @FXML
    void close(ActionEvent event) {
        RegisterStudent.scanWindow.close();
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
                if (disconnectReader){
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(Functions.class.getResource("ScannerNotPresent.fxml")));
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
        //1sec delay before scanning
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> {
            try {
                Register.student_uid = getUID();
                Register.scanWindow.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        delay.play();

        //reset uid
        uid = "";
    }
}
