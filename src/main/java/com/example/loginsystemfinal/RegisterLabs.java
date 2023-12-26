package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterLabs implements Initializable {
    private String display;
    Labs lab = new Labs();
    @FXML
    private Text displayMess;
    public static Stage addSuccess;
    public static Stage addFailed;
    private final DBConnect connects = new DBConnect();
    private final Connection con = connects.getConnection();

    @FXML
    private MFXTextField labID;

    @FXML
    private MFXTextField labName;

    @FXML
    void addLab(ActionEvent event) throws IOException {
        add();
    }
    public void add() throws IOException {
        String insertQuery = "INSERT INTO labs(labID, labName) VALUES(?, ?)";
        if (labName.getText().length() == 0) {
            display = "Please fill this up.";
            displayMess.setText(display);
            displayMess.setVisible(true);
        } else {
            try {
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, labID.getText());
                ps.setString(2, labName.getText().toUpperCase());
                ps.execute();
                Clear();
                setLabID();

                //successful window shows
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("addedSuccessfully.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
                addSuccess = stage;
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(stage);

            } catch (SQLException e) {
                e.printStackTrace();
                Clear();

                //add failed window shows
                Stage stage = new Stage(StageStyle.TRANSPARENT);
                FXMLLoader fxmlLoader = new FXMLLoader(loginPage.class.getResource("addFailed.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
                addFailed = stage;
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(stage);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setLabID(){
        labID.setText(String.valueOf(lab.nextID()));
    }

    @FXML
    void clear(ActionEvent event) {
        Clear();
    }
    private void Clear(){
        labName.clear();
    }

    private void Find(){
        displayMess.setText(display);
        FilteredList<Labs> filteredData = new FilteredList<>(lab.getlabInfo(), p -> true);
        labName.textProperty().addListener( e -> {
            filteredData.setPredicate(myObject -> {
                if (labName.getText().toUpperCase().equals(myObject.lab_name)) {
                    displayMess.setText("Lab name already exist.");
                    displayMess.setVisible(true);
                    return true;
                } else {
                    displayMess.setVisible(false);
                }
                return false;
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabID();
        Find();
    }
}
