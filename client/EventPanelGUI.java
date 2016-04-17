package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Model.Event;
import Model.InfoPackage;
import customui.UpVoteButton;
import library.ImageLibrary;

// individual panels of events on the eventfeedgui
public class EventPanelGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private UpVoteButton upArrowButton;
	private JLabel upVoteCounter;


	private JPanel leftContainer;
	private JPanel buttonHolder;
	private JLabel titleHolder;
	private JLabel numAttendingHolder;
	private int pressed = 0;
	public JLabel numAttendingHolder() {
		return numAttendingHolder;
	}

	public void numAttendingHolder(JLabel numAttendingHolder) {
		this.numAttendingHolder = numAttendingHolder;
	}
	private JLabel locationHolder;
	private JPanel rightContainer;
	
	private Event e;
	private int eventCategory;
	private WHClient whClient;
	private EventPanelGUI epg;
	private EventDetailGUI edg;
	public EventPanelGUI(Event e, WHClient whClient) {
		this.whClient = whClient;
		this.e = e;
		this.epg = this;
		
		setSize(100,100);
		setLayout(new BorderLayout());
		initializeVariables();
		createGUI();
		//addActionAdapters();
		//setLocationRelativeTo(null);
		addMouseActions();
		setMinimumSize(new Dimension(640,480));
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}// TODO
	
	private void initializeVariables() {
		
		
		upArrowButton = new UpVoteButton();
		upVoteCounter = new JLabel();
		upVoteCounter.setText("" + e.getUpvotes());
		upVoteCounter.setFont(new Font ("Impact", Font.PLAIN, 25));
		leftContainer = new JPanel();
		
		titleHolder = new JLabel();
		titleHolder.setText(e.getEventName());
		titleHolder.setFont(new Font ("Impact", Font.PLAIN, 22));
		
		
		numAttendingHolder = new JLabel();
		numAttendingHolder.setText( e.getAttendees() + " attending");
		numAttendingHolder.setFont(new Font ("Impact", Font.PLAIN, 18));
		
		
		locationHolder = new JLabel();
		locationHolder.setText("at " + e.getEventLoc());
		locationHolder.setFont(new Font ("Impact", Font.PLAIN, 18));
		rightContainer = new JPanel();
	}
	
	private void createGUI(){
		
		leftContainer.setLayout(new BorderLayout());
		leftContainer.add(upVoteCounter, BorderLayout.WEST);
		
		leftContainer.add(upArrowButton, BorderLayout.EAST);
		
		rightContainer.setLayout(new GridLayout(3,0));
		rightContainer.add(titleHolder);
		rightContainer.add(locationHolder);
		rightContainer.add(numAttendingHolder);
		
		
		add(leftContainer, BorderLayout.WEST);
		add(rightContainer, BorderLayout.EAST);
		
		
		this.setBackgroundColor(this.e.getType(), leftContainer, rightContainer);
		
	}
	
	private void addMouseActions(){
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent me) {
//				new EventDetailGUI(e, whc);
//				System.out.println("Enters mouseClicked");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				edg = new EventDetailGUI(e, whClient, epg);
				System.out.println("Enters mouseClicked");
			}
		});
		
		upArrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(pressed > 0){
					return;
				}
				whClient.incrementUpvote(e);
				upVoteCounter.setText("" + e.getUpvotes());
				pressed++;
			}
		});
	}
	
	private void setBackgroundColor(int category, JPanel left, JPanel right){
		if(category == 0){//sports = red
			this.setBackground(new Color(255, 105, 98));
			leftContainer.setBackground(new Color(255, 105, 98));
			rightContainer.setBackground(new Color(255, 105, 98));
		}
		else if(category == 1){//career = blue
			this.setBackground(new Color(183, 209, 236));
			leftContainer.setBackground(new Color(183, 209, 236));
			rightContainer.setBackground(new Color(183, 209, 236));
		}
		else if (category == 2){//cultural = green
			this.setBackground(new Color(119, 221, 119));
			leftContainer.setBackground(new Color(119, 221, 119));
			rightContainer.setBackground(new Color(119, 221, 119));
		}
		else if(category == 3){//club = orange
			this.setBackground(new Color(255, 179, 69));
			leftContainer.setBackground(new Color(255, 179, 69));
			rightContainer.setBackground(new Color(255, 179, 69));
		}
	}

	public void postToBoard(InfoPackage p) {
		edg.postToBoard(p);
	}

	public Event getE() {
		return e;
	}

	public void setE(Event e) {
		this.e = e;
	}
	
}
