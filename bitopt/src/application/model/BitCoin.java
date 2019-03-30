package application.model;

import java.util.ArrayList;

public class BitCoin {
	private double currentBTCUSDPrice;
	private double currentBTCEURPrice;
	private double currentETHUSDPrice;
	private double currentETHEURPrice;
	private ArrayList<Double> btcusdPrices;
	private ArrayList<Double> btceurPrices;
	private ArrayList<Double> ethusdPrices;
	private ArrayList<Double> etheurPrices;
	
	public BitCoin() {
		super();
		this.currentBTCUSDPrice = 0.0;
		this.currentBTCEURPrice = 0.0;
		this.currentETHUSDPrice = 0.0;
		this.currentETHEURPrice = 0.0;
		this.btcusdPrices = new ArrayList<Double>();
		this.btceurPrices = new ArrayList<Double>();
		this.ethusdPrices = new ArrayList<Double>();
		this.etheurPrices = new ArrayList<Double>();
	}
	
	public void generateData() {
		//Generate BTCUSD Data
		// define the range 
		int upper_max = 5000; 
        int upper_min = 4000;
        int lower_max = 3000;
		int lower_min = 2000; 
        int upper_range = upper_max - upper_min + 1;
        int lower_range = lower_max - lower_min + 1;
        
        int bitusd_max = (int)(Math.random() * upper_range) + upper_min;
        int bitusd_min = (int)(Math.random() * lower_range) + lower_min;
        int bitusd_range = bitusd_max - bitusd_min + 1;
        
        // generate random numbers within 1 to 10 
        for (int i = 0; i < 87600; i++) { 
        	double rand = (double)(Math.random() * bitusd_range) + bitusd_min; 
            this.btcusdPrices.add(rand);
             
            // Output is different everytime this code is executed 
            System.out.println("$" + String.format("%4.2f", rand)); 
        }
        
        this.currentBTCUSDPrice = this.btcusdPrices.get(this.btcusdPrices.size()-1);
	}

	public double getCurrentBTCUSDPrice() {
		return currentBTCUSDPrice;
	}

	public void setCurrentBTCUSDPrice(double currentBTCUSDPrice) {
		this.currentBTCUSDPrice = currentBTCUSDPrice;
	}

	public double getCurrentBTCEURPrice() {
		return currentBTCEURPrice;
	}

	public void setCurrentBTCEURPrice(double currentBTCEURPrice) {
		this.currentBTCEURPrice = currentBTCEURPrice;
	}

	public double getCurrentETHUSDPrice() {
		return currentETHUSDPrice;
	}

	public void setCurrentETHUSDPrice(double currentETHUSDPrice) {
		this.currentETHUSDPrice = currentETHUSDPrice;
	}

	public double getCurrentETHEURPrice() {
		return currentETHEURPrice;
	}

	public void setCurrentETHEURPrice(double currentETHEURPrice) {
		this.currentETHEURPrice = currentETHEURPrice;
	}

	public ArrayList<Double> getBtcusdPrices() {
		return btcusdPrices;
	}

	public void setBtcusdPrices(ArrayList<Double> btcusdPrices) {
		this.btcusdPrices = btcusdPrices;
	}

	public ArrayList<Double> getBtceurPrices() {
		return btceurPrices;
	}

	public void setBtceurPrices(ArrayList<Double> btceurPrices) {
		this.btceurPrices = btceurPrices;
	}

	public ArrayList<Double> getEthusdPrices() {
		return ethusdPrices;
	}

	public void setEthusdPrices(ArrayList<Double> ethusdPrices) {
		this.ethusdPrices = ethusdPrices;
	}

	public ArrayList<Double> getEtheurPrices() {
		return etheurPrices;
	}

	public void setEtheurPrices(ArrayList<Double> etheurPrices) {
		this.etheurPrices = etheurPrices;
	}
}