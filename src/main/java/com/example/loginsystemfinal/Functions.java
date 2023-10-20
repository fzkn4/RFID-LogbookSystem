package com.example.loginsystemfinal;

import javafx.scene.layout.StackPane;

public class Functions {

    //this function removes previous scenes in a stackpane, thus, optimizing the use of memory
    public static void remove(StackPane Stack){
        if (Stack.getChildren().size() > 1) {
            Stack.getChildren().remove(0);
        }
    }
}
