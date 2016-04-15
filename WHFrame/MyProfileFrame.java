package WHFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.Event;
import Model.User;
import customui.WHButton;

public class MyProfileFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private User currentUser;
	private Color yellowColor;
	private JPanel mainPanel;
	private WHButton backButton;
	private JLabel name;
	private JPanel eventInfoPanel;
	private JLabel useremail;
	private JLabel username;
	private JLabel userImage;
	private JPanel messageBoardPanel;
	private JPanel conversationPanel;
	private JPanel addPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	private ImageIcon profilepicture;
	private WHButton editButton;
	private JPanel titlePanel;
	
	private EditProfileFrame editProfile;
	private MyProfileFrame myProfile;


	public MyProfileFrame(User u) {
		this.setSize(new Dimension(900,602));
		this.currentUser = u;
		myProfile = this;
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		editProfile = new EditProfileFrame(currentUser);
		yellowColor = new Color(255,204,102);
		mainPanel = new JPanel();
		mainPanel.setBackground(yellowColor);
		backButton = new WHButton("< Back");
		eventInfoPanel = new JPanel();
		name = new JLabel(currentUser.fname() + " " + currentUser.lname());
		useremail = new JLabel("Email: " + currentUser.email());
		username = new JLabel("Username: " + currentUser.username());
		messageBoardPanel = new JPanel();
		conversationPanel = new JPanel();
		addPanel = new JPanel();
		editButton = new WHButton("Edit");
		westPanel = new JPanel();
		centerPanel = new JPanel();
		centerNorthPanel = new JPanel();		
		profilepicture = new ImageIcon("img/default_profile_pic.png");
		userImage = new JLabel (profilepicture);
		titlePanel = new JPanel();
	}
	
	private void createGUI() {
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		name.setFont(new Font ("Impact", Font.PLAIN, 25));
		useremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		username.setFont(new Font ("Impact", Font.PLAIN, 25));
		messageBoardPanel.setBackground(yellowColor);
		conversationPanel.setBackground(yellowColor);
		messageBoardPanel.setLayout(new BorderLayout());
		messageBoardPanel.setBackground(yellowColor);
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(yellowColor);
		westPanel.setBackground(yellowColor);
		centerPanel.setBackground(yellowColor);
		centerNorthPanel.setBackground(yellowColor);
		westPanel.add(backButton, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		messageBoardPanel.setPreferredSize(new Dimension (300,600));
		conversationPanel.add(userImage);
		messageBoardPanel.add(conversationPanel, BorderLayout.CENTER);
		addPanel.setSize(100, 100);
		addPanel.setBackground(yellowColor);
		messageBoardPanel.add(addPanel, BorderLayout.SOUTH);
		messageBoardPanel.setBackground(yellowColor);
		add(messageBoardPanel, BorderLayout.EAST);
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
		titlePanel.add(editButton);	
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		mainPanel.add(editButton, BorderLayout.SOUTH);
		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void addActions() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO: go back to main feed page.
			}
		});
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				myProfile.setVisible(false);
				editProfile.setVisible(true);
			}
		});
	}
	
	public static void main (String [] args) {
		User u = new User("Ziad", "Azar", "zazar@usc.edu", "zezefresh", "love2code");
		MyProfileFrame mpf = new MyProfileFrame(u);
		mpf.setVisible(true);
		mpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}