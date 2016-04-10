import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class WhatsHappServer extends Thread {
	
	private Vector<WhatsHappServerThread> serverThreads;
	private ServerSocket ss = null;
	private static Vector<Event> allEvents;
	private ArrayList<Socket> sockets;
	private int port = 6789;
	public MySQLDriver driver;
	
	public WhatsHappServer() throws IOException {
		this.start();
	}
	
	
	// wait for connections...
	public void run() {
		try {
			ss = new ServerSocket(port);
			while (true) {
				Socket s = ss.accept();
				// once connection made with client, create a new thread to start accepting and sending connections from client
				(new WhatsHappServerThread(s, this)).start();
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
	
	
	
}
