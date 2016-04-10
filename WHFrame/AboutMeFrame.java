package WHFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import customui.WHButton;
import library.ImageLibrary;

public class AboutMeFrame extends JFrame{
	AMPanel amp;
	JPanel aminfo;
	JLabel am;
	JLabel un;
	JLabel numEvents;
	JLabel popEvent;
	JLabel suggestions;
	WHButton BB;
	JPanel amCenter;
	JPanel amSouth;
	
	public AboutMeFrame()
	{
		setTitle("AboutMe");
		am = new JLabel("About Me                                                                                                    ");
		am.setForeground(new Color(35, 139, 230));
		un = new JLabel("Username: ");
		un.setForeground(new Color(35, 139, 230));
		numEvents = new JLabel("Number of Epic Events I've Hosted: ");
		numEvents.setForeground(new Color(35, 139, 230));
		popEvent = new JLabel("Most Popular Event: ");
		popEvent.setForeground(new Color(35, 139, 230));
		suggestions = new JLabel("WhatsHapp Suggestion Results: ");
		suggestions.setForeground(new Color(35, 139, 230));
		BB = new WHButton("Go Back");
		
		
		
		
		setSize(900,602);
		amp = new AMPanel();
		amp.setLayout(new BorderLayout());
		aminfo = new JPanel();
		aminfo.setLayout(new BorderLayout());
		aminfo.setBackground(new Color(130,25,44));
		aminfo.setBorder(BorderFactory.createRaisedBevelBorder());
		
		aminfo.add(am, BorderLayout.NORTH);
		
		amCenter = new JPanel();
		amCenter.setBackground(new Color(130,25,44));
		amCenter.setLayout(new BoxLayout(amCenter,BoxLayout.Y_AXIS));
		amCenter.add(Box.createVerticalStrut(150));
		amCenter.add(un);
		amCenter.add(Box.createVerticalStrut(25));
		amCenter.add(numEvents);
		amCenter.add(Box.createVerticalStrut(25));
		amCenter.add(popEvent);
		amCenter.add(Box.createVerticalStrut(25));
		amCenter.add(suggestions);
		
		amSouth = new JPanel();
		amSouth.setBackground(new Color(130,25,44));
		amSouth.add(BB);
	
		
		
		aminfo.add(amCenter, BorderLayout.CENTER);
		aminfo.add(amSouth, BorderLayout.SOUTH);

		amp.add(aminfo,BorderLayout.WEST);
		add(amp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//	public static void main(String [] args)
//	{
//		AboutMeFrame amf = new AboutMeFrame();
//	
//		amf.setVisible(true);
//	}
}
class AMPanel extends JPanel {
	private static final long serialVersionUID = 7141608019316770268L;

	private static final Image mBackgroundImage;
	private static final String mTitle = "About Me!";

	static {
		mBackgroundImage = ImageLibrary.getImage("img/me2.png");
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(35, 139, 230));
		Font font = g.getFont().deriveFont(40.0f);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int heightc = metrics.getHeight()/2;
		int widthc = metrics.stringWidth(mTitle)/2;
		g.drawString(mTitle, (getWidth()/4) - widthc, (getHeight()/3) - heightc);
	}
}