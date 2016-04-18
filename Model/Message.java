package Model;

import java.io.Serializable;

/*
 * 
 * Message - contains the username and message of the user who sent the message, to be posted in the
 * message board of a EventDetailGUI
 * 
 */

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String message;
	private boolean isSuccessful = false;
	
	// constructor:
	public Message(String username, String message) {
		this.username = username;
		this.message = message;
	}
	
	// getters:
	public String username() {
		return username;
	}
	
	public String message() {
		return message;
	}
	
	public boolean isSuccessful() {
		return isSuccessful;
	}
	
	// setters:
	public void setSuccessful(boolean success) {
		this.isSuccessful = success;
	}
	
}
