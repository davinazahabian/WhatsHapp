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

import Model.Event;
import Model.User;
import customui.WHButton;

public class MyProfileFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private User u;
	private Color yellowColor;
	private Color highlightColor;
	private JPanel mainPanel;
	
	private WHButton backButton;
	private JLabel name;
	private JPanel eventInfoPanel;
	private JLabel useremail;
	private JLabel eventStartTime;
	private JLabel eventEndTime;
	private JLabel eventLocation;
	private JLabel eventDetail;
	private JLabel attending;
	
	private JPanel messageBoardPanel;
	private JPanel conversationPanel;
	private JLabel messageBoardLabel;
	private JTextArea messageBoard;
	private JScrollPane messageBoardPane;
	private JPanel addPanel;
	private JTextArea addMessageArea;
	private WHButton postMessage;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel centerNorthPanel;
	private JPanel centerCenterPanel;
	
	private ImageIcon thumb;
	private JButton thumbButton;
	private WHButton going;
	private JTextArea description;
	
	public MyProfileFrame(User u) {
		
		this.setSize(new Dimension(900,602));
		this.u = u;
		
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		yellowColor = new Color(255,204,102);
		highlightColor = new Color(247,234,161);
		mainPanel = new JPanel();
		mainPanel.setBackground(yellowColor);
		
		
		
		backButton = new WHButton("< Back");
		eventInfoPanel = new JPanel();
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		name = new JLabel(u.fname() + " " + u.lname());
		useremail = new JLabel("Email: " + u.email());
		username = new JLabel("Starts: " + e.getEventStartTime());
		eventEndTime = new JLabel("Ends: " + e.getEventEndTime());
		eventLocation = new JLabel("Location: " + e.getEventLoc());
		eventDetail = new JLabel("About: " + e.getEventDesc());
		
		name.setFont(new Font ("Impact", Font.PLAIN, 25));
		useremail.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventStartTime.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventEndTime.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventLocation.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventDetail.setFont(new Font ("Impact", Font.PLAIN, 18));
		
		messageBoardPanel = new JPanel();
		messageBoardPanel.setBackground(yellowColor);
		conversationPanel = new JPanel();
		conversationPanel.setBackground(yellowColor);
		messageBoardLabel = new JLabel("Message Board");
		messageBoardPanel.setLayout(new BorderLayout());
		messageBoardPanel.setBackground(yellowColor);
		messageBoard = new JTextArea();
		messageBoardPane = new JScrollPane(messageBoard);
		messageBoard.setPreferredSize(new Dimension (292,406));
		messageBoard.setEditable(false);
		messageBoardPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		addPanel = new JPanel();
		addPanel.setLayout(new BorderLayout());
		addPanel.setBackground(yellowColor);
		addMessageArea = new JTextArea(5, 1);
		addMessageArea.setEditable(true);
		addMessageArea.setLineWrap(true);
		addMessageArea.setWrapStyleWord(true);
		addMessageArea.setSelectionColor( highlightColor);
		
		
		postMessage = new WHButton("Post Message");
		going = new WHButton("Going");
		westPanel = new JPanel();
		westPanel.setBackground(yellowColor);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(yellowColor);
		centerNorthPanel = new JPanel();
		centerNorthPanel.setBackground(yellowColor);
		centerCenterPanel = new JPanel();
		
		attending = new JLabel( e.getAttendees() + " attending");
		thumb = new ImageIcon("img/thumb_button.png");
		
		thumbButton = new JButton(thumb);
		thumbButton.setPreferredSize(new Dimension(71,75));

		thumbButton.setBackground(yellowColor);
		description = new JTextArea("About: " + "\n" + e.getEventDesc());
		description.setMaximumSize(new Dimension(300,50));
		description.setFont(new Font ("Impact", Font.PLAIN, 20));
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBackground(yellowColor);
		description.setEditable(false);
		
	}
	
	private void createGUI() {
		
	

		westPanel.add(backButton, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		
//		updateMessageBoard();		
		Dimension mbSize = new Dimension (300,600);
		messageBoardPanel.setPreferredSize(mbSize);
		messageBoardLabel.setFont(new Font ("Phosphate", Font.PLAIN, 30));
		messageBoardPanel.add(messageBoardLabel, BorderLayout.NORTH);
		conversationPanel.add(messageBoard, BorderLayout.NORTH);
		conversationPanel.add(addMessageArea, BorderLayout.SOUTH);
		messageBoardPanel.add(conversationPanel, BorderLayout.CENTER);
		addPanel.setSize(100, 100);
		addPanel.add(addMessageArea, BorderLayout.CENTER);
		addPanel.add(postMessage, BorderLayout.SOUTH);
		addPanel.setBackground(yellowColor);
		messageBoardPanel.add(addPanel, BorderLayout.SOUTH);
		messageBoardPanel.setBackground(yellowColor);
		add(messageBoardPanel, BorderLayout.EAST);
		
		
		eventTitle.setFont(new Font ("Phosphate", Font.BOLD, 45));
		attending.setFont(new Font ("Impact", Font.PLAIN, 25));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(yellowColor);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(eventTitle);
		titlePanel.add(Box.createVerticalGlue());

		//titlePanel.add(Box.createRigidArea(new Dimension(100,10)));
		JLabel votes = new JLabel("" + e.getUpvotes());
		votes.setFont(new Font ("Impact", Font.PLAIN, 40));
		
		JPanel upvotes = new JPanel();
		upvotes.setBackground(yellowColor);
		upvotes.add(thumbButton);
		upvotes.add(votes);
		titlePanel.add(attending);
		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		titlePanel.add(upvotes);
		titlePanel.add(Box.createRigidArea(new Dimension(30,30)));
		//JPanel information = new JPanel();
		titlePanel.add(eventDate);
		titlePanel.add(eventStartTime);
		titlePanel.add(eventEndTime);
		titlePanel.add(eventLocation);
		titlePanel.add(description);
		titlePanel.add(Box.createRigidArea(new Dimension(50,60)));
		titlePanel.add(going);
				
		mainPanel.add(titlePanel,BorderLayout.NORTH);
		mainPanel.add(going, BorderLayout.SOUTH);
		
	
		
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		
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
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(900,602));
		User u = new User("Ziad", "Azar", "zazar@usc.edu", "zezefresh", "love2code");
		f.add(new MyProfileFrame(u), BorderLayout.CENTER);
		f.setVisible(true);
	}
}