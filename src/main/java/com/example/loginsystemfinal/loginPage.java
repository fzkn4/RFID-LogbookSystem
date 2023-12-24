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
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        scene.getAccelerators().put(Functions.closeKey, Functions.Exit());

        stage.show();
        Mainstage = stage;
        //centering window
        Functions func = new Functions();
        func.setWindowCenter(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}