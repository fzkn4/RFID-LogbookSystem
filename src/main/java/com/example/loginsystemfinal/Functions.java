package com.example.loginsystemfinal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
}
