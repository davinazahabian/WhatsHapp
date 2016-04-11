package WHFrame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import customui.WHButton;

public class EventInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	SplashPanel sp;
	JPanel mb;
	JLabel mbl;
	JScrollPane jsp;
	JTextArea jta;
	WHButton BB;
	public EventInfo()
	{
		setSize(900,602);
		sp = new SplashPanel();
		sp.setLayout(new BorderLayout());
		mbl = new JLabel("Any Questions or Comments?");
		mb = new JPanel();
		mb.setLayout(new BorderLayout());
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		mb.add(mbl, BorderLayout.NORTH);
		mb.add(jsp, BorderLayout.SOUTH);
		sp.add(mb, BorderLayout.EAST);
		BB = new WHButton("Back");
		//mb.add(BB, BorderLayout.SOUTH);
		add(sp);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public static void main(String [] args)
	{
		EventInfo ei = new EventInfo();
		ei.setVisible(true);
	}
	
	
}
