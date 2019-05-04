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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * The Portfolio controller is the support for the aboutUs view
 * The class handles intializing the background color and the title
 * A logout button and access to the other views are displayed at the top
 * @author Tommy Herz txl635
 *
 */
public class AboutUsController implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@FXML
	Label label = new Label();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//sets background colors
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("About Us");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
	}
	/**
	 * Load to login view when logout button is pressed
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
	/**
	 * Load to About Us view when About Us is pressed
	 * @param event
	 */
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
	/**
	 * Load to About Us view when About Us is pressed
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
	 * Load to About Us view when About Us is pressed
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
	 * Load to About Us view when About Us is pressed
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
}
