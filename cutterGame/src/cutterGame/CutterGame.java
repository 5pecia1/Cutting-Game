package cutterGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CutterGame {
	private final int gameWidth = 500, gameHeight = 350;
	private JFrame mainFrame;
	
	public CutterGame(){
		mainFrame= new JFrame("CutterGame");
		
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(gameWidth, gameHeight);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	public void start() {//각종 jframe 기능 //재시작 및 종료 기능
		
		
	}
	
	class StartButtonJPanel extends JPanel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
