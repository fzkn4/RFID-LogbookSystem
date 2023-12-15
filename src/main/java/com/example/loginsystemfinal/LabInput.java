package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    public static Stage operate;
    @FXML
    private MFXComboBox<String> options;
    private String selected;

    @FXML
    private MFXButton cancel;
    @FXML
    private MFXButton confirm;
    ObservableList<Labs> labList = FXCollections.observableArrayList();

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
    private static ObservableList<String> getlab() {
        ObservableList<String> labs = FXCollections.observableArrayList();

        DBConnect connect = new DBConnect();
        Connection connection = connect.getConnection();

        if (connection != null) {
            String query = "SELECT labName FROM labs";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String labname = resultSet.getString("labName");
                    labs.add(labname);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Failed to establish a database connection.");
        }
        return labs;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        options.getItems().addAll(getlab());
    }
}
