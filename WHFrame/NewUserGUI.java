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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import customui.WHButton;
import library.ImageLibrary;


public class NewUserGUI extends JFrame{
	 JTextField usernameField;
	 JTextField passwordField;
	 JTextField firstNameField;
	 JTextField lastNameField;
	 JTextField emailField;
	 
	 JLabel usernameLabel;
	 JLabel passwordLabel;
	 JLabel firstNameLabel;
	 JLabel lastNameLabel;
	 JLabel emailLabel;
	 SplashPanel2 splash;
	 WHButton backButton;
	 WHButton signUpButton;
	 
	 
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
		passwordField = new JTextField(10);
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		emailField = new JTextField(10);
		
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		emailLabel = new JLabel("Email: ");
		
		signUpButton = new WHButton("Sign Up");
		
		ImageIcon water = new ImageIcon("back-icon.png");
	    backButton = new WHButton(water);
	   
	    splash = new SplashPanel2();
	}
	
	private void create() {
		setLayout(new GridLayout(1, 1));
		splash.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		splash.add(usernameLabel,c);
		splash.add(usernameField,c);
		c.gridy = 2;
		splash.add(passwordLabel,c);
		splash.add(passwordField,c);
		
		c.gridy = 3;
		splash.add(firstNameLabel,c);
		splash.add(firstNameField,c);
		
		c.gridy = 4;
		splash.add(lastNameLabel,c);
		splash.add(lastNameField,c);
		
		c.gridy = 5;
		splash.add(emailLabel,c);
		splash.add(emailField,c);
		
		c.gridy = 6;
		c.gridwidth = 6;
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
		mBackgroundImage = ImageLibrary.getImage("img/splash.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(35, 139, 230));
		Font font = g.getFont().deriveFont(40.0f);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int heightc = metrics.getHeight()/2;
		int widthc = metrics.stringWidth(mTitle)/2;
		g.drawString(mTitle, (getWidth()/2) - widthc, (getHeight()/3) - heightc);
	}
}

