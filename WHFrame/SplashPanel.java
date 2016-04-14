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
			mBackgroundImage = ImageLibrary.getImage("img/campus.jpg");
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(), null);
			
			Font font = new Font("Phosphate", Font.PLAIN, 100);
			g.setFont(font);
			FontMetrics metrics = g.getFontMetrics(font);
			int heightc = metrics.getHeight()/2;
			int widthc = metrics.stringWidth(mTitle)/2;
			int x = (getWidth()/2) - widthc;
			int y = (getHeight()/4) - heightc;
			
			g.setColor(new Color(128, 0, 0));
			g.drawString(mTitle, ShiftWest(x, 1), ShiftNorth(y, 1));
			g.drawString(mTitle, ShiftWest(x, 1), ShiftSouth(y, 1));
			g.drawString(mTitle, ShiftEast(x, 1), ShiftNorth(y, 1));
			g.drawString(mTitle, ShiftEast(x, 1), ShiftSouth(y, 1));
			g.setColor(new Color(255, 204, 0));
			g.drawString(mTitle, x, y);
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

