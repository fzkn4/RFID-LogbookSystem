module com.example.loginsystemfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.example.loginsystemfinal to javafx.fxml;
    exports com.example.loginsystemfinal;
}