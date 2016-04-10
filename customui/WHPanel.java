package customui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import library.ImageLibrary;

public class WHPanel extends JPanel{
	private static final long serialVersionUID = 4893800319807315401L;
	
	private static final Image mBackgroundImage;
	
	static {
		mBackgroundImage = ImageLibrary.getImage("img/splash.png");
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
	}
	
}
