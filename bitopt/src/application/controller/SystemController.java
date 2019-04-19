package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SystemController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	ComboBox<String> cpuComboBox = new ComboBox<String>();
	
	@FXML
	GridPane gridPane = new GridPane();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cpuComboBox.getItems().addAll("Intel I9","Intel I7","Intel I5","Intel I3");	
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
			Image image = new Image("File:image/intelI9.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(175);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}else if(selectedCpu.equals("Intel I7")) {
			Image image = new Image("File:image/intelI7.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(175);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}else if(selectedCpu.equals("Intel I5")) {
			Image image = new Image("File:image/intelI5.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(175);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}else {
			Image image = new Image("File:image/intelI3.png");
			ImageView imageview = new ImageView();
			imageview.setImage(image);
			imageview.setFitHeight(175);
			imageview.setFitWidth(150);
			gridPane.add(imageview, 0, 0);
		}
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
