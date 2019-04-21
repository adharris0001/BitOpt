package application.model;

public class Transaction {

	private String date;
	private String transactionType;
	private double transactionChange;
	private double currentBalance;
	private double previousBalance;
	
	public Transaction(String date, String transactionType, double transactionChange, double currentBalance, double previousBalance) {
		
		this.date = date;
		this.transactionType = transactionType;
		this.transactionChange = transactionChange;
		this.currentBalance = currentBalance;
		this.previousBalance = previousBalance;
	}
	
	public String toString() {
		
		String ret = "";
		
		ret = this.date + "\t\t\t" + this.transactionType + "\t\t\t" + this.transactionChange + "\t\t\t\t" +  this.currentBalance;
		
		return ret;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionChange() {
		return transactionChange;
	}

	public void setTransactionChange(double transactionChange) {
		this.transactionChange = transactionChange;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(double previousBalance) {
		this.previousBalance = previousBalance;
	}
}
