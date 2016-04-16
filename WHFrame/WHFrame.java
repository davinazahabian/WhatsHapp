package WHFrame;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import Model.InfoPackage;
import WHFrame.SplashPanel;
import client.WHClient;
import customui.OutlinedLabel;
//import WHFrame.ThemeColors;
import customui.WHButton;
import library.ImageLibrary;

public class WHFrame extends JFrame{
	SplashPanel sf;
	JTextField utf;
	JPasswordField ptf;
	WHButton loginButton;
	WHButton signUpButton;
	WHButton guestButton;
	OutlinedLabel suQuestion; //suQuestion
	OutlinedLabel ul;
	OutlinedLabel pl;
	WHClient mClient;
	public WHFrame(WHClient whClient)
	{
		this.mClient = whClient;
		setTitle("WhatsHapp");
		setSize(900,602);
		mClient = whClient;
		sf = new SplashPanel();
		//sf.setLayout(new GridLayout(1,2)); //1 row and 2 columns
		sf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		utf = new JTextField(10);
		ptf = new JPasswordField(10);
		suQuestion = new OutlinedLabel("Don't have an account? Register Now!");
		suQuestion.setFont(new Font ("Impact", Font.BOLD, 15));
		suQuestion.setForeground(new Color(255, 204, 0));
		suQuestion.setOutlineColor(Color.black);
		suQuestion.setOpaque(false);
		
		loginButton =  new WHButton("Login");
		signUpButton = new WHButton("Create an account!");
		guestButton = new WHButton("Continue in guest mode");
		ul = new OutlinedLabel("Username:  ");
		ul.setFont(new Font ("Impact", Font.BOLD, 15));
		ul.setForeground(new Color(255, 204, 0));
		ul.setOutlineColor(Color.black);
		ul.setOpaque(false);
		
		pl = new OutlinedLabel("Password:  ");
		pl.setFont(new Font ("Impact", Font.BOLD, 15));
		pl.setForeground(new Color(255, 204, 0));
		pl.setOutlineColor(Color.black);
		pl.setOpaque(false);
		
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
		addActions();
		setVisible(true);
	}
	
	private void addActions() {
		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WHFrame.this.setVisible(false);
				mClient.setNug(new NewUserGUI(mClient));
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = utf.getText();
				String password = ptf.getPassword().toString();
				InfoPackage ip = new InfoPackage();
				ip.setLogin(true);
				ip.setUsername(username);
				ip.setPassword(password);
				mClient.sendToServer(ip);
			}
		});

	}

	public static void setUIFont (javax.swing.plaf.FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get (key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put (key, f);//font setter
		}
	}

	public void showError() {
		JOptionPane.showMessageDialog(this, "Username or password incorrect");
	}
	
//	public static void main(String [] args)
//	{
//		WHFrame whf = new WHFrame();//random comment to make commit
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
//		whf.setCursor(c);
//		whf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		
//		try {
//			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
//		try {
//			setUIFont(new javax.swing.plaf.FontUIResource(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KaushanScript-Regular.ttf")).deriveFont(12.0f)));
//		} catch (FontFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		whf.setVisible(true);
//	}
}


