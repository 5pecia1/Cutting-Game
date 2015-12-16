package cutterGmaeClient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

import cutterGame.CutterGame;

public class CutterGameClient extends CutterGame{
	private String name ="";
	private Socket socket = null;

	@Override
	public void start() {
		try {
			socket = new Socket("localhost", 7777);
		} catch (UnknownHostException e) {//���� ���
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println(name);
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}
	@Override
	public void exitGame(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainFrame.setVisible(false);
		mainFrame.dispose();
	}
}
