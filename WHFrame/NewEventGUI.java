package WHFrame;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import library.ImageLibrary;



public class NewEventGUI extends JFrame{
	JTextField eventNameField;
	JTextField dateField;
	JTextField timeField;
	JTextField locationField;
	JTextArea descriptionField;
	JTextField hostField;
	JComboBox<String> typeBox;

	JScrollPane jsp;
	JLabel eventNameLabel;
	JLabel dateLabel;
	JLabel timeLabel;
	JLabel endTimeLabel;
	JLabel locationLabel;
	JLabel descriptionLabel;
	JLabel hostLabel;
	JLabel typeLabel;
	SplashPanel splash;
	JButton backButton;
	JButton createButton;
	JSpinner spinner;
	JSpinner spinner2;
	JDatePickerImpl datePicker;
	public NewEventGUI(){
		setTitle("Trojan Office");
		setSize(640,480);
		setMinimumSize(new Dimension(640,480));
		//setJMenuBar(new OfficeMenuBar());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initComps();
		create();
		addActions();

		setVisible(true);
	}





	private void initComps() {
		eventNameField = new JTextField(10);
		dateField = new JTextField(10);
		timeField = new JTextField(10);
		locationField = new JTextField(10);
		hostField = new JTextField(10);
		descriptionField = new JTextArea(5, 10);

		eventNameLabel = new JLabel("Event Name: ");
		dateLabel = new JLabel("Date: ");
		timeLabel = new JLabel("Start Time: ");
		hostLabel = new JLabel("Host: ");
		locationLabel = new JLabel("Location: ");
		descriptionLabel = new JLabel("Description: ");
		typeLabel = new JLabel ("Type: ");
		createButton = new JButton("Create Event");
		endTimeLabel = new JLabel("End Time: ");
		ImageIcon water = new ImageIcon("back-icon.png");
		backButton = new JButton(water);

		DefaultComboBoxModel<String> typeName = new DefaultComboBoxModel();

		typeName.addElement("Sports");
		typeName.addElement("Career");
		typeName.addElement("Cultural");
		typeName.addElement("Club");
		typeBox = new JComboBox<String>(typeName);
		typeBox.setSelectedIndex(0);
		splash = new SplashPanel();

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

	private void create() {
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

		//			c.gridy = 7;
		//			c.gridwidth = 7;
		//			splash.add(backButton,c);
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
			}
		});
	}

	public static void main(String[] args){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");

		//whf.setCursor(c);
		new NewEventGUI();
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