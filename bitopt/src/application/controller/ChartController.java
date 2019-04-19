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
	public void homeHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void systemHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/System.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void portfolioHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Portfolio.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Brings scene to Chart.FXML. Controller = ChartController.java
	public void chartHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Chart.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Brings scene to Chart.FXML. Controller = ChartController.java
	public void aboutHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Chart.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
