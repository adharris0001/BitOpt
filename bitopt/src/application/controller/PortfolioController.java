package application.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import application.model.BitCoin;
import application.model.Portfolio;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author User
 *
 */
public class PortfolioController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	private HBox hbox = new HBox();
	
	@FXML
	private AnchorPane panel = new AnchorPane();
	
	@FXML
	private Label label = new Label();
	
	@FXML
	private Label topCryptoCurrencyLabel = new Label();
	
	@FXML
	private Label availableBalanceLabel = new Label();
	
	@FXML
	private Label availableBalanceAmountLabel = new Label();
	
	@FXML
	private Label lastBalanceLabel = new Label();
	
	@FXML
	private Label lastBalanceAmountLabel = new Label();
	
	@FXML
	private Label accountChoiceLabel = new Label();
	
	@FXML
	private Label transactionLabel = new Label();
	
	@FXML
	private Label dateLabel = new Label();
	
	@FXML
	private Label descriptionLabel = new Label();
	
	@FXML
	private Label amountLabel = new Label();
	
	@FXML
	private Label balanceLabel = new Label();
	
	@FXML
	private Label errorLabel = new Label();
	
	@FXML
	private GridPane topGridPane = new GridPane();
	
	@FXML
	private GridPane middleGridPane = new GridPane();
	
	@FXML
	private GridPane bottomGridPane = new GridPane();
	
	@FXML
	private ChoiceBox accountSelect = new ChoiceBox();
	
	@FXML
	private ChoiceBox coinTransactionChoice = new ChoiceBox();
	
	@FXML
	private RadioButton coinTransactionRadio = new RadioButton();
	
    @FXML
    private ListView<String> listView;
    ObservableList<String> items = FXCollections.observableArrayList();
    
    Portfolio portfolio = new Portfolio("Cryptocurrency");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
		
		String userSelection = "";
		
		try {
			
			portfolio.loadTransactions(userSelection, "portfolioData/" + userSelection + ".csv" );
		} 
		
		catch (IOException e1) {

			e1.printStackTrace();
		}
		
		//setting default labels
		
		topCryptoCurrencyLabel.setText("BitCoin USD");
		topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void errorLabelMessage(String message) {
		
		String errorResponse = message;
		errorLabel.setText(errorResponse);
		errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		errorLabel.setTextFill(Color.RED);
	}
	
}
