/**
 * 
 */
package application.model;

import java.util.ArrayList;

/**
 * @author User
 *
 */

//Will use generics once I figure out how to

public class Portfolio {

	private double currentBTCUSDPrice;
	private double currentBTCEURPrice;
	private double currentETHUSDPrice;
	private double currentETHEURPrice;
	
	public Portfolio() {
		
		this.currentBTCUSDPrice = 0.0;
		this.currentBTCEURPrice = 0.0;
		this.currentETHUSDPrice = 0.0;
		this.currentETHEURPrice = 0.0;
	}
	
	public String toString() {
		
		String ret = "";
		return ret;
	}
	
	public void addCoin(double coin) {
		
		//add coin to this.whatever coin
		//show new value
	}
	
	public void removeCoin(double coin) {
		
		//remove coin from this.whatever coin
		//show new value
	}
	
	/*public <genericCoin> showCoin(<genericCoin> coin){
		
		//return this.whatevercoin
	}*/

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
}
