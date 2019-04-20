package application.controller;

import java.io.IOException;
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

public class LoginController implements EventHandler<ActionEvent>, Initializable {
	public static User user;
	public static BitCoin newData = new BitCoin();
	
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
	public void initialize(URL location, ResourceBundle resources) {
		newData.generateData();
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		// Have Library read in all books - titles and authors only!
		try {
			String attemptedUserName = textField.getText();
			String attemptedPassword = passwordField.getText();
				
			user = User.validate(attemptedUserName, attemptedPassword);
				
			if(user == null) {
				errorLabel.setText("Incorrect Username or Password.");
				errorLabel.setTextFill(Color.RED);
				errorLabel.setFont(Font.font("Cambria", 14));
				errorLabel.setAlignment(Pos.CENTER);
			}
						
		}catch( IOException e ) {
			// TODO: There was a problem! Alert the user via the GUI.
			e.printStackTrace();
		}
		
		// Redirect user to next view - Library
		try {
			if(user != null) {
				Parent root = FXMLLoader.load(getClass().getResource("../view/Home.fxml"));
				System.out.println("Loading Personnel Scene");			
				Main.stage.setScene(new Scene(root, 800, 800));
				Main.stage.show();
				
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
