package cutterGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ActionListener{
	protected CutterGame cutterGame;
	private JButton startButton;
	private JLabel gamename, gamename2;
	
	
	
	public StartPanel(CutterGame cutterGame) {
		
		this.setLayout(null);
		this.cutterGame = cutterGame;
		gamename = new JLabel("Cutter ");//;
		gamename2 = new JLabel("Game");//;
		startButton = new JButton("Start");
		
		
		gamename.setForeground(Color.ORANGE);//;
		gamename2.setForeground(Color.RED);//;
		
		gamename.setFont(gamename.getFont().deriveFont(50.0f));//;
		gamename2.setFont(gamename2.getFont().deriveFont(50.0f));//;
		
		gamename.setBounds(0, 0, 200, 100);//;
		gamename2.setBounds(CutterGame.GAMEWIDTH, 0, 200, 100);//;
		
		startButton.setFont(gamename.getFont().deriveFont(20.0f));//;
		
		startButton.setBounds(200, 200, 100, 40);
		
		this.add(gamename);
		this.add(gamename2);
		
		this.add(startButton);
		
		Thread thread = new Thread(() ->{
			for(int x = 0; x < 190; x+=20) {//;
				for(int y = 0; y < 30; y+=10){//;
					if ((x <= 130)&&(y < 35)) {
						gamename.setBounds(x, y, 200, 100);
					}
					if ((x <= 120)&&(y < 35)) {
						gamename2.setBounds(330 - x, 80-y, 200, 80);//;
					}
					
					try{
						Thread.sleep(60); //;
					}
					catch(InterruptedException ignore){}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();

		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cutterGame.setGameCardLayout();
	}
	
	
}
