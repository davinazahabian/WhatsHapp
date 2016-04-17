package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import Model.InfoPackage;
public class WHServerThread extends Thread {
	
	private Socket s;
    private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private WHServer whs;
    
    public WHServerThread(Socket s, WHServer whs) {
		this.s = s;
		this.whs = whs;
		try {
			oos = new ObjectOutputStream(this.s.getOutputStream());
			ois = new ObjectInputStream(this.s.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }
	}
    
    public void run() {
    	boolean isDone = false;
    	while(!isDone) {
			InfoPackage p;
			try {
				// if closed, reestablish connection
				if(s.isClosed()) {
					System.out.println("enters here");
					break;
//					try {
//						ServerSocket ss = new ServerSocket(6780);
//						s = ss.accept();
//						oos = new ObjectOutputStream(s.getOutputStream());
//						ois = new ObjectInputStream(s.getInputStream());
//					} catch (UnknownHostException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				}
				p = (InfoPackage)ois.readObject();
				// guest attempt: server->driver->send back events
				if (p.isGuest()) {
					System.out.println("Guet is true");
					p.setEvents(whs.getAllEvents());
					System.out.println("events size:" + p.getEvents().size());
					p.setValid(true);
					sendToClient(p);
				// login attempt: server->driver->send back events and user info
				} else if (p.isLogin()) {
					System.out.println("Username:" + p.getUsername() + " Password:" + p.getPassword());
					p.setUser(whs.loginAttempt(p.getUsername(), p.getPassword()));
					p.setUser(p.getUser());
					p.setLogin(true);
					p.setEvents(whs.getAllEvents());
					sendToClient(p);
				// signup attempt: if valid attempt, set valid and send package with user already in it and events back to client
				// if not valid, set not valid and send package with user already in it back to client
				} else if (p.isSignup()) {
					if (whs.signupAttempt(p.getUser())) {
						p.setUser(p.getUser());
						p.setSignup(true);
						p.setEvents(whs.getAllEvents());
						p.setValid(true);
						sendToClient(p);
					} else {
						p.setUser(p.getUser());
						p.setSignup(true);
						p.setEvents(whs.getAllEvents());
						p.setValid(false);
						sendToClient(p);
					}
				// new event submission attempt
				} else if (p.isNewEvent()) {
					System.out.println("enters new event run method in server");
					p.setValid(whs.addEventAttempt(p.getEvent()));
					whs.sendEventToAll(p);
					//sendToClient(p);
				// getting category of events
				} else if (p.isGettingSports()) {
					p.setEvents(whs.sportsEvents());
					p.setValid(true);
					sendToClient(p);
				} else if (p.isGettingCareer()) {
					p.setEvents(whs.careerEvents());
					p.setValid(true);
					sendToClient(p);
				} else if (p.isGettingCultural()) {
					p.setEvents(whs.culturalEvents());
					p.setValid(true);
					sendToClient(p);
				} else if (p.isGettingClub()) {
					p.setEvents(whs.clubEvents());
					p.setValid(true);
					sendToClient(p);
				// post message attempt
				} else if (p.isPostingMessage()) {
					p.setValid(whs.sendMessageAttempt(p.getEvent()));
					sendToClient(p);
				// upvoting event attempt
				} else if (p.isUpvoting()) {
					p.setValid(whs.upvoteEventAttempt(p.getEvent()));
					sendToClient(p);
				// add attendee attempt
				} else if (p.isAddingAttendee()) {
					p.setValid(whs.addAttendeeAttempt(p.getEvent(), p.getUser()));
					sendToClient(p);
				}

			} catch(EOFException e) {
			    //eof - no error in this case
			}catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				
				//e.printStackTrace();
				//System.out.println("It enters here");
				//break;
			}
			

    	}
    }
    
	public void sendToClient(InfoPackage response) {
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
			ioe.printStackTrace();
			
		}
	}
}
