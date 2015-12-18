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
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class CutterGameServer {
	private TreeSet<RankInformation> rankTree;
	private int number = 0;
	private ServerSocket serverSocket;
	
	public CutterGameServer() {
		rankTree = new TreeSet<>();
	}

	public void start() {
		System.out.println("������ ���۵Ǿ����ϴ�");

		try {
			serverSocket = new ServerSocket(7777);
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

	private synchronized NavigableSet<RankInformation> rankTreeInputNavigableSetOutput(Integer score, String name){
		//���������������ĵ� tree ����
		//TreeSet�� ���ÿ� ���� ������� �Ͼ�� �ʰ� �Ѵ�.
		rankTree.add(new RankInformation(score,name, System.currentTimeMillis()));
		
		NavigableSet<RankInformation> descendingSet = rankTree.descendingSet();
		return descendingSet;
		
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
					
					NavigableSet<RankInformation> descendingSet = rankTreeInputNavigableSetOutput(score, name);
					
					int rank = 0;
					for (RankInformation rankInformation : descendingSet) {
						rank++;
						so.println(rank + "|" + rankInformation);//��ũ, ����, ��¥, �̸�
						
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

	class RankInformation implements Comparable<RankInformation>{
		private int score;
		private String name;
		private Date date;

		public RankInformation(int score, String name, long date) {
			this.score = score;
			this.name = name;
			this.date = new Date(date);
		}
		public String getIngormation() {
			return name;
		}
		public Date getDate() {
			return date;
		}
		public int getScore(){
			return score;
		}
		
		public String toString(){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss(E)");
			
			return  score + "|" + dateFormat.format(date) + "|" + name; 
		}
		@Override
		public int compareTo(RankInformation o) {//�� �ߺ� ����Ѵ�.
			if(score<=o.getScore()) return -1;
			else return 1;
		}
	}

}
