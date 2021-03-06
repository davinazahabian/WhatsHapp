package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.Event;
import Model.InfoPackage;
import Model.Message;
import Model.User;
import WHFrame.MainFeedFrame;
import WHFrame.MyProfileFrame;
import WHFrame.NewEventGUI;
import WHFrame.NewUserGUI;
import WHFrame.WHFrame;
/*
 * 
 * WHClient - contains instances of all GUI frames and manages the execution of the program, sending
 * requests to the server whenever necessary
 * 
 */
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
	private Vector<Event> allEvents= new Vector<Event>();
	private Vector<Event> sports= new Vector<Event>();
	private Vector<Event> career= new Vector<Event>();
	private Vector<Event> cultural= new Vector<Event>();
	private Vector<Event> club= new Vector<Event>();

	// GUI
	private WHFrame whf;
	private MainFeedFrame mff;
	private MyProfileFrame mpf;
	private NewEventGUI neg;
	private NewUserGUI nug;

	public WHClient() {
		//setIconImage(new ImageIcon("img/icon.png").getImage());

		whf = new WHFrame(this);
		whf.setVisible(true);
		try {
			s = new Socket("localhost", port);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			this.start();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			//	try {
			//	if (s != null) {
			//	//s.close();
			//	}
			//	 
			//	} catch (IOException ioe) {
			//	ioe.printStackTrace();
			//	}
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

						allEvents = p.getEvents();
						setMff(new MainFeedFrame(this));
						this.whf.setVisible(false);
						getMff().populateFeed(allEvents);

						// login attempt returned
					} else if (p.isLogin()) {
						if (p.getUser() == null) {
							whf.showError();
						} else {
							setRegistered(true);
							setAllEvents(p.getEvents());
							System.out.println("events size" + p.getEvents().size());
							setCurrentUser(p.getUser());
							whf.showSuccess(p.getEvents());
						}



					// signup attempt returned
					} else if (p.isSignup()) {
						System.out.println("Recieved successfully");
						if(p.isValid()) {
							setRegistered(true);
							setAllEvents(p.getEvents());
							setCurrentUser(p.getUser());
							nug.showSuccess(allEvents);
						} else {
							nug.signUpFailure();
						}


					// new event submission attempt returned
					} else if (p.isNewEvent()) {
						System.out.println("Enters new Event in run");
						System.out.println("isnewevent");
						if (p.isValid()) {
							this.allEvents.insertElementAt(p.getEvent(), 0);
							if (p.getEvent().getType() == 0) { // sports
								sports.add(p.getEvent());
								System.out.println("sports added");
							} else if (p.getEvent().getType() == 1) { // career
								career.add(p.getEvent());
							} else if (p.getEvent().getType() == 2) { // cultural
								cultural.add(p.getEvent());
							} else { // club
								club.add(p.getEvent());
							}
							if(neg!=null)
								neg.newEvent();
							
							else
								mff.newEvent();
								//mff.showEventSuccess();
						} else {
							neg.showEventFailure();
						}


						// category of events returned
					} else if (p.isGettingSports()) {
						this.sports = p.getEvents();
						mff.setVisible(false);
						
						boolean trending = mff.isTrending();
						setMff(new MainFeedFrame(this));
						getMff().manual = true;
					    getMff().getCategoryBox().setSelectedItem("Sports");
					    getMff().manual = false;
						if (trending) { getMff().getSortByTrending().setSelected(true); }
						getMff().populateFeed(sports);
						getMff().setVisible(true);

					} else if (p.isGettingCareer()) {
						this.career = p.getEvents();
						mff.setVisible(false);
						
						boolean trending = mff.isTrending();
						setMff(new MainFeedFrame(this));
						getMff().manual = true;
					    getMff().getCategoryBox().setSelectedItem("Career");
					    getMff().manual = false;
						if (trending) { getMff().getSortByTrending().setSelected(true); }
						getMff().populateFeed(career);
						getMff().setVisible(true);

					} else if (p.isGettingCultural()) {
						this.cultural = p.getEvents();
						mff.setVisible(false);
						
						boolean trending = mff.isTrending();
						setMff(new MainFeedFrame(this));
						getMff().manual = true;
					    getMff().getCategoryBox().setSelectedItem("Cultural");
					    getMff().manual = false;
						if (trending) { getMff().getSortByTrending().setSelected(true); }
						getMff().populateFeed(cultural);
						getMff().setVisible(true);

					} else if (p.isGettingClub()) {
						this.club = p.getEvents();
						mff.setVisible(false);
						
						boolean trending = mff.isTrending();
						setMff(new MainFeedFrame(this));
						getMff().manual = true;
					    getMff().getCategoryBox().setSelectedItem("Club");
					    getMff().manual = false;
						if (trending) { getMff().getSortByTrending().setSelected(true); }
						getMff().populateFeed(club);
						getMff().setVisible(true);


						// message send request returned
					} else if (p.isPostingMessage()) {
						if (p.isValid()) {
							if(p.getEvent() == null)
								System.out.println("event is null client run method");
							else{
								System.out.println("event is not null in run method");
							}
							mff.postToBoard(p);
							System.out.println("It enters ispoting in client run");
						} else {
							mff.cannotFulfillRequest();
						}



						// add attendee request returned
					} else if (p.isAddingAttendee()) {
						if (p.isValid()) {
							return;
						} else {
							mff.cannotFulfillRequest();
						}
					}
				}
			} catch(EOFException e) {
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

	// action listener on login button activates this
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


	// getters and setters
	public WHFrame getWhf() {
		return whf;
	}
	public void setWhf(WHFrame whf) {
		this.whf = whf;
	}
	public MainFeedFrame getMff() {
		return mff;
	}
	public void setMff(MainFeedFrame mff) {
		this.mff = mff;
	}
	public MyProfileFrame getMpf() {
		return mpf;
	}
	public void setMpf(MyProfileFrame mpf) {
		this.mpf = mpf;
	}
	public NewEventGUI getNeg() {
		return neg;
	}
	public void setNeg(NewEventGUI neg) {
		this.neg = neg;
	}
	public boolean isRegistered() {
		return isRegistered;
	}
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public NewUserGUI getNug() {
		return nug;
	}
	public void setNug(NewUserGUI nug) {
		this.nug = nug;
	}
	public Vector<Event> getAllEvents() {
		return allEvents;
	}
	public void setAllEvents(Vector<Event> allEvents) {
		this.allEvents = allEvents;
	}
	public Vector<Event> getSports() {
		return sports;
	}



	public void setSports(Vector<Event> sports) {
		this.sports = sports;
	}



	public Vector<Event> getCareer() {
		return career;
	}



	public void setCareer(Vector<Event> career) {
		this.career = career;
	}



	public Vector<Event> getCultural() {
		return cultural;
	}



	public void setCultural(Vector<Event> cultural) {
		this.cultural = cultural;
	}



	public Vector<Event> getClub() {
		return club;
	}



	public void setClub(Vector<Event> club) {
		this.club = club;
	}
	
	public static void main(String [] args) {
		WHClient whc = new WHClient();
	}
	
}