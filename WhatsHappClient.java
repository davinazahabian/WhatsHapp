
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
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class WhatsHappClient {
	
	protected Socket s;
	private int port = 6789;
	private WhatsHappClientThread wct;
	public boolean isDone = false;
	private boolean isConnected = false;
	
	//private SplashScreenGUI ssg;
	
	public WhatsHappClient() {
		//ssg = new SplashScreenGUI();
		// ssg.setVisible(true);
		
		try {
			s = new Socket("localhost", port);
			isConnected = true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
		
		
}
