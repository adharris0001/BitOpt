package application.model;

import java.util.ArrayList;

/**
 * This is a class that models the real world crypto-currency market and initializes
 * a dataset based upon this model.
 * 
 * @author Conor Wallace (bhd445)
 * UTSA CS 3443 - Lab 2
 * Spring 2019
 */
public class BitCoin {
	private double computeCapability;
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
		this.computeCapability = 0.0;
		this.currentBTCUSDPrice = 0.0;
		this.currentBTCEURPrice = 0.0;
		this.currentETHUSDPrice = 0.0;
		this.currentETHEURPrice = 0.0;
		this.btcusdPrices = new ArrayList<Double>();
		this.btceurPrices = new ArrayList<Double>();
		this.ethusdPrices = new ArrayList<Double>();
		this.etheurPrices = new ArrayList<Double>();
	}
	
	/**
	 * purpose - Models the real world dynamics of the crypto-currency market by
	 * setting a range of values for both bitcoin and ethereum that represents the total range of
	 * values of both markets of all time. Once these boundaries are set, a second set of boundaries are randomly
	 * generated within the first boundary to represent the fluctuation of the market over time.
	 */
	public void generateData() {
		//Generate BTCUSD Data
		// define the total range for all time
		int btcusd_upper_max = 5000; 
        int btcusd_upper_min = 4000;
        int btcusd_lower_max = 3000;
		int btcusd_lower_min = 2000; 
        int btcusd_upper_range = btcusd_upper_max - btcusd_upper_min + 1;
        int btcusd_lower_range = btcusd_lower_max - btcusd_lower_min + 1;
        
        //define the inner boundary for the current market
        int bitusd_max = (int)(Math.random() * btcusd_upper_range) + btcusd_upper_min;
        int bitusd_min = (int)(Math.random() * btcusd_lower_range) + btcusd_lower_min;
        int bitusd_range = bitusd_max - bitusd_min + 1;
        
        // generate data for every hour for the past year 
        for (int i = 0; i < 87600; i++) { 
        	double btcusd_rand = (double)(Math.random() * bitusd_range) + bitusd_min; 
            this.btcusdPrices.add(btcusd_rand);
             
            // Output is different everytime this code is executed 
            System.out.println("$" + String.format("%4.2f", btcusd_rand)); 
        }
        
        this.currentBTCUSDPrice = this.btcusdPrices.get(this.btcusdPrices.size()-1);
        
        //Generate BITEUR Data
        // define the total range for all time
        //Exchange rate from BTCUSD to BTCEUR is roughly 89%.
        double usd2eurRate = 0.89;
		int btceur_upper_max = (int)(usd2eurRate * btcusd_upper_max); 
        int btceur_upper_min = (int)(usd2eurRate * btcusd_upper_min);
        int btceur_lower_max = (int)(usd2eurRate * btcusd_lower_max);
		int btceur_lower_min = (int)(usd2eurRate * btcusd_lower_min); 
        int btceur_upper_range = btceur_upper_max - btceur_upper_min + 1;
        int btceur_lower_range = btceur_lower_max - btceur_lower_min + 1;
        
        //define the inner boundary for the current market
        int biteur_max = (int)(Math.random() * btceur_upper_range) + btceur_upper_min;
        int biteur_min = (int)(Math.random() * btceur_lower_range) + btceur_lower_min;
        int biteur_range = biteur_max - biteur_min + 1;
        
        // generate data for every hour for the past year 
        for (int i = 0; i < 87600; i++) { 
        	double btceur_rand = (double)(Math.random() * biteur_range) + biteur_min; 
            this.btceurPrices.add(btceur_rand);
             
            // Output is different everytime this code is executed 
            System.out.println("$" + String.format("%4.2f", btceur_rand)); 
        }
        
        this.currentBTCEURPrice = this.btceurPrices.get(this.btceurPrices.size()-1);
        
		//Generate ETHUSD Data
        // define the total range for all time
		int ethusd_upper_max = 500; 
        int ethusd_upper_min = 400;
        int ethusd_lower_max = 150;
		int ethusd_lower_min = 100; 
        int ethusd_upper_range = ethusd_upper_max - ethusd_upper_min + 1;
        int ethusd_lower_range = ethusd_lower_max - ethusd_lower_min + 1;
        
        //define the inner boundary for the current market
        int ethusd_max = (int)(Math.random() * ethusd_upper_range) + ethusd_upper_min;
        int ethusd_min = (int)(Math.random() * ethusd_lower_range) + ethusd_lower_min;
        int ethusd_range = ethusd_max - ethusd_min + 1;
        
        // generate data for every hour for the past year 
        for (int i = 0; i < 87600; i++) { 
        	double ethusd_rand = (double)(Math.random() * ethusd_range) + ethusd_min; 
            this.ethusdPrices.add(ethusd_rand);
             
            // Output is different everytime this code is executed 
            System.out.println("$" + String.format("%4.2f", ethusd_rand)); 
        }
        
        this.currentETHUSDPrice = this.ethusdPrices.get(this.ethusdPrices.size()-1);
        
        //Generate BITEUR Data
        // define the total range for all time
        //Exchange rate from ETHUSD to ETHEUR is roughly 89%.
		int etheur_upper_max = (int)(usd2eurRate * ethusd_upper_max); 
        int etheur_upper_min = (int)(usd2eurRate * ethusd_upper_min);
        int etheur_lower_max = (int)(usd2eurRate * ethusd_lower_max);
		int etheur_lower_min = (int)(usd2eurRate * ethusd_lower_min); 
        int etheur_upper_range = etheur_upper_max - etheur_upper_min + 1;
        int etheur_lower_range = etheur_lower_max - etheur_lower_min + 1;
        
        //define the inner boundary for the current market
        int etheur_max = (int)(Math.random() * etheur_upper_range) + etheur_upper_min;
        int etheur_min = (int)(Math.random() * etheur_lower_range) + etheur_lower_min;
        int etheur_range = etheur_max - etheur_min + 1;
        
        // generate data for every hour for the past year 
        for (int i = 0; i < 87600; i++) { 
        	double etheur_rand = (double)(Math.random() * etheur_range) + etheur_min; 
            this.etheurPrices.add(etheur_rand);
             
            // Output is different everytime this code is executed 
            System.out.println("$" + String.format("%4.2f", etheur_rand)); 
        }
        
        this.currentETHEURPrice = this.etheurPrices.get(this.etheurPrices.size()-1);
	}

	/**
	 * purpose - Compute the total trend of the crypto-currency market by computing the percent
	 * difference of each market (BTCUSD, BTCEUR, ETHUSD, ETHEUR) and then finding the average of the
	 * four differences
	 */
	public void optimize() {
		double btcusdSum = 0.0;
		double btcusdAverage = 0.0;
		double btceurSum = 0.0;
		double btceurAverage = 0.0;
		double ethusdSum = 0.0;
		double ethusdAverage = 0.0;
		double etheurSum = 0.0;
		double etheurAverage = 0.0;
		double btcusdOptimized = 0.0;
		double btceurOptimized = 0.0;
		double ethusdOptimized = 0.0;
		double etheurOptimized = 0.0;
		int i = 0;
		
		//find the sum of each market
		for(i = 0; i < this.btcusdPrices.size(); i++) {
			btcusdSum += this.btcusdPrices.get(i);
			btceurSum += this.btceurPrices.get(i);
			ethusdSum += this.ethusdPrices.get(i);
			etheurSum += this.etheurPrices.get(i);
		}
		
		//find the average each market 
		btcusdAverage = (btcusdSum / this.btcusdPrices.size());
		btceurAverage = (btceurSum / this.btceurPrices.size());
		ethusdAverage = (ethusdSum / this.ethusdPrices.size());
		etheurAverage = (etheurSum / this.etheurPrices.size());	
		
		//find the percent difference of each market
		btcusdOptimized = ((btcusdAverage - this.btcusdPrices.get(i-1)) / ((btcusdAverage + this.btcusdPrices.get(i-1))/2)) * 100;
		btceurOptimized = ((btceurAverage - this.btceurPrices.get(i-1)) / ((btceurAverage + this.btceurPrices.get(i-1))/2)) * 100;
		ethusdOptimized = ((ethusdAverage - this.ethusdPrices.get(i-1)) / ((ethusdAverage + this.ethusdPrices.get(i-1))/2)) * 100;
		etheurOptimized = ((etheurAverage - this.etheurPrices.get(i-1)) / ((etheurAverage + this.etheurPrices.get(i-1))/2)) * 100;
		
		//find the average of all four market differences
		double systemOptimized = ((btcusdOptimized + btceurOptimized + ethusdOptimized + etheurOptimized) / 4);
		
		System.out.println(systemOptimized);
		
		//adjust the power consumption given the trend in the market
		this.setComputeCapability(this.computeCapability + systemOptimized);
	}
	
	//getters and setters
	public double getComputeCapability() {
		return computeCapability;
	}

	public void setComputeCapability(double computeCapability) {
		this.computeCapability = computeCapability;
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