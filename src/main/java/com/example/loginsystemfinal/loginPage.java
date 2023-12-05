package com.example.loginsystemfinal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class loginPage extends Application {
    public static Stage Mainstage;
    @Override
    public void start(Stage stage){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("loginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Mainstage = stage;
        }catch(Exception e){

        }
    }

    public static void main(String[] args) {
        launch();
    }
}