
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

public class Event extends Package {
	// hi
	private static final long serialVersionUID = 1L;
	private String eventName;
	private String eventDate;
	private String eventTime;
	private String eventDesc;
	private String eventLoc;
	private Timestamp timePosted;
	private long milliseconds;
	private boolean sports;
	private boolean career;
	private boolean cultural;
	private boolean club;
	private int upvotes;
	private int attendees;
	private String messageBoard;
	
	public Event(String eventName, String eventDate, String eventTime, String eventDesc, String eventLoc,
			boolean sports, boolean career, boolean cultural, boolean club) {
		super();
		//super("event");
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventDesc = eventDesc;
		this.eventLoc = eventLoc;
		Date date = new Date();
		timePosted = new Timestamp(date.getTime());
		milliseconds = date.getTime();
		this.sports = sports;
		this.career = career;
		this.cultural = cultural;
		this.club = club;	
	}
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getEventLoc() {
		return eventLoc;
	}

	public void setEventLoc(String eventLoc) {
		this.eventLoc = eventLoc;
	}

	public Timestamp getTimePosted() {
		return timePosted;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

	public boolean isSports() {
		return sports;
	}

	public void setSports(boolean sports) {
		this.sports = sports;
	}

	public boolean isCareer() {
		return career;
	}

	public void setCareer(boolean career) {
		this.career = career;
	}

	public boolean isCultural() {
		return cultural;
	}

	public void setCultural(boolean cultural) {
		this.cultural = cultural;
	}

	public boolean isClub() {
		return club;
	}

	public void setClub(boolean club) {
		this.club = club;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}

	public String getMessageBoard() {
		return messageBoard;
	}

	public void setMessageBoard(String messageBoard) {
		this.messageBoard = messageBoard;
	}
	
	private void addMessage(Message m) {
		String username = m.username();
		String message = m.message();
		messageBoard = messageBoard + "\n" + username + ": " + message;
	}
}
