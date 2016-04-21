package WHFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import Model.Event;
import Model.InfoPackage;
import client.EventDetailGUI;
import client.EventPanelGUI;
import client.WHClient;
import customui.WHButton;
import library.ImageLibrary;

/*
 * 
 * MainFeedFrame - the main GUI frame that displays the feed of events, which can be filtered by
 * category or sorted by most recent or trending; clicking "My Profile" displays the user's profile
 * information, or a message to Guest users to sign up; clicking "New Event" displays a form to
 * create a new event
 * 
 */


public class MainFeedFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private LeftPanel leftPanel;
	private WHButton myProfileButton;

	private MiddlePanel middlePanel;
	private JPanel feedPanel;
	private JScrollPane feedScrollPane;
	private Vector<EventPanelGUI> eventPanels;

	private JPanel rightPanel;
	private RightUpperPanel upperPanel;
	private RightLowerPanel lowerPanel;
	private WHButton newEventButton;
	private JPanel sortPanel;
	private JPanel filterPanel;
	private JPanel sortFilterPanel;
	private String[] categories = {"All", "Sports", "Career", "Cultural", "Club"};
	private JComboBox<String> categoryBox;
	public JComboBox<String> getCategoryBox() {
		return categoryBox;
	}
	public void setCategoryBox(JComboBox<String> categoryBox) {
		this.categoryBox = categoryBox;
	}
	private ButtonGroup sortBy;
	private JRadioButton sortByTrending;
	private JRadioButton sortByDefault;  // sort by time posted
	private boolean isTrending;
	private boolean isDefault;
	private Vector<EventPanelGUI> panels; 
	public boolean manual = false;
	public boolean isTrending() {
		return isTrending;
	}
	public void setTrending(boolean isTrending) {
		this.isTrending = isTrending;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	private WHClient whClient;
	

	public MainFeedFrame(WHClient whClient) {
		
		this.whClient = whClient;
		setTitle("WhatsHapp");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,3));
		setSize(900,620);
		instantiateComponents();
		createGUI();
		addActions();
