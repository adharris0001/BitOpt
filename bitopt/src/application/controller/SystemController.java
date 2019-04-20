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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SystemController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	HBox hbox = new HBox();
	
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
	StackedBarChart<String, Number> optimizedChart;
	
	@Override
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
	
	public void cpuHandle(ActionEvent event) {
		String selectedCpu = cpuComboBox.getValue();
		System.out.println(selectedCpu);
		if(selectedCpu.equals("Intel I9")) {
			Image image = new Image("File:images/intelI9.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 8");
			cpuSpeed.setText("Processor Base Frequency: 3.6 GHz");
			cpuThreads.setText("# of Threads: 16");
			cpuTurbo.setText("Max Turbo Frequency: 5.0 GHz");
			cpuCache.setText("Cache: 16 MB SmartCache");
		}else if(selectedCpu.equals("Intel I7")) {
			Image image = new Image("File:images/intelI7.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 8");
			cpuSpeed.setText("Processor Base Frequency: 3.6 GHz");
			cpuThreads.setText("# of Threads: 8");
			cpuTurbo.setText("Max Turbo Frequency: 4.9 GHz");
			cpuCache.setText("Cache: 12 MB SmartCache");
		}else if(selectedCpu.equals("Intel I5")) {
			Image image = new Image("File:images/intelI5.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 6");
			cpuSpeed.setText("Processor Base Frequency: 2.9 GHz");
			cpuThreads.setText("# of Threads: 6");
			cpuTurbo.setText("Max Turbo Frequency: 4.1 GHz");
			cpuCache.setText("Cache: 9 MB SmartCache");
		}else {
			Image image = new Image("File:images/intelI3.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
			cpuCores.setText("# of Cores: 2");
			cpuSpeed.setText("Processor Base Frequency: 2.1 GHz");
			cpuThreads.setText("# of Threads: 4");
			cpuTurbo.setText("Max Turbo Frequency: 3.9 GHz");
			cpuCache.setText("Cache: 4 MB SmartCache");
		}
	}

	public void gpuHandle(ActionEvent event) {
		String selectedGpu = gpuComboBox.getValue();
		System.out.println(selectedGpu);
		if(selectedGpu.equals("GTX 1080")) {
			Image image = new Image("File:images/gtx1080.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(145);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("GPU Architecture: Pascal");
			gpuCores.setText("CUDA Cores: 2560");
			gpuBuffer.setText("Frame Buffer: 8 GB GDDR5X");
			gpuSpeed.setText("Memory Speed: 10 Gbps");
			gpuBoost.setText("Boost Clock: 1733 MHz");
		}else if(selectedGpu.equals("GTX 1070")) {
			Image image = new Image("File:images/gtx1070.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(100);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("GPU Architecture: Pascal");
			gpuCores.setText("CUDA Cores: 2432");
			gpuBuffer.setText("Frame Buffer: 8 GB GDDR5");
			gpuSpeed.setText("Memory Speed: 8 Gbps");
			gpuBoost.setText("Boost Clock: 1683 MHz");
		}else if(selectedGpu.equals("GTX 1060")) {
			Image image = new Image("File:images/gtx1060.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(100);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("GPU Architecture: Pascal");
			gpuCores.setText("CUDA Cores: 1280");
			gpuBuffer.setText("Frame Buffer: 6 GB GDDR5X");
			gpuSpeed.setText("Memory Speed: 8 Gbps");
			gpuBoost.setText("Boost Clock: 1708 MHz");
		}else {
			Image image = new Image("File:images/gtx1050.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(165);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
			gpuArchitecture.setText("GPU Architecture: Pascal");
			gpuCores.setText("CUDA Cores: 768");
			gpuBuffer.setText("Frame Buffer: 3 GB GDDR5X");
			gpuSpeed.setText("Memory Speed: 7 Gbps");
			gpuBoost.setText("Boost Clock: 1518 MHz");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void optimizeHandle(ActionEvent event) {
		System.out.println("Something");
		
		gtxPane.add(gpuArchitecture, 0, 0);
		gtxPane.add(gpuCores, 0, 1);
		gtxPane.add(gpuBuffer, 0, 2);
		gtxPane.add(gpuSpeed, 0, 3);
		gtxPane.add(gpuBoost, 0, 4);
		gtxPane.add(cpuCores, 1, 0);
		gtxPane.add(cpuThreads, 1, 1);
		gtxPane.add(cpuSpeed, 1, 2);
		gtxPane.add(cpuTurbo, 1, 3);
		gtxPane.add(cpuCache, 1, 4);
		
	    //Defining the axes               
	    CategoryAxis xAxis = new CategoryAxis();    
	    xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Optimized Results"))); 
	    
	    xAxis.setLabel("category");
	    NumberAxis yAxis = new NumberAxis(); 
	    yAxis.setLabel("Compute Capability"); 
	    	    
	    //Prepare XYChart.Series objects by setting data 
	    XYChart.Series<String, Number> series1 = new XYChart.Series<>();  
	    series1.setName("Previous Compute Power"); 
	    series1.getData().add(new XYChart.Data<>("Optimized Results", LoginController.newData.getComputeCapability()));  
	         
	    System.out.println(LoginController.newData.getComputeCapability());
	    
	    double oldComputeCapability = LoginController.newData.getComputeCapability();
	    double optimized = LoginController.newData.optimize();
	    double newComputeCapability = 0.0;
	    
	    if(optimized > 0) {
	    	newComputeCapability = oldComputeCapability + optimized;
	    } else {
	    	newComputeCapability = oldComputeCapability - optimized;
	    }
	    
	    System.out.println(LoginController.newData.getComputeCapability());
	    
	    XYChart.Series<String, Number> series2 = new XYChart.Series<>(); 
	    series2.setName("Optimized Compute Power"); 
	    series2.getData().add(new XYChart.Data<>("Optimized Results", newComputeCapability)); 
	    
	    //Setting the data to bar chart
	    optimizedChart.getData().addAll(series1, series2);
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
