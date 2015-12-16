package cutterGmaeClient;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cutterGame.CutterGame;
import cutterGame.StartPanel;

public class StartPanelClient extends StartPanel {
	private JTextField nameField = null;
	
	public StartPanelClient(CutterGame cutterGame) {
		super(cutterGame);
		nameField = new JTextField(20);
		
		
		super.add(new JLabel("ID를 입력해 주세요"));
		super.add(nameField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((CutterGameClient)cutterGame).setName(nameField.getText());
		cutterGame.setGameCardLayout();
	}

}
