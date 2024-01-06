package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.smartcardio.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.util.Duration.seconds;


public class Operate implements Initializable{
    private boolean disconectReader = false;
    private String uid = "";
    public static Stage scannerNotPresentStage = new Stage();
    @FXML
    private AnchorPane operatePane;

    @FXML
    private Text time;

    @FXML
    private Text date;
    @FXML
    private Text labName;
    public static String display;
    @FXML
    private MFXTextField name;
    @FXML
    private MFXTextField courseYear;
    @FXML
    private MFXTextField rfid;

    @FXML
    private MFXTextField timeIn;

    @FXML
    private MFXTextField timeOut;


    @FXML
    private MFXButton back;
    @FXML
    void gotoLogin(ActionEvent event) throws IOException {
        disconectReader = true;
    }


    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE) {
            loginPage.Mainstage.show();

            Stage operateStage = (Stage) operatePane.getScene().getWindow();
            operateStage.close();
        }
    }

    /* function for reading nfc tags and returning the corresponding values in it from a database. */
    /*
     * reference to this function: https://gist.github.com/mwllgr/f42ceb7051739eec85f360aa7f2c859f
     * huge thanks to: https://gist.github.com/mwllgr
     *
     */
    private void getUID() throws IOException {
        try {
            while (true){
                boolean dis = disconectReader;

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
                        uid =  String.format("%0" + (response.getData().length * 2) + "X", new BigInteger(1, response.getData()));
                        card.disconnect(false);
                        rfid.setText(uid);
                        System.out.println(uid);
                        break;
                    }else if(dis){
                        System.out.println("disconnecting...");
                        break;
                    }
                }
                if (dis){
                    loginPage.Mainstage.show();

                    Stage operateStage = (Stage) operatePane.getScene().getWindow();
                    operateStage.close();
                    break;
                }
                Thread.sleep(3000);
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
    }

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Functions.clock(date, time);
        labName.setText(display.toUpperCase());

        // 1-second delay before card reader starts to read.
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> {
            try {
                getUID();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        delay.play();
    }
}
