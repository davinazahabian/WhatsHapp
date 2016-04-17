package Model;
import java.io.Serializable;
import java.util.Vector;

public class User extends InfoPackage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private Vector<Event> eventsCreated;
	private Vector<Event> eventsAttending;
	
	// constructor:
	public User(String fname, String lname, String email, String username, String password) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	// getters:
	public String username() {
		return username;
	}
	
	public String password() {
		return password;
	}
	
	public String fname() {
		return fname;
	}
	
	public String lname() {
		return lname;
	}
	
	public String email() {
		return email;
	}
	
	public Vector<Event> eventsCreated() {
		return eventsCreated;
	}
	
	public Vector<Event> eventsAttending() {
		return eventsAttending;
	}
	
	// setters:
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setEventsCreated(Vector<Event> events) {
		this.eventsCreated = events;
	}
	
	public void setEventsAttending(Vector<Event> events) {
		this.eventsAttending = events;
	}

	// other methods:
//	private int hashPassword(String password) {
//		// TODO
//	}
	
	public void addEventCreated(Event newEvent) {
		eventsCreated.add(newEvent);
	}
	
	public void addEventAttending(Event newEvent) {
		eventsAttending.add(newEvent);
	}
	
}
