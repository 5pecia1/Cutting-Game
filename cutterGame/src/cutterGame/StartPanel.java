package cutterGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener{
	private CutterGame cutterGame;
	private JButton startButton;
	
	public StartPanel(CutterGame cutterGame) {
		this.cutterGame = cutterGame;
		startButton = new JButton("Start");
		
		this.add(startButton);
		
		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cutterGame.setGameCardLayout();
	}
	
	
}
