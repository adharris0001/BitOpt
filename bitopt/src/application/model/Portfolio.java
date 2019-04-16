/**
 * @author Anthony D. Harris xxg795
 *
 */
package application.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Portfolio {
	
	private String name;
	
	//creating a hash map: the key will be a String of crypto coin name, the values will be an ArrayList of transactions
	
	private Map<String, ArrayList<Transaction>> accountInfo;
	
	public Portfolio(String name) {
		
		this.name = name;
		
		//loading the keys to the map
		
		loadCoinName();
		this.accountInfo = new HashMap<String, ArrayList<Transaction>>();
	}
	
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
	
	public void loadCoinName(){
	
		//We are only using four different currencies. Each one will be keys to the values in the map
		
		ArrayList<String> cryptoCurrencyNames = new ArrayList<String>();
		cryptoCurrencyNames.addAll(Arrays.asList("bTCUSD", "bTCEURO", "eTHUSD", "eTHEUR"));
		
		for(String name : cryptoCurrencyNames) {
			
			this.accountInfo.put(name, new ArrayList<Transaction>());
		}
	}
	
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

	public void save(String coinType) throws IOException{
		
		try {

			FileWriter Writer = new FileWriter(new File("portfolioData/" + coinType + ".csv"));
					
			String line = "";
			
			//for a given key, saving all the transactions back to a .csv file
			
			for(Transaction transaction : this.accountInfo.get(coinType)) {

				line = transactionCSV(transaction);
				Writer.write(line);
			}
			
			Writer.close();
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public String transactionCSV(Transaction transaction) {
		
		String ret = "";
		ret += transaction.getDate() + ",";
		ret += transaction.getTransactionType() + ",";
		ret += Double.toString(transaction.getTransactionChange()) + ",";
		ret += Double.toString(transaction.getCurrentBalance()) + ",";
		ret += Double.toString(transaction.getPreviousBalance()) + ",";
		return ret;
	}
	
	public void addCoin(String coinType, double coin) {
		
		int length = 0;
		Transaction transaction = new Transaction("", "", 0.0, 0.0, 0.0);
		
		//for a given key, performing a deposit (credit) of a certain coin amount, then adding the transaction to the map
		
		for(String name : this.accountInfo.keySet()) {
			
			if(name.equals(coinType)){
				
				length = this.accountInfo.get(name).size();
				transaction = this.accountInfo.get(name).get(length - 1);
				transaction.setPreviousBalance(transaction.getCurrentBalance());
				transaction.setCurrentBalance(transaction.getCurrentBalance() + coin);
				transaction.setTransactionChange(coin);
				transaction.setTransactionType("credit");
				transaction.setDate("4/21/2019");
				this.accountInfo.get(name).add(transaction);
			}
		}
	}
	
	public boolean removeCoin(String coinType, double coin) {
		
		int length = 0;
		Transaction transaction = new Transaction("", "", 0.0, 0.0, 0.0);
		
		//for a given key, performing a withdrawal (debit) of a certain coin amount, then adding the transaction to the map
		
		for(String name : this.accountInfo.keySet()) {
			
			if(name.equals(coinType)){
				
				length = this.accountInfo.get(name).size();
				transaction = this.accountInfo.get(name).get(length - 1);
				
				//can't withdraw more than what you have in the bank
				//note: controller should take the "false" return and display an error message to the view
				
				if((transaction.getCurrentBalance() - coin) < 0) {
					
					return false;
				}
				
				//if you have enough in the bank, then continue with the withdrawal and add transaction to the map
				
				else {
					
					transaction.setPreviousBalance(transaction.getCurrentBalance());
					transaction.setCurrentBalance(transaction.getCurrentBalance() - coin);
					transaction.setTransactionChange(coin);
					transaction.setTransactionType("debit");
					transaction.setDate("4/21/2019");
					this.accountInfo.get(name).add(transaction);
				}
			}			
		}
		
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ArrayList<Transaction>> getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(Map<String, ArrayList<Transaction>> accountInfo) {
		this.accountInfo = accountInfo;
	}

}
