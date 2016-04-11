import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
			Package p;
			try {
				p = (Package)ois.readObject();
				if (p.isGuest()) {
					
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    }
    
	public void sendToClient(Package response) {
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
}
