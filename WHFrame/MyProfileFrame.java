package WHFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Model.User;
import client.WHClient;
import customui.WHButton;
import library.ImageLibrary;

/* 
 * 
 * MyProfileFrame - the GUI that displays a user's profile information, which is shown after the
 * user clicks the "About Me" button; when "Edit" is pressed, the EditProfileFrame is shown to
 * allow the user to edit their profile information; when "Back" is pressed, the user is returned
 * to the MainFeedFrame
 * 
 */

public class MyProfileFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	User currentUser;
	private Color yellowColor;
	private JPanel mainPanel;
	private WHButton backButton;
	private JLabel name;
	private JLabel useremail;
	private JLabel username;
	private JPanel eventInfoPanel;
	private JLabel userImage;
	private JPanel eastPanel;
	private JPanel imagePanel;
	private JPanel addPanel;
	private JPanel westPanel;
	private ImageIcon profilepicture;
	private WHButton editButton;
	private WHButton signoutButton;
	private JPanel titlePanel;
	private JPanel westButtonPanel;
	
	private EditProfileFrame editProfile;
	private MyProfileFrame myProfile;
	private WHClient whClient;


	public MyProfileFrame(User u, WHClient whClient) {
		this.setSize(new Dimension(750,500));
		this.setMinimumSize(new Dimension(750,500));
		this.setMaximumSize(new Dimension(750,500));
		this.whClient = whClient;
		this.currentUser = u;
		myProfile = this;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());

		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		setCursor(c);
		editProfile = new EditProfileFrame(this);
		yellowColor = new Color(255,204,102);
		mainPanel = new JPanel();
		mainPanel.setBackground(yellowColor);
		backButton = new WHButton("  < Back ");
		eventInfoPanel = new JPanel();
		name = new JLabel(currentUser.fname() + " " + currentUser.lname());
		useremail = new JLabel("Email: " + currentUser.email());
		username = new JLabel("Username: " + currentUser.username());
		eastPanel = new JPanel();
		imagePanel = new JPanel();
		addPanel = new JPanel();
		editButton = new WHButton("    Edit    ");
		signoutButton = new WHButton ("Sign Out");
		westButtonPanel = new JPanel();
		westPanel = new JPanel();
		profilepicture = new ImageIcon("img/default_profile_pic.png");
		userImage = new JLabel (profilepicture);
		titlePanel = new JPanel();
	}
	
	private void createGUI() {
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		name.setFont(new Font ("Impact", Font.PLAIN, 25));
		useremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		username.setFont(new Font ("Impact", Font.PLAIN, 25));
		eastPanel.setBackground(yellowColor);
		imagePanel.setBackground(yellowColor);
		eastPanel.setLayout(new BorderLayout());
		eastPanel.setBackground(yellowColor);
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(yellowColor);
		westPanel.setBackground(yellowColor);
		westButtonPanel.setLayout(new BoxLayout(westButtonPanel, BoxLayout.Y_AXIS));
		westButtonPanel.setBackground(yellowColor);
		westButtonPanel.add(backButton);
		westButtonPanel.add(Box.createRigidArea(new Dimension(10,30)));
		westButtonPanel.add(editButton);
		westButtonPanel.add(Box.createRigidArea(new Dimension(10,30)));
		westButtonPanel.add(signoutButton);
		westPanel.add(westButtonPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		eastPanel.setPreferredSize(new Dimension (300,600));
		imagePanel.add(userImage);
		eastPanel.add(imagePanel, BorderLayout.CENTER);
		addPanel.setSize(100, 100);
		addPanel.setBackground(yellowColor);
		eastPanel.add(addPanel, BorderLayout.SOUTH);
		eastPanel.setBackground(yellowColor);
		add(eastPanel, BorderLayout.EAST);
		name.setFont(new Font ("Phosphate", Font.BOLD, 45));
		titlePanel.setBackground(yellowColor);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(name);
		titlePanel.add(Box.createVerticalGlue());
		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		titlePanel.add(username);
		titlePanel.add(Box.createRigidArea(new Dimension(25,25)));		
		titlePanel.add(useremail);
		titlePanel.add(Box.createRigidArea(new Dimension(10, 850)));
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void addActions() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				myProfile.setVisible(false);
				whClient.getMff().setVisible(true);
			}
		});
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				myProfile.setVisible(false);
				editProfile.setVisible(true);
			}
		});
		
		signoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				myProfile.setVisible(false);
				whClient.setMpf(null);
				whClient.getWhf().clearTextFields();
				whClient.getWhf().setVisible(true);
			}
		});
	}
	
	void updateUserInfo() {
		String newName = editProfile.fname.getText() + " " + editProfile.lname.getText();
		name.setText(newName);
		useremail.setText("Email: " + editProfile.edituseremail.getText());
		username.setText("Username: " + editProfile.editusername.getText());
		profilepicture = editProfile.pictures.get(editProfile.pictureIndex);
		userImage.setIcon(profilepicture);
		whClient.setMpf(this);
	}
}