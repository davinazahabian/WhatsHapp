
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
	
	protected Socket s;
	private int port = 6789;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private WHFrame whf;
//	private EventFeedGUI efg;
	
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
		}
	}
	
	public void run() {
		while(true) {
			
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
			
			Package p = null;
			try {
				p = (Package)ois.readObject();
				if (p != null) {
					
				}
			}
		}
	}
	
	public void guestAttempt() {
		Package p = new Package();
		p.setGuest(true);
		try{
			oos.writeObject(p);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
		
		
}
