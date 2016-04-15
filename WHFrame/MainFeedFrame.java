
package WHFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import Model.Event;
import client.EventPanelGUI;
import customui.WHButton;
import library.ImageLibrary;

public class MainFeedFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private LeftPanel leftPanel;
	private WHButton myProfileButton;
	private WHButton signOutButton;

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
	private JComboBox<String> categories;
	private JRadioButton sortByTrending;
	private JRadioButton sortByDefault;

	public MainFeedFrame() {
		setTitle("WhatsHapp");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,3));
		setSize(900,602);
		instantiateComponents();
		createGUI();
		addActions();
	}
	
	public void instantiateComponents() {
		leftPanel = new LeftPanel();
		myProfileButton = new WHButton("My Profile");
		signOutButton = new WHButton("Sign Out");
		
		middlePanel = new MiddlePanel();
		feedPanel = new JPanel();
		feedScrollPane = new JScrollPane(feedPanel);
		eventPanels = new Vector<EventPanelGUI>();

		rightPanel = new JPanel();
		upperPanel = new RightUpperPanel();
		lowerPanel = new RightLowerPanel();
		newEventButton = new WHButton("+ New Event");
		sortPanel = new JPanel();
		filterPanel = new JPanel();
		sortFilterPanel = new JPanel(new BorderLayout());
		categories = new JComboBox<String>();
		sortByTrending = new JRadioButton("Trending", false);
		sortByDefault = new JRadioButton("Most Recent", true);
		
	}
	
	public void createGUI() {
		// setting button sizes
		myProfileButton.setPreferredSize(newEventButton.getPreferredSize());
//		signOutButton.setPreferredSize(newEventButton.getPreferredSize());
		
		// setting panel sizes
		upperPanel.setPreferredSize(new Dimension(300,300));
		lowerPanel.setPreferredSize(new Dimension(300,300));

		
		// left panel
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		leftPanel.add(myProfileButton);
		add(leftPanel);
		
		// middle panel
		// TODO: create feed of EventPanelGUIs and place into the scrollpane
		// would we initialize scroll pane here? after we've placed GUI's into the panel?
		middlePanel.setLayout(new BorderLayout());
//		middlePanel.add(feedScrollPane, BorderLayout.CENTER);
		add(middlePanel);
		
		// right panel
//		rightPanel.setLayout(new BorderLayout());
//		upperPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
//		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		// sort and filter panel
		sortPanel.setBackground(new Color(255,204,102));
		sortPanel.setBorder(BorderFactory.createTitledBorder("Sort By"));
		sortPanel.add(sortByDefault);
		sortPanel.add(sortByTrending);
		filterPanel.setBackground(new Color(255,204,102));
		filterPanel.setBorder(BorderFactory.createTitledBorder("Filter By"));
		filterPanel.add(categories);
		sortFilterPanel.add(sortPanel, BorderLayout.NORTH);
		sortFilterPanel.add(filterPanel, BorderLayout.SOUTH);
		
//		upperPanel.add(newEventButton);
//		lowerPanel.add(sortFilterPanel);
		rightPanel.add(upperPanel, BorderLayout.NORTH);
		rightPanel.add(lowerPanel, BorderLayout.SOUTH);
		
		// adding sort and filter panel to right panel
		//rightGBC.gridy = 450;
		//rightPanel.add(sortFilterPanel,rightGBC);
		
		add(rightPanel);		
	}
	
	public void addActions() {
		
	}
	
	
	public static void main(String [] args) {
		MainFeedFrame mff = new MainFeedFrame();
		mff.setVisible(true);
	}
	
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
		background = ImageLibrary.getImage("img/left.png");
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
		background = ImageLibrary.getImage("img/middle.png");
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
		background = ImageLibrary.getImage("img/right_top.png");
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
		background = ImageLibrary.getImage("img/right_bottom.png");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
	
}
