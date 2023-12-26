package com.example.loginsystemfinal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.smartcardio.*;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class sample {
    static boolean stop = false;
//    public static void main(String[] args) throws IOException {
//
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.getMonth() + " " + localDate.getDayOfMonth() + ", " + localDate.getYear());
//        DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
//        String time = formatter.format(new Date());
//        System.out.println(time);
//        do {
//            Scanner input = new Scanner(System.in);
//            int choice = input.nextInt();
//            stop = (choice == 1);
//            System.out.println(getUID());
//        }while(true);
//    }
//
//    public static String getUID() throws IOException {
//        String uid = null;
//        try {
//            // Connect to PC/SC interface (pcscd has to run!)
//            System.out.println("Connecting to PC/SC interface...");
//            TerminalFactory factory = TerminalFactory.getInstance("PC/SC", null);
//
//            // Get all terminals (card readers)
//            List<CardTerminal> terminals = factory.terminals().list();
//            // Connect to first terminal (card reader)
//            CardTerminal terminal = terminals.get(0);
//            System.out.println("Reader found: " + terminal.getName());
//            if (stop){
//                System.exit(0);
//            }
//
//            // Endless loop to wait for cards
//            while (true) {
//                terminal.waitForCardPresent(10);
//
//                if (terminal.isCardPresent()) {
//                    // Card found, get details
//                    Card card = terminal.connect("*");
//                    System.out.println("Card found, retrieving UID!");
//
//                    // Send UID request
//                    CardChannel channel = card.getBasicChannel();
//                    ResponseAPDU response = channel.transmit(new CommandAPDU(new byte[] {
//                            (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00
//                    }));
//
//                    if (response.getSW1() == 0x63 && response.getSW2() == 0x00) {
//                        System.err.println("Error");
//                        break;
//                    }
//
//                    // passing uid to another variable
//                    uid = bin2hex(response.getData());
//                    card.disconnect(true);
//                    channel.close();
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("uid not found.");
//        }
//        return uid;
//    }
//    static String bin2hex(byte[] data) {
//        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
//    }
//


//    public class FilteredListExample {
        public static void main(String[] args) {
            // Create an observable list of strings
            ObservableList<String> originalList = FXCollections.observableArrayList("apple", "api", "cherry", "date");

            // Create a filtered list using the FXCollections.filtered method
            FilteredList<String> filteredList = new FilteredList<>(originalList, s -> s.startsWith("ap"));

            // Determine the number of elements in the filtered list
            int filteredListSize = filteredList.size();
            System.out.println("Number of elements in the filtered list: " + filteredListSize);
        }
    }
//}
