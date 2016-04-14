package WHFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Event;
import Model.User;
import customui.WHButton;

public class EditProfileFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private User u;
	private Color yellowColor;
	private JPanel mainPanel;
	
	private WHButton backButton;
	private JPanel eventInfoPanel;
	private JTextField fname;
	private JTextField lname;

	private JTextField useremail;
	private JLabel username;
	private JLabel editname;
	private JLabel email;
	private JTextField edituseremail;
	private JTextField editusername;
	private JLabel userImage;
	
	private JPanel messageBoardPanel;
	private JPanel conversationPanel;
	private JPanel addPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	
	
	private ImageIcon profilepicture;
	private WHButton save;
	private WHButton next;

	public EditProfileFrame(User u) {
		
		this.setSize(new Dimension(900,602));
		this.u = u;
		
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		System.out.println("break 00");
		
		yellowColor = new Color(255,204,102);
		mainPanel = new JPanel();
		mainPanel.setBackground(yellowColor);
		
		System.out.println("break 01");
		
		backButton = new WHButton("< Back");
		eventInfoPanel = new JPanel();
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		fname = new JTextField(u.fname());
		lname = new JTextField(u.lname());
		email = new JLabel("Email :");
		username = new JLabel("Username: ");
		edituseremail = new JTextField( u.email());
		editusername = new JTextField( u.username());
		
		System.out.println("break 02");
		
		fname.setFont(new Font ("Impact", Font.PLAIN, 25));
		lname.setFont(new Font ("Impact", Font.PLAIN, 25));
		email.setFont(new Font ("Impact", Font.PLAIN, 25));
		username.setFont(new Font ("Impact", Font.PLAIN, 25));
		edituseremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		editusername.setFont(new Font ("Impact", Font.PLAIN, 25));
		
		messageBoardPanel = new JPanel();
		messageBoardPanel.setBackground(yellowColor);
		conversationPanel = new JPanel();
		conversationPanel.setBackground(yellowColor);
		messageBoardPanel.setLayout(new BorderLayout());
		messageBoardPanel.setBackground(yellowColor);
		
		addPanel = new JPanel();
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(yellowColor);
	
		save = new WHButton("Save");
		next = new WHButton(">");
		westPanel = new JPanel();
		westPanel.setBackground(yellowColor);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(yellowColor);
		centerNorthPanel = new JPanel();
		centerNorthPanel.setBackground(yellowColor);
		
		//attending = new JLabel( e.getAttendees() + " attending");
		profilepicture = new ImageIcon("img/default_profile_pic.png");
		userImage = new JLabel (profilepicture);
		
		System.out.println("break 0");
		

	}
	
	private void createGUI() {
		
		System.out.println("break 1");

		westPanel.add(backButton, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		
		
		Dimension mbSize = new Dimension (300,600);
		messageBoardPanel.setPreferredSize(mbSize);
		
		conversationPanel.add(userImage);
		conversationPanel.add(next);
		messageBoardPanel.add(conversationPanel, BorderLayout.CENTER);
		
		addPanel.setSize(100, 100);
		
		addPanel.setBackground(yellowColor);
		messageBoardPanel.add(addPanel, BorderLayout.SOUTH);
		messageBoardPanel.setBackground(yellowColor);
		add(messageBoardPanel, BorderLayout.EAST);
		
		System.out.println("break 2");
		
		//name.setFont(new Font ("Phosphate", Font.BOLD, 45));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(yellowColor);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(fname);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(lname);
		titlePanel.add(Box.createVerticalGlue());

		

		System.out.println("break 3");

		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));

		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		
		titlePanel.add(username);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(editusername);	
		titlePanel.add(Box.createRigidArea(new Dimension(25,25)));
		
	
		System.out.println("break 3.5");
		
		titlePanel.add(email);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(edituseremail);
		
		titlePanel.add(Box.createRigidArea(new Dimension(0, 750)));
		titlePanel.add(save);
				
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		mainPanel.add(save, BorderLayout.SOUTH);
		
		System.out.println("break 4");
		
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);
		System.out.println("break 5");
		
	}
	
	private void addActions() {
		
	}
	
	// called when new messages are sent to the server so that message board is updated.
//	private void updateMessageBoard() {
//		Vector<Message> messageBoardContents = e.getMessageBoard();
//		for (int i=0; i<messageBoardContents.size(); i++) {
//			Message m = messageBoardContents.elementAt(i);
//			messageBoard.append(m.username()+": "+m.message()+"\n");
//		}
//	}
	
	public static void main (String [] args) {
		User u = new User("Ziad", "Azar", "zazar@usc.edu", "zezefresh", "love2code");
		EditProfileFrame epf = new EditProfileFrame(u);
		epf.setVisible(true);
		epf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}