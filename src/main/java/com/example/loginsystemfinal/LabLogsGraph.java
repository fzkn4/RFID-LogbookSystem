package com.example.loginsystemfinal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class LabLogsGraph implements Initializable {

    @FXML
    private BarChart<?, ?> labChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("CCS");
        series1.getData().add(new XYChart.Data("January", 231));
        series1.getData().add(new XYChart.Data("February", 432));
        series1.getData().add(new XYChart.Data("March", 233));
        series1.getData().add(new XYChart.Data("April", 543));
        series1.getData().add(new XYChart.Data("May", 756));
        series1.getData().add(new XYChart.Data("June", 645));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("CTEA");
        series2.getData().add(new XYChart.Data("January", 534));
        series2.getData().add(new XYChart.Data("February", 423));
        series2.getData().add(new XYChart.Data("March", 756));
        series2.getData().add(new XYChart.Data("April", 543));
        series2.getData().add(new XYChart.Data("May", 876));
        series2.getData().add(new XYChart.Data("June", 345));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("CBE");
        series3.getData().add(new XYChart.Data("January", 453));
        series3.getData().add(new XYChart.Data("February", 432));
        series3.getData().add(new XYChart.Data("March", 21));
        series3.getData().add(new XYChart.Data("April", 312));
        series3.getData().add(new XYChart.Data("May", 222));
        series3.getData().add(new XYChart.Data("June", 321));

        XYChart.Series series4 = new XYChart.Series();
        series4.setName("COC");
        series4.getData().add(new XYChart.Data("January", 543));
        series4.getData().add(new XYChart.Data("February", 234));
        series4.getData().add(new XYChart.Data("March", 564));
        series4.getData().add(new XYChart.Data("April", 756));
        series4.getData().add(new XYChart.Data("May", 866));
        series4.getData().add(new XYChart.Data("June", 877));

        labChart.getData().addAll(series1,series2,series3,series4);

    }
}
