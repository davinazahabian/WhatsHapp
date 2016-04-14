package Model;
import java.io.Serializable;
import java.util.Vector;

// this serializable class sets up the types of packages that will be sent: user, event, and message
// these classes will always have a string identifier, to identify the contents when being received

public class InfoPackage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private User user = null;
	private Event event = null;
	private Message message = null;
	
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
	// get certain category of events
	private boolean isGettingSports = false;
	private boolean isGettingCareer = false;
	private boolean isGettingCultural = false;
	private boolean isGettingClub = false;
	// post message to a message board
	private boolean isPostingMessage = false;
	// upvoting event
	private boolean isUpvoting = false;
	// adding attendee to event
	private boolean isAddingAttendee = false;
	
	private String userName;
	private String passWord;
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

	public boolean isGettingSports() {
		return isGettingSports;
	}

	public void setGettingSports(boolean isGettingSports) {
		this.isGettingSports = isGettingSports;
	}

	public boolean isGettingCareer() {
		return isGettingCareer;
	}

	public void setGettingCareer(boolean isGettingCareer) {
		this.isGettingCareer = isGettingCareer;
	}

	public boolean isGettingCultural() {
		return isGettingCultural;
	}

	public void setGettingCultural(boolean isGettingCultural) {
		this.isGettingCultural = isGettingCultural;
	}

	public boolean isGettingClub() {
		return isGettingClub;
	}

	public void setGettingClub(boolean isGettingClub) {
		this.isGettingClub = isGettingClub;
	}

	public boolean isPostingMessage() {
		return isPostingMessage;
	}

	public void setPostingMessage(boolean isPostingMessage) {
		this.isPostingMessage = isPostingMessage;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isUpvoting() {
		return isUpvoting;
	}

	public void setUpvoting(boolean isUpvoting) {
		this.isUpvoting = isUpvoting;
	}

	public boolean isAddingAttendee() {
		return isAddingAttendee;
	}

	public void setAddingAttendee(boolean isAddingAttendee) {
		this.isAddingAttendee = isAddingAttendee;
	}
	
	
}
