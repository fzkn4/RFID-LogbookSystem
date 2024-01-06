package com.example.loginsystemfinal;
import javax.smartcardio.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;


public class sample {

    public static void main(String[] args) throws CardException, NoSuchAlgorithmException, InterruptedException {
        String uid = "";
        boolean disconnect = true;
        while (true){
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
                    System.out.println(uid);
                    break;
                }else if(disconnect){
                    System.out.println("disconnecting...");
                    Thread.sleep(1000);
                    break;
                }
            }
            Thread.sleep(2000);
        }
        }
    }