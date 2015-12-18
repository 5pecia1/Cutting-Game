package cutterGame;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndPanel extends JPanel implements ActionListener{
	
	protected CutterGame cutterGame;
	private int highscore = 0;
	private JButton restartButton;
	private JButton endButton;
	private JLabel scoreLable, scoreLable2, labelname, labelname2;
	private BoxLayout boxLayout;
	private JPanel collectPanel, collectPanel2, collectPanel3;

	public EndPanel(CutterGame cutterGame) {
		
		collectPanel = new JPanel();//현재스코어
		collectPanel2 = new JPanel();//가장 높은 스코어
		collectPanel3 = new JPanel();//나머지 버튼
		
		boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);
		this.cutterGame = cutterGame;
		restartButton = new JButton("Restart?");
		endButton = new JButton("End Game");
		
		scoreLable = new JLabel("0");
		scoreLable2 = new JLabel("0");
		
		labelname = new JLabel("SCORE");
		labelname2 = new JLabel("MYHIGH");
		
		collectPanel.add(labelname);
		collectPanel.add(scoreLable);
		
		collectPanel2.add(labelname2);
		collectPanel2.add(scoreLable2);
		
		collectPanel3.add(restartButton);
		collectPanel3.add(endButton);
		
		this.add(collectPanel);
		this.add(collectPanel2);
		this.add(collectPanel3);
		
		
		
		restartButton.addActionListener(this);
		endButton.addActionListener(this);
	}
	
	public void run(int score){
		if (highscore < score){
			highscore = score;
		}
		scoreLable.setText(score+"");
		scoreLable2.setText(highscore+"");
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
