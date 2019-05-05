package application.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Controller class for the crypto-mining computer system setup view where
 * the user can select the components that make up their computer and then
 * optimizing the power consumption of that setup based on the percent difference
 * of the current crypto market vs. the history of the market.
 * 
 * @author Conor Wallace (bhd445)
 * UTSA CS 3443 - Lab 2
 * Spring 2019
 */
public class SystemController implements EventHandler<ActionEvent>, Initializable{
	private String systemGPU;
	private String systemCPU;
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	MenuBar menubar = new MenuBar();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@FXML
	Label label = new Label();
	
	@FXML
	ComboBox<String> cpuComboBox = new ComboBox<String>();

	@FXML
	ComboBox<String> gpuComboBox = new ComboBox<String>();
	
	@FXML
	GridPane gridPane = new GridPane();
	
	@FXML
	GridPane gtxPane = new GridPane();
	
	@FXML
	Label gpuArchitecture = new Label();
	
	@FXML
	Label gpuCores = new Label();

	@FXML
	Label gpuBuffer = new Label();
	
	@FXML
	Label gpuSpeed = new Label();
	
	@FXML
	Label gpuBoost = new Label();
	
	@FXML
	Label cpuCores = new Label();
	
	@FXML
	Label cpuSpeed = new Label();
	
	@FXML
	Label cpuThreads = new Label();
	
	@FXML
	Label cpuTurbo = new Label();
	
	@FXML
	Label cpuCache = new Label();
	
	@FXML
	BarChart<String, Number> optimizedChart;
	
	@Override
	/**
	 * purpose - When the view is loaded up, this method initializes gui components.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
		cpuComboBox.getItems().addAll("Intel I9","Intel I7","Intel I5","Intel I3");	
		gpuComboBox.getItems().addAll("GTX 1080","GTX 1070","GTX 1060","GTX 1050");	
	}

	@Override
	/**
	 * 
	 * @param event - The event in which the user clicks logout
	 */
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
	
	/**
	 * Based on the item the user selects, appropriate gui components are loaded
	 * 
	 * @param event - The event in which the user selects a CPU
	 */
	public void cpuHandle(ActionEvent event) {
		String selectedCpu = cpuComboBox.getValue();
		System.out.println(selectedCpu);
		if(selectedCpu.equals("Intel I9")) {
			this.setSystemCPU("Intel I9");
			Image image = new Image("File:images/intelI9.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 8");
			cpuSpeed.setText("Base Freq: 3.6 GHz");
			cpuThreads.setText("# of Threads: 16");
			cpuTurbo.setText("Turbo Freq: 5.0 GHz");
			cpuCache.setText("Cache: 16 MB");
		}else if(selectedCpu.equals("Intel I7")) {
			this.setSystemCPU("Intel I7");
			Image image = new Image("File:images/intelI7.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 8");
			cpuSpeed.setText("Base Freq: 3.6 GHz");
			cpuThreads.setText("# of Threads: 8");
			cpuTurbo.setText("Turbo Freq: 4.9 GHz");
			cpuCache.setText("Cache: 12 MB");
		}else if(selectedCpu.equals("Intel I5")) {
			this.setSystemCPU("Intel I5");
			Image image = new Image("File:images/intelI5.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 6");
			cpuSpeed.setText("Base Freq: 2.9 GHz");
			cpuThreads.setText("# of Threads: 6");
			cpuTurbo.setText("Turbo Freq: 4.1 GHz");
			cpuCache.setText("Cache: 9 MB");
		}else {
			this.setSystemCPU("Intel I3");
			Image image = new Image("File:images/intelI3.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 2");
			cpuSpeed.setText("Base Freq: 2.1 GHz");
			cpuThreads.setText("# of Threads: 4");
			cpuTurbo.setText("Turbo Freq: 3.9 GHz");
			cpuCache.setText("Cache: 4 MB");
		}
	}

