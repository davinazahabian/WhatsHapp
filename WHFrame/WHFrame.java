package WHFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import WHFrame.ThemeColors;
import customui.WHButton;
import library.ImageLibrary;

public class WHFrame extends JFrame{
	SplashPanel sf;
	
	JTextField utf;
	JPasswordField ptf;
	WHButton loginButton;
	WHButton signUpButton;
	WHButton guestButton;
	JLabel suQuestion; //suQuestion
	JLabel ul;
	JLabel pl;
	
	public WHFrame()
	{
		setTitle("WhatsHapp");
		setSize(900,602);
		sf = new SplashPanel();
		//sf.setLayout(new GridLayout(1,2)); //1 row and 2 columns
		sf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		utf = new JTextField(10);
		ptf = new JPasswordField(10);
		suQuestion = new JLabel("Don't have an account? Register Now!");
		loginButton =  new WHButton("Login");
		signUpButton = new WHButton("Create an account!");
		guestButton = new WHButton("Continue in guest mode");
		ul = new JLabel("Username:  ");
		ul.setForeground(new Color(35, 139, 230)); //change color of text
		
		
		pl = new JLabel("Password:  ");
		pl.setForeground(new Color(35, 139, 230));
		pl.setSize(getWidth(), getHeight());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		sf.add(ul, gbc); //add username label
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.insets = new Insets(8,0,0,0);
		sf.add(pl, gbc); //add password label
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		sf.add(utf, gbc); //add username text field
		
		gbc.gridx = 1;
		gbc.gridy = 1;
	//	gbc.gridwidth = 2;
		gbc.insets = new Insets(8,0,0,0);
		gbc.gridwidth = 2;
		sf.add(ptf, gbc); //add password text field

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(8,0,0,0);
		sf.add(loginButton, gbc); //add login button
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,40,0,0);
		sf.add(suQuestion, gbc); //add question label
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(8,40,0,0);
		sf.add(signUpButton, gbc); //add sign up button
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(8,0,0,0);
		sf.add(guestButton, gbc); //add guest button
		
		add(sf);
	}
	
	public static void main(String [] args)
	{
		WHFrame whf = new WHFrame();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		whf.setCursor(c);
		whf.setVisible(true);
	}
}


