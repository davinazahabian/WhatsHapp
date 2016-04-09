
public class Message extends Package {

	private static final long serialVersionUID = 1L;
	private String username;
	private String message;
	
	// constructor:
	public Message(String username, String message) {
		super("message");
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
