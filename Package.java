import java.io.Serializable;

// this serializable class sets up the types of packages that will be sent: user, event, and message
// these classes will always have a string identifier, to identify the contents when being received

public class Package implements Serializable {
	private static final long serialVersionUID = 1L;
	private String identifier;
	private boolean isValid;
	
	public Package(String identifier) {
		this.identifier = identifier;
	}
	
	public String identifier() {
		return identifier;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
