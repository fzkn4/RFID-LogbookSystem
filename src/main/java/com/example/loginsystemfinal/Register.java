package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Register implements Initializable {
    Scene studentScene;
    public static Stage registerStudentStage = new Stage();

    @FXML
    private MFXComboBox<String> options;

    @FXML
    private ImageView profileImage;
    @FXML
    private StackPane registerStackpane;

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.F4){
            if (options.isShowing()){
                options.hide();
            }else{
                options.show();
            }
        }
    }
    @FXML
    void selectedOption(ActionEvent event) throws IOException {
        if (options.getValue().equals("Students")){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterStudent.fxml")));
             studentScene = options.getScene();
            registerStudentStage = (Stage) studentScene.getWindow();


            //adding to stack pane
            registerStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(registerStackpane);

        }else if(options.getValue().equals("Labs")){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterLabs.fxml")));
            Scene scene = options.getScene();

            //adding to stack pane
            registerStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(registerStackpane);


        }else if(options.getValue().equals("Admins")){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterAdmins.fxml")));
             Scene scene = options.getScene();

            //adding to stack pane
            registerStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(registerStackpane);

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> option =
                FXCollections.observableArrayList(
                        "Students",
                        "Labs",
                        "Admins"
                );
        options.getItems().addAll(option);

        /*
        lambda function that expands combobox when mouseclicked or when it gains focus.
        link to source : https://stackoverflow.com/questions/35869386/javafx-automatically-expand-choicebox-when-on-focus
        */
        final ChangeListener<? super Boolean> showHideBox = (__, ___, isFocused ) ->{
            if (isFocused){
                options.show();
            }else{
                options.hide();
            }
        };
        options.focusedProperty().addListener( showHideBox );
        options.addEventFilter( MouseEvent.MOUSE_RELEASED, release ->{
            release.consume();
            options.requestFocus();
        } );
    }
}
