package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
import Model.InfoPackage;
import Model.Message;
import customui.WHButton;
/*
 * 
 * EventDetailGUI - the GUI for the individual event detail page containing event details and the
 * message board, takes an Event object as an argument; acts as a wrapper for an instance of Event
 * 
 */

public class EventDetailGUI extends JFrame {
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
	
	private ImageIcon thumb;
	private JButton thumbButton;
	private WHButton going;
	private JTextArea description;
	
	private WHClient whClient;
	private EventDetailGUI edg;
	private EventPanelGUI epg;
	
	public EventDetailGUI(Event e, WHClient whc, EventPanelGUI epg) {
		this.whClient = whc;
		this.setSize(new Dimension(900,602));
		this.e = e;
		this.edg = this;
		this.epg = epg;
		
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
		eventTitle = new JLabel(e.getEventName());
		eventDate = new JLabel("Date: " + e.getEventDate());
		eventStartTime = new JLabel("Starts: " + e.getEventStartTime());
		eventEndTime = new JLabel("Ends: " + e.getEventEndTime());
		eventLocation = new JLabel("Location: " + e.getEventLoc());
		eventDetail = new JLabel("About: " + e.getEventDesc());
		
		eventTitle.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventDate.setFont(new Font ("Impact", Font.PLAIN, 25));
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
		messageBoard = new JTextArea(e.getMessageBoard());
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
		postMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent f) {
				whClient.sendMessage(e, new Message(whClient.getCurrentUser().username(), addMessageArea.getText()));
				//messageBoard.setText(e.getMessageBoard());
				addMessageArea.setText(" ");
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				edg.setVisible(false);
				whClient.getMff().setVisible(true);
			}
		});
		
		going.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				whClient.addAttendee(e, whClient.getCurrentUser());
				attending.setText("" + e.getAttendees() + " attending");
				epg.numAttendingHolder().setText("" + e.getAttendees() + " attending");
			}
		});
		
	}

	public void postToBoard(InfoPackage p) {
		messageBoard.append(p.getMessage().username() + ":  " +p.getMessage().message() + '\n');
	}
}
