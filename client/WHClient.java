package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Model.Event;
import Model.InfoPackage;
import Model.Message;
import Model.User;
import WHFrame.SplashPanel;
import WHFrame.WHFrame;


public class WHClient extends Thread {
	// tells us whether current session is guest user or registered
	private boolean isRegistered = false;
	protected Socket s;
	private int port = 6789;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	// instance of current user, for populating the about me page
	private User currentUser = null;
	
	public WHClient() {		
		try {
			s = new Socket("localhost", port);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			this.start();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				 
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	public void run() {
		while(true) {
			// if closed, reestablish connection
			if(s.isClosed()) {
				try {
					s = new Socket("localhost", port);
					oos = new ObjectOutputStream(s.getOutputStream());
					ois = new ObjectInputStream(s.getInputStream());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// otherwise, get ready to accept packages from server
			InfoPackage p = null;
			try {
				p = (InfoPackage)ois.readObject();
				if (p != null) {
					
					// guest attempt returned
					if (p.isGuest()) {
						//TODO: create eventpanelguis using event vector and populate eventfeedgui
					
					// login attempt returned
					} else if (p.isLogin()) {
						if (p.getUser() == null) {
							// TODO: system dialog letting the user know that username/password does not exist
						} else {
							isRegistered = true;
							currentUser = p.getUser();
							//TODO: create eventpanelguis using event vector and populate eventfeedgui
							// create aboutme page
							// enable all features for the user
						}
						
					
					// signup attempt returned
					} else if (p.isSignup()) {
						if (p.isValid()) {
							isRegistered = true;
							currentUser = p.getUser();
							//TODO: create eventpanelguis using event vector and populate eventfeedgui
							// create aboutme page, other personalization
							// enable all features for the user
						} else {
							// TODO: notify user that signup was invalid (username already taken)
							// please try again, or continue as guest
						}
						
					// new event submission attempt returned
					} else if (p.isNewEvent()) {
						if (p.isValid()) {
							// TODO: display message to user saying, "Your event was submitted!"
						} else {
							// TODO: display warning message to user saying,
							// "There is an event at the same place and same time. Please try again"
						}
						
					// category of events returned
					} else if (p.isGettingSports()) {
						// TODO: use for showing sports events only, p.getEvents()
					} else if (p.isGettingCareer()) {
						// TODO: use for showing career events only, p.getEvents()
					} else if (p.isGettingCultural()) {
						// TODO: use for showing cultural events only, p.getEvents()
					} else if (p.isGettingClub()) {
						// TODO: use for showing club events only, p.getEvents()
					
					// message send request returned
					} else if (p.isPostingMessage()) {
						if (p.isValid()) {
							// TODO: update messageBoard of current eventdetailgui in real time,
							// (event has new message stored already in memory)							
						} else {
							// TODO: display system dialog to user saying, "Message failed to post."
						}
					}
					
					
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	// action listener on guest button on splash screen activates this
	public void guestRequest() {
		InfoPackage p = new InfoPackage();
		p.setGuest(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// action listener on login button on splash screen activates this
	public void loginRequest(String username, String password) {
		InfoPackage p = new InfoPackage();
		p.setUsername(username); p.setPassword(password);
		p.setLogin(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// action listener on signup button on splash screen activates this.
	// create an instance of user from the information given, pass it in
	public void signupRequest(User u) {
		InfoPackage p = new InfoPackage();
		p.setUser(u);
		p.setSignup(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// action listener on "new event" button on main feed page activates this.
	// create an instance of event from the information given, pass it in
	public void newEventRequest(Event newEvent) {
		InfoPackage p = new InfoPackage();
		p.setEvent(newEvent);
		p.setNewEvent(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	
	// TODO: method that populates AboutMe panel with currentUser's information
	
	
	public void getSportsEvents() {
		InfoPackage p = new InfoPackage();
		p.setGettingSports(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void getCareerEvents() {
		InfoPackage p = new InfoPackage();
		p.setGettingCareer(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void getCulturalEvents() {
		InfoPackage p = new InfoPackage();
		p.setGettingCultural(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void getClubEvents() {
		InfoPackage p = new InfoPackage();
		p.setGettingClub(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// action listener on "Send Message" button on eventdetailgui activates this
	public void sendMessage(Event e, Message m) {
		InfoPackage p = new InfoPackage();
		// change Event's messageBoard, then send to driver to update database.
		e.addMessage(m);
		p.setEvent(e);
		p.setMessage(m);
		p.setPostingMessage(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// action listener on thumbs up button on eventpanelGUI activates this
	public void incrementUpvote(Event e) {
		
	}
	
	// action listener on "Going" button on eventdetailGUI activates this
	public void addAttendee(Event e) {
		
	}
	
	public static void main(String [] args) {
		WHClient c = new WHClient();
	}
		
		
}
