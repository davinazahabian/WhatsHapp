package server;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;

import com.mysql.jdbc.Driver;

import Model.Event;
import Model.Message;
import Model.User;

import java.sql.*; 
/* 
 * 
 * category identification index:
 * 0 = sports
 * 1 = career
 * 2 = cultural
 * 3 = club
 * 
 * */

public class MySQLDriver {
	
	private Connection con;
	// queries
	private final static String allEvents = "SELECT * FROM EVENTS";
	private final static String sportsEvents = "SELECT * FROM SPORTSEVENT";
	private final static String careerEvents = "SELECT * FROM CAREEREVENT";
	private final static String culturalEvents = "SELECT * FROM CULTURALEVENT";
	private final static String clubEvents = "SELECT * FROM CLUBEVENT";

	// a new user will not have events created and events attending yet, will be left null
	private final static String insertUser = "INSERT INTO USERS(USERNAME,PASSWORD,FNAME,LNAME,EMAIL) VALUES(?,?,?,?,?)";
	private final static String selectUser = "SELECT * FROM USERS WHERE USERNAME=?";
	private final static String selectEvent = "SELECT * FROM EVENTS WHERE STARTTIME=? AND EVENTLOCATION=?";
	private final static String selectEventName = "SELECT * FROM EVENTS WHERE EVENTNAME=?";
	private final static String insertEvent = "INSERT INTO EVENTS(EVENT_NAME,EVENT_HOST,EVENT_CATEGORY,STARTTIME,ENDTIME,TIMEPOSTED,EVENT_DESCRIPTION,EVENT_DATE,EVENT_LOCATION) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertSportsEvent = "INSERT INTO SPORTSEVENT(ID,NAME,HOST,DATE,TIME,DESCRIPTION,LOCATION,ATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertClubEvent = "INSERT INTO CLUBEVENT(ID,NAME,HOST,DATE,TIME,DESCRIPTION,LOCATION,ATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertCulturalEvent = "INSERT INTO CULTURALEVENT(ID,NAME,HOST,DATE,TIME,DESCRIPTION,LOCATION,ATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertCareerEvent = "INSERT INTO CAREEREVENT(EVENTID,EVENT_NAME,EVENT_HOST,EVENT_DATE,EVENT_TIME,EVENT_DESCRIPTION,EVENT_LOCATION,EMPLO";
	private final static String addAttendee = "INSERT INTO ATTEND(ATTENDEE_COUNT,USERNAME,EVENTID) VALUES(?,?,?)";
//  private final static String passWord = "SELECT Password FROM Users WHERE USERNAME"
//	private final static String createTable = "CREATE TABLE Users" +"(Password int," + "Username varchar(255));";
//	private final static String insertFileName = "UPDATE USERS SET FILENAMES=? WHERE USERNAME = ?";
//	private final static String getFileNames = "SELECT FILENAMES FROM USERS WHERE USERNAME = ?";
	
	public MySQLDriver() {
		try { new Driver(); }
		catch(SQLException sqe){ sqe.printStackTrace(); }
		try {
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/WhatsHapp?user=root");
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public void stop() {
		try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
	
	// get vector of all events, send back for event feed.
	public Vector<Event> retrieveAllEvents() {
		Vector<Event> events = new Vector<Event>();
		PreparedStatement ps;
		try {
			// SELECT * FROM EVENTS
			ps = con.prepareStatement(allEvents);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String host = rs.getString(3);
				int category = rs.getInt(4);
				String startTime = rs.getString(5);
				String endTime = rs.getString(6);
				String timePosted = rs.getString(7);
				String description = rs.getString(8);
				String date = rs.getString(9);
				String location = rs.getString(10);
				int upvotes = rs.getInt(11);
				String attendees = rs.getString(12);				
				String messageBoard = rs.getString(13);
				Event e;
				// sports
				if (category == 0) {
					e = new Event(name,date,startTime,endTime,description,location,0,timePosted,host);
				// career
				} else if (category == 1) {
					e = new Event(name,date,startTime,endTime,description,location,1,timePosted,host);
				// cultural
				} else if (category == 2) {
					e = new Event(name,date,startTime,endTime,description,location,2,timePosted,host);
				// club
				} else {
					e = new Event(name,date,startTime,endTime,description,location,3,timePosted,host);
				}
				e.setUpvotes(upvotes);
				e.setMessageBoard(messageBoard);
				/* TODO: parse attendees to get attendee count, set attendee count of this event */
				// maybe just store attendee count in database?
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return events;
	}
	
	// when a user's login is authenticated
	public User retrieveUser(String username, String password) {
		User info = null;
		try {
			// SELECT * FROM USERS WHERE USERNAME=?
			PreparedStatement ps = con.prepareStatement(selectUser);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(2) == password) {
					info = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
					String eventsCreated = (rs.getString(6));
					String eventsAttending = (rs.getString(7));
					// TODO: above are strings of eventID's, parse through and match to the events in Events table,
					// grab those events, put them in vectors, set as eventsCreated and eventsAttending for this User
				}
			}
		} catch(SQLException sqe){ sqe.printStackTrace(); };
		return info;
	}
	
	// true if user exists, false if user does not exist
	public boolean userExists(String username) {
		try {
			// SELECT * FROM USERS WHERE USERNAME=?
			PreparedStatement ps = con.prepareStatement(selectUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while(result.next()){
				return true;
			}
		} catch(SQLException e) {e.printStackTrace();}
		return false;
	}
	
	// returns true if signup successful, false if credentials already exist or exception thrown
	public boolean newUserSignup(User u) {
		try {
			if (userExists(u.username())) {
				return false;
			}
			// INSERT INTO USERS(USERNAME,PASSWORD,FNAME,LNAME,EMAIL)
			PreparedStatement ps = con.prepareStatement(insertUser);
			ps.setString(1, u.username());
			ps.setString(2, u.password());
			ps.setString(3, u.fname());
			ps.setString(4, u.lname());
			ps.setString(5, u.email());
			ps.executeUpdate();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	// true if event exists (same start time and location), false if event does not exist
	public boolean eventExists(Event event) {
		try {
			// SELECT * FROM EVENTS WHERE STARTTIME=? AND EVENTLOCATION=?
			PreparedStatement ps = con.prepareStatement(selectEvent);
			ps.setString(1, event.getEventStartTime());
			ps.setString(2, event.getEventLoc());
			ResultSet result = ps.executeQuery();
			while(result.next()){
				return true;
			}
		} catch(SQLException e) {e.printStackTrace();}
		return false;
	}
	
	// returns true if event submission successful, false if event exists/submission unsuccessful
	public boolean newEventEntry(Event event) {
		try {
			if (eventExists(event)) {
				return false;
			}
			// INSERT INTO EVENTS(EVENT_NAME,EVENT_HOST,EVENT_CATEGORY,STARTTIME,ENDTIME,TIMEPOSTED,EVENT_DESCRIPTION,EVENT_DATE,EVENT_LOCATION)
			// eventID is autoincremented; upvotes, attendees, messageboard are all null to start
			PreparedStatement ps = con.prepareStatement(insertEvent);
			ps.setString(1, event.getEventName());
			ps.setString(2, event.getEventHost());
			ps.setInt(3, event.getType());
			ps.setString(4, event.getEventStartTime());
			ps.setString(5, event.getEventEndTime());
			ps.setString(6, event.getTimePosted());
			ps.setString(7, event.getEventDesc());
			ps.setString(8, event.getEventDate());
			ps.setString(9, event.getEventLoc());
			ps.executeUpdate();
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	// retrieve vector of single category of events
	public Vector<Event> retrieveSportsEvents() {
		Vector<Event> events = new Vector<Event>();
		PreparedStatement ps;
		try {
			// SELECT * FROM SPORTSEVENT
			ps = con.prepareStatement(sportsEvents);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String host = rs.getString(3);
				String date = rs.getString(4);
				String timePosted = rs.getString(5);
				String startTime = rs.getString(6);
				String endTime = rs.getString(7);
				String description = rs.getString(8);
				String location = rs.getString(9);
				int upvotes = rs.getInt(10);
				String attendees = rs.getString(11);				
				String messageBoard = rs.getString(12);
				Event e = new Event(name,date,startTime,endTime,description,location,0,timePosted,host);
				e.setUpvotes(upvotes);
				/* TODO: parse attendees to get attendee count, set attendee count of this event */
				// maybe just store attendee count in database?
				e.setMessageBoard(messageBoard);
				events.add(e);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return events;
	}
	
	public Vector<Event> retrieveCareerEvents() {
		Vector<Event> events = new Vector<Event>();
		PreparedStatement ps;
		try {
			// SELECT * FROM CAREEREVENT
			ps = con.prepareStatement(careerEvents);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String host = rs.getString(3);
				String date = rs.getString(4);
				String timePosted = rs.getString(5);
				String startTime = rs.getString(6);
				String endTime = rs.getString(7);
				String description = rs.getString(8);
				String location = rs.getString(9);
				int upvotes = rs.getInt(10);
				String attendees = rs.getString(11);				
				String messageBoard = rs.getString(12);
				Event e = new Event(name,date,startTime,endTime,description,location,1,timePosted,host);
				e.setUpvotes(upvotes);
				/* TODO: parse attendees to get attendee count, set attendee count of this event */
				// maybe just store attendee count in database?
				e.setMessageBoard(messageBoard);
				events.add(e);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return events;
	}
	
	public Vector<Event> retrieveCulturalEvents() {
		Vector<Event> events = new Vector<Event>();
		PreparedStatement ps;
		try {
			// SELECT * FROM CULTURALEVENT
			ps = con.prepareStatement(culturalEvents);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String host = rs.getString(3);
				String date = rs.getString(4);
				String timePosted = rs.getString(5);
				String startTime = rs.getString(6);
				String endTime = rs.getString(7);
				String description = rs.getString(8);
				String location = rs.getString(9);
				int upvotes = rs.getInt(10);
				String attendees = rs.getString(11);				
				String messageBoard = rs.getString(12);
				Event e = new Event(name,date,startTime,endTime,description,location,2,timePosted,host);
				e.setUpvotes(upvotes);
				/* TODO: parse attendees to get attendee count, set attendee count of this event */
				// maybe just store attendee count in database?
				e.setMessageBoard(messageBoard);
				events.add(e);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return events;
	}
	
	public Vector<Event> retrieveClubEvents() {
		Vector<Event> events = new Vector<Event>();
		PreparedStatement ps;
		try {
			// SELECT * FROM CLUBEVENT
			ps = con.prepareStatement(clubEvents);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String name = rs.getString(2);
				String host = rs.getString(3);
				String date = rs.getString(4);
				String timePosted = rs.getString(5);
				String startTime = rs.getString(6);
				String endTime = rs.getString(7);
				String description = rs.getString(8);
				String location = rs.getString(9);
				int upvotes = rs.getInt(10);
				String attendees = rs.getString(11);				
				String messageBoard = rs.getString(12);
				Event e = new Event(name,date,startTime,endTime,description,location,3,timePosted,host);
				e.setUpvotes(upvotes);
				/* TODO: parse attendees to get attendee count, set attendee count of this event */
				// maybe just store attendee count in database?
				e.setMessageBoard(messageBoard);
				events.add(e);
			}
		} catch (SQLException e) { e.printStackTrace(); }
		return events;
	}
	
	// for now, look up by event name and post it on that event's message board
	// edge case: what if two events of the same name? should we keep track of eventID?
	public boolean postMessage(Event e, Message m) {
		PreparedStatement ps;
		try {
			// SELECT * FROM EVENTS WHERE EVENTNAME=?
			ps = con.prepareStatement(selectEventName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
			}
		} catch (SQLException e) { e.printStackTrace(); return false;}
	}
	

}

