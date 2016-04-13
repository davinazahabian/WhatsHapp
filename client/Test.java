package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import Model.Event;

public class Test extends JFrame {
	JScrollPane scrollPane;
	  JPanel contentPane;
	  JPanel panel;
	public Test(){
		this.setLayout(new GridLayout(3, 1));
        JPanel emptyPanel = new JPanel();
        JPanel emptyPanel2 = new JPanel();
         panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        for (int i = 0; i < 100; i++) {
        	Event event = new Event("World Cup", "March 45th", "25:00", "27:00", "It is the world cup", "USA", 0, "hosr", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        	EventPanelGUI epg = new EventPanelGUI(event);
        	epg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        	panel.add(epg,0);
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       // scrollPane.setBounds(50*4, 30*4, 300, 50*4);
        scrollPane.setBounds(0, 0, 900, 602);
        contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(900, 602));
        contentPane.add(scrollPane);
        this.setContentPane(contentPane);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	
	public void addNewPanel(){
		Event event = new Event("World Cup", "March 45th", "25:00", "27:00", "It is the world cup", "USA", 0, "hosr", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
    	EventPanelGUI epg = new EventPanelGUI(event);
    	epg.setBorder(BorderFactory.createLineBorder(Color.RED));
    	panel.add(epg, 0);
	}

    public static void main(String... args) {
    
       Test test = new Test();
       for (int i = 0; i < 10; i++) {
		test.addNewPanel();
	}
    }
    
    @Override
    public void paintComponents(Graphics g) {
    	// TODO Auto-generated method stub
    	super.paintComponents(g);
    	scrollPane.setBounds(0,0,getWidth(), getHeight());
    	 contentPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
    	 scrollPane.revalidate();
    	// scroll
    }
  
}