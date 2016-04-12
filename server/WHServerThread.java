package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
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
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }
	}
    
    public void run() {
    	boolean isDone = false;
    	while(!isDone) {
			InfoPackage p;
			try {
				p = (InfoPackage)ois.readObject();
//				if (p.isLoginRequest()) {
//					System.out.println("Username: " + p.getUserName() + " " + "Password " + p.getPassWord());
//				}
//				
//				else if(p.isSignUpRequest()){
//					System.out.println("Username: " + p.getUserName() + " " + "Password " + p.getPassWord());
//				}
				
				// guest attempt: server->driver->send back events
				if (p.isGuest()) {
					p.setEvents(whs.getAllEvents());
					p.setValid(true);
				// login attempt: server->driver->send back events and user info
				} else if (p.isLogin()) {
					p.setEvents(whs.getAllEvents());
					p.setUser(whs.loginAttempt(p.getUsername(), p.getPassword()));
					p.setValid(true);
				// signup attempt: if valid attempt, set valid and send package with user already in it and events back to client
					// if not valid, set not valid and send package with user already in it back to client
				} else if (p.isSignup()) {
					if (whs.signupAttempt(p.getUser())) {
						p.setEvents(whs.getAllEvents());
						p.setValid(true);
					} else {
						p.setValid(false);
					}
				// new event submission attempt
				} else if (p.isNewEvent()) {
					p.setValid(whs.addEventAttempt(p.getEvent()));
				}
				
				
				// send package back to client
				sendToClient(p);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    }
    
	public void sendToClient(InfoPackage response) {
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
}
