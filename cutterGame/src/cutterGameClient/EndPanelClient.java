package cutterGameClient;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cutterGame.CutterGame;
import cutterGame.EndPanel;

public class EndPanelClient extends EndPanel { 
	private static String[] article = {"����","����", "��¥", "�̸�"};
	private JTable rankTable = null;
	private DefaultTableModel tableModel =null;
	private PrintWriter so;
	private BufferedReader si;

	public EndPanelClient(CutterGame cutterGame) {
		super(cutterGame);
		rankTable = new JTable(new DefaultTableModel(new Object[][]{},article));		
		tableModel = (DefaultTableModel)rankTable.getModel();
		rankTable.setPreferredScrollableViewportSize(new Dimension(CutterGame.GAMEWIDTH-200, CutterGame.GAMEHEIGHT-200));



		try {
			Socket socket = ((CutterGameClient)cutterGame).getSocket();

			so = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			si = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
		super.add(new JScrollPane(rankTable));
	}

	@Override
	public void run(int score) {
		super.run(score);
		new Thread(()->{//�ð��� ���� �ɸ� �� �����Ƿ� Thread ���
			so.println(score+ "|" + ((CutterGameClient)cutterGame).getName());
			so.flush();

			rankInput();
		}).start();
	}

	private void rankInput(){
		String line = "";

		for (int i = tableModel.getRowCount(); i > 0; i--) {//table ���
			tableModel.removeRow(0);
		}

		try {
			while(true){
				line = si.readLine();
				if(line.equals("0")) break;
				tableModel.addRow(getTableModelRowStringArray(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String[] getTableModelRowStringArray(String line){
		int i = 0;
		String[] finalSplitArray = new String[article.length];
		for (i = 0; i < article.length-1; i++) {//������ �̸��� �ۿ��� �־��ش�
			int splitLocation = line.indexOf('|');
			finalSplitArray[i] = line.substring(0, splitLocation);
			line = line.substring(splitLocation+1);
		}
		finalSplitArray[i] = line;

		return finalSplitArray;
	}
}
