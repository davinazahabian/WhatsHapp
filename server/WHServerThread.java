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
					p.setEvents(whs.guestAttempt());
					p.setValid(true);
					sendToClient(p);
				// login attempt: server->driver->send back events and user info
				} else if (p.isLogin()) {
					p.setEvents(whs.guestAttempt());
					p.setUser(whs.loginAttempt(p.getUsername(), p.getPassword()));
					p.setValid(true);
					sendToClient(p);
				}
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
