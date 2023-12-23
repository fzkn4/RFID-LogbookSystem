package com.example.loginsystemfinal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.smartcardio.*;
import java.awt.*;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static javafx.util.Duration.seconds;

public class Functions {
    private static String uid;
    public static Stage scannerNotPresentStage;



    //this function removes previous scenes in a stackpane, thus, optimizing the use of memory
    public static void remove(StackPane Stack){
        for(int i = 0; i < Stack.getChildren().size() - 1; i++){
                Stack.getChildren().remove(i);
        }
    }


    //this function displays time.
    public static void clock(Text displaydate, Text displayTime){
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(seconds(1),
                event -> {
                    LocalDate localDate = LocalDate.now();
                    String date = localDate.getMonth() + " " + localDate.getDayOfMonth() + ", " + localDate.getYear();
                    DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
                    String time = formatter.format(new Date());
                    displaydate.setText(date);
                    displayTime.setText(time);
                }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    // this function creates combination key with runnable.
    // ref: c
    public static KeyCombination closeKey = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
    public static Runnable Exit(){
        return ()-> System.exit(0);
    }




    /* function for reading nfc tags and returning the corresponding values in it from a database. */
    /*
    * reference to this function: https://gist.github.com/mwllgr/f42ceb7051739eec85f360aa7f2c859f
    * huge thanks to: https://gist.github.com/mwllgr
    *
    */
    public static String getUID() throws IOException {
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
            //created boolean value to prevent overriding login failed window with the error message.
            LoginScan.exec = false;
            //displays error message if scanner is not present.
            System.out.println("Error: " + e.toString());
            scannerNotPresentStage = new Stage();
            Parent root = FXMLLoader.load(Functions.class.getResource("ScannerNotPresent.fxml"));
            Scene scene = new Scene(root);
            scannerNotPresentStage.setScene(scene);
            scannerNotPresentStage.initStyle(StageStyle.UNDECORATED);
            scannerNotPresentStage.show();
        }
        return uid;
    }
    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }
    //closing scannerNotPresent stage on duration
    public static void closeOnDuration(){
    PauseTransition delay = new PauseTransition(Duration.seconds(3));
    delay.setOnFinished( event -> {
        scannerNotPresentStage.close();
    });
    delay.play();
    }
}
