import java.io.Serializable;
import java.util.Vector;

// this serializable class sets up the types of packages that will be sent: user, event, and message
// these classes will always have a string identifier, to identify the contents when being received

public class Package implements Serializable {
	private static final long serialVersionUID = 1L;
//	private String identifier;
	
	private Vector<Event> events;
	
	// is valid attempt of any kind
	private boolean isValid = false;
	// login attempt
	private boolean isLogin = false;
	// signup attempt
	private boolean isSignup = false;
	// guest attempt
	private boolean isGuest = false;
	
	public Package() {
		setEvents(new Vector<Event>());
	}
	
	
	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public boolean isSignup() {
		return isSignup;
	}

	public void setSignup(boolean isSignup) {
		this.isSignup = isSignup;
	}

	public boolean isGuest() {
		return isGuest;
	}

	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}


	public Vector<Event> events() {
		return events;
	}


	public void setEvents(Vector<Event> events) {
		this.events = events;
	}


	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}



//	public Package(String identifier) {
//		this.identifier = identifier;
//	}
//	
//	public String identifier() {
//		return identifier;
//	}

}
