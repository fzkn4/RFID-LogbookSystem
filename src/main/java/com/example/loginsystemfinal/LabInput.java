package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LabInput implements Initializable {
    int i = 0;
    public static Stage operate;
    @FXML
    private MFXComboBox<String> options;
    private String selected;

    @FXML
    private MFXButton cancel;
    @FXML
    private MFXButton confirm;

    @FXML
    void close(ActionEvent event) throws IOException {
        close();
        loginPageController.labInputStage.close();
    }

    @FXML
    void gotoOperate(ActionEvent event) throws IOException {
        operate();
    }

    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) operate();
        if (event.getCode() == KeyCode.ESCAPE) close();
        if (event.getCode() == KeyCode.F1) options.show();
    }

    private void operate() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("Operate.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        loginPageController.labInputStage.close();
        operate = stage;
        //centering window
        Functions func = new Functions();
        func.setWindowCenter(stage);

    }

    //closing current window and back to Login page.
    private void close(){
        loginPageController.labInputStage.close();
        loginPage.Mainstage.show();
    }

    @FXML
    void selected(ActionEvent event) {
        Operate.display = options.getValue();
        selected = options.getValue();
    }

    //fetching labName through OOP
    private static ObservableList<String> getLabList(){
        Labs labs = new Labs();
        ObservableList<String> labsList = FXCollections.observableArrayList();
        for (int i = 0; i < labs.getlabInfo().size(); i++) {
            String lab = labs.getlabInfo().get(i).getLab_name();
            labsList.add(lab);
        }
        return labsList;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        options.getItems().addAll(getLabList());
        options.getSelectionModel().selectFirst();

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
