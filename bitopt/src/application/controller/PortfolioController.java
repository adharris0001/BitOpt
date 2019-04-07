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

/**
 * @author User
 *
 */
public class PortfolioController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	HBox hbox = new HBox();
	
	@FXML
	AnchorPane panel = new AnchorPane();
	
	@FXML
	Label label = new Label();
	
	@FXML
	Label errorLabel = new Label();
	
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
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
