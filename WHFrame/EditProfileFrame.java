package WHFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.User;
import customui.WHButton;

/*
 * 
 * EditProfileFrame - the GUI that displays an editable form for users to edit their profile info,
 * displayed when the user clicks "Edit" on the MyProfileFrame, pressing "Back" allows the user to
 * return to their MyProfileFrame, pressing "Save" allows the user to save the info they've entered,
 * pressing "Next" allows the user to scan through the available avatars to select one they like
 * 
 */

public class EditProfileFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private User currentUser;
	private Color yellowColor;
	private JPanel mainPanel;
	private WHButton backButton;
	private JPanel eventInfoPanel;
	
	JTextField fname;
	JTextField lname;
	JTextField useremail;
	JTextField edituseremail;
	JTextField editusername;
	
	private JLabel username;
	private JLabel email;
	private JLabel userImage;
	private JPanel messageBoardPanel;
	private JPanel conversationPanel;
	private JPanel addPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	private JPanel titlePanel;
	private ImageIcon profilepicture;
	private Vector<ImageIcon> avatars;
	private WHButton saveButton;
	private WHButton nextButton;
	
	private MyProfileFrame myProfile;
	private EditProfileFrame editProfile;

	public EditProfileFrame(MyProfileFrame mpf) {
		this.currentUser = mpf.currentUser;
		this.myProfile = mpf;
		editProfile = this;
		
		this.setSize(new Dimension(900,602));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		yellowColor = new Color(255,204,102);
		mainPanel = new JPanel();
		backButton = new WHButton("< Back");
		eventInfoPanel = new JPanel();
		fname = new JTextField(currentUser.fname());
		lname = new JTextField(currentUser.lname());
		email = new JLabel("Email :");
		username = new JLabel("Username: ");
		edituseremail = new JTextField( currentUser.email());
		editusername = new JTextField( currentUser.username());		
		messageBoardPanel = new JPanel();
		conversationPanel = new JPanel();
		addPanel = new JPanel();
		saveButton = new WHButton("Save");
		nextButton = new WHButton(">");
		westPanel = new JPanel();
		centerPanel = new JPanel();
		centerNorthPanel = new JPanel();
		profilepicture = new ImageIcon("img/default_profile_pic.png");
		userImage = new JLabel (profilepicture);
		titlePanel = new JPanel();
		avatars = new Vector<ImageIcon>();
	}
	
	private void createGUI() {
		mainPanel.setBackground(yellowColor);
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		fname.setFont(new Font ("Impact", Font.PLAIN, 25));
		lname.setFont(new Font ("Impact", Font.PLAIN, 25));
		email.setFont(new Font ("Impact", Font.PLAIN, 25));
		username.setFont(new Font ("Impact", Font.PLAIN, 25));
		edituseremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		editusername.setFont(new Font ("Impact", Font.PLAIN, 25));
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
		conversationPanel.add(nextButton);
		messageBoardPanel.add(conversationPanel, BorderLayout.CENTER);
		addPanel.setSize(100, 100);
		addPanel.setBackground(yellowColor);
		messageBoardPanel.add(addPanel, BorderLayout.SOUTH);
		messageBoardPanel.setBackground(yellowColor);
		add(messageBoardPanel, BorderLayout.EAST);
		titlePanel.setBackground(yellowColor);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(fname);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(lname);
		titlePanel.add(Box.createVerticalGlue());
		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		titlePanel.add(username);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(editusername);	
		titlePanel.add(Box.createRigidArea(new Dimension(25,25)));
		titlePanel.add(email);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(edituseremail);
		titlePanel.add(Box.createRigidArea(new Dimension(0, 750)));
		titlePanel.add(saveButton);
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		mainPanel.add(saveButton, BorderLayout.SOUTH);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	private void addActions() {
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editProfile.setVisible(false);
				myProfile.setVisible(true);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				myProfile.updateUserInfo();
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// TODO
			}
		});
	}
	
}