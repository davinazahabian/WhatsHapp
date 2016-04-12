//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.util.Vector;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//
//import customui.WHButton;
//
//public class EventDetailGUI extends JPanel {
//	
//	private static final long serialVersionUID = 1L;
//	private Event e;
//	private Color redColor;
//	private Font font;
//	private JPanel mainPanel;
//	
//	private JButton backButton;
//	private JLabel eventTitle;
//	private JPanel eventInfoPanel;
//	private JLabel eventDate;
//	private JLabel eventTime;
//	private JLabel eventLocation;
//	private JLabel eventDetail;
//	
//	private JPanel messageBoardPanel;
//	private JLabel messageBoardLabel;
//	private JTextArea messageBoard;
//	private JPanel addPanel;
//	private JTextArea addMessageArea;
//	private WHButton sendMessage;
//	
//	public EventDetailGUI(Event e) {
//		super(new GridBagLayout());
//		this.setSize(new Dimension(900,602));
//		this.e = e;
//		
//		instantiateComponents();
//		createGUI();
//		addActions();
//	}
//	
//	private void instantiateComponents() {
//		redColor = new Color(130,25,44);
//		mainPanel = new JPanel();
//		font = new Font("Arial", Font.BOLD, 26);
//		// this should be an arrow image:
//		backButton = new JButton();
//		eventInfoPanel = new JPanel();
//		eventInfoPanel.setLayout(new BoxLayout(eventInfoPanel, BoxLayout.Y_AXIS));
//		eventTitle = new JLabel(e.getEventName());
//		eventDate = new JLabel(e.getEventDate());
//		eventTime = new JLabel(e.getEventTime());
//		eventLocation = new JLabel(e.getEventLoc());
//		eventDetail = new JLabel(e.getEventDesc());
//		
//		messageBoardPanel = new JPanel();
//		messageBoardLabel = new JLabel("Message Board");
//		messageBoardPanel.setLayout(new BorderLayout());
//		messageBoard = new JTextArea();
//		addPanel = new JPanel();
//		addPanel.setLayout(new BorderLayout());
//		addMessageArea = new JTextArea();
//		sendMessage = new WHButton("Send Message");
//	}
//	
//	private void createGUI() {
//		mainPanel.setBackground(redColor);
//		
//		eventTitle.setFont(font);
//		font = font.deriveFont(20);
//		eventDate.setFont(font);
//		eventTime.setFont(font);
//		font = font.deriveFont(18);
//		eventLocation.setFont(font);
//		font = font.deriveFont(13);
//		eventDetail.setFont(font);
//		
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.anchor = GridBagConstraints.PAGE_START;
//		add(eventTitle, gbc);
//		
//		eventInfoPanel.add(eventDate);
//		eventInfoPanel.add(Box.createVerticalGlue());
//		eventInfoPanel.add(eventTime);
//		eventInfoPanel.add(Box.createVerticalGlue());
//		eventInfoPanel.add(eventLocation);
//		eventInfoPanel.add(Box.createVerticalGlue());
//		eventInfoPanel.add(eventDetail);
//		gbc.anchor = GridBagConstraints.LINE_START;
//		gbc.gridx = 500;
//		gbc.gridy = 200;
//		eventInfoPanel.setBackground(redColor);
//		add(eventInfoPanel, gbc);
//		
//		messageBoardPanel.setSize(100, 300);
//		font = font.deriveFont(16);
//		messageBoardLabel.setFont(font);
//		messageBoardPanel.add(messageBoardLabel, BorderLayout.NORTH);
//		updateMessageBoard();
//		messageBoardPanel.add(messageBoard, BorderLayout.CENTER);
//		addPanel.setSize(100, 100);
//		addPanel.add(addMessageArea, BorderLayout.CENTER);
//		addPanel.add(sendMessage, BorderLayout.SOUTH);
//		addPanel.setBackground(redColor);
//		messageBoardPanel.add(addPanel, BorderLayout.SOUTH);
//		gbc.anchor = GridBagConstraints.LINE_END;
//		gbc.gridx = 300;
//		gbc.gridy = 500;
//		messageBoardPanel.setBackground(redColor);
//		add(messageBoardPanel, gbc);
//		
//		setVisible(true);
//		
//	}
//	
//	private void addActions() {
//		
//	}
//	
//	// called when new messages are sent to the server so that message board is updated.
//	private void updateMessageBoard() {
//		Vector<Message> messageBoardContents = e.getMessageBoard();
//		for (int i=0; i<messageBoardContents.size(); i++) {
//			Message m = messageBoardContents.elementAt(i);
//			messageBoard.append(m.username()+": "+m.message()+"\n");
//		}
//	}
//	
//	public static void main (String [] args) {
//		JFrame f = new JFrame();
//		f.setSize(new Dimension(900,602));
//		Event e = new Event("Game", "April 10, 2016", "12:00", "Baseball game, USC v. UCLA", "Dedeaux Field", true, false, false, false);
//		f.add(new EventDetailGUI(e), BorderLayout.CENTER);
//		f.setVisible(true);
//	}
//}
