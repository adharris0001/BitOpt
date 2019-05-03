package application.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    
    int countSelects; 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		countSelects = 1;
		
		//Adding the proper color scheme of the scene to match other views
		
		backgroundDisplay();
		
		//adding selections to drop down boxes
		
		initializeDropDowns();
		
		//loading transactions with respect to bTCUSD as the default initial view
		//loading all transactions as well
		//initializing the most recent transaction
		
		Transaction recent = getTransactions("bTCUSD");
		
		//setting default labels
		
		updateLabels("bTCUSD", recent);
		
		//initial list view that user sees in portfolio view all based on bTCUSD values
		
		updateListView("bTCUSD");
	}
	
	public void handleAccountSelect(ActionEvent event){

		countSelects++;
		
		//clearing the transaction choice amount text field for possible new entries
		
		transactionChoiceAmount.clear();
		errorLabel.setText("");
		
		//getting the String name of the account that the user is choosing
		//displaying the view of all the values and labels with respect to the account selection
		
		accountSelectView(accountSelect.getValue());
	}
	
	public void handleTransactionChoice(ActionEvent event){
		
		//clearing the items array list  
		
		items.clear();
		errorLabel.setText("");
		
		//checking to see if user is entering data correctly
		
		if(errorCheck()) {
						
			//getting the double amount of the user's entry and initializing variable
			
			double selectedTransactionAmount = Double.parseDouble(transactionChoiceAmount.getText());
			
			if(coinTransactionChoice.getValue().equals("addCoin")) {	
								
				//add to portfolio transaction
				
				portfolio.addCoin(accountSelect.getValue(), selectedTransactionAmount);
				coinTransaction(accountSelect.getValue());
			}
			
			else {
				
				//subtract to portfolio transaction
				//if withdrawing more than you have in your account, print error
				
				if (portfolio.removeCoin(accountSelect.getValue(), selectedTransactionAmount)) {
					
					coinTransaction(accountSelect.getValue());
				}
				
				else {
					
					errorLabelMessage("Can't withdraw more than you have");
				}
			}
		}
		
		coinTransactionChoice.setValue(null);
	}
	
	public void backgroundDisplay() {
		
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
	}
	
	public void initializeDropDowns() {
		
		accountSelect.getItems().addAll("bTCUSD","bTCEUR","eTHUSD","eTHEUR");
		coinTransactionChoice.getItems().addAll("addCoin","removeCoin");
	}
	
	public void accountSelectView(String selectedAccount) {
		
		//clearing the previous values from the items ArrayList and the listView
		
		clearViewItems();
		
		//loading transactions with respect to user's account selection
		//initializing the most recent transaction
		
		Transaction recent = portfolio.recentTransaction(selectedAccount);
			
			//updating labels based on the user's account selection
			
			updateLabels(selectedAccount, recent);
				
			//update the list view with transaction values based on the user's account selection
			
			updateListView(selectedAccount);
	}
	
	public void clearViewItems() {
		
		items.clear();
		listView.getItems().clear();
	}
	
	public Transaction getTransactions(String selectedAccount) {
		
		try {
			
			portfolio.loadTransactions(selectedAccount, "portfolioData/" + selectedAccount + ".csv" );
			portfolio.loadTransactions("bTCEUR", "portfolioData/bTCEUR.csv" );
			portfolio.loadTransactions("eTHUSD", "portfolioData/eTHUSD.csv" );
			portfolio.loadTransactions("eTHEUR", "portfolioData/eTHEUR.csv" );
		} 
		
		catch (IOException e1) {

			e1.printStackTrace();
		}
		
		Transaction recent = portfolio.recentTransaction(selectedAccount);
		return recent;
	}
	
	public boolean errorCheck() {	
		
		if(!(checkSelectionAndAmount())) {

			return false;
		}
		
		else {
			
			return transactionChoiceAmountError();
		}
	}
	
	public boolean checkSelectionAndAmount() {
		
		if(accountSelect.getSelectionModel().isEmpty()) {
			
			transactionChoiceAmountErrorNotification("select an account");
			return false;
		}
		
		if(transactionChoiceAmount.getText().equals("")) {
			
			transactionChoiceAmountErrorNotification("Blank transaction amount field");
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	public boolean transactionChoiceAmountError() {
		
		if(!(isNumeric(transactionChoiceAmount.getText()))){
			
			transactionChoiceAmountErrorNotification("Enter a numerical value in the transaction amount field");
			return false;
		}
		
		if((Double.parseDouble(transactionChoiceAmount.getText()) <= 0)){
			
			transactionChoiceAmountErrorNotification("Enter a value greater than 0");
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	public void transactionChoiceAmountErrorNotification(String message) {
		
		errorLabelMessage(message);
		coinTransactionChoice.setValue(null);
	}
	
	public boolean isNumeric(String transactionAmount) {
		
		try {
			
			double selectedTransactionAmount = Double.parseDouble(transactionAmount);
		}
		
		catch(NumberFormatException e){
			
			return false;
		}
		
		return true;
	}
	
	public void coinTransaction(String selectedAccount) {
				
		//getting most recent add or remove coin transaction
		
		Transaction recent = portfolio.recentTransaction(selectedAccount);
		
		//last balance is now current balance and current balance is new balance
		//updating labels based on the user's account selection
		
		updateLabels(selectedAccount, recent);

		//update list view
		
		updateListView(selectedAccount);
	}
	
	public void updateLabels(String selectedAccount, Transaction recent) {
		
		//update top label
		
		switch (selectedAccount) {
		
			case "bTCUSD":
				
				topCryptoCurrencyLabel.setText("BitCoin USD");
				break;
				
			case "bTCEUR":
				
				topCryptoCurrencyLabel.setText("BitCoin EUR");
				break;
				
			case "eTHUSD":
				
				topCryptoCurrencyLabel.setText("Ethereum USD");
				break;
				
			case "eTHEUR":
				
				topCryptoCurrencyLabel.setText("Ethereum EUR");
				break;
			
			default:
				
				topCryptoCurrencyLabel.setText("BitCoin USD");
				break;
		}
		
		topCryptoCurrencyLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		
		//update balance labels
		
		availableBalanceAmountLabel.setText("$" + Double.toString(recent.getCurrentBalance()));
		lastBalanceAmountLabel.setText("$" + Double.toString(recent.getPreviousBalance()));
	}
	
	public void updateListView(String selectedAccount) {
		
		clearViewItems();
		
		ArrayList<Transaction> transactions = portfolio.getAccountInfo().get(selectedAccount);
		
		int length = portfolio.getAccountInfo().get(selectedAccount).size() - 1;
		
		for(int i = length; i >= 0; i--) {
			
			items.add(transactions.get(i).toString());
		}
				
		listView.getItems().addAll(items);	
	}
	
	public void saveUpdatedFile(String selectedAccount) {
		
		try {
			
			portfolio.save(selectedAccount);
		} 
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void errorLabelMessage(String message) {
		
		String errorResponse = message;
		errorLabel.setText(errorResponse);
		errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		errorLabel.setTextFill(Color.RED);
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
			Parent root = FXMLLoader.load(getClass().getResource("../view/AboutUs.fxml"));
			System.out.println("Loading Personnel Scene");			
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
