package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Validation implements Initializable {
    public static Stage mainPage;

    public static Stage stage;
    Stage validationStage;
    @FXML
    private MFXButton ok;
    @FXML
    private AnchorPane validationScene;
    private boolean exec = true;

    @FXML
    void gotoMain(MouseEvent event) throws IOException {
        exec = false;
        goMain();
    }
    @FXML
    void gotoMain_key(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER){
            exec = false;
            goMain();
        }
    }

    private void goMain() throws IOException {
        stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        //implementing keycombination with runnable function
        scene.getAccelerators().put(Functions.closeKey, Functions.Exit());
        stage.show();

        validationStage = (Stage) validationScene.getScene().getWindow();
        validationStage.close();
        while (loginPage.Mainstage.isShowing())loginPage.Mainstage.close();

        mainPage = stage;
    }

    private void closeOnDuration(){
        if (exec){
            PauseTransition delay = new PauseTransition(Duration.seconds(0.05));
            delay.setOnFinished( event -> {
                try {
                    goMain();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            delay.play();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeOnDuration();
    }
}
