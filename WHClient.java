
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

import WHFrame.SplashPanel;
import WHFrame.WHFrame;


public class WHClient extends Thread {
	// tells us whether current session is guest user or registered
	private boolean isRegistered = false;
	
	protected Socket s;
	private int port = 6789;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private WHFrame whf;
//	private EventFeedGUI efg;
//	private Vector<EventPanelGUI> epg;
	
	public WHClient() {
		whf = new WHFrame();
//		efg = new EventFeedGUI();
		
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
				System.out.println("ioe: " + ioe.getMessage());
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
			Package p = null;
			try {
				p = (Package)ois.readObject();
				if (p != null) {
					
					// guest attempt returned
					if (p.isGuest()) {
						//TODO: create eventpanelguis using event vector and populate eventfeedgui
					
					
					// login attempt returned
					} else if (p.isLogin()) {
						if (p.user() == null) {
							System.out.println("Username or password invalid. Please try again.");
						} else {
							isRegistered = true;
							//TODO: create eventpanelguis using event vector and populate eventfeedgui
							// create aboutme page
							// enable all features for the user
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
		Package p = new Package();
		p.setGuest(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	
	// action listener on login button on splash screen activates this
	public void loginRequest(String username, String password) {
		Package p = new Package();
		p.setUsername(username); p.setPassword(password);
		p.setLogin(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
		
		
}
