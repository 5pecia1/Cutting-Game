package cutterGmaeClient;

import javax.swing.JFrame;

import cutterGame.CutterGame;

public class CutterGameClient extends CutterGame{
	@Override
	public void start() {
		startPanel = new StartPanelClient(this);
		gamePanel = new GamePanelClient(this);
		endPanel = new EndPanelClient(this);
		
		mainFrame.add(startPanel,"start");
		mainFrame.add(gamePanel,"game");
		mainFrame.add(endPanel,"end");
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.pack();
		
		mainFrame.setVisible(true);
		
		setStartCardLayout();		
	}
}
