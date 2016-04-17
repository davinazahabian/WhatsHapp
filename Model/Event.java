package Model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import client.WHClient;

public class Event implements Comparator<Event>, Comparable<Event>, Serializable{
	
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
	private int upvotes = 0;
	private int attendees = 0;
	private String messageBoard;
	private String eventHost;
	// formatted String of attendees for database/listing on the eventdetailGUI
	private Vector<String> attendeeList;
	public Event(){
		
	}
	public Event(String eventName, String eventDate, String eventStartTime,String eventEndTime, String eventDesc, String eventLoc,
			 int type, String timePosted, String eventHost) {
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.eventDesc = eventDesc;
		this.eventLoc = eventLoc;
		Date date = new Date();
		milliseconds = date.getTime();
		this.timePosted = timePosted;
		this.setType(type);
		this.eventHost = eventHost;
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
	
	public void upvote() {
		this.upvotes = this.upvotes+1;
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
	
	public void addMessage(Message m) {
		String username = m.username();
		String message = m.message();
		messageBoard = messageBoard + "\n" + username + ": " + message;
	}
	
	//Collections.sort(list); for most recent to least recent
	@Override
	public int compareTo(Event o) {
		return (o.timePosted).compareTo(this.timePosted);
	}
	
	//Collections.sort(list, new Event()); for most upvotes to least upvotes
	@Override
	public int compare(Event o1, Event o2) {
		return o2.upvotes - o1.upvotes;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEventHost() {
		return eventHost;
	}
	public void setEventHost(String eventHost) {
		this.eventHost = eventHost;
	}
	public String getAttendeeList() {
		String attendeeString = null;
		for (String s : this.attendeeList) {
			attendeeString = attendeeString + s + "\n";
		}
		return attendeeString;
	}
	public void setAttendeeList(Vector<String> attendeeList) {
		this.attendeeList = attendeeList;
	}
	public void addAttendee(String username) {
		this.attendeeList.add(username);
		this.attendees = this.attendees+1;
	}

}