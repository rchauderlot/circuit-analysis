package es.chauder.circuitanalyzer.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToolBar;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    @FXML
    ToolBar toolBar;

    @FXML
    LineChart lineChart;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or <tt>null</tt> if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        XYChart.Series<Double, Double> serie = new XYChart.Series<Double, Double>();
        serie.setName("Fender");
        for (int i = 2; i <= 2000; i++) {
            serie.getData().add(new XYChart.Data<Double, Double>((double)i*10, Math.sin(((double)i)/100) - 1));
        }
        lineChart.getData().add(serie);

    }
}
