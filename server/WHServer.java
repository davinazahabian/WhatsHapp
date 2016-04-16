package server;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import Model.Event;
import Model.InfoPackage;
import Model.Message;
import Model.User;

public class WHServer extends Thread {
	
	private Vector<WHServerThread> serverThreads;
	private ServerSocket ss = null;
//	private static Vector<Event> allEvents;
	private ArrayList<Socket> sockets;
	private int port = 6780;
	MySQLDriver driver;
	
	public WHServer() throws IOException {
		//driver = new MySQLDriver();
		serverThreads = new Vector<WHServerThread>();
		sockets = new ArrayList<>();
		this.start();
	}
	
	
	// wait for connections...
	public void run() {
		try {
			ss = new ServerSocket(port);
			while (true) {
				System.out.println("Enters here");
				Socket s = ss.accept();
				// once connection made with client, create a new thread to start accepting and sending connections from client
				WHServerThread wst = new WHServerThread(s, this);
				serverThreads.add(wst);
				InfoPackage ip = new InfoPackage();
				ip.setGuest(true);
				wst.sendToClient(ip);
				wst.start();
				System.out.println("enters here ddsfd");
				sockets.add(s);
			}
		} catch (Exception e) {
			for(Socket s : sockets)
				try { s.close(); } catch (IOException e1) { }
		} finally {
			try {
				if(ss != null) ss.close();
			} catch (IOException e) { }
		}
	}
	
	// communicating with the driver:
	
	// returns a vector of events
	public Vector<Event> getAllEvents() {
		return driver.retrieveAllEvents();
	}
	
	public User loginAttempt(String username, String password) {
		return driver.retrieveUser(username, password);
	}
	
	public boolean signupAttempt(User u) {
		return driver.newUserSignup(u);
	}
	
	public boolean addEventAttempt(Event e) {
		return driver.newEventEntry(e);
	}
	
	public Vector<Event> sportsEvents() {
		return driver.retrieveSportsEvents();
	}
	
	public Vector<Event> careerEvents() {
		return driver.retrieveCareerEvents();
	}
	
	public Vector<Event> culturalEvents() {
		return driver.retrieveCulturalEvents();
	}
	
	public Vector<Event> clubEvents() {
		return driver.retrieveClubEvents();
	}
	
	public boolean sendMessageAttempt(Event e, Message m) {
		return driver.postMessage(e, m);
	}
	
	public boolean upvoteEventAttempt(Event e) {
		return driver.upvoteEvent(e);
	}
	
	public boolean addAttendeeAttempt(Event e, User u) {
		return driver.addAttendee(e, u);
	}
	
	public static void main (String[] args){
		try {
			new WHServer();
		} catch (IOException e) { e.printStackTrace(); }
	}
}
