import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import dzahabia_CSCI201L_Assignment3.Package;

public class WhatsHappServerThread extends Thread {
	
	private Socket s;
    private ObjectOutputStream oos;
	private ObjectInputStream ois;
    
    public WhatsHappServerThread(Socket s, WhatsHappServer whs) {
		this.s = s;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }
	}
    
    public void run() {
    	boolean isDone = false;
    	while(!isDone) {
			Package p = (Package)ois.readObject();
			if(p.identifier().equals("user")) {
				
			} else if(p.identifier().equals("event")) {
				
			} else if(p.identifier().equals("message")) {
				
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
