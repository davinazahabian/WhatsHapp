import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class WHServer extends Thread {
	
	private Vector<WHServerThread> serverThreads;
	private ServerSocket ss = null;
	private static Vector<Event> allEvents;
	private ArrayList<Socket> sockets;
	private int port = 6789;
	MySQLDriver driver;
	
	public WHServer() throws IOException {
		driver = new MySQLDriver();
		serverThreads = new Vector<WHServerThread>();
		this.start();
	}
	
	
	// wait for connections...
	public void run() {
		try {
			ss = new ServerSocket(port);
			while (true) {
				Socket s = ss.accept();
				// once connection made with client, create a new thread to start accepting and sending connections from client
				WHServerThread wst = new WHServerThread(s, this);
				serverThreads.add(wst);
				wst.start();
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
	public void loginAttempt() {
		
	}
	
	public void signupAttempt() {
		
	}
	
	public void addEventAttempt() {
		
	}
	
	public void sendMessageAttempt() {
		
	}
}
