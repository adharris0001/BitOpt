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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
/**
 * The Chart controller displays data from bitcoin.java in the form of a line chart
 * A logout button and access to the other views are displayed at the top
 * The chart loads when the view is loaded
 * You can also check on the exact value from the past 48 hours for the data
 * All data is from the loginController instance of bitcoin
 * @author Tommy Herz txl635
 */
public class ChartController implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@FXML
	LineChart<String,Number> lineChart;
	
	@FXML
	ComboBox<String> comboBox;
	
	@FXML
	ComboBox<String> comboBoxC;
	
	@FXML
	Label bitVal;
	
	@FXML 
	Label ethVal;
	
	//gets value from the static BitCoin instance from LoginController
	//initialize ArrayList of doubles
	ArrayList<Double> btceur = LoginController.newData.getBtceurPrices();
	ArrayList<Double> btcusd = LoginController.newData.getBtcusdPrices();
	ArrayList<Double> etheur = LoginController.newData.getEtheurPrices();
	ArrayList<Double> ethusd = LoginController.newData.getEthusdPrices();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//gray background
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		//set comboBoxs
		comboBoxSet();
		//Now generates chart from the get go
		generateChart();
	}
	
	/**
	 * This is called when the view is loaded
	 * Generates a chart and displays it
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void generateChart() {
		
		//loads title
		lineChart.setTitle("Bitcoin & Etherium values (last 48 hours)");
	
		//each time you generate the chart, you must clear the chart first
		lineChart.getData().clear();
	
		//check to make sure bitCoin.java is here
		if(btceur==null||btcusd==null||etheur==null||ethusd==null){
			System.out.println("Error: currupted data or missing files found.");
			
		} else {
			//makes the series that a line chart can read (linecharts are pretty dumb for how smart they are)
			XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
			XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
			XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
			XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
			
			//adds data from ArrayList to data that the line chart API can read. goes from 0 to 48. 48 values called.
			for(int i =0; i<49; i++) {
				series1.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), btcusd.get(i)));
				series2.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), btceur.get(i)));
				series3.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), etheur.get(i)));
				series4.getData().add(new XYChart.Data<String, Number>(Integer.toString(i), ethusd.get(i)));
			}
			//Sets name for each line on chart
			series1.setName("BitCoin USD");
			series2.setName("BitCoin EUR");
			series3.setName("Etherium EUR");
			series4.setName("Etherium USD");
			
			//adds all lines to lineChart
			lineChart.getData().addAll(series1,series2,series3,series4);
			}
	}
	/**
	 * sets the combo boxs values for user
	 * @param Is called on start up
	 */
	public void comboBoxSet(){
	        for (int i = 1 ; i <=48 ; i++) {
	        	comboBox.getItems().add(Integer.toString(i));
	        }
	        comboBoxC.getItems().add("USD");
	        comboBoxC.getItems().add("EUR");
	}
	/**
	 * changes label's value to match with current value of what
	 * @param Is called when the check value button is pressed
	 */
	public void findValue() {
		int i = Integer.parseInt(comboBox.getValue());
		if (comboBoxC.getValue().contains("USD")) {
			bitVal.setText("$ "+Math.round(btcusd.get(i)));
			ethVal.setText("$ "+Math.round(ethusd.get(i)));
		}
		else {
			bitVal.setText("€ "+Math.round(btceur.get(i)));
			ethVal.setText("€ "+Math.round(etheur.get(i)));
		}
	}
	/**
	 * Load to System view when System setup is pressed
	 * @param event
	 */
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
	/**
	 * Load to Portfolio view when Portfolio is pressed
	 * @param event
	 */
	public void portfolioHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Portfolio4.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Load to Chart view when charts is pressed
	 * @param event
	 */
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
	/**
	 * Load to About Us view when About Us is pressed
	 * @param event
	 */
	public void aboutHandle(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/AboutUs.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Load to Login view when the logout button is pressed
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {
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
