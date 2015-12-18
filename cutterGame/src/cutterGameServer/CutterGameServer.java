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
		System.out.println("서버가 시작되었습니다");

		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("Listening to prot 7777");
			System.out.println();

			while(true){ //클라이언트 다중 접속 가능
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트가 연결 되었습니다 : " + socket);
				number++;
				System.out.println("현재 접속중인 클라이언트 수 : " + number);
				new Thread(multiSocket(socket)).start();;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error");
		}

	}

	private synchronized NavigableSet<RankInformation> rankTreeInputNavigableSetOutput(Integer score, String name){
		//오름차순으로정렬된 tree 리턴
		//TreeSet에 동시에 여러 입출력이 일어나지 않게 한다.
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
					String line = si.readLine();//name에 |이 들어갈 수 있으므로 split하지 않는다.
					int splitLocation = line.indexOf('|');
 
					Integer score = Integer.parseInt(line.substring(0, splitLocation));
					String name = line.substring(splitLocation+1);

					System.out.println("[" + name +"]의 점수 " + score);
					
					NavigableSet<RankInformation> descendingSet = rankTreeInputNavigableSetOutput(score, name);
					
					int rank = 0;
					for (RankInformation rankInformation : descendingSet) {
						rank++;
						so.println(rank + "|" + rankInformation);//랭크, 점수, 날짜, 이름
						
					}
					so.println("0");//end값 전송
					so.flush();
				}
			} catch (IOException e) {
				System.out.println("클라이언트 연결이 해제되었습니다 : " + socket);
				number--;
				System.out.println("현재 접속중인 클라이언트 수 : " + number);
			}catch(NullPointerException e){
				System.out.println("클라이언트 연결이 해제되었습니다 : " + socket);
				number--;
				System.out.println("현재 접속중인 클라이언트 수 : " + number);
				
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
		public int compareTo(RankInformation o) {//값 중복 허용한다.
			if(score<=o.getScore()) return -1;
			else return 1;
		}
	}

}
