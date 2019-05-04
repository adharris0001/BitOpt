package application.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to select the current user and validate their user account. 
 * 
 * @author Conor Wallace (bhd445)
 * UTSA CS 3443 - Lab 2
 * Spring 2019
 */
public class User {
	private String username;
	
	public User(String username) {
		super();
		this.username = username;
	}
	
	/**
	 * Result - boolean value to check whether or not the requested account is valid
	 * 
	 * @param attemptName - user's attempted username
	 * @param attemptPassword - user's attempted password
	 */
	public boolean validate(String attemptName, String attemptPassword) throws IOException {
		boolean isValidUser = false;
		try {
			// open user file for reading
			Scanner userScan = new Scanner( new File("userData/users.csv"));
			
			// read in, line by line, checking user login credentials
			while( userScan.hasNextLine() ) {
				String line = userScan.nextLine();
				String[] tokens = line.split(",");
				String userName = tokens[0];
				String userPassword = tokens[1];
				
				System.out.println(userName + " " + userPassword);
				System.out.println(attemptName + " " + attemptPassword);
				
				//check to see if this account matches the requested account
				if(attemptName.equals(userName) && attemptPassword.equals(userPassword)) {
					isValidUser = true;
					System.out.println("Setting new user");
				}
				else {
					System.out.println("Username or Password is Doesn't Match.");
				}
			}
			// close the file!
			userScan.close();
		}catch( IOException e ) {
			e.printStackTrace();
		}
		
		//if a match occurred return true, otherwise return false
		return isValidUser;
	}

	//getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
