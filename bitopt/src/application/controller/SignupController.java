package application.controller;

import java.io.FileWriter;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Controller for new users to setup an account with the app.
 * 
 * @author Conor Wallace (bhd445)
 * UTSA CS 3443 - Lab 2
 * Spring 2019
 */
public class SignupController implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	TextField textField = new TextField();
	
	@FXML
	PasswordField password = new PasswordField();
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	Label label = new Label();
	
	@FXML
	Label errorLabel = new Label();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@Override
	/**
	 * purpose - When the view is loaded up, this method initializes gui components.
	 */
	public void initialize(URL location, ResourceBundle resources) {
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
	 * @param event - The event in which the user clicks sign up
	 */
	public void handle(ActionEvent event) {
		String newUsername = textField.getText();
		String newPassword = password.getText();
		System.out.println(newUsername + newPassword);
        
		//check that the user has input an argument to both username and password fields
		if(newUsername.equals("") || newPassword.equals("")) {
			System.out.println("In error");
			errorLabel.setText("Invalid Username or Password.");
			errorLabel.setTextFill(Color.RED);
			errorLabel.setFont(Font.font("Cambria", 14));
			errorLabel.setAlignment(Pos.CENTER);
		}
		else {
			//if a valid username and password are input, append these credentials to the user csv,
			//then reload the login view
		    try {
		    	FileWriter fileWriter = new FileWriter("userData/users.csv", true);
		    	
		        fileWriter.append(newUsername);
		        fileWriter.append(",");
		        fileWriter.append(newPassword);
		        fileWriter.append("\n");
		        fileWriter.close();
	            System.out.println("CSV file was created successfully !!!");
	            
				Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
				System.out.println("Loading Personnel Scene");			
				Main.stage.setScene(new Scene(root, 800, 800));
				Main.stage.show();
		             
	        } catch (Exception e) {
	        	System.out.println("Error in CsvFileWriter !!!");
		        e.printStackTrace();
		    }
		}
	}
}
