package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.BitCoin;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Controller to allow users to enter their login credentials and access the rest
 * of the app.
 * 
 * @author Conor Wallace (bhd445)
 * UTSA CS 3443 - Lab 2
 * Spring 2019
 */
public class LoginController implements EventHandler<ActionEvent>, Initializable {
	public static User user;
	public static BitCoin newData;
	public static boolean initialLoad = true;
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@FXML
	Label label = new Label();
	
	@FXML
	Label errorLabel = new Label();
	
	@FXML
	TextField textField = new TextField();
	
	@FXML
	PasswordField passwordField = new PasswordField();
	
	@Override
	/**
	 * purpose - When the view is loaded up, this method initializes gui components
	 * as well as instantiating a new BitCoin object and generating a crypto data set.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		//flag to check whether or not the data set has already been generated
		if(initialLoad == true) {
			newData = new BitCoin();
			newData.generateData();
			initialLoad = false;
			System.out.println("initializing dataset");
		}
		
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
	}

	@Override
	/**
	 * 
	 * @param event - The event in which the user clicks login
	 */
	public void handle(ActionEvent event) {
		String attemptedUserName = textField.getText();
		String attemptedPassword = passwordField.getText();
		
		//check that the user has input an argument to both username and password fields
		if(attemptedUserName.equals("") || attemptedPassword.equals("")) {
			System.out.println("In error");
			errorLabel.setText("Invalid Username or Password.");
			errorLabel.setTextFill(Color.RED);
			errorLabel.setFont(Font.font("Cambria", 14));
			errorLabel.setAlignment(Pos.CENTER);
		}
		else {
		    try {
		    	user = new User(attemptedUserName);
				
		    	//check that this user has a registered account
				if(!user.validate(attemptedUserName, attemptedPassword)) {
					errorLabel.setText("Incorrect Username or Password.");
					errorLabel.setTextFill(Color.RED);
					errorLabel.setFont(Font.font("Cambria", 14));
					errorLabel.setAlignment(Pos.CENTER);
				}
				else {
					Parent root = FXMLLoader.load(getClass().getResource("../view/AboutUs.fxml"));
					System.out.println("Loading Personnel Scene");			
					Main.stage.setScene(new Scene(root, 800, 800));
					Main.stage.show();
				}		             
	        } catch (Exception e) {
	        	System.out.println("Error in CsvFileWriter !!!");
		        e.printStackTrace();
		    }
		}
	}
	
	/**
	 * 
	 * @param event - The event in which the user clicks sign up
	 */
	public void signUpHandle(ActionEvent event) {
		System.out.println("Sign up success!");
		// Redirect user to next view - Library
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Signup.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
