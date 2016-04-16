package WHFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
	private WHButton backButton;
	private WHButton saveButton;
	private WHButton nextButton;
	JTextField fname;
	JTextField lname;
	JTextField useremail;
	JTextField edituseremail;
	JTextField editusername;
	private JLabel username;
	private JLabel email;
	
	private JPanel mainPanel;
	private JPanel eastPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	private JPanel titlePanel;

	private JPanel imagePanel = new JPanel();
	
	private ImageIcon defaultAvatar = new ImageIcon("img/default_profile_pic.png");
	private JPanel defaultPanel = new JPanel();
	private JLabel defaultLabel;
	
	private final ImageIcon millerAvatar = new ImageIcon("img/Avatar1-1.png");
	private JPanel millerPanel = new JPanel();
	private JLabel millerLabel;

	private final ImageIcon koalaAvatar = new ImageIcon("img/Avatar2.png");
	private JPanel koalaPanel = new JPanel();
	private JLabel koalaLabel;

	private final ImageIcon linkAvatar = new ImageIcon("img/Avatar3.png");
	private JPanel linkPanel = new JPanel();
	private JLabel linkLabel;

	private final ImageIcon peachAvatar = new ImageIcon("img/Avatar4.png");
	private JPanel peachPanel = new JPanel();
	private JLabel peachLabel;

	private final ImageIcon trumpAvatar = new ImageIcon("img/Avatar5.png");
	private JPanel trumpPanel = new JPanel();
	private JLabel trumpLabel;

	private final ImageIcon dogeAvatar = new ImageIcon("img/Avatar6.png");
	private JPanel dogePanel = new JPanel();
	private JLabel dogeLabel;


	private int pictureIndex = 0;

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
		fname = new JTextField(currentUser.fname());
		lname = new JTextField(currentUser.lname());
		email = new JLabel("Email :");
		username = new JLabel("Username: ");
		edituseremail = new JTextField( currentUser.email());
		editusername = new JTextField( currentUser.username());		
		eastPanel = new JPanel();
		saveButton = new WHButton("Save");
		nextButton = new WHButton(">");
		westPanel = new JPanel();
		centerPanel = new JPanel();
		centerNorthPanel = new JPanel();
		titlePanel = new JPanel();
		defaultLabel = new JLabel(defaultAvatar);
		millerLabel = new JLabel(millerAvatar);
		koalaLabel = new JLabel(koalaAvatar);
		linkLabel = new JLabel(linkAvatar);
		peachLabel = new JLabel(peachAvatar);
		trumpLabel = new JLabel(trumpAvatar);
		dogeLabel = new JLabel(dogeAvatar);
	}
	
	private void createGUI() {
		eastPanel.setPreferredSize(new Dimension (300,600));
		
		eastPanel.setLayout(new BorderLayout());
		imagePanel.setLayout(new CardLayout());
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

		mainPanel.setBackground(yellowColor);
		eastPanel.setBackground(yellowColor);
		imagePanel.setBackground(yellowColor);
		westPanel.setBackground(yellowColor);
		centerPanel.setBackground(yellowColor);
		centerNorthPanel.setBackground(yellowColor);
		titlePanel.setBackground(yellowColor);

		fname.setFont(new Font ("Impact", Font.PLAIN, 25));
		lname.setFont(new Font ("Impact", Font.PLAIN, 25));
		email.setFont(new Font ("Impact", Font.PLAIN, 25));
		username.setFont(new Font ("Impact", Font.PLAIN, 25));
		edituseremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		editusername.setFont(new Font ("Impact", Font.PLAIN, 25));
		
		westPanel.add(backButton, BorderLayout.NORTH);
		
		defaultPanel.add(defaultLabel);
		millerPanel.add(millerLabel);
		koalaPanel.add(koalaLabel);
		linkPanel.add(linkLabel);
		peachPanel.add(peachLabel);
		trumpPanel.add(trumpLabel);
		dogePanel.add(dogeLabel);
		
		imagePanel.add(defaultPanel);
		imagePanel.add(millerPanel);
		imagePanel.add(koalaPanel);
		imagePanel.add(linkPanel);
		imagePanel.add(peachPanel);
		imagePanel.add(trumpPanel);
		imagePanel.add(dogePanel);
		eastPanel.add(imagePanel, BorderLayout.CENTER);
		eastPanel.add(nextButton, BorderLayout.SOUTH);
		
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
		
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
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
				CardLayout cl = (CardLayout)imagePanel.getLayout();
				cl.next(imagePanel);
			}
		});
	}
	
}