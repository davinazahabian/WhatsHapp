package WHFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import Model.Event;
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
	private String[] categories = {"Sports", "Career", "Cultural", "Club"};
	private JComboBox<String> categoryBox;
	private ButtonGroup sortBy;
	private JRadioButton sortByTrending;
	private JRadioButton sortByDefault;  // sort by time posted
	
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
	}
	
	public void instantiateComponents() {
		leftPanel = new LeftPanel();
		myProfileButton = new WHButton("My Profile");
		
		middlePanel = new MiddlePanel();
		eventPanels = new Vector<EventPanelGUI>();
		feedPanel = new JPanel();
		feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
		
		populateFeed("Default");
		
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
					whClient.mff.setVisible(false);
					whClient.mpf.setVisible(true);
				} else {
					// TODO option pane that asks to sign up
				}
			}
		});
		
		newEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (whClient.isRegistered()) {
					whClient.mff.setVisible(false);
					whClient.neg.setVisible(true);
				} else {
					// TODO option pane that asks to sign up
				}
			}
		});
		
		sortByTrending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				populateFeed("Trending");
			}
		});
		
		sortByDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				populateFeed("Default");
			}
		});
		
		categoryBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				String category = categoryBox.getSelectedItem().toString();
				populateFeed(category);
			}
		});
	}
	
	public void populateFeed(String category) {
		
		if (category.equals("Sports")) {
			
		} else if (category.equals("Career")) {
			
		} else if (category.equals("Cultural")) {
			
		} else if (category.equals("Club")) {
			
		} else if (category.equals("Default")) {
			/***********************************************/
			// the following is for testing the scroll pane, delete when creating
			int curr = 0;
			for (int i=0; i<100; i++) {
				curr+=1;
				EventPanelGUI epg = new EventPanelGUI(new Event("Coachella","April 15","12 AM","12 PM","A popular music and arts festival","Indio, CA",curr%3,"now","Davina Zahabian"));
			    epg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    eventPanels.add(epg);
				feedPanel.add(epg);
			}
			/**********************************************/
		} else if (category.equals("Trending")) {
			
		} else {
			/***********************************************/
			// the following is for testing the scroll pane, delete when creating
			int curr = 0;
			for (int i=0; i<100; i++) {
				curr+=1;
				EventPanelGUI epg = new EventPanelGUI(new Event("Coachella","April 15","12 AM","12 PM","A popular music and arts festival","Indio, CA",curr%3,"now","Davina Zahabian"));
			    epg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    eventPanels.add(epg);
				feedPanel.add(epg);
			}
			/**********************************************/
		}
	}
	
//	public static void main(String [] args) {
//		MainFeedFrame mff = new MainFeedFrame();
//		mff.setVisible(true);
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
