package Model;


import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

public class Event extends InfoPackage implements Comparator<Event>, Comparable<Event> {
	
	private static final long serialVersionUID = 1L;
	public static final String[] types = {"Sports", "Career", "Cultural", "Club"};
	private String eventName;
	private String eventDate;
	private String eventStartTime;
	private String eventEndTime;
	private String eventDesc;
	private String eventLoc;
	private String timePosted;
	private long milliseconds;
	private int type;
	private int upvotes;
	private int attendees;
	private String messageBoard;

	public Event(String eventName, String eventDate, String eventStartTime,String eventEndTime, String eventDesc, String eventLoc,
			 int type, String timePosted) {
		super("event");
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.eventDesc = eventDesc;
		this.eventLoc = eventLoc;
		Date date = new Date();
		milliseconds = date.getTime();
		this.timePosted = timePosted;
		this.type = type;
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

	public String getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(String eventTime) {
		this.eventStartTime = eventTime;
	}

	public String getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(String eventEndTime) {
		this.eventEndTime = eventEndTime;
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

	public String getTimePosted() {
		return timePosted;
	}

	public long getMilliseconds() {
		return milliseconds;
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
	
	//Collections.sort(list); for most recent to least recent
	@Override
	public int compareTo(Event o) {
		return (this.timePosted).compareTo(o.timePosted);
	}
	
	//Collections.sort(list, new Event()); for most upvotes to least upvotes
	@Override
	public int compare(Event o1, Event o2) {
		return o2.upvotes - o1.upvotes;
	}
}