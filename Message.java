import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String message;
	
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
	
}
