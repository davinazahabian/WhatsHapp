package Model;
import java.io.Serializable;
import java.util.Vector;

// this serializable class sets up the types of packages that will be sent: user, event, and message
// these classes will always have a string identifier, to identify the contents when being received

public class InfoPackage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private User user = null;
	private Event event = null;
	
	private Vector<Event> events;
	private String username = null; private String password = null;
	// is valid attempt of any kind
	private boolean isValid = false;
	// login attempt
	private boolean isLogin = false;
	// signup attempt
	private boolean isSignup = false;
	// guest attempt
	private boolean isGuest = false;
	// add new event attempt
	private boolean isNewEvent = false;
	
	public InfoPackage() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vector<Event> getEvents() {
		return events;
	}

	public void setEvents(Vector<Event> events) {
		this.events = events;
	}

	public boolean isNewEvent() {
		return isNewEvent;
	}

	public void setNewEvent(boolean isNewEvent) {
		this.isNewEvent = isNewEvent;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event newEvent) {
		this.event = newEvent;
	}
	
	
}
