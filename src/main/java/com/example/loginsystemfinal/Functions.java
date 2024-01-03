package com.example.loginsystemfinal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.smartcardio.*;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static javafx.util.Duration.seconds;

public class Functions {
    private static String uid;
    public static boolean disconectReader = false;
    public static Stage scannerNotPresentStage;



    //this function removes previous scenes in a stackpane, thus, optimizing the use of memory
    public static void remove(StackPane Stack){
        if (Stack.getChildren().size() > 0)
                Stack.getChildren().remove(0);
    }


    //this function displays time.
    public static void clock(Text displaydate, Text displayTime){
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

    //closing stage on duration
    public static void closeOnDuration(Stage stage){
    PauseTransition delay = new PauseTransition(Duration.seconds(3));
    delay.setOnFinished( event -> {
        stage.hide();
    });
    delay.play();
    }


    //function for centering window
    public static void setWindowCenter(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
