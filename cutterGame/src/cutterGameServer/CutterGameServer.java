package cutterGameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class CutterGameServer {
	private TreeMap<Integer, RankInformation> rankTree;//���� key ���� ���ÿ� �������� ���� DB ���� �ذ��ؾ���.
	private int number = 0;
	
	public CutterGameServer() {
		rankTree = new TreeMap<>();
	}

	public void start() {
		System.out.println("������ ���۵Ǿ����ϴ�");

		try {
			ServerSocket serverSocket = new ServerSocket(7777);
			System.out.println("Listening to prot 7777");
			System.out.println();

			while(true){ //Ŭ���̾�Ʈ ���� ���� ����
				Socket socket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ�� ���� �Ǿ����ϴ� : " + socket);
				number++;
				System.out.println("���� �������� Ŭ���̾�Ʈ �� : " + number);
				new Thread(multiSocket(socket)).start();;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
		}

	}

	private synchronized Set<Map.Entry<Integer, RankInformation>> rankTreeInputNavigableSetOutput(Integer score, String name){
		//���������������ĵ� tree ����
		//TreeMap�� ���ÿ� ���� ������� �Ͼ�� �ʰ� �Ѵ�.
		rankTree.put(score, new RankInformation(name, System.currentTimeMillis()));
		
		
		NavigableMap<Integer, RankInformation> descendingMap = rankTree.descendingMap();
		return descendingMap.entrySet();
		
	}

	private Runnable multiSocket (Socket socket){
		return () ->{
			try {
				BufferedReader si = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter so = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				while(true){
					String line = si.readLine();//name�� |�� �� �� �����Ƿ� split���� �ʴ´�.
					int splitLocation = line.indexOf('|');
 
					Integer score = Integer.parseInt(line.substring(0, splitLocation));
					String name = line.substring(splitLocation+1);

					System.out.println("[" + name +"]�� ���� " + score);
					
					
					Set<Map.Entry<Integer, RankInformation>> descendingEntrySet = rankTreeInputNavigableSetOutput(score, name);
					int rank = 0;
					for (Map.Entry<Integer, RankInformation> entry : descendingEntrySet) {
						//���ĵ� ���� �޾� ���� ����Ʈ
						rank++;
						so.println(rank + "|" + entry.getKey() + "|" + entry.getValue());//��ũ, ����, ��¥, �̸�
						so.flush();
					}
					so.println("0");//end�� ����
					so.flush();
				}
			} catch (IOException e) {
				System.out.println("Ŭ���̾�Ʈ ������ �����Ǿ����ϴ� : " + socket);
				number--;
				System.out.println("���� �������� Ŭ���̾�Ʈ �� : " + number);
			}catch(NullPointerException e){
				System.out.println("Ŭ���̾�Ʈ ������ �����Ǿ����ϴ� : " + socket);
				number--;
				System.out.println("���� �������� Ŭ���̾�Ʈ �� : " + number);
				
			}finally{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}

	class RankInformation{
		private String name;
		private Date date;

		public RankInformation(String name, long date) {
			this.name = name;
			this.date = new Date(date);
		}
		public String getIngormation() {
			return name;
		}
		public Date getDate() {
			return date;
		}
		
		public String toString(){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(E)");
			
			return  dateFormat.format(date) + "|" + name; 
		}
	}

}
