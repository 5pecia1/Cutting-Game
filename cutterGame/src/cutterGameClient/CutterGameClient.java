package cutterGameClient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cutterGame.CutterGame;

public class CutterGameClient extends CutterGame{
	private String hostIP = "localhost";
	private String name ="";
	private Socket socket = null;

	@Override
	public void start() {
		try {
			socket = new Socket(hostIP, 7777);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null,"�ּ� �߸� ��");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�˼��մϴ�. ���� ������ ������ �ʾҽ��ϴ�...\n "
					+ "so0o0o0o0o0o0o0o0o0o0o0ol@gmail.com���� �����ּ���");
			System.exit(0);
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
			e.printStackTrace();
		}
		mainFrame.setVisible(false);
		mainFrame.dispose();
	}
}
