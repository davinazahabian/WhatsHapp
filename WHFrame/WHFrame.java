package WHFrame;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;

import java.awt.Insets;

import java.awt.Point;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JPasswordField;

import javax.swing.JTextField;

import javax.swing.UIManager;

import Model.Event;
import Model.InfoPackage;

import WHFrame.SplashPanel;

import client.WHClient;

import customui.OutlinedLabel;

import customui.WHButton;

import library.ImageLibrary;

/*
 * 
 * WHFrame - displays a form for returning users to enter their credentials to log in, credentials
 * will be verified with the database; also has the option to sign up or continue as a guest; the
 * first frame seen by the user upon launch
 * 
 */

public class WHFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private SplashPanel background;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private WHButton loginButton;
	private WHButton signUpButton;
	private WHButton guestButton;
	private OutlinedLabel registerNow;
	private OutlinedLabel usernameLabel;
	private OutlinedLabel passwordLabel;
	private WHClient whClient;

	public WHFrame(WHClient whClient) {
		this.whClient = whClient;
		//setIconImage(new ImageIcon("img/icon.png").getImage());

		setTitle("WhatsHapp");
		setSize(900,602);

		this.setMinimumSize(new Dimension(900,602));
		this.setMaximumSize(new Dimension(900,602));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		instantiateComponents();
		createGUI();
		addActions();
		setVisible(true);
		setLocationRelativeTo(null);
	}


	private void instantiateComponents() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		setCursor(c);
		background = new SplashPanel();
		usernameTextField = new JTextField(10);
		passwordTextField = new JPasswordField(10);
		registerNow = new OutlinedLabel("Don't have an account? Register Now!");
		loginButton =  new WHButton("Login");
		signUpButton = new WHButton("    Create an account!    ");
		guestButton = new WHButton("Continue in guest mode");
		usernameLabel = new OutlinedLabel("Username:  ");
		passwordLabel = new OutlinedLabel("Password:  ");
	}


	private void createGUI() {
		//sf.setLayout(new GridLayout(1,2)); //1 row and 2 columns
		background.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		registerNow.setFont(new Font ("Impact", Font.BOLD, 15));
		registerNow.setForeground(new Color(255, 204, 0));
		registerNow.setOutlineColor(Color.black);
		registerNow.setOpaque(false);
		usernameLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		usernameLabel.setForeground(new Color(255, 204, 0));
		usernameLabel.setOutlineColor(Color.black);
		usernameLabel.setOpaque(false);
		passwordLabel.setFont(new Font ("Impact", Font.BOLD, 15));
		passwordLabel.setForeground(new Color(255, 204, 0));
		passwordLabel.setOutlineColor(Color.black);
		passwordLabel.setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		background.add(usernameLabel, gbc); //add username label

		gbc.gridx = 0;
		gbc.gridy = 1;

		gbc.insets = new Insets(8,0,0,0);

		background.add(passwordLabel, gbc); //add password label


		gbc.gridx = 1;

		gbc.gridy = 0;

		gbc.insets = new Insets(0,0,0,0);

		background.add(usernameTextField, gbc); //add username text field


		gbc.gridx = 1;

		gbc.gridy = 1;

		//	gbc.gridwidth = 2;

		gbc.insets = new Insets(8,0,0,0);

		gbc.gridwidth = 2;

		background.add(passwordTextField, gbc); //add password text field



		gbc.gridx = 1;

		gbc.gridy = 2;

		gbc.insets = new Insets(8,0,0,0);

		background.add(loginButton, gbc); //add login button


		gbc.gridx = 2;

		gbc.gridy = 0;

		gbc.insets = new Insets(0,75,0,0);

		background.add(registerNow, gbc); //add question label


		gbc.gridx = 2;

		gbc.gridy = 1;

		gbc.insets = new Insets(8,80,0,0);

		background.add(signUpButton, gbc); //add sign up button


		gbc.gridx = 2;

		gbc.gridy = 2;

		gbc.insets = new Insets(8,80,0,0);

		background.add(guestButton, gbc); //add guest button


		add(background);

	}


	private void addActions() {

		signUpButton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				WHFrame.this.setVisible(false);

				whClient.setNug(new NewUserGUI(whClient));

			}

		});


		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				String password = new String(passwordTextField.getPassword());
				System.out.println(password);
				whClient.loginRequest(username, password);
			}

		});

		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				WHFrame.this.setVisible(false);
//				whClient.setMff(new MainFeedFrame(whClient));
				whClient.guestRequest();
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
	
	public void showSuccess(Vector<Event> allEvents) {
		JOptionPane.showMessageDialog(this, "Login successful :) Welcome to WhatsHapp!");
		this.setVisible(false);
		whClient.setMff(new MainFeedFrame(whClient));
		whClient.getMff().populateFeed(allEvents);
	}
	
	public void clearTextFields() {
		this.usernameTextField.setText(null);
		this.passwordTextField.setText(null);
	}
}