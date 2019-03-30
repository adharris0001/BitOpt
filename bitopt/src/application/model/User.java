package application.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class User {
	private String username;
	
	public User(String username) {
		super();
		this.username = username;
	}
	
	public static User validate(String attemptName, String attemptPassword) throws IOException {
		User user = null;
		try {
			// open personnel file for reading
			Scanner userScan = new Scanner( new File("userData/users.csv"));
			
			// read in, line by line, creating employees
			while( userScan.hasNextLine() ) {
				String line = userScan.nextLine();
				String[] tokens = line.split(",");
				String userName = tokens[0];
				String userPassword = tokens[1];
				
				System.out.println(userName + " " + userPassword);
				System.out.println(attemptName + " " + attemptPassword);
				
				if(attemptName.equals(userName) && attemptPassword.equals(userPassword)) {
					user = new User(attemptName);
					System.out.println("Setting new user");
				}
				else {
					System.out.println("Username or Password is incorrect.");
				}
			}
			// close the file!
			userScan.close();
		}catch( IOException e ) {
			e.printStackTrace();
		}
		
		return user;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
