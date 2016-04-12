package server;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.Date;

import com.mysql.jdbc.Driver;

import Model.Event;
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
	private final static String insertUser = "INSERT INTO USERS(USERNAME,PASSWORD,FNAME,LNAME,EMAIL,EVENTSCREATED,EVENTSATTENDING) VALUES(?,?,?,?,?,?,?)";
	private final static String selectUser = "SELECT * FROM USERS WHERE USERNAME=?";
	private final static String insertEvent = "INSERT INTO EVENTS(EVENTID,EVENT_NAME,EVENT_HOST,EVENT_TIME,EVENT_DESCRIPTION,EVENT_DATE,EVENT_LOCATION,NUM_UPVOTES,MYATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private final static String insertSportsEvent = "INSERT INTO SPORTSEVENT(ID,NAME,HOST,DATE,TIME,DESCRIPTION,LOCATION,ATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertClubEvent = "INSERT INTO CLUBEVENT(ID,NAME,HOST,DATE,TIME,DESCRIPTION,LOCATION,ATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertCulturalEvent = "INSERT INTO CULTURALEVENT(ID,NAME,HOST,DATE,TIME,DESCRIPTION,LOCATION,ATTENDEES,MESSAGEBOARD) VALUES(?,?,?,?,?,?,?,?,?)";
	private final static String insertCareerEvent = "INSERT INTO CAREEREVENT(EVENTID,EVENT_NAME,EVENT_HOST,EVENT_DATE,EVENT_TIME,EVENT_DESCRIPTION,EVENT_LOCATION,EMPLO";
	private final static String addAttendee = "INSERT INTO ATTEND(ATTENDEE_COUNT,USERNAME,EVENTID) VALUES(?,?,?)";
//  private final static String passWord = "SELECT Password FROM Users WHERE USERNAME"
//	private final static String createTable = "CREATE TABLE Users" +"(Password int," + "Username varchar(255));";
//	private final static String insertFileName = "UPDATE USERS SET FILENAMES=? WHERE USERNAME = ?";
//	private final static String getFileNames = "SELECT FILENAMES FROM USERS WHERE USERNAME = ?";
	
	public MySQLDriver(){
		try{
			new Driver();
		}catch(SQLException sqe){
			sqe.printStackTrace();
		}
		try {
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/WhatsHapp?user=root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop(){
		try{con.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
//	public String getFileNames(String username){
//		try {
//			PreparedStatement ps = con2.prepareStatement(getFileNames);
//			ps.setString(1, username);
//			ResultSet result = ps.executeQuery();
//			if(result.next())
//			{
//				String temp = result.getString(1);
//				if(temp == null)
//					return "";
//				temp = temp.trim();
//				if(temp.equals("null"))
//					return "";
//				else
//					return result.getString(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "";
//	}
	
//	public String[] getFileName(String username){
//		String text = getFileNames(username);
//		String[] splited = text.split(" ");
//		return splited;
//	}
//	public void insertFile (String username, String filename){
//		String[] names = getFileName(username);
//		for (int i = 0; i < names.length; i++) {
//			if(filename.equals(names[i]))
//				return;
//		}
//		System.out.println("Username = " + username  + " Filename = " + filename);
//		String text = getFileNames(username) + " " + filename ;
//		System.out.println("Text = " + text);
//		try{
//			PreparedStatement ps = con2.prepareStatement(insertFileName);
//			ps.setString(1, text);
//			ps.setString(2, username);
//			ps.executeUpdate();
//			//FactoryServerGUI.addMessage("Adding Product:" + productName + "to table with count 0");
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		
//	}
	
//	public boolean doesExist(String userName){
//		try{
//			PreparedStatement ps = con2.prepareStatement(selectUser);
//			ps.setString(1, userName);
//			ResultSet  result = ps.executeQuery();
//			while(result.next()){
//				return true;
//			}
//		}catch(SQLException e){ e.printStackTrace();}
//			return false;
//		
//	}
//	
//	public boolean validate(String username, int passWord){
//		if(!doesExist(username)){
//			return false;
//		}
//		try{
//		PreparedStatement ps = con2.prepareStatement(selectUser);
//		ps.setString(1, username);
//		ResultSet  result = ps.executeQuery();
//		while(result.next()){
//			System.out.println("database: " + result.getInt(1) + "  Input: " + passWord);
//			if(result.getInt(1) != passWord)
//			{
//				System.out.println("database: " + result.getInt(1) + "  Input: " + passWord);
//				return false;
//			}
//		}}catch(SQLException sqe){};
//		return true;
//	}
//
//	public void addUser (String username, int passWord, String email, String fname, String lname){
//		try{
//			PreparedStatement ps = con2.prepareStatement(insertUser);
//			ps.setString(1, username);
//			ps.setInt(2, passWord);
//			ps.setString(3, email);
//			ps.setString(4, fname);
//			ps.setString(5, lname);
//			ps.executeUpdate();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//	}
	
	// get vector of all events, send back for guest's event feed.
	public Vector<Event> retrieveAllEvents() {
		Vector<Event> events = new Vector<Event>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(allEvents);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				long id = rs.getLong(1);
				String name = rs.getString(2);
//				String host = rs.getString(3);
				int category = rs.getInt(4);
				String startTime = rs.getString(5);
				String endTime = rs.getString(6);
				String timePosted = rs.getString(7);
				String description = rs.getString(8);
				String date = rs.getString(9);
				String location = rs.getString(10);
				int upvotes = rs.getInt(11);
//				String attendees = rs.getString(12);				
				String messageBoard = rs.getString(13);
				Event e;
				// sports
				if (category == 0) {
					e = new Event(name,date,startTime,endTime,description,location,0,timePosted);
				// career
				} else if (category == 1) {
					e = new Event(name,date,startTime,endTime,description,location,1,timePosted);
				// cultural
				} else if (category == 2) {
					e = new Event(name,date,startTime,endTime,description,location,2,timePosted);
				// club
				} else {
					e = new Event(name,date,startTime,endTime,description,location,3,timePosted);
				}
				e.setUpvotes(upvotes);
				e.setMessageBoard(messageBoard);
				events.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}
	
	// when a user's login is authenticated
	public User retrieveUser(String username, String password) {
		User info = null;
		try {
			PreparedStatement ps = con.prepareStatement(selectUser);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(2) == password) {
					info = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
//					info.setEventsCreated(rs.getBlob(6));
//					info.setEventsAttending(rs.getBlob(7));
				}
			}
		} catch(SQLException sqe){ sqe.printStackTrace(); };
		return info;
	}

}

