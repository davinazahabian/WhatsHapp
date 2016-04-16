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
import customui.UpVoteButton;
import library.ImageLibrary;

// individual panels of events on the eventfeedgui
public class EventPanelGUI extends JPanel {
	private UpVoteButton upArrowButton;
	private JLabel upVoteCounter;
	private JPanel leftContainer;
	private JPanel buttonHolder;
	private JLabel titleHolder;
	private JLabel numAttendingHolder;
	private JLabel locationHolder;
	private JPanel rightContainer;
	
	private Event e;
	private int eventCategory;
	
	
	public EventPanelGUI(Event e){
		this.e = e;
		setSize(100,100);
		setLayout(new BorderLayout());
		initializeVariables();
		createGUI();
		//addActionAdapters();
		//setLocationRelativeTo(null);
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
	
	public static void main(String [] args) {
		JFrame frame = new JFrame();
		frame.setSize(320, 240);
		Event temp = new Event("event name", "event date", "event start time", "event end time", "event description", "event location",
				  3, "time posted", "event host");
		frame.add(new EventPanelGUI(temp));
		frame.setVisible(true);
	}

}
