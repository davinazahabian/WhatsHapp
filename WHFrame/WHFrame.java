package WHFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import WHFrame.WHFrame.ThemeColors;
import customui.WHButton;
import library.ImageLibrary;

public class WHFrame extends JFrame{
	SplashFrame sf;
	
	JTextField utf;
	JPasswordField ptf;
	WHButton loginButton;
	WHButton signUpButton;
	WHButton guestButton;
	JLabel suQuestion; //suQuestion
	JLabel ul;
	JLabel pl;
	
	public WHFrame()
	{
		setTitle("WhatsHapp");
		setSize(640,480);
		sf = new SplashFrame();
		//sf.setLayout(new GridLayout(1,2)); //1 row and 2 columns
		sf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		utf = new JTextField(10);
		ptf = new JPasswordField(10);
		suQuestion = new JLabel("Don't have an account? Register Now!");
		loginButton =  new WHButton("Login");
		signUpButton = new WHButton("Create an account!");
		guestButton = new WHButton("Continue in guest mode");
		ul = new JLabel("username:  ");
		pl = new JLabel("Password:  ");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		sf.add(ul, gbc); //add username label
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.insets = new Insets(8,0,0,0);
		sf.add(pl, gbc); //add password label
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,0,0);
		sf.add(utf, gbc); //add username text field
		
		gbc.gridx = 1;
		gbc.gridy = 1;
	//	gbc.gridwidth = 2;
		gbc.insets = new Insets(8,0,0,0);
		gbc.gridwidth = 2;
		sf.add(ptf, gbc); //add password text field

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(8,0,0,0);
		sf.add(loginButton, gbc); //add login button
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,40,0,0);
		sf.add(suQuestion, gbc); //add question label
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(8,40,0,0);
		sf.add(signUpButton, gbc); //add sign up button
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(8,0,0,0);
		sf.add(guestButton, gbc); //add guest button
		
		
//		sf.add(utf);
//		sf.add(suQuestion);
//		sf.add(ptf);
//		sf.add(signUpButton);
//		sf.add(loginButton);
//		sf.add(guestButton);
//		
		
		add(sf);
		
		
	}
	
	
	
	
	
	public static void main(String [] args)
	{
//		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//		setUIFont(new javax.swing.plaf.FontUIResource(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/kenvector_future.ttf")).deriveFont(12.0f)));

		WHFrame whf = new WHFrame();
		whf.setVisible(true);
	}
}

class SplashFrame extends JPanel { //outer class - custom login frame
	private static final long serialVersionUID = 7141608019316770268L;

	private static final Image mBackgroundImage;
	private static final String mTitle = "WhatsHapp";

	static {
		mBackgroundImage = ImageLibrary.getImage("img/splash.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
		//g.setColor(ThemeColors.MainColor);
		g.setColor(new Color(35, 139, 230));
		Font font = g.getFont().deriveFont(40.0f);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int heightc = metrics.getHeight()/2;
		int widthc = metrics.stringWidth(mTitle)/2;
		g.drawString(mTitle, (getWidth()/2) - widthc, (getHeight()/3) - heightc);
	}
}
