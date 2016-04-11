package customui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import library.ImageLibrary;

public class WHButton extends JButton {
	private static final long serialVersionUID = 3964529762960557244L;
	
	private Image mCurrentImage;
	
	private static final Image mBackgroundImage;
	private static final Image mBackgroundImageSelected;
	
	static {
		mBackgroundImage = ImageLibrary.getImage("img/button.png");
		mBackgroundImageSelected = ImageLibrary.getImage("img/button_selected.png");
	}

	{
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		mCurrentImage = mBackgroundImage;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mCurrentImage = mBackgroundImageSelected;
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				mCurrentImage = mBackgroundImage;
			}
		});
	}
	
	public WHButton(String string) {
		super(string);
	}

	public WHButton(ImageIcon water) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(mCurrentImage, 0, 0, getWidth(), getHeight(), null);
		super.paintComponent(g);
	}
	
}
