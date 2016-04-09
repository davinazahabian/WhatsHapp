import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class WhatsHappServer extends Thread {
	private ServerSocket ss;
	private Socket s;
	private Vector<FeedThread> feedThreads;
	private Vector<MessageThread> messageThreads;
	private WhatsHappServerThread wst;
	private int currentPort;
	
	// allowable port range : 1024-49151
	// generate new port for each connecting client
	public WhatsHappServer() {
		this.currentPort = 1024;
		
	}
	
	public void run() {
		try {
			ss = new ServerSocket(currentPort);
			currentPort += 1;
			while (true) {
				s = ss.accept();
				// once connection made with client, create a new thread to start accepting and sending connections from client
				wst = new WhatsHappServerThread(s, this);
				wst.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
