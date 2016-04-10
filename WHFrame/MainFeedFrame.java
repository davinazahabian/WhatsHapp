package WHFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.ImageLibrary;

public class MainFeedFrame extends JFrame {
	
	JButton AM;
	JButton sportsButton;
	JButton culturalButton;
	JButton careerButton;
	JButton clubButton;
	JButton trendingButton;
	JButton newEventButton;
//	Event event;
	JScrollPane jsp;
	SplashFrame sf;
	JPanel mfButtons;
	JPanel mff;
	//create an event which will make a jpanel and store with correct info
	
	MainFeedFrame()
	{
		setTitle("WhatsHapp");
		setSize(640,480);
		
		mfButtons = new JPanel();
		mff = new JPanel();
		AM = new JButton("About Me!");
		sportsButton = new JButton("Sports");
		culturalButton = new JButton("Cultural");
		careerButton = new JButton("Career");
		clubButton = new JButton("Club");
		newEventButton = new JButton("New Button");
		trendingButton = new JButton(); //"trending"
		trendingButton.setIcon(new ImageIcon("img/firebtn.png"));
		sf = new SplashFrame();
		sf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		sf.add(AM, gbc);
		//mfButtons.add(AM);
		//mfButtons.add(trendingButton);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		sf.add(trendingButton, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		sf.add(sportsButton, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		sf.add(culturalButton, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		sf.add(careerButton, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 0;
		sf.add(clubButton, gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 0;
		
		sf.add(newEventButton, gbc);
		
		gbc.insets = new Insets(200,0,0,0);
		
		
//		mfButtons.add(sportsButton);
//		mfButtons.add(culturalButton);
//		mfButtons.add(careerButton);
//		mfButtons.add(clubButton);
		
		
		
		jsp = new JScrollPane();
		mff.add(jsp);
		
		
//		sf.add(mfButtons, BorderLayout.NORTH);
//		sf.add(mff, BorderLayout.SOUTH);
		add(sf);
	}
	
	public static void main(String [] args)
	{
		MainFeedFrame mff = new MainFeedFrame();
		mff.setVisible(true);
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
