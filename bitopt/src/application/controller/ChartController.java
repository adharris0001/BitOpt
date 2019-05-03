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
//Tommy Herz
public class ChartController implements EventHandler<ActionEvent>, Initializable {
	
	/* javadoc notes:
	I added comments to most functions and some comments on some blocks of code.
	But heres the rundown:
	-This view shows the local values from bitcoin and displays the on a linechart
	-You can view what value is at a certain hour for bot eth and btc
	-Also supports different currency values
	-This class is the controller for Chart.fxml
	-You can go to any other view using the menubar in this class.
	-You can also log out from this class
	*/
	
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
	// BitCoin
	BitCoin bitCoin = new BitCoin();
	
	//gets value from the static BitCoin instance from LoginController
	ArrayList<Double> btceur = LoginController.newData.getBtceurPrices();
	ArrayList<Double> btcusd = LoginController.newData.getBtcusdPrices();
	ArrayList<Double> etheur = LoginController.newData.getEtheurPrices();
	ArrayList<Double> ethusd = LoginController.newData.getEthusdPrices();
	
	
	//Suppress warning is needed because eclipse is bad 
	//Main method to generate LineChart
	@SuppressWarnings("unchecked")
	public void generateChart() {
		
		//title
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
	//used to set comboboxs
	public void comboBoxSet(){
	        for (int i = 1 ; i <=48 ; i++) {
	        	comboBox.getItems().add(Integer.toString(i));
	        }
	        comboBoxC.getItems().add("USD");
	        comboBoxC.getItems().add("EUR");
	}
	//called from "Check Value button" .  Gets combobox values and returns values from arraylists of bitcoin/etheruem
	public void findValue() {
		int i = Integer.parseInt(comboBox.getValue());
		if (comboBoxC.getValue().contains("USD")) {
			bitVal.setText("$ "+Math.round(btcusd.get(i)));
			ethVal.setText("$ "+Math.round(ethusd.get(i)));
		}
		else {
			bitVal.setText("� "+Math.round(btceur.get(i)));
			ethVal.setText("� "+Math.round(etheur.get(i)));
		}
	}

	//Brings scene to System.fxml Controller = SystemController.java
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

	//Brings scene to Portfolio.fxml Controller = PortfolioController.java
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
	
	//Brings scene to AboutUs.FXML. Controller = AboutUsController.java
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

	//logs user out
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
