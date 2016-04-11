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
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

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
	 JButton signUpButton;
	 JSpinner spinner;
	 JSpinner spinner2;
	 
	 public NewEventGUI(){
		 setTitle("WhatsHapp");
			setSize(900,602);
			setMinimumSize(new Dimension(900,602));
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
		signUpButton = new JButton("Create Event");
		endTimeLabel = new JLabel("End Time: ");
		ImageIcon water = new ImageIcon("back-icon.png");
	    backButton = new JButton(water);
	    
	   DefaultComboBoxModel<String> typeName = new DefaultComboBoxModel();

	      typeName.addElement("Career");
	      typeName.addElement("Sports");
	      typeName.addElement("Cultural");
	      typeName.addElement("Club");
	    typeBox = new JComboBox<String>(typeName);
	    typeBox.setSelectedIndex(0);
	    splash = new SplashPanel();
	    
	    Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());
        
        SpinnerDateModel model2 = new SpinnerDateModel();
        model2.setValue(calendar.getTime());

        spinner = new JSpinner(model);
        spinner2 = new JSpinner(model2);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);
        spinner.setEditor(editor);
        
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter2 = (DateFormatter)editor2.getTextField().getFormatter();
        formatter2.setAllowsInvalid(false); // this makes what you want
        formatter2.setOverwriteMode(true);
        spinner2.setEditor(editor2);
        
        Component mySpinnerEditor = spinner.getEditor();
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) mySpinnerEditor).getTextField();
        jftf.setColumns(9);
        
        Component mySpinnerEditor2 = spinner2.getEditor();
        JFormattedTextField jftf2 = ((JSpinner.DefaultEditor) mySpinnerEditor2).getTextField();
        jftf2.setColumns(9);
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
		splash.add(dateField,c);
		
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
		splash.add(signUpButton,c);
		
//		c.gridy = 7;
//		c.gridwidth = 7;
//		splash.add(backButton,c);
		add(splash);
	}

	private void addActions() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(ImageLibrary.getImage("img/cursor.png") , new Point(0, 0), "img");
		//whf.setCursor(c);
		new NewEventGUI();
	}


	
}
