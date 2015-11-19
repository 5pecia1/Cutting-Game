package cutterGame;

import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CutterGame {
	private final int gameWidth = 500, gameHeight = 350;

	private JFrame mainFrame;
	private CardLayout cardLayout ;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private JPanel startPanel, gamePanel, endPanel;
	private LinkedList<Throwee> throweeList;
	private Thread physicsCalculation;
	
	public CutterGame(){
		throweeList = new LinkedList<>();
		cardLayout = new CardLayout();
		mainFrame= new JFrame("CutterGame");
		mainFrame.setLayout(cardLayout);
		
		canvas = new Canvas();
		canvas.setBackground(Color.white);
		canvas.setSize(gameWidth,gameHeight);

		startPanel = new JPanel();//sung bin
		gamePanel = new JPanel();
		endPanel = new JPanel(); // wait...
		
//		startPanel.add
		gamePanel.add(canvas);
//		endPanel.add
		

		mainFrame.add(startPanel,"start");
		mainFrame.add(gamePanel,"game");
//		mainFrame.add(endPanel,"end");
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(gameWidth, gameHeight);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		canvas.createBufferStrategy(2);//visible 이전에 하면 안됨
		bufferStrategy = canvas.getBufferStrategy();
	}
	public void start() {
		cardLayout.show(mainFrame.getContentPane(),"game"); //아직 start 아님
		
		
	}
}
