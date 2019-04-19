package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		}else if(selectedCpu.equals("Intel I7")) {
			Image image = new Image("File:images/intelI7.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}else if(selectedCpu.equals("Intel I5")) {
			Image image = new Image("File:images/intelI5.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}else {
			Image image = new Image("File:images/intelI3.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(150);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}
	}

	public void gpuHandle(ActionEvent event) {
		String selectedGpu = gpuComboBox.getValue();
		System.out.println(selectedGpu);
		if(selectedGpu.equals("GTX 1080")) {
			Image image = new Image("File:images/gtx1080.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(135);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
		}else if(selectedGpu.equals("GTX 1070")) {
			Image image = new Image("File:images/gtx1070.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(135);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
		}else if(selectedGpu.equals("GTX 1060")) {
			Image image = new Image("File:images/gtx1060.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(135);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
		}else {
			Image image = new Image("File:images/gtx1050.jpg");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(165);
			imageview.setFitWidth(165);
			gridPane.add(imageview, 0, 1);
		}
	}
	
	public void optimizeHandle(ActionEvent event) {
		System.out.println("Something");
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
