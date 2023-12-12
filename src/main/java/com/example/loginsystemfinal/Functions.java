package com.example.loginsystemfinal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.util.Calendar;

import static javafx.util.Duration.seconds;

public class Functions {



    //this function removes previous scenes in a stackpane, thus, optimizing the use of memory
    public static void remove(StackPane Stack){
        for(int i = 0; i < Stack.getChildren().size() - 1; i++){
                Stack.getChildren().remove(i);
        }
    }


    //this function displays time.
    public static void clock(Text display){
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(seconds(1),
                event -> {
                    final Calendar cal = Calendar.getInstance();
                    display.setText(format.format(cal.getTime()));
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
}