//		setVisible(true);
	}
	public void instantiateComponents() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		setCursor(c);
		leftPanel = new LeftPanel();
		myProfileButton = new WHButton("My Profile");
		middlePanel = new MiddlePanel();
		eventPanels = new Vector<EventPanelGUI>();
		feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		getEvents("Default");
		feedScrollPane = new JScrollPane(feedPanel);
		feedScrollPane.setPreferredSize(new Dimension(300,400));
		feedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		feedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		rightPanel = new JPanel();
		upperPanel = new RightUpperPanel();
		lowerPanel = new RightLowerPanel();
		newEventButton = new WHButton("+ New Event");
		sortPanel = new JPanel();
		filterPanel = new JPanel();
		sortFilterPanel = new JPanel(new BorderLayout());
		categoryBox = new JComboBox<String>(categories);
		sortBy = new ButtonGroup();
		sortByTrending = new JRadioButton("Trending", false);
		sortByDefault = new JRadioButton("Most Recent", true);
	}
	public void createGUI() {
		// setting button sizes
		myProfileButton.setPreferredSize(newEventButton.getPreferredSize());
		// setting panel sizes
		leftPanel.setPreferredSize(new Dimension(300,600));
		middlePanel.setPreferredSize(new Dimension(300,600));
		rightPanel.setPreferredSize(new Dimension(300,600));
		upperPanel.setPreferredSize(new Dimension(300,300));
		lowerPanel.setPreferredSize(new Dimension(300,300));

		// left panel
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		leftPanel.add(myProfileButton);
		add(leftPanel);
		// middle panel
		middlePanel.setLayout(new BorderLayout());
		middlePanel.add(feedScrollPane, BorderLayout.SOUTH);
		add(middlePanel);
		// right panel
		rightPanel.setLayout(new BorderLayout());
		upperPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// sort and filter panel
		sortPanel.setBackground(new Color(255,204,102));
		sortPanel.setBorder(BorderFactory.createTitledBorder("Sort By"));
		sortBy.add(sortByDefault);
		sortBy.add(sortByTrending);
		sortPanel.add(sortByDefault);
		sortPanel.add(sortByTrending);
		filterPanel.setBackground(new Color(255,204,102));
		filterPanel.setBorder(BorderFactory.createTitledBorder("Filter By"));
		filterPanel.add(categoryBox);
		sortFilterPanel.add(sortPanel, BorderLayout.NORTH);
		sortFilterPanel.add(filterPanel, BorderLayout.SOUTH);
		upperPanel.add(newEventButton);
		lowerPanel.add(sortFilterPanel);
		rightPanel.add(lowerPanel, BorderLayout.SOUTH);
		rightPanel.add(upperPanel, BorderLayout.CENTER);
		add(rightPanel);
	}
	public void addActions() {
		myProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (whClient.isRegistered()) {
					whClient.getMff().setVisible(false);
					whClient.setMpf(new MyProfileFrame(whClient.getCurrentUser(), whClient));
				} else {
					askToSignup();
				}
			}
		});
		newEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (whClient.isRegistered()) {
					whClient.getMff().setVisible(false);
					whClient.setNeg(new NewEventGUI(whClient));
				} else {
					askToSignup();
				}
			}
		});
		sortByTrending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(manual)
					return;
				feedPanel.removeAll();
				setTrending(true);
				setDefault(false);
				populateFeed(whClient.getAllEvents());
				repaint();
				revalidate();
				System.out.println("Enters here in sortByTrending");
				
				//getEvents(categoryBox.getSelectedItem().toString());
			}
		});
		sortByDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setDefault(true);
				setTrending(false);
				getEvents(categoryBox.getSelectedItem().toString());
			}
		});
		categoryBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				if(!manual)
				getEvents(categoryBox.getSelectedItem().toString());
			}
		});
	}
	
	// TODO
	public void getEvents(String category) {
		if (category.equals("All")) {
			System.out.println("All events");
			setVisible(false);
			MainFeedFrame mff = new MainFeedFrame(whClient);
			whClient.setMff(mff);
			mff.populateFeed(whClient.getAllEvents());
			mff.setVisible(true);
		} else if (category.equals("Sports")) {
			System.out.println("Sports events");
			whClient.getSportsEvents();
		} else if (category.equals("Career")) {
			whClient.getCareerEvents();
		} else if (category.equals("Cultural")) {
			whClient.getCulturalEvents();
		} else if (category.equals("Club")) {
			whClient.getClubEvents();
		}
	}
	
	public void populateFeed(Vector<Event> events) {
//		feedPanel.removeAll();
		System.out.println("entered populate");
		System.out.println(events.size());
		panels = new Vector<>();
		// sort by trending and insert into feed
		if (this.sortByTrending.isSelected()) {
			System.out.println("Trending selected");
			Collections.sort(events, new Event());
			for (int i=0; i<events.size(); i++) {
				EventPanelGUI epg = new EventPanelGUI(events.get(i),whClient);
				panels.add(epg);
				epg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				eventPanels.add(epg);
				feedPanel.add(epg);
				System.out.println(events.get(i).getEventName() + " " + events.get(i).getTimePosted() );
			}
		// sort by time posted and insert into feed
		} else {
			System.out.println("Default selected");
			Collections.sort(events);
			for (int i=0; i<events.size(); i++) {
				EventPanelGUI epg = new EventPanelGUI(events.get(i),whClient);
				epg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panels.add(epg);
				eventPanels.add(epg);
				feedPanel.add(epg);
				System.out.println(events.get(i).getEventName() + " " + events.get(i).getTimePosted() );
			}
		}
//		feedPanel.revalidate();
//		feedPanel.repaint();
//		revalidate();
//		repaint();
		setVisible(false);
		setVisible(true);
	}
	
	public void askToSignup() {
		Object[] answers = {"Sign Me Up!", "No Thanks"};
		int n = JOptionPane.showOptionDialog(whClient.getMff(),
				"Would you like to sign up?",
				"Sign up to get premium access to WhatsHapp!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,answers,answers[0]);
		if (n == JOptionPane.YES_OPTION) {
			whClient.getMff().setVisible(false);
			whClient.setNug(new NewUserGUI(whClient));
		}
	}
	public JRadioButton getSortByDefault() {
		return sortByDefault;
	}
	public JRadioButton getSortByTrending() {
		return sortByTrending;
	}
	
	public void newEvent() {
		
		
		setVisible(false);
		whClient.setMff(new MainFeedFrame(whClient));
		whClient.getMff().populateFeed(whClient.getAllEvents());
		whClient.getMff().setVisible(true);
	}
	public void postToBoard(InfoPackage p) {
		for (Iterator iterator = eventPanels.iterator(); iterator.hasNext();) {
			EventPanelGUI eventPanelGUI = (EventPanelGUI) iterator.next();
			if(eventPanelGUI.getE().getEventName().equals(p.getEvent().getEventName())){
				eventPanelGUI.postToBoard(p);
			}
		}
	}
	
	public void showEventSuccess() {
		JOptionPane.showMessageDialog(this,"Your event was submitted!");
	}
	
	public void cannotFulfillRequest() {
		JOptionPane.showMessageDialog(this,"Cannot fulfill request at this time.","Warning",JOptionPane.WARNING_MESSAGE);
	}

	//	public static void main(String [] args) {
	//	MainFeedFrame mff = new MainFeedFrame();
	//	mff.setVisible(true);
	//	}
}

	
/*
 * 
 * LeftPanel, MiddlePanel, RightPanel - these panels are for the background of the MainFeedFrame, when placed together they
 * display the background image.
 * 
 */
class LeftPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Image background;

	static {
		background = ImageLibrary.getImage("img/left-1.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}

class MiddlePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Image background;

	static {
		background = ImageLibrary.getImage("img/middle-1.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}

class RightUpperPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Image background;

	static {
		background = ImageLibrary.getImage("img/right_top-1.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}
class RightLowerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Image background;

	static {
		background = ImageLibrary.getImage("img/right_bottom-1.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}