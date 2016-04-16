package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
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
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.IFNONNULL;

import Model.Event;
import Model.InfoPackage;
import Model.Message;
import Model.User;
import WHFrame.MainFeedFrame;
import WHFrame.SplashPanel;
import WHFrame.WHFrame;


public class WHClient extends Thread {
	// networking
	protected Socket s;
	private int port = 6780;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	// data
	// instance of current user, for populating the about me page
	private User currentUser = null;
	// tells us whether current session is guest user or registered
	private boolean isRegistered = false;
	// set of events sorted by default (time posted) and by trending (upvotes)
	private Vector<Event> allEventsDefault= new Vector<Event>();
	private Vector<Event> allEventsTrending= new Vector<Event>();
	
	// should we have vector<event> ready for all categories?
	private Vector<Event> sports= new Vector<Event>();
	private Vector<Event> career= new Vector<Event>();
	private Vector<Event> cultural= new Vector<Event>();
	private Vector<Event> club= new Vector<Event>();


	// GUI
	private MainFeedFrame mff = new MainFeedFrame();
	private EventDetailGUI edg;

	
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
						System.out.println("Guest is true");
						InfoPackage ip = new InfoPackage();
						ip.setGuest(true);
						sendToServer(ip);
						this.allEventsDefault = p.getEvents();
						// TODO: sort events by trending and insert into allEventsTrending
//						for (Event e : allEventsDefault) {
//							//TODO: create eventpanelguis and populate eventfeedgui
//						}
					
					// login attempt returned
					} else if (p.isLogin()) {
						if (p.getUser() == null) {
							// TODO: system dialog letting the user know that username/password does not exist
						} else {
							isRegistered = true;
							currentUser = p.getUser();
							this.allEventsDefault = p.getEvents();
							// TODO: sort events by trending and insert into allEventsTrending
							for (Event e : allEventsDefault) {
								//TODO: create eventpanelguis and populate eventfeedgui
							}
							// TODO: create AboutMeFrame and store as data member
						}
						
					
					// signup attempt returned
					} else if (p.isSignup()) {
						System.out.println("Recieved successfully");
//						if (p.isValid()) {
//							isRegistered = true;
//							currentUser = p.getUser();
//							this.allEventsDefault = p.getEvents();
//							// TODO: sort events by trending and insert into allEventsTrending
//							for (Event e : allEventsDefault) {
//								//TODO: create eventpanelguis and populate eventfeedgui
//							}
//							// TODO: create AboutMeFrame and store as data member
//						} else {
//							// TODO: notify user that signup was invalid (username already taken)
//							// "please try again, or continue as guest"
//						}
						
					// new event submission attempt returned
					} else if (p.isNewEvent()) {
						if (p.isValid()) {
							// note: adding the event depends how we are creating the eventpanels
							// we want the most recent event to be at the top, so:
							// insert at index 0, create and place panels from 0...size-1
							this.allEventsDefault.insertElementAt(p.getEvent(), 0);
							// TODO: sort events by trending and insert into allEventsTrending
//							for (Event e : allEventsDefault or allEventsTrending ) {
//								//TODO: recreate eventpanelguis and re-populate eventfeedgui
//								// check which sorting rule is in use when creating panels
//							}
							// TODO: display message to user saying, "Your event was submitted!"
						} else {
							// TODO: display warning message to user saying,
							// "There is an event at the same place and same time. Please try again"
						}
						
					// category of events returned
					} else if (p.isGettingSports()) {
						this.sports = p.getEvents();
						for (Event e : sports) {
							//TODO: create eventpanelguis and re-populate eventfeedgui
						}
					} else if (p.isGettingCareer()) {
						this.career = p.getEvents();
						for (Event e : career) {
							//TODO: create eventpanelguis and re-populate eventfeedgui
						}
					} else if (p.isGettingCultural()) {
						this.cultural = p.getEvents();
						for (Event e : cultural) {
							//TODO: create eventpanelguis and re-populate eventfeedgui
						}
					} else if (p.isGettingClub()) {
						this.club = p.getEvents();
						for (Event e : club) {
							//TODO: create eventpanelguis and re-populate eventfeedgui
						}

					// message send request returned
					} else if (p.isPostingMessage()) {
						if (p.isValid()) {
							// TODO: update messageBoard of current eventdetailgui in real time,
							// (event has new message stored already in memory, just need to update textarea)							
						} else {
							// TODO: display system dialog to user saying, "Message failed to post."
						}
					
					// add attendee request returned
					} else if (p.isAddingAttendee()) {
						if (p.isValid()) {
							// TODO: make sure attendee count/attendee list are updated in the GUIs
							// (event has it stored already in memory, just need to update GUIs)							
						} else {
							// TODO: display system dialog to user saying, "Cannot fulfill request at this time."
						}
					}
					
					
				}
				
			}catch(EOFException e) {
			    //eof - no error in this case
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
		InfoPackage p = new InfoPackage();
		e.upvote();
		p.setEvent(e);
		p.setUpvoting(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// action listener on "Going" button on eventdetailGUI activates this
	// e = event being attended, u = user attending the event
	public void addAttendee(Event e, User u) {
		InfoPackage p = new InfoPackage();
		// update attendee count and attendee list of event
		e.addAttendee(u.username());
		p.setUser(u);
		p.setEvent(e);
		p.setAddingAttendee(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void sendToServer(InfoPackage p){
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public static void main(String [] args) {
		WHClient c = new WHClient();
	}
		
		
}
