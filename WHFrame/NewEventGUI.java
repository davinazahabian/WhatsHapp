package WHFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import Model.Event;
import client.WHClient;
import customui.OutlinedLabel;
import customui.WHButton;
import library.ImageLibrary;
import WHFrame.*;

/*
 * 
 * NewEventGUI - the GUI for the new event form; will create an Event object using the details of a
 * given event, add it to the corresponding tables in the database, and add it to the feed as an
 * instance of EventPanelGUI
 * 
 */

public class NewEventGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField eventNameField;
	private JTextField locationField;
	private JTextArea descriptionField; 
	private JTextField hostField;
	private JComboBox<String> typeBox;

	private OutlinedLabel eventNameLabel;
	private OutlinedLabel dateLabel;
	private OutlinedLabel timeLabel;
	private OutlinedLabel endTimeLabel;
	private OutlinedLabel locationLabel;
	private OutlinedLabel descriptionLabel;
	private OutlinedLabel hostLabel;
	private OutlinedLabel typeLabel;
	private SplashPanel splash;
	private WHButton backButton;
	private WHButton createButton;
	private JSpinner spinner;
	private JSpinner spinner2;
	private JDatePickerImpl datePicker;
	private WHClient whClient;
	


	public NewEventGUI(WHClient whClient) {
		this.whClient = whClient;
		setTitle("What's Happ");
		setSize(900,602);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());

		initializeComponents();
		createGUI();
		addActions();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void initializeComponents() {
		eventNameField = new JTextField(10);
		locationField = new JTextField(10);
		hostField = new JTextField(10);
		descriptionField = new JTextArea(5,10);

		typeBox = new JComboBox<String>();
		eventNameLabel = new OutlinedLabel("Event Name: ");
		dateLabel = new OutlinedLabel("Date: ");
		timeLabel = new OutlinedLabel("Start Time: ");
		hostLabel = new OutlinedLabel("Host: ");
		locationLabel = new OutlinedLabel("Location: ");
		descriptionLabel = new OutlinedLabel("Description: ");
		typeLabel = new OutlinedLabel ("Type: ");
		endTimeLabel = new OutlinedLabel("End Time: ");
		createButton = new WHButton("Create Event");
		backButton = new WHButton("Cancel");

		splash = new SplashPanel();

		// date picker stuff
		spinner = getSpinner();
		spinner2 = getSpinner();
		UtilDateModel model4 = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model4, p);
		// Don't know about the formatter, but there it is...
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	}

	private void createGUI() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		this.setCursor(cursor);
		typeBox.addItem("Sports");
		typeBox.addItem("Career");
		typeBox.addItem("Cultural");
		typeBox.addItem("Club");
		typeBox.setSelectedIndex(0);
		eventNameLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		eventNameLabel.setForeground(new Color(255, 204, 0));
		eventNameLabel.setOutlineColor(Color.black);
		eventNameLabel.setOpaque(false);
		dateLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		dateLabel.setForeground(new Color(255, 204, 0));
		dateLabel.setOutlineColor(Color.black);
		dateLabel.setOpaque(false);
		timeLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		timeLabel.setForeground(new Color(255, 204, 0));
		timeLabel.setOutlineColor(Color.black);
		timeLabel.setOpaque(false);
		hostLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		hostLabel.setForeground(new Color(255, 204, 0));
		hostLabel.setOutlineColor(Color.black);
		hostLabel.setOpaque(false);
		locationLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		locationLabel.setForeground(new Color(255, 204, 0));
		locationLabel.setOutlineColor(Color.black);
		locationLabel.setOpaque(false);
		descriptionLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		descriptionLabel.setForeground(new Color(255, 204, 0));
		descriptionLabel.setOutlineColor(Color.black);
		descriptionLabel.setOpaque(false);
		descriptionField.setEditable(true);
		descriptionField.setLineWrap(true);
		descriptionField.setWrapStyleWord(true);
		
		typeLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		typeLabel.setForeground(new Color(255, 204, 0));
		typeLabel.setOutlineColor(Color.black);
		typeLabel.setOpaque(false);
		endTimeLabel.setFont(new Font ("Impact", Font.BOLD, 20));
		endTimeLabel.setForeground(new Color(255, 204, 0));
		endTimeLabel.setOutlineColor(Color.black);
		endTimeLabel.setOpaque(false);
		setLayout(new GridLayout(1, 1));
		splash.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3);
		splash.add(eventNameLabel,c);
		splash.add(eventNameField,c);
		c.gridy = 2;
		splash.add(dateLabel,c);
		splash.add(datePicker,c);

		c.gridy = 3;
		splash.add(timeLabel,c);
		splash.add(spinner,c);

		c.gridy = 4;
		splash.add(endTimeLabel,c);
		splash.add(spinner2, c);

		c.gridy = 5;
		splash.add(locationLabel,c);
		splash.add(locationField,c);

		c.gridy = 6;
		splash.add(hostLabel,c);
		splash.add(hostField,c);

		c.gridy = 7;
		splash.add(descriptionLabel,c);
		splash.add(descriptionField,c);

		c.gridy = 8;
		splash.add(typeLabel, c);
		splash.add(typeBox, c);

		c.gridy = 9;
		c.gridwidth = 5;
		splash.add(createButton,c);
		
		c.gridy = 10;
		c.gridwidth = 5;
		splash.add(backButton,c);

		add(splash);
	}

	private void addActions() {
		
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) datePicker.getModel().getValue();
				System.out.println(spinner.getValue());
				System.out.println(spinner2.getValue());
				System.out.println(selectedDate.toString());

				String[] date = (selectedDate.toString().split(" "));
				String[] start = ((String)spinner.getValue().toString()).split(" ");
				String[] end = ((String)spinner2.getValue().toString()).split(" ");

				System.out.println(date[0] + " " +  date[1] + " " + date[2] + " " + date[5] + " " + start[3] + " to " + end[3]  );
				String name = eventNameField.getText();
				String location = locationField.getText();
				String description = descriptionField.getText();
				String date2 = date[0] + " " +  date[1] + " " + date[2] + " " + date[5];
				String startTime = start[3];
				String endTime = end[3];
				String host = hostField.getText();
				Event event = new Event(name, date2, startTime, endTime, description, location,typeBox.getSelectedIndex(), new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()), host);
				
				whClient.newEventRequest(event);

				
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				whClient.getNeg().setVisible(false);
				whClient.getMff().setVisible(true);
			}
		});
	}
	
	public void showEventFailure() {
		JOptionPane.showMessageDialog(this,"There is already an event at the same place and same time. Please try again!", "Warning!", JOptionPane.WARNING_MESSAGE, new ImageIcon("img/icon.png"));
	}
	
	public void newEvent() {
		System.out.println("entered new event");
		
		setVisible(false);
		whClient.setMff(new MainFeedFrame(whClient));
		whClient.getMff().populateFeed(whClient.getAllEvents());
		whClient.getMff().setVisible(true);
	}
	public WHClient getWhClient() {
		return whClient;
	}

	public void setWhClient(WHClient whClient) {
		this.whClient = whClient;
	}

	public JSpinner getSpinner(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());

		JSpinner temp = new JSpinner(model);

		JSpinner.DateEditor editor = new JSpinner.DateEditor(temp, "HH:mm:ss");
		DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
		formatter.setAllowsInvalid(false); // this makes what you want
		formatter.setOverwriteMode(true);
		temp.setEditor(editor);

		Component mySpinnerEditor =temp.getEditor();
		JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
		jftf.setColumns(9);

		return temp;
	}
}

class DateLabelFormatter extends AbstractFormatter {

	private static final long serialVersionUID = 1L;
	private String datePattern = "yyyy-MM-dd";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException, java.text.ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

}