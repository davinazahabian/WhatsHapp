package Model;

public class Message {

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
