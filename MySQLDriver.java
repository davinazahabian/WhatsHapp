
import java.sql.SQLException;

import com.mysql.jdbc.Driver;
import java.sql.*; 


public class MySQLDriver {
	
	private Connection con;
	private Connection con2;
	private Statement stmt = null;
	private final static String insertUser = "INSERT INTO USERS(USERNAME,PASSWORD,EMAIL, FNAME, LNAME) VALUES(?,?,?,?,?)";
	private final static String selectUser = "SELECT * FROM USERS WHERE USERNAME=?";
	//private final static String passWord = "SELECT Password FROM Users WHERE USERNAME"
	private final static String createTable = "CREATE TABLE Users"
			+ "(Password int,"
			+ "Username varchar(255));";
	private final static String insertFileName = "UPDATE USERS SET FILENAMES=? WHERE USERNAME = ?";
	private final static String getFileNames = "SELECT FILENAMES FROM USERS WHERE USERNAME = ?";
	
	public MySQLDriver(){
		try{
			new Driver();
		}catch(SQLException sqe){
			sqe.printStackTrace();
		}
		connect();

		try {
			con2  = DriverManager.getConnection("jdbc:mysql://localhost:3306/WhatsHapp?user=root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connect(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root");
		}catch(SQLException dqe){
			dqe.printStackTrace();
		}
	}
	
	public void stop(){
		try{con.close();} catch (SQLException e) {e.printStackTrace();}
	}
	
	public String getFileNames(String username){
		try {
			PreparedStatement ps = con2.prepareStatement(getFileNames);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			if(result.next())
			{
				String temp = result.getString(1);
				if(temp == null)
					return "";
				temp = temp.trim();
				if(temp.equals("null"))
					return "";
				else
					return result.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String[] getFileName(String username){
		String text = getFileNames(username);
		String[] splited = text.split(" ");
		return splited;
	}
	public void insertFile (String username, String filename){
		String[] names = getFileName(username);
		for (int i = 0; i < names.length; i++) {
			if(filename.equals(names[i]))
				return;
		}
		System.out.println("Username = " + username  + " Filename = " + filename);
		String text = getFileNames(username) + " " + filename ;
		System.out.println("Text = " + text);
		try{
			PreparedStatement ps = con2.prepareStatement(insertFileName);
			ps.setString(1, text);
			ps.setString(2, username);
			ps.executeUpdate();
			//FactoryServerGUI.addMessage("Adding Product:" + productName + "to table with count 0");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public boolean doesExist(String userName){
		try{
			PreparedStatement ps = con2.prepareStatement(selectUser);
			ps.setString(1, userName);
			ResultSet  result = ps.executeQuery();
			while(result.next()){
				return true;
			}
		}catch(SQLException e){ e.printStackTrace();}
			return false;
		
	}
	
	public boolean validate(String username, int passWord){
		if(!doesExist(username)){
			return false;
		}
		try{
		PreparedStatement ps = con2.prepareStatement(selectUser);
		ps.setString(1, username);
		ResultSet  result = ps.executeQuery();
		while(result.next()){
			System.out.println("database: " + result.getInt(1) + "  Input: " + passWord);
			if(result.getInt(1) != passWord)
			{
				System.out.println("database: " + result.getInt(1) + "  Input: " + passWord);
				return false;
			}
		}}catch(SQLException sqe){};
		return true;
	}

	public void addUser (String username, int passWord, String email, String fname, String lname){
		try{
			PreparedStatement ps = con2.prepareStatement(insertUser);
			ps.setString(1, username);
			ps.setInt(2, passWord);
			ps.setString(3, email);
			ps.setString(4, fname);
			ps.setString(5, lname);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args){
		MySQLDriver driver = new MySQLDriver();
		driver.addUser("ncheruku", 1296, "ncheruku@usc.edu", "Nikhil", "Cherukuri");
	}
}

