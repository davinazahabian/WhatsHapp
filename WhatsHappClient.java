
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;


public class WhatsHappClient {
	
	private int port = -1;
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public WhatsHappClient() {
		if(s != null) {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			s = new Socket("hostname", port);
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) { e.printStackTrace(); }	
	}
	

	
	public void write(String str) throws IOException {
		mWriter.write(str);
	}
	
	public void writeln(String str) throws IOException {
		mWriter.write(str);
		mWriter.newLine();
	}
	
	public void flush() throws IOException {
		mWriter.flush();
	}
	
	public String read() throws IOException {
		return mReader.readLine();
	}
	
	public void readFile(File f) throws IOException {
		FileOutputStream fos = new FileOutputStream(f);
		int x = 0;
		while((x=mReader.read()) != 0) fos.write(x);
		fos.close();
	}

	public boolean isOnline() {
		try {
			writeln(Command.Heartbeat.toString());
			flush();
		} catch (Exception e) { return false; }
		return true;
	}

	public void setUser(String inUsername) {
		mUsername = inUsername;
	}
	
	public boolean isAuthentic() {
		return mUsername != null;
	}
	
	public ConnectionThread() {
		
	}
	
}
