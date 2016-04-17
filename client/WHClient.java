package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
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

public class WHClient extends Thread {

	// networking
	protected Socket s;
	private int port = 6780;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	//int x = 0;
	
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
					if (p.isGuest() ) {
//						x++;
//						System.out.println("Guest is true");	
//						// what does this do:
//						InfoPackage ip = new InfoPackage();
//						ip.setGuest(true);
//						sendToServer(ip);
						
						this.allEvents = p.getEvents();
						mff.populateFeed(allEvents);


						// login attempt returned
					} else if (p.isLogin()) {
						if (p.getUser() == null) {
							whf.showError();
						} else {
							setRegistered(true);
							setAllEvents(p.getEvents());
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
							JOptionPane.showMessageDialog(nug, "Sign Up Failure:(");
						}


					// new event submission attempt returned
					} else if (p.isNewEvent()) {
						if (p.isValid()) {
							this.allEvents.insertElementAt(p.getEvent(), 0);
//							this.mff.populateFeed(allEvents);
							JOptionPane.showMessageDialog(this.getNeg(),"Your event was submitted!");
						} else {
							JOptionPane.showMessageDialog(this.getNeg(),"There is already an event at the same place and same time. Please try again!","Warning",JOptionPane.WARNING_MESSAGE);
						}


						// category of events returned
					} else if (p.isGettingSports()) {
						this.sports = p.getEvents();
						// Davina
						this.mff.populateFeed(sports);

					} else if (p.isGettingCareer()) {
						this.career = p.getEvents();
						// Davina
						this.mff.populateFeed(career);

					} else if (p.isGettingCultural()) {
						this.cultural = p.getEvents();
						// Davina
						this.mff.populateFeed(cultural);

					} else if (p.isGettingClub()) {
						this.club = p.getEvents();
						// Davina
						this.mff.populateFeed(club);


						// message send request returned
					} else if (p.isPostingMessage()) {
						if (p.isValid()) {
							// TODO: update messageBoard of current eventdetailgui in real time,
							// (event has new message stored already in memory, just need to update textarea)
						} else {
							JOptionPane.showMessageDialog(this.getMff(),"Cannot fulfill request at this time.","Warning",JOptionPane.WARNING_MESSAGE);
						}


						// add attendee request returned
					} else if (p.isAddingAttendee()) {
						if (p.isValid()) {
							// TODO: make sure attendee count/attendee list are updated in the GUIs
							// (event has it stored already in memory, just need to update GUIs)
						} else {
							JOptionPane.showMessageDialog(this.getMff(),"Cannot fulfill request at this time.","Warning",JOptionPane.WARNING_MESSAGE);
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