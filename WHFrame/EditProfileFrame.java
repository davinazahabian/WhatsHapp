package WHFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import library.ImageLibrary;

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
	private JPanel westButtonPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	private JPanel titlePanel;
	private JPanel buttonPanel;

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

	final ImageIcon trumpAvatar = new ImageIcon("img/Avatar5.png");
	private JPanel trumpPanel = new JPanel();
	private JLabel trumpLabel;

	private final ImageIcon dogeAvatar = new ImageIcon("img/Avatar6.png");
	private JPanel dogePanel = new JPanel();
	private JLabel dogeLabel;

	
	int pictureIndex = 0;
	ArrayList<ImageIcon> pictures;

	private MyProfileFrame myProfile;
	private EditProfileFrame editProfile;

	public EditProfileFrame(MyProfileFrame mpf) {
		this.currentUser = mpf.currentUser;
		this.myProfile = mpf;
		editProfile = this;
		pictures = new ArrayList<ImageIcon>();
		this.setSize(new Dimension(640,550));
		this.setMinimumSize(new Dimension(640,550));
		this.setMaximumSize(new Dimension(640,550));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(yellowColor);
		setLocationRelativeTo(null);
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		setCursor(c);
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
		buttonPanel = new JPanel();
		saveButton = new WHButton("  Save  ");
		nextButton = new WHButton(">");
		westPanel = new JPanel();
		westButtonPanel = new JPanel();
		centerPanel = new JPanel();
		centerNorthPanel = new JPanel();
		titlePanel = new JPanel();
		defaultLabel = new JLabel(defaultAvatar);
		pictures.add(defaultAvatar);
		millerLabel = new JLabel(millerAvatar);
		pictures.add(millerAvatar);
		koalaLabel = new JLabel(koalaAvatar);
		pictures.add(koalaAvatar);
		linkLabel = new JLabel(linkAvatar);
		pictures.add(linkAvatar);
		peachLabel = new JLabel(peachAvatar);
		pictures.add(peachAvatar);
		trumpLabel = new JLabel(trumpAvatar);
		pictures.add(trumpAvatar);
		dogeLabel = new JLabel(dogeAvatar);
		pictures.add(dogeAvatar);
		
	}
	
	private void createGUI() {
		eastPanel.setPreferredSize(new Dimension (300,600));
		
		eastPanel.setLayout(new BorderLayout());
		
		imagePanel.setLayout(new CardLayout());
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		
		mainPanel.setBackground(yellowColor);
		eastPanel.setBackground(yellowColor);
		buttonPanel.setBackground(yellowColor);
		imagePanel.setBackground(yellowColor);
		westPanel.setBackground(yellowColor);
		centerPanel.setBackground(yellowColor);
		centerNorthPanel.setBackground(yellowColor);
		titlePanel.setBackground(yellowColor);
		defaultPanel.setBackground(yellowColor);
		millerPanel.setBackground(yellowColor);
		koalaPanel.setBackground(yellowColor);
		linkPanel.setBackground(yellowColor);
		peachLabel.setBackground(yellowColor);
		peachPanel.setBackground(yellowColor);
		trumpPanel.setBackground(yellowColor);
		dogePanel.setBackground(yellowColor);
		
		fname.setFont(new Font ("Impact", Font.PLAIN, 25));
		lname.setFont(new Font ("Impact", Font.PLAIN, 25));
		email.setFont(new Font ("Impact", Font.PLAIN, 25));
		username.setFont(new Font ("Impact", Font.PLAIN, 25));
		edituseremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		editusername.setFont(new Font ("Impact", Font.PLAIN, 25));
	
		
										// picture indexes:
		defaultPanel.add(defaultLabel);	// 0
		millerPanel.add(millerLabel);	// 1
		koalaPanel.add(koalaLabel);		// 2
		linkPanel.add(linkLabel);		// 3
		peachPanel.add(peachLabel);		// 4
		trumpPanel.add(trumpLabel);		// 5
		dogePanel.add(dogeLabel);		// 6
		
		imagePanel.add(defaultPanel);
		imagePanel.add(millerPanel);
		imagePanel.add(koalaPanel);
		imagePanel.add(linkPanel);
		imagePanel.add(peachPanel);
		imagePanel.add(trumpPanel);
		imagePanel.add(dogePanel);
		eastPanel.add(imagePanel, BorderLayout.NORTH);

		buttonPanel.add(nextButton, BorderLayout.NORTH);
		eastPanel.add(buttonPanel, BorderLayout.CENTER);
		
		
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
		
		
		westButtonPanel.setLayout(new BoxLayout(westButtonPanel, BoxLayout.Y_AXIS));
		westButtonPanel.setBackground(yellowColor);
		westButtonPanel.add(backButton);
		westButtonPanel.add(Box.createRigidArea(new Dimension(15,30)));
		westButtonPanel.add(saveButton);
		westPanel.add(westButtonPanel, BorderLayout.NORTH);
		
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
				editProfile.setVisible(false);
				myProfile.setVisible(true);
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				CardLayout cl = (CardLayout)imagePanel.getLayout();
				cl.next(imagePanel);
				if (pictureIndex == 6) {pictureIndex = 0;}
				pictureIndex += 1;
			}
		});
	}
	
}