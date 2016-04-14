package customui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import library.ImageLibrary;

public class UpVoteButton extends JButton {
	private static final long serialVersionUID = 3964529762960557244L;
	
	private Image mCurrentImage;
	
	private static final Image mBackgroundImage;
	private static final Image mBackgroundImageSelected;
	
	static {
		mBackgroundImage = ImageLibrary.getImage("img/thumb_button.png");
		mBackgroundImageSelected = ImageLibrary.getImage("img/thumb_button_selected.png");
	}

	{
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		mCurrentImage = mBackgroundImage;
		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent arg0){
				mCurrentImage = mBackgroundImageSelected;
			}
			public void mouseReleased(MouseEvent arg0){
				mCurrentImage = mBackgroundImage;
			}

		});
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				mCurrentImage = mBackgroundImageSelected;
//				revalidate();
//				repaint();
//			}
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				mCurrentImage = mBackgroundImage;
//				revalidate();
//				repaint();
//			}
//		
//		});
	}
	
	public UpVoteButton() {
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(mCurrentImage, 0, 0, getWidth(), getHeight(), null);
		super.paintComponent(g);
	}
	
}