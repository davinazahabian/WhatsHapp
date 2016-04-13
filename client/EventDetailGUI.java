package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Event;
import customui.WHButton;

public class EventDetailGUI extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Event e;
	private Color yellowColor;
	private Color highlightColor;
	private JPanel mainPanel;
	
	private WHButton backButton;
	private JLabel eventTitle;
	private JPanel eventInfoPanel;
	private JLabel eventDate;
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
	
	public EventDetailGUI(Event e) {
		super(new BorderLayout());
		this.setSize(new Dimension(900,602));
		this.e = e;
		
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	private void instantiateComponents() {
		yellowColor = new Color(255,204,102);
		highlightColor = new Color(247,234,161);
		mainPanel = new JPanel();
		//mainPanel.setBackground(yellowColor);
		
		
		
		backButton = new WHButton("< Back");
		eventInfoPanel = new JPanel();
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		eventTitle = new JLabel(e.getEventName());
		eventDate = new JLabel(e.getEventDate());
		eventStartTime = new JLabel(e.getEventStartTime());
		eventEndTime = new JLabel(e.getEventEndTime());
		eventLocation = new JLabel(e.getEventLoc());
		eventDetail = new JLabel(e.getEventDesc());
		
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
		
		westPanel = new JPanel();
		westPanel.setBackground(yellowColor);
		
		centerPanel = new JPanel();
		//centerPanel.setBackground(yellowColor);
		centerNorthPanel = new JPanel();
		centerNorthPanel.setBackground(yellowColor);
		centerCenterPanel = new JPanel();
		
		attending = new JLabel( e.getAttendees() + " attending");
		

		
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
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(eventTitle);
		titlePanel.add(Box.createVerticalGlue());

		titlePanel.add(Box.createRigidArea(new Dimension(25,25)));
		JLabel votes = new JLabel("" + e.getUpvotes());
		votes.setFont(new Font ("Impact", Font.PLAIN, 25));
		
		JPanel upvotes = new JPanel();
		upvotes.add(votes);
		titlePanel.add(attending);
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(upvotes);
		
//		centerPanel.add(attending);
		
		mainPanel.add(centerPanel, BorderLayout.SOUTH);
		mainPanel.add(titlePanel,BorderLayout.NORTH);
	
		
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
		Event e = new Event("BaseBall Game", "April 10, 2016", "12:00 p.m.", "4:00 p.m.", "USC Trojans Baseball will be playing against the UCLA Bruins! ", "Dedeaux Field", 1 , "10:00 a.m.");
		f.add(new EventDetailGUI(e), BorderLayout.CENTER);
		f.setVisible(true);
	}
}
