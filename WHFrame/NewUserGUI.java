package WHFrame;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class NewUserGUI extends JFrame{
	 JTextField usernameField;
	 JTextField passwordField;
	 JTextField firstNameField;
	 JTextField lastNameField;
	 JTextField emailField;
	 
	 JLabel usernameLabel;
	 JLabel passwordLabel;
	 JLabel firstNameLabel;
	 JLabel lastNameLabel;
	 JLabel emailLabel;
	 
	 JButton backButton;
	 JButton signUpButton;
	 
	 
	 public NewUserGUI(){
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
		usernameField = new JTextField(10);
		passwordField = new JTextField(10);
		firstNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		emailField = new JTextField(10);
		
		usernameLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		firstNameLabel = new JLabel("First Name: ");
		lastNameLabel = new JLabel("Last Name: ");
		emailLabel = new JLabel("Email: ");
		
		signUpButton = new JButton("Sign Up");
		
		ImageIcon water = new ImageIcon("back-icon.png");
	    backButton = new JButton(water);
	   
	    
	}
	
	private void create() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3);
		add(usernameLabel,c);
		add(usernameField,c);
		c.gridy = 2;
		add(passwordLabel,c);
		add(passwordField,c);
		
		c.gridy = 3;
		add(firstNameLabel,c);
		add(firstNameField,c);
		
		c.gridy = 4;
		add(lastNameLabel,c);
		add(lastNameField,c);
		
		c.gridy = 5;
		add(emailLabel,c);
		add(emailField,c);
		
		c.gridy = 6;
		c.gridwidth = 6;
		add(signUpButton,c);
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		
//		JPanel panel = new JPanel();
//		panel.setLayout(new GridLayout(1, 1));
//		panel.setSize(new Dimension(50, 50));
//		panel.add(backButton);
//		panel.setSize(new Dimension(50, 50));
//		add(panel);
		
	}

	private void addActions() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new NewUserGUI();
	}


	
}

