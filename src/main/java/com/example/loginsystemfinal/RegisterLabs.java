package com.example.loginsystemfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private final DBConnect connects = new DBConnect();
    private final Connection con = connects.getConnection();

    @FXML
    private MFXTextField labID;
    @FXML
    private MFXButton add;


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
                MainPage.addSuccess.showAndWait();
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(MainPage.addSuccess);

            } catch (SQLException e) {
                e.printStackTrace();
                Clear();

                AddFailed.displayFailed = "Something went wrong.";
                //add failed window shows
                MainPage.addFailedStage.showAndWait();
                //centering window
                Functions func = new Functions();
                func.setWindowCenter(MainPage.addFailedStage);
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
                if (String.valueOf(myObject.getLab_name()).equals(labName.getText().toUpperCase())) {
                    displayMess.setText("Lab name already exist.");
                    displayMess.setVisible(true);
                    add.setDisable(true);
                    return true;
                } else {
                    displayMess.setVisible(false);
                    add.setDisable(false);
                    return false;
                }
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabID();
        Find();
    }
}
