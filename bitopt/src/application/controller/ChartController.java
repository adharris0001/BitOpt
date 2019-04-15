package application.controller;

import java.util.ArrayList;

import application.Main;
import application.model.BitCoin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartController {

	@FXML
	LineChart<String,Number> lineChart;
	BitCoin bitCoin = new BitCoin();
	
	//currently gets null. needs to be a static variable
	ArrayList<Double> btceur = bitCoin.getBtceurPrices();
	ArrayList<Double> btcusd = bitCoin.getBtcusdPrices();
	ArrayList<Double> etheur = bitCoin.getEtheurPrices();
	ArrayList<Double> ethusd = bitCoin.getEthusdPrices();
	
	
	@SuppressWarnings("unchecked")
	public void generateChart(ActionEvent e) {
		
		btcusd = bitCoin.getBtcusdPrices();
		lineChart.getData().clear();
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		
		for(int i =0; i<btcusd.size(); i++) {
		series.getData().add(new XYChart.Data<String, Number>("Hour: " + i , btcusd.get(i)));
		}
		series.setName("BitCoin USD");
		
		lineChart.getData().addAll(series);
		
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
}
