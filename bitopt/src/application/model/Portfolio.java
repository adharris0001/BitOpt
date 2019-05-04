package application.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Portfolio class holds transaction data in an account based on its respective cryptocurrency
 * @author Blake Powell vnh034
 * @author Anthony Harris xxg795
 *
 */
public class Portfolio {
	
	private String name;
	
	//creating a hash map: the key will be a String of crypto coin name, the values will be an ArrayList of transactions
	
	private Map<String, ArrayList<Transaction>> accountInfo;
	
	/**
	 * @param name - name is passed when the portfolio is created
	 */
	public Portfolio(String name) {
		
		this.name = name;
		this.accountInfo = new HashMap<String, ArrayList<Transaction>>();
		loadCoinName();
	}
	
	/**
	 * @return String representation of portfolio
	 */
	public String toString() {
		
		String ret = "";
		
		for(String coinName : this.accountInfo.keySet()) {
			
			ret += coinName + "\n";
						
			for(Transaction transaction : this.accountInfo.get(coinName)) {
				
				ret += transaction.toString() + "\n";
			}
		}
		
		return ret;
	}
	
	/**
	 * This method adds each currency to the portfolio account
	 */
	public void loadCoinName(){
	
		//We are only using four different currencies. Each one will be keys to the values in the map
		
		ArrayList<String> cryptoCurrencyNames = new ArrayList<String>();
		cryptoCurrencyNames.addAll(Arrays.asList("bTCUSD", "bTCEUR", "eTHUSD", "eTHEUR"));
		
		for(String name : cryptoCurrencyNames) {
			
			this.accountInfo.put(name, new ArrayList<Transaction>());
		}
	}
	
	/**
	 * loadTransactions will pull all transaction history from the .csv file of the user selected currency account
	 * @param userSelection -  A String value of the user's account selection
	 * @param file - Name of the file which will populate with selected currency transaction history
	 * @throws IOException - If the file passed is missing or inaccessible, an exception is thrown
	 */
	public void loadTransactions(String userSelection, String file) throws IOException {
		
		try {
			
			File f = new File(file);
			Scanner scan = new Scanner(f);
			
			//looking at a specific .csv file, pulling data from the file, setting that data to Transaction variables, adding the transactions to the map
			
			while(scan.hasNextLine()) {
				
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				Transaction transaction = new Transaction(tokens [0], tokens [1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]));

				for(String coinName : this.accountInfo.keySet()) {
					
					if(coinName.equals(userSelection)) {
						
						this.accountInfo.get(coinName).add(transaction);
					}
				}
			}
			
			scan.close();
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * This method saves all new transactions by appending them to the end of the file corresponding to the selected currency
	 * @param coinType - The type of currency is passed in order to save new transaction data to it's individual .csv file 
	 * @throws IOException - If the file passed is missing or inaccessible, an exception is thrown
	 */
	public void save(String coinType) throws IOException{
		
		try {

			FileWriter Writer = new FileWriter(new File("portfolioData/" + coinType + ".csv"), true);
					
			String line = "";
			//get the most recent transaction to have occured and add to the file
			Transaction recent = recentTransaction(coinType);
			line = transactionCSV(recent);
			Writer.append(line);
			Writer.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * This method gets and formats all information from a recent transaction
	 * @param transaction - the transaction which will be written to the .csv file
	 * @return - String representation of a transaction
	 */
	public String transactionCSV(Transaction transaction) {
		
		String ret = "";
		ret += transaction.getDate() + ",";
		ret += transaction.getTransactionType() + ",";
		ret += Double.toString(transaction.getTransactionChange()) + ",";
		ret += Double.toString(transaction.getCurrentBalance()) + ",";
		ret += Double.toString(transaction.getPreviousBalance()) + "\n";
		return ret;
	}
	
	/**
	 * This method will add an amount from user input to their selected currency
	 * @param coinType - the type of currency is passed so that the method will know which currency to add to
	 * @param coin - the value which is to be added to the account
	 * @throws IOException - If the file for the coin type passed is missing or inaccessible, an exception is thrown
	 */
	public void addCoin(String coinType, double coin) {
		
		int length = 0;
		Transaction transaction = new Transaction("", "", 0.0, 0.0, 0.0);
		Transaction recent = recentTransaction(coinType);
		
		//for a given key, performing a deposit (credit) of a certain coin amount, then adding the transaction to the map
				
		transaction.setPreviousBalance(recent.getCurrentBalance());
		transaction.setCurrentBalance(recent.getCurrentBalance() + coin);
		transaction.setTransactionChange(coin);
		transaction.setTransactionType("credit");
		transaction.setDate("4/21/2019");
		this.accountInfo.get(coinType).add(transaction);

		try {
			
			save(coinType);
		} 
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * This method subtracts an amount from user input out of the selected currency
	 * @param coinType - the type of currency is passed so that the method will know which currency to subtract from
	 * @param coin - the value which is to be subtracted from the account
	 * @return will return true if withdrawal is successful and false if it would cause an error
	 * @throws IOException - If the file for the coin type passed is missing or inaccessible, an exception is thrown
	 */
	public boolean removeCoin(String coinType, double coin) {
		
		int length = 0;
		Transaction transaction = new Transaction("", "", 0.0, 0.0, 0.0);
		Transaction recent = recentTransaction(coinType);
		
		//for a given key, performing a withdrawal (debit) of a certain coin amount, then adding the transaction to the map
		
		for(String name : this.accountInfo.keySet()) {
			
			if(name.equals(coinType)){
				
				//can't withdraw more than what you have in the bank
				//note: controller should take the "false" return and display an error message to the view
				
				if((recent.getCurrentBalance() - coin) < 0) {
					
					return false;
				}
				
				//if you have enough in the bank, then continue with the withdrawal and add transaction to the map
				
				else {
					
					transaction.setPreviousBalance(recent.getCurrentBalance());
					transaction.setCurrentBalance(recent.getCurrentBalance() - coin);
					transaction.setTransactionChange(coin);
					transaction.setTransactionType("debit");
					transaction.setDate("4/21/2019");
					this.accountInfo.get(name).add(transaction);
				}
			}			
		}
		
		try {
			save(coinType);
			
		} 
		
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * This method returns the most recent transaction in order to display relevant account data to the user
	 * @param coin - selected currency in the portfolio
	 * @return the most recent transaction of the selected currency
	 */
	public Transaction recentTransaction(String coin) {
		
		int lastIndex = this.accountInfo.get(coin).size() - 1;		
		return this.accountInfo.get(coin).get(lastIndex);	
	}

	/**
	 * return the name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * change the name
	 * @param name - new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * returns the account information
	 * @return account information
	 */
	public Map<String, ArrayList<Transaction>> getAccountInfo() {
		return accountInfo;
	}

	/**
	 * change the account information
	 * @param accountInfo - new account information
	 */
	public void setAccountInfo(Map<String, ArrayList<Transaction>> accountInfo) {
		this.accountInfo = accountInfo;
	}
}
