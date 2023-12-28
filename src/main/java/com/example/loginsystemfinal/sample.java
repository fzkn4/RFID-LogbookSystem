//package com.example.loginsystemfinal;
//
//import javax.smartcardio.*;
//import java.io.IOException;
//import java.util.List;
//
//public class sample{
//    public static <PcscProvider, ApduReaderWriter> void main(final String[] args) {
//        public void initTargetDep(){
//            try {
//                while (true) {
//                    Card card = cardTerminal.connect("direct");
//                    ApduReaderWriter apduReaderWriter = new ApduReaderWriter(card, false);
//                    try {
//                        log.info("Waiting...");
//                        connectAsTarget(apduReaderWriter);
//                    }
//                    catch (Exception e1) {
//                        e1.printStackTrace();
//                        try {
//                            Thread.sleep(1000);
//                        }
//                        catch (InterruptedException e) {
//                            break;
//                        }
//                    }
//                    finally {
//                        card.disconnect(true);
//                    }
//                }
//            }
//            catch (CardException e) {
//                throw new IOException(e);
//            }
//        }
//}
