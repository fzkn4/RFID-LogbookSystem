package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Register implements Initializable {

    @FXML
    private MFXComboBox<String> options;

    @FXML
    private ImageView profileImage;
    @FXML
    private StackPane registerStackpane;
    @FXML
    void selectedOption(ActionEvent event) throws IOException {
        if (options.getValue().equals("Students")){
            Parent root = FXMLLoader.load(getClass().getResource("RegisterStudent.fxml"));
            Scene scene = options.getScene();

            //adding to stack pane
            registerStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(registerStackpane);

        }else if(options.getValue().equals("Labs")){
            Parent root = FXMLLoader.load(getClass().getResource("RegisterLabs.fxml"));
            Scene scene = options.getScene();

            //adding to stack pane
            registerStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(registerStackpane);


        }else if(options.getValue().equals("Admins")){
            Parent root = FXMLLoader.load(getClass().getResource("RegisterAdmins.fxml"));
            Scene scene = options.getScene();

            //adding to stack pane
            registerStackpane.getChildren().add(root);
            //calling remove stack
            Functions.remove(registerStackpane);

        }
    }

    //removing scenes on stackpane


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> option =
                FXCollections.<String>observableArrayList(
                        "Students",
                        "Labs",
                        "Admins"
                );
        options.getItems().addAll(option);
    }
}
