
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.EventPanelGUI;
import library.ImageLibrary;

public class MainFeedFrame extends JFrame {
	
//	JButton AM;
//	JButton sportsButton;
//	JButton culturalButton;
//	JButton careerButton;
//	JButton clubButton;
//	JButton trendingButton;
//	JButton newEventButton;
//	Event event;
	JScrollPane jsp;
	SplashFrame2 sf;
//	JPanel mfButtons;
//	JPanel mff;
	//create an event which will make a jpanel and store with correct info
	
	public MainFeedFrame()
	{
		setTitle("WhatsHapp");
		setSize(900,602);
		
//		mfButtons = new JPanel();
//		mff = new JPanel();
//		AM = new JButton("About Me!");
//		sportsButton = new JButton("Sports");
//		culturalButton = new JButton("Cultural");
//		careerButton = new JButton("Career");
//		clubButton = new JButton("Club");
//		newEventButton = new JButton("New Button");
//		trendingButton = new JButton(); //"trending"
//		trendingButton.setIcon(new ImageIcon("img/firebtn.png"));
		sf = new SplashFrame2();
		sf.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
//		sf.add(AM, gbc);
		//mfButtons.add(AM);
		//mfButtons.add(trendingButton);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
//		sf.add(trendingButton, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
//		sf.add(sportsButton, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
//		sf.add(culturalButton, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
//		sf.add(careerButton, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 0;
//		sf.add(clubButton, gbc);
		
		gbc.gridx = 6;
		gbc.gridy = 0;
		
//		sf.add(newEventButton, gbc);
		
		gbc.insets = new Insets(200,0,0,0);

		//jsp = new JScrollPane();
		//mff.add(jsp);
		
		
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

class SplashFrame2 extends JPanel { //outer class - custom login frame
	private static final long serialVersionUID = 7141608019316770268L;

	private static final Image mBackgroundImage;
	private static final String mTitle = "Here's What's Happening!";

	static {
		mBackgroundImage = ImageLibrary.getImage("img/campus.jpg");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
		Font font = new Font("Phosphate", Font.PLAIN, 40);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int heightc = metrics.getHeight()/2;
		int widthc = metrics.stringWidth(mTitle)/2;
		int x = (getWidth()/2) - widthc;
		int y = (getHeight()/4) - heightc;
		g.setColor(new Color(128, 0, 0));
		g.drawString(mTitle, ShiftWest(x, 1), ShiftNorth(y, 1)-60);
		g.drawString(mTitle, ShiftWest(x, 1), ShiftSouth(y, 1)-60);
		g.drawString(mTitle, ShiftEast(x, 1), ShiftNorth(y, 1)-60);
		g.drawString(mTitle, ShiftEast(x, 1), ShiftSouth(y, 1)-60);
		g.setColor(new Color(255, 204, 0));
		g.drawString(mTitle, x, y-60);
		
	}
	int ShiftNorth(int p, int distance) {
		return (p - distance);
	}
	int ShiftSouth(int p, int distance) {
		return (p + distance);
	}
	int ShiftEast(int p, int distance) {
		return (p + distance);
	}
	int ShiftWest(int p, int distance) {
		return (p - distance);
	}
}
