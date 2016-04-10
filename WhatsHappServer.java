import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class WhatsHappServer extends Thread {
	
	private ServerSocket ss = null;
	private static Vector<Event> allEvents;
	private ArrayList<Socket> sockets;
	private Vector<FeedThread> feedThreads;
	private Vector<MessageThread> messageThreads;
	private int currentPort;
	
	
	// allowable port range : 1024-49151
	// generate new port for each connecting client
	public WhatsHappServer() {
		this.currentPort = 1024;
		this.start();
	}
	
	
	// wait for connections...
	public void run() {
		try {
			ss = new ServerSocket(currentPort);
			currentPort += 1;
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
