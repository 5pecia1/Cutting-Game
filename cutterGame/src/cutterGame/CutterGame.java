package cutterGame;

import java.awt.CardLayout;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CutterGame {
	public static final int GAMEWIDTH = 500, GAMEHEIGHT = 350;
	
	private JFrame mainFrame;
	private CardLayout cardLayout ;
	private JPanel startPanel, endPanel;
	private GamePanel gamePanel;
	

	
	public CutterGame(){
		cardLayout = new CardLayout();
		mainFrame= new JFrame("CutterGame");
		mainFrame.setLayout(cardLayout);//게임을 시작, 게임, 끝 세 부분으로 나눈다.
		
		startPanel = new StartPanel(this);
		gamePanel = new GamePanel(this);
		endPanel = new JPanel(); // wait...
		
//		endPanel.add
		
		mainFrame.add(startPanel,"start");
		mainFrame.add(gamePanel,"game");
		mainFrame.add(endPanel,"end");
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.pack();
	
		mainFrame.setVisible(true);
	}
	public void start() {
		setStartCardLayout();		
	}
	
	public void setStartCardLayout(){
		new Thread(() -> cardLayout.show(mainFrame.getContentPane(),"start"));
	}
	public void setGameCardLayout(){
		new Thread(() -> {
			cardLayout.show(mainFrame.getContentPane(),"game");
			gamePanel.start();
		}).start();	
	}
	public void setEndCardLayout(){
		new Thread(() -> {
			cardLayout.show(mainFrame.getContentPane(),"end");
		});
	}
	
}
