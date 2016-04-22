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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Event;
import Model.InfoPackage;
import Model.Message;
import WHFrame.NewUserGUI;
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
//	private JPanel centerCenterPanel;
	
	private JPanel titlePanel;
	private JPanel detailPanel;
	private JLabel votes;
	private JPanel upvotes;
	
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
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());

		
		instantiateComponents();
		createGUI();
		addActions();
		setVisible(true);
	}
	
	private void instantiateComponents() {
		yellowColor = new Color(255,204,102);
		highlightColor = new Color(247,234,161);
		mainPanel = new JPanel();
		backButton = new WHButton("< Back");
		eventInfoPanel = new JPanel();
		eventTitle = new JLabel(e.getEventName());
		eventDate = new JLabel("Date: " + e.getEventDate());
		eventStartTime = new JLabel("Starts: " + e.getEventStartTime());
		eventEndTime = new JLabel("Ends: " + e.getEventEndTime());
		eventLocation = new JLabel("Location: " + e.getEventLoc());
		eventDetail = new JLabel("About: " + e.getEventDesc());
		messageBoardPanel = new JPanel();
		conversationPanel = new JPanel();
		messageBoardLabel = new JLabel("Message Board");
		messageBoard = new JTextArea(e.getMessageBoard());
		messageBoardPane = new JScrollPane(messageBoard);
		addPanel = new JPanel();
		addMessageArea = new JTextArea(5, 1);
		postMessage = new WHButton("Post Message");
		going = new WHButton("Going");
		westPanel = new JPanel();
		centerPanel = new JPanel();
		centerNorthPanel = new JPanel();
		centerNorthPanel.setBackground(yellowColor);
//		centerCenterPanel = new JPanel();
		attending = new JLabel( e.getAttendees() + " attending");
		thumb = new ImageIcon("img/thumb_button.png");
		thumbButton = new JButton(thumb);
		description = new JTextArea("About: " + "\n" + e.getEventDesc());
		
		titlePanel = new JPanel();
		detailPanel = new JPanel();
		votes = new JLabel("" + e.getUpvotes());
		upvotes = new JPanel();
	}
	
	private void createGUI() {
		westPanel.setBackground(yellowColor);
		centerPanel.setBackground(yellowColor);
		thumbButton.setBackground(yellowColor);
		addPanel.setBackground(yellowColor);
		mainPanel.setBackground(yellowColor);
		messageBoardPanel.setBackground(yellowColor);
		conversationPanel.setBackground(yellowColor);
		messageBoardPanel.setBackground(yellowColor);
		description.setBackground(yellowColor);
		messageBoardPanel.setBackground(yellowColor);
		detailPanel.setBackground(yellowColor);
		addPanel.setBackground(yellowColor);
		upvotes.setBackground(yellowColor);

		thumbButton.setPreferredSize(new Dimension(71,75));
		messageBoard.setPreferredSize(new Dimension (292,406));
		description.setMaximumSize(new Dimension(300,50));
		messageBoardPanel.setPreferredSize(new Dimension (300,600));
		addPanel.setSize(100, 100);

		addMessageArea.setEditable(true);
		addMessageArea.setLineWrap(true);
		addMessageArea.setWrapStyleWord(true);
		addMessageArea.setSelectionColor(highlightColor);
		
		addPanel.setLayout(new BorderLayout());
		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
		messageBoardPanel.setLayout(new BorderLayout());
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

		messageBoard.setEditable(false);
		messageBoardPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setEditable(false);
		
		description.setFont(new Font ("Impact", Font.PLAIN, 20));
		eventTitle.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventDate.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventStartTime.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventEndTime.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventLocation.setFont(new Font ("Impact", Font.PLAIN, 25));
		eventDetail.setFont(new Font ("Impact", Font.PLAIN, 18));
		messageBoardLabel.setFont(new Font ("Phosphate", Font.PLAIN, 30));
		eventTitle.setFont(new Font ("Phosphate", Font.BOLD, 45));
		attending.setFont(new Font ("Impact", Font.PLAIN, 25));
		votes.setFont(new Font ("Impact", Font.PLAIN, 40));

		
		
		// back button (west)
		westPanel.add(backButton, BorderLayout.NORTH);
		
		// message board (east)
		messageBoardPanel.add(messageBoardLabel, BorderLayout.NORTH);
		conversationPanel.add(messageBoard, BorderLayout.NORTH);
		messageBoardPanel.add(conversationPanel, BorderLayout.CENTER);
		addPanel.add(addMessageArea, BorderLayout.CENTER);
		addPanel.add(postMessage, BorderLayout.SOUTH);
		messageBoardPanel.add(addPanel, BorderLayout.SOUTH);
		

		
		// main panel (center)
		// upvotes
		upvotes.add(thumbButton);
		upvotes.add(votes);
		
		// title panel
		titlePanel.add(eventTitle);
		titlePanel.add(Box.createVerticalGlue());
		
		detailPanel.add(attending);
		detailPanel.add(Box.createVerticalGlue());
		detailPanel.add(upvotes);
		detailPanel.add(Box.createVerticalGlue());
		detailPanel.add(eventDate);
		detailPanel.add(Box.createVerticalGlue());
		detailPanel.add(eventStartTime);
		detailPanel.add(Box.createVerticalGlue());
		detailPanel.add(eventEndTime);
		detailPanel.add(Box.createVerticalGlue());
		detailPanel.add(eventLocation);
		detailPanel.add(Box.createVerticalGlue());
		detailPanel.add(description);
		detailPanel.add(Box.createVerticalBox());
		//detailPanel.add(Box.createVerticalGlue());
		//detailPanel.add(Box.createGlue());
		detailPanel.add(going);
		
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(detailPanel,BorderLayout.CENTER);
		//mainPanel.add(going, BorderLayout.SOUTH);
	
		add(westPanel, BorderLayout.WEST);
		add(messageBoardPanel, BorderLayout.EAST);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	private void addActions() {
		postMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent f) {
				if (whClient.isRegistered()) {
					whClient.sendMessage(e, new Message(whClient.getCurrentUser().username(), addMessageArea.getText()));
					//messageBoard.setText(e.getMessageBoard());
					addMessageArea.setText(" ");					
				} else {
					askToSignup();
				}

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
				if (whClient.isRegistered()) {
					whClient.addAttendee(e, whClient.getCurrentUser());
					attending.setText("" + e.getAttendees() + " attending");
					epg.numAttendingHolder().setText("" + e.getAttendees() + " attending");					
				} else {
					askToSignup();
				}

			}
		});
		
		thumbButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (whClient.isRegistered()) {
					if(epg.getPressed() > 0){
						return;
					}
					whClient.incrementUpvote(e);
					epg.getUpVoteCounter().setText("" + e.getUpvotes());
					epg.setPressed(epg.getPressed()+1);
					votes.setText("" + e.getUpvotes());
				} else {
					askToSignup();
				}

			}
		});
		
	}

	public void askToSignup() {
		Object[] answers = {"Sign Me Up!", "No Thanks"};
		int n = JOptionPane.showOptionDialog(whClient.getMff(),
				"Would you like to sign up?",
				"Sign up to get premium access!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,answers,answers[0]);
		if (n == JOptionPane.YES_OPTION) {
			this.setVisible(false);
			whClient.setNug(new NewUserGUI(whClient));
		}
	}
	
	public void postToBoard(InfoPackage p) {
		messageBoard.append(p.getMessage().username() + ":  " +p.getMessage().message() + '\n');
	}
	
}
