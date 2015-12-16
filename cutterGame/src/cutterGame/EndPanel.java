package cutterGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndPanel extends JPanel implements ActionListener{
	private CutterGame cutterGame;
	private JButton restartButton;
	private JButton endButton;
	private JLabel scoreLable;

	public EndPanel(CutterGame cutterGame) {
		this.cutterGame = cutterGame;
		restartButton = new JButton("Restart?");
		endButton = new JButton("End Game");
		scoreLable = new JLabel("0");
		
		this.add(scoreLable);
		this.add(restartButton);
		this.add(endButton);
		
		
		restartButton.addActionListener(this);
		endButton.addActionListener(this);
	}
	
	public void run(int score){
		scoreLable.setText(score+"");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == restartButton){
			System.out.println("RR");//test code
			cutterGame.setGameCardLayout();
		}
		else if(e.getSource() == endButton){
			cutterGame.exitGame();
		}
	}
}