	/**
	 * Based on the item the user selects, appropriate gui components are loaded
	 * 
	 * @param event - The event in which the user selects a GPU
	 */
	public void gpuHandle(ActionEvent event) {
		String selectedGpu = gpuComboBox.getValue();
		System.out.println(selectedGpu);
		if(selectedGpu.equals("GTX 1080")) {
			this.setSystemGPU("GTX 1080");
			Image image = new Image("File:images/gtx1080.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(145);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("Arch: Pascal");
			gpuCores.setText("Cores: 2560");
			gpuBuffer.setText("Buffer: 8 GB");
			gpuSpeed.setText("Speed: 10 Gbps");
			gpuBoost.setText("Clock: 1733 MHz");
		}else if(selectedGpu.equals("GTX 1070")) {
			this.setSystemGPU("GTX 1070");
			Image image = new Image("File:images/gtx1070.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(100);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("Arch: Pascal");
			gpuCores.setText("Cores: 2432");
			gpuBuffer.setText("Buffer: 8 GB");
			gpuSpeed.setText("Speed: 8 Gbps");
			gpuBoost.setText("Clock: 1683 MHz");
		}else if(selectedGpu.equals("GTX 1060")) {
			this.setSystemGPU("GTX 1060");
			Image image = new Image("File:images/gtx1060.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(100);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("Arch: Pascal");
			gpuCores.setText("Cores: 1280");
			gpuBuffer.setText("Buffer: 6 GB");
			gpuSpeed.setText("Speed: 8 Gbps");
			gpuBoost.setText("Clock: 1708 MHz");
		}else {
			this.setSystemGPU("GTX 1050");
			Image image = new Image("File:images/gtx1050.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(165);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("Arch: Pascal");
			gpuCores.setText("Cores: 768");
			gpuBuffer.setText("Buffer: 3 GB");
			gpuSpeed.setText("Speed: 7 Gbps");
			gpuBoost.setText("Clock: 1518 MHz");
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Depending on the power of the components selected, the default power consumption
	 * is selected (i.e. most powerful part = lowest default power consumption)
	 * 
	 * @param event - The event in which the user clicks Optimize
	 */
	public void optimizeHandle(ActionEvent event) {
		double cpuPower = 0.0;
		double gpuPower = 0.0;
		double computePower = 0.0;
		
		if(this.getSystemCPU().equals("Intel I9")) {
			cpuPower = 25.0;
		} else if(this.getSystemCPU().equals("Intel I7")) {
			cpuPower = 30.0;
		} else if(this.getSystemCPU().equals("Intel I5")) {
			cpuPower = 35.0;
		} else if(this.getSystemCPU().equals("Intel I3")) {
			cpuPower = 40.0;
		}
		
		if(this.getSystemGPU().equals("GTX 1080")) {
			gpuPower = 25.0;
		} else if(this.getSystemGPU().equals("GTX 1070")) {
			gpuPower = 30.0;
		} else if(this.getSystemGPU().equals("GTX 1060")) {
			gpuPower = 35.0;
		} else if(this.getSystemGPU().equals("GTX 1050")) {
			gpuPower = 40.0;
		}
		
		computePower = cpuPower + gpuPower;
		LoginController.newData.setComputeCapability(computePower);
		
		gtxPane.add(gpuArchitecture, 0, 1);
		gtxPane.add(gpuCores, 0, 2);
		gtxPane.add(gpuBuffer, 0, 3);
		gtxPane.add(gpuSpeed, 0, 4);
		gtxPane.add(gpuBoost, 0, 5);
		gtxPane.add(cpuCores, 1, 1);
		gtxPane.add(cpuThreads, 1, 2);
		gtxPane.add(cpuSpeed, 1, 3);
		gtxPane.add(cpuTurbo, 1, 4);
		gtxPane.add(cpuCache, 1, 5);
		
		//instantiate a chart to show optimization results.
	    //Defining the axes               
	    CategoryAxis xAxis = new CategoryAxis();    
	    xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Original System Power", "Optimized System Power"))); 
	    
	    xAxis.setLabel("category");
	    NumberAxis yAxis = new NumberAxis(); 
	    yAxis.setLabel("Compute Capability"); 
	         
	    System.out.println(LoginController.newData.getComputeCapability());
	    
	    //optimize power consumption
	    double oldComputeCapability = LoginController.newData.getComputeCapability();
	    LoginController.newData.optimize();
	    double newComputeCapability = LoginController.newData.getComputeCapability();
	    
	    System.out.println(LoginController.newData.getComputeCapability());
	    
	    //Prepare XYChart.Series objects by setting data 
	    XYChart.Series<String, Number> series1 = new XYChart.Series<>();  
	    series1.setName("Original System Power"); 
	    series1.getData().add(new XYChart.Data<>(" ", oldComputeCapability));
	    
	    XYChart.Series<String, Number> series2 = new XYChart.Series<>();  
	    series2.setName("Optimized System Power"); 
	    series2.getData().add(new XYChart.Data<>(" ", newComputeCapability));
	    
	    //Setting the data to bar chart
	    optimizedChart.getData().addAll(series1, series2);
	}

	/**
	 * 
	 * @param event - The event in which the user clicks the System Setup menu button
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
	 * 
	 * @param event - The event in which the user clicks the Portfolio menu button
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
	 * 
	 * @param event - The event in which the user clicks the Charts menu button
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
	 * 
	 * @param event - The event in which the user clicks the About Us menu button
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
	
	//getters and setters
	public String getSystemGPU() {
		return systemGPU;
	}

	public void setSystemGPU(String systemGPU) {
		this.systemGPU = systemGPU;
	}

	public String getSystemCPU() {
		return systemCPU;
	}

	public void setSystemCPU(String systemCPU) {
		this.systemCPU = systemCPU;
	}
}
