package cutterGame;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CutterGame {
	private final int gameWidth = 500, gameHeight = 350;
	
	private JFrame mainFrame;
	private CardLayout cardLayout ;
	private JPanel startPanel, endPanel;
	private GamePanel gamePanel;
	

	
	public CutterGame(){
		cardLayout = new CardLayout();
		mainFrame= new JFrame("CutterGame");
		mainFrame.setLayout(cardLayout);
		
		startPanel = new JPanel();//sung bin
		gamePanel = new GamePanel(gameWidth, gameHeight);
		endPanel = new JPanel(); // wait...
		
//		startPanel.add
//		endPanel.add
		
		mainFrame.add(startPanel,"start");
		mainFrame.add(gamePanel,"game");
//		mainFrame.add(endPanel,"end");
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public void start() {
//		cardLayout.first(mainFrame.getContentPane());
		cardLayout.show(mainFrame.getContentPane(),"game");
		gamePanel.start();
		
	}
}
