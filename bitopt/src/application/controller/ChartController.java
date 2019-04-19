package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.model.BitCoin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ChartController implements EventHandler<ActionEvent>, Initializable {
	//handle currently does nothing
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@FXML
	LineChart<String,Number> lineChart;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
	}
	
	BitCoin bitCoin = new BitCoin();
	
	//gets value from the static BitCoin instance from LoginController
	ArrayList<Double> btceur = LoginController.newData.getBtceurPrices();
	ArrayList<Double> btcusd = LoginController.newData.getBtcusdPrices();
	ArrayList<Double> etheur = LoginController.newData.getEtheurPrices();
	ArrayList<Double> ethusd = LoginController.newData.getEthusdPrices();
	
	
	//Suppress warning is needed because eclipse is bad
	@SuppressWarnings("unchecked")
	public void generateChart(ActionEvent e) {
		//title
		lineChart.setTitle("Bitcoin & Etherium values (last 48 hours)");
	
		//each time you generate chart, clear chart first
		lineChart.getData().clear();
		//makes the series that linechart can read
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
		
		//adds data from ArrayList to data that the linechart api can read. goes from 0 to 48. 48 values called.
		for(int i =0; i<49; i++) {
			series1.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), btcusd.get(i)));
			series2.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), btceur.get(i)));
			series3.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), etheur.get(i)));
			series4.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), ethusd.get(i)));
		}
		//Sets name for each line
		series1.setName("BitCoin USD");
		series2.setName("BitCoin EUR");
		series3.setName("Etherium EUR");
		series4.setName("Etherium USD");
		
		lineChart.getData().addAll(series1,series2,series3,series4);	
	}
	
	//brings user back home
	public void homeHandle(ActionEvent arg0){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
