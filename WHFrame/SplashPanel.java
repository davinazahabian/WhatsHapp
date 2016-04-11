package WHFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import library.ImageLibrary;

public class SplashPanel extends JPanel {
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
			g.setColor(new Color(35, 139, 230));
			Font font = g.getFont().deriveFont(40.0f);
			g.setFont(font);
			FontMetrics metrics = g.getFontMetrics(font);
			int heightc = metrics.getHeight()/2;
			int widthc = metrics.stringWidth(mTitle)/2;
			g.drawString(mTitle, (getWidth()/2) - widthc, (getHeight()/4) - heightc);
		}
	}

