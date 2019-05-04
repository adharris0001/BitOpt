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
 * The Portfolio controller gives the user access to all of their account information
 * A logout button and access to the other views are displayed at the top
 * Balances are shown at the top of the view dedicated to Portfolio
 * In the middle of the screen, the user is able to select their crypto currency as well as their desired action, deposit (addCoin) or withdraw (removeCoin)
 * A list view at the bottom shows all transaction history pulled from a .csv file, with each file individually linked to their respective currencies
 * Error messages are displayed for any case in which a user deviates from standard or expected input
 * @author Blake Powell vnh034
 * @author Anthony Harris xxg795
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
	
	/**
	 * When an account is selected from the dropdown menu, the view is updated with values corresponding to that account
	 * @param event - when the account select combo box is initialized to a value by the user, this event is triggered
	 */
	public void handleAccountSelect(ActionEvent event){
		
		//clearing the transaction choice amount text field for possible new entries
		
		transactionChoiceAmount.clear();
		errorLabel.setText("");
		
		//getting the String name of the account that the user is choosing
		//displaying the view of all the values and labels with respect to the account selection
		
		accountSelectView(accountSelect.getValue());
	}
	
	/**
	 * When choosing a transaction type, either addCoin or removeCoin, the respective method is called for the selected account
	 * @param event - when the coin transaction choice combo box is initialized to a value by the user, this event is triggered
	 */
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
	
	/**
	 * Set background color and text
	 */
	public void backgroundDisplay() {
		
		panel.setStyle("-fx-background-color: #8c8c8c;");
		hbox.setStyle("-fx-background-color: #000000;");
		label.setText("Welcome to BitOpt");
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("Cambria", 34));
		label.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Adding all options to the two dropdown boxes present in the portfolio view
	 */
	public void initializeDropDowns() {
		
		accountSelect.getItems().addAll("bTCUSD","bTCEUR","eTHUSD","eTHEUR");
		coinTransactionChoice.getItems().addAll("addCoin","removeCoin");
	}
	
	/**
	 * This method calls other methods which will update the view for the selected account
	 * @param selectedAccount - currently selected currency account
	 */
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
	
	/**
	 * Clear transaction information from the list when a new account is selected
	 */
	public void clearViewItems() {
		
		items.clear();
		listView.getItems().clear();
	}
	
	/**
	 * This method loads all transaction histories preemptively to their respective account views
	 * @param selectedAccount - currently selected currency account
	 * @return the most recent transaction in the selected account
	 * @throws IOException - If the file passed is missing or inaccessible, an exception is thrown
	 */
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
	
	/**
	 * This method will check for general error, and otherwise will call a more specific error check with suitable error messages
	 * @return boolean value based on the result of error checks when validating user input
	 */
	public boolean errorCheck() {	
		
		//checking if the user selected an account and entered a valid amount
		if(!(checkSelectionAndAmount())) {

			return false;
		}
		
		else {
			
			//true if user entered a double value for the transaction amount; false otherwise
			return transactionChoiceAmountError();
		}
	}
	
	/**
	 * This method checks for errors in account selection and errors in transaction amount entries
	 * @return boolean value based on the result of error checks when validating user input
	 */
	public boolean checkSelectionAndAmount() {
		
		//checks if user chose an account selection
		if(accountSelect.getSelectionModel().isEmpty()) {
			
			transactionChoiceAmountErrorNotification("select an account");
			return false;
		}
		
		//checks to see if user entered something in the transaction amount field
		if(transactionChoiceAmount.getText().equals("")) {
			
			transactionChoiceAmountErrorNotification("Blank transaction amount field");
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	/**
	 * This method is ensuring values used in an amount to deposit or withdraw from an account are valid input
	 * >>	i.e. text rather than numerical values or values that would put an account in the negative 
	 * @return boolean value based on the result of error checks when validating user input
	 */
	public boolean transactionChoiceAmountError() {
		
		//if the user's entry is not a numeric value return false
		if(!(isNumeric(transactionChoiceAmount.getText()))){
			
			transactionChoiceAmountErrorNotification("Enter a numerical value in the transaction amount field");
			return false;
		}
		
		//won't allow negative or zero value transactions
		if((Double.parseDouble(transactionChoiceAmount.getText()) <= 0)){
			
			transactionChoiceAmountErrorNotification("Enter a value greater than 0");
			return false;
		}
		
		else {
			
			return true;
		}
	}
	
	/**
	 * Sets the error message for the user when invalid input is given to the application
	 * @param message - the error that is passed from prior checks to be displayed
	 */
	public void transactionChoiceAmountErrorNotification(String message) {
		
		errorLabelMessage(message);
		coinTransactionChoice.setValue(null);
	}
	
	/**
	 * This method is checking for numeric input by the user
	 * @param transactionAmount - the user input for amount to be deposited or withdrawn
	 * @return boolean value based on the result of error checks when validating user input
	 * @throws NumberFormatException - exception is thrown when value passed cannot be converted from a string into a numerical value
	 */
	public boolean isNumeric(String transactionAmount) {
		
		//if amount given is unable to be converted to type Double, false is returned to transactionChoiceAmountError and an error is displayed
		try {
			
			double selectedTransactionAmount = Double.parseDouble(transactionAmount);
		}
		
		catch(NumberFormatException e){
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is called when a deposit or withdrawal is made by the addCoin or removeCoin methods respectively
	 * >>	it allows the application to update all relevant information such as balance and listed transaction history
	 * @param selectedAccount - currently selected currency account
	 */
	public void coinTransaction(String selectedAccount) {
				
		//getting most recent add or remove coin transaction
		
		Transaction recent = portfolio.recentTransaction(selectedAccount);
		
		//last balance is now current balance and current balance is new balance
		//updating labels based on the user's account selection
		
		updateLabels(selectedAccount, recent);

		//update list view
		
		updateListView(selectedAccount);
	}
	
	/**
	 * Update all labels with relevant information such as balance and selected currency
	 * @param selectedAccount - currently selected currency account
	 * @param recent - most recent transaction for the selected currency
	 */
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
	
	/**
	 * Updates list view of transaction history for the current account
	 * @param selectedAccount - currently selected currency account
	 */
	public void updateListView(String selectedAccount) {
		
		clearViewItems();
		
		ArrayList<Transaction> transactions = portfolio.getAccountInfo().get(selectedAccount);
		
		int length = portfolio.getAccountInfo().get(selectedAccount).size() - 1;
		
		//populate list from end of transaction history to beginning in order to show most recent transactions at the top 
		for(int i = length; i >= 0; i--) {
			
			items.add(transactions.get(i).toString());
		}
				
		listView.getItems().addAll(items);	
	}
	
	/**
	 * Save changes made in transactions to the corresponding .csv file
	 * @param selectedAccount - currently selected currency account
	 * @throws IOException - If the file passed is missing or inaccessible, an exception is thrown
	 */
	public void saveUpdatedFile(String selectedAccount) {
		
		try {
			
			portfolio.save(selectedAccount);
		} 
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes and displays an error message to the user based on failed checks
	 * @param message - error to be displayed to the user
	 */
	public void errorLabelMessage(String message) {
		
		String errorResponse = message;
		errorLabel.setText(errorResponse);
		errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		errorLabel.setTextFill(Color.RED);
	}
	
	@Override
	/**
	 * Return to Login view when logout button is pressed
	 */
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
	 * Load to Home view when Home is pressed
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
	 * Load to System view when System is pressed
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
	 * Load to Portfolio view when Portfolio is pressed
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
	 * Load to Chart view when Chart is pressed
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
