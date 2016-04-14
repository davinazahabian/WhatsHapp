package WHFrame;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import customui.OutlinedLabel;
import customui.WHButton;
import library.ImageLibrary;


public class NewUserGUI extends JFrame{
	 JTextField usernameField;
	 JPasswordField passwordField;
	 JPasswordField repasswordField;
	 JTextField firstNameField;
	 JTextField lastNameField;
	 JTextField emailField;
	 
	 OutlinedLabel usernameLabel;
	 OutlinedLabel passwordLabel;
	 OutlinedLabel repasswordLabel;
	 OutlinedLabel firstNameLabel;
	 OutlinedLabel lastNameLabel;
	 OutlinedLabel emailLabel;
	 SplashPanel2 splash;
	 WHButton backButton;
	 WHButton signUpButton;
	 
	 //TODO ADD BACK BUTTON YO
	 
	 public NewUserGUI(){
		 	setTitle("Sign Up!");
			setSize(900,602);
			setMinimumSize(new Dimension(640,480));
			//setJMenuBar(new OfficeMenuBar());
			setLocationRelativeTo(null);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		 initComps();
		 create();
		 addActions();
		 
		 setVisible(true);
	 }

	private void initComps() {
		usernameField = new JTextField(10);
		repasswordField = new JPasswordField(10);
		passwordField = new JPasswordField(10);
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		emailField = new JTextField(10);
		
		usernameLabel = new OutlinedLabel("Username: ");
		usernameLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		usernameLabel.setForeground(new Color(255, 204, 0));
		usernameLabel.setOutlineColor(Color.black);
		usernameLabel.setOpaque(false);
		
		passwordLabel = new OutlinedLabel("Password: ");
		passwordLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		passwordLabel.setForeground(new Color(255, 204, 0));
		passwordLabel.setOutlineColor(Color.black);
		passwordLabel.setOpaque(false);
		
		repasswordLabel = new OutlinedLabel("Repeat Password: ");
		repasswordLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		repasswordLabel.setForeground(new Color(255, 204, 0));
		repasswordLabel.setOutlineColor(Color.black);
		repasswordLabel.setOpaque(false);
		
		firstNameLabel = new OutlinedLabel("First Name: ");
		firstNameLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		firstNameLabel.setForeground(new Color(255, 204, 0));
		firstNameLabel.setOutlineColor(Color.black);
		firstNameLabel.setOpaque(false);
		
		lastNameLabel = new OutlinedLabel("Last Name: ");
		lastNameLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		lastNameLabel.setForeground(new Color(255, 204, 0));
		lastNameLabel.setOutlineColor(Color.black);
		lastNameLabel.setOpaque(false);
		
		emailLabel = new OutlinedLabel("Email: ");
		emailLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		emailLabel.setForeground(new Color(255, 204, 0));
		emailLabel.setOutlineColor(Color.black);
		emailLabel.setOpaque(false);
		
		signUpButton = new WHButton("Sign Up");
		
		
	    backButton = new WHButton("< Back");
	   
	    splash = new SplashPanel2();
	}
	
	private void create() {
		setLayout(new GridLayout(1, 1));
		splash.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		splash.add(firstNameLabel,c);
		splash.add(firstNameField,c);
		
		c.gridy = 2;
		splash.add(lastNameLabel,c);
		splash.add(lastNameField,c);
		
		
		c.gridy = 3;
		splash.add(usernameLabel,c);
		splash.add(usernameField,c);
		
		c.gridy = 4;
		splash.add(emailLabel,c);
		splash.add(emailField,c);
		
		c.gridy = 5;
		splash.add(passwordLabel,c);
		splash.add(passwordField,c);
		
		c.gridy = 6;
		splash.add(repasswordLabel,c);
		splash.add(repasswordField,c);
		
		
		c.gridy = 7;
		c.gridwidth = 7;
		splash.add(signUpButton,c);
		
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		
//		JPanel panel = new JPanel();
//		panel.setLayout(new GridLayout(1, 1));
//		panel.setSize(new Dimension(50, 50));
//		panel.splash.add(backButton);
//		panel.setSize(new Dimension(50, 50));
//		splash.add(panel);
		add(splash);
	}

	private void addActions() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
	
		//whf.setCursor(c);
		new NewUserGUI();
	}
}

class SplashPanel2 extends JPanel {
	private static final long serialVersionUID = 7141608019316770268L;

	private static final Image mBackgroundImage;
	private static final String mTitle = "WhatsHapp";

	static {
		mBackgroundImage = ImageLibrary.getImage("img/campus.jpg");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font font = new Font("Phosphate", Font.PLAIN, 100);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int heightc = metrics.getHeight()/2;
		int widthc = metrics.stringWidth(mTitle)/2;
		int x = (getWidth()/2) - widthc;
		int y = (getHeight()/4) - heightc;
		
		g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(new Color(128, 0, 0));
		g.drawString(mTitle, ShiftWest(x, 1), ShiftNorth(y, 1));
		g.drawString(mTitle, ShiftWest(x, 1), ShiftSouth(y, 1));
		g.drawString(mTitle, ShiftEast(x, 1), ShiftNorth(y, 1));
		g.drawString(mTitle, ShiftEast(x, 1), ShiftSouth(y, 1));
		g.setColor(new Color(255, 204, 0));
		g.drawString(mTitle, x, y);
		
//		g.setColor(new Color(35, 139, 230));
//		Font font = g.getFont().deriveFont(40.0f);
//		g.setFont(font);
//		FontMetrics metrics = g.getFontMetrics(font);
//		int heightc = metrics.getHeight()/2;
//		int widthc = metrics.stringWidth(mTitle)/2;
//		g.drawString(mTitle, (getWidth()/2) - widthc, (getHeight()/3) - heightc);
	}
	int ShiftNorth(int p, int distance) {
		return (p - distance);
	}
	int ShiftSouth(int p, int distance) {
		return (p + distance);
	}
	int ShiftEast(int p, int distance) {
		return (p + distance);
	}
	int ShiftWest(int p, int distance) {
		return (p - distance);
	}
}

