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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SignupController implements EventHandler<ActionEvent>, Initializable {
	
	@FXML
	TextField textField = new TextField();
	
	@FXML
	TextField textField1 = new TextField();
	
	@FXML
	HBox hbox = new HBox();
	
	@FXML
	Label label = new Label();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
	}

	@Override
	public void handle(ActionEvent event) {
		String newUsername = textField.getText();
		String newPassword = textField1.getText();
		System.out.println(newUsername + newPassword);
		FileWriter fileWriter = null;
         
	    try {
	    	fileWriter = new FileWriter("userData/users.csv", true);
	    	
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
