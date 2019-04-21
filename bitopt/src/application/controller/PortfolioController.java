package application.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
//import application.model.BitCoin;
import application.model.Portfolio;
import application.model.Transaction;
//import application.model.User;
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
//import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
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
	private ComboBox<String> accountSelect = new ComboBox<String>();
	
	@FXML
	private ComboBox<String> coinTransactionChoice = new ComboBox<String>();
	
	@FXML
	private RadioButton coinTransactionRadio = new RadioButton();
	
    @FXML
    private TextField transactionChoiceAmount;
	
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
		accountSelect.getItems().addAll("bTCUSD","bTCEUR","eTHUSD","eTHEUR");
		coinTransactionChoice.getItems().addAll("addCoin","removeCoin");
		
//		String userSelection = "";
		
		try {
			
			//portfolio.loadTransactions(userSelection, "portfolioData/" + userSelection + ".csv" );
			portfolio.loadTransactions("bTCUSD", "portfolioData/bTCUSD.csv" );
		} 
		
		catch (IOException e1) {

			e1.printStackTrace();
		}
		
		//setting default labels
		
		topCryptoCurrencyLabel.setText("BitCoin USD");
		topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		
		for(String coin : portfolio.getAccountInfo().keySet()){
			
			if(coin.equals("bTCUSD")){
				
				Transaction recent = portfolio.recentTransaction(coin);
				availableBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
				lastBalanceAmountLabel.setText(Double.toString(recent.getPreviousBalance()));
				
				for(Transaction transaction : portfolio.getAccountInfo().get(coin)){
					
					items.add(transaction.toString());
				}		
			}
		}
		
		listView.getItems().addAll(items);
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
	
	public void handleAccountSelect(ActionEvent event){

		String selectedAccount = accountSelect.getValue();
		//System.out.println(selectedCpu);
		if(selectedAccount.equals("bTCUSD")) {
			Transaction recent = portfolio.recentTransaction(selectedAccount);
			//update top label
			topCryptoCurrencyLabel.setText("BitCoin USD");
			topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			//update balance labels
			availableBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
			lastBalanceAmountLabel.setText(Double.toString(recent.getPreviousBalance()));
			//update list view
			for(Transaction transaction : portfolio.getAccountInfo().get(selectedAccount)){
				
				items.add(transaction.toString());
			}		
		}else if(selectedAccount.equals("bTCEUR")) {
			Transaction recent = portfolio.recentTransaction(selectedAccount);
			//update top label
			topCryptoCurrencyLabel.setText("BitCoin EUR");
			topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			//update balance labels
			availableBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
			lastBalanceAmountLabel.setText(Double.toString(recent.getPreviousBalance()));
			//update list view
			for(Transaction transaction : portfolio.getAccountInfo().get(selectedAccount)){
				
				items.add(transaction.toString());
			}		
		}else if(selectedAccount.equals("eTHUSD")) {
			Transaction recent = portfolio.recentTransaction(selectedAccount);
			//update top label
			topCryptoCurrencyLabel.setText("Ethereum USD");
			topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			//update balance labels
			availableBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
			lastBalanceAmountLabel.setText(Double.toString(recent.getPreviousBalance()));
			//update list view
			for(Transaction transaction : portfolio.getAccountInfo().get(selectedAccount)){
				
				items.add(transaction.toString());
			}		
		}else {
			Transaction recent = portfolio.recentTransaction(selectedAccount);
			//update top label
			topCryptoCurrencyLabel.setText("Ethereum EUR");
			topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			//update balance labels
			availableBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
			lastBalanceAmountLabel.setText(Double.toString(recent.getPreviousBalance()));
			//update list view
			for(Transaction transaction : portfolio.getAccountInfo().get(selectedAccount)){
				
				items.add(transaction.toString());
			}		
		}
	}
	
	public void handleTransactionChoice(ActionEvent event){
		
		String selectedTransaction = coinTransactionChoice.getValue();
		double selectedTransactionAmount = Double.parseDouble(transactionChoiceAmount.getText());
		//System.out.println(selectedCpu);
		if(selectedTransaction.equals("addCoin")) {	
			Transaction recent = portfolio.recentTransaction(accountSelect.getValue());
			//add or subtract to portfolio transaction
			portfolio.addCoin(accountSelect.getValue(), selectedTransactionAmount);
			//last balance is now current balance and current balance is new balance
			lastBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
			Transaction newTransaction = portfolio.recentTransaction(accountSelect.getValue());
			availableBalanceAmountLabel.setText(Double.toString(newTransaction.getCurrentBalance()));
			//save it back to the file
			try {
				portfolio.save(accountSelect.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
			//update list view
			for(Transaction transaction : portfolio.getAccountInfo().get(accountChoiceLabel)){
				
				items.add(transaction.toString());
			}		
		}else {
			Transaction recent = portfolio.recentTransaction(accountSelect.getValue());
			//add or subtract to portfolio transaction
			portfolio.removeCoin(accountSelect.getValue(), selectedTransactionAmount);
			//last balance is now current balance and current balance is new balance
			lastBalanceAmountLabel.setText(Double.toString(recent.getCurrentBalance()));
			Transaction newTransaction = portfolio.recentTransaction(accountSelect.getValue());
			availableBalanceAmountLabel.setText(Double.toString(newTransaction.getCurrentBalance()));
			//save it back to the file
			try {
				portfolio.save(accountSelect.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
			//update list view
			for(Transaction transaction : portfolio.getAccountInfo().get(accountChoiceLabel)){
				
				items.add(transaction.toString());
			}		
		}
	}
	
	public void errorLabelMessage(String message) {
		
		String errorResponse = message;
		errorLabel.setText(errorResponse);
		errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		errorLabel.setTextFill(Color.RED);
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
