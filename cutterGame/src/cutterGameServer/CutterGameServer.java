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
	private TreeMap<Integer, RankInformation> rankTree;//같은 key 값을 동시에 저장하지 못함 DB 사용시 해결해야함.
	private int number = 0;
	
	public CutterGameServer() {
		rankTree = new TreeMap<>();
	}

	public void start() {
		System.out.println("서버가 시작되었습니다");

		try {
			ServerSocket serverSocket = new ServerSocket(7777);
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

	private synchronized Set<Map.Entry<Integer, RankInformation>> rankTreeInputNavigableSetOutput(Integer score, String name){
		//오름차순으로정렬된 tree 리턴
		//TreeMap에 동시에 여러 입출력이 일어나지 않게 한다.
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
					String line = si.readLine();//name에 |이 들어갈 수 있으므로 split하지 않는다.
					int splitLocation = line.indexOf('|');
 
					Integer score = Integer.parseInt(line.substring(0, splitLocation));
					String name = line.substring(splitLocation+1);

					System.out.println("[" + name +"]의 점수 " + score);
					
					
					Set<Map.Entry<Integer, RankInformation>> descendingEntrySet = rankTreeInputNavigableSetOutput(score, name);
					int rank = 0;
					for (Map.Entry<Integer, RankInformation> entry : descendingEntrySet) {
						//정렬된 값을 받아 소켓 프린트
						rank++;
						so.println(rank + "|" + entry.getKey() + "|" + entry.getValue());//랭크, 점수, 날짜, 이름
						so.flush();
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
