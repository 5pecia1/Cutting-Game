package throwCollection;

import java.util.ArrayList;
import java.util.LinkedList;

import cutterGame.CutterGame;

public class Thrower {
	public final static int EASY = 25;//던지는것의 갯수와 폭탄의 갯수는 숫자에 비례한다. 
	public final static int NORMAL = 40; 
	public final static int HARD = 80; 
	
	private final int numberOfThrowee;
	private final int randomSize = 100000;
	private ArrayList<CuttableObject> cuttableList;//자를 수 있는 폭탄들
	private LinkedList<Throwee> throweeList;
	private int seed;
	
	public Thrower(int seed, LinkedList<Throwee> throweeList) {
		this.seed = seed;
		this.throweeList = throweeList;
		cuttableList = new ArrayList<>();
		cuttableList.add(new BigCuttable(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT));
		cuttableList.add(new SmallCuttable(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT));
		
		numberOfThrowee = cuttableList.size() * 2;//폭탄은 자를 수 있는것의 *2만큼 던진다.
	}

	public void run() {
		int decideThrow = (int)(Math.random() * randomSize +1);//던지는 것이 나올 확률
		
		if(decideThrow < seed){
			int decideThrowee = (int)(Math.random()*numberOfThrowee);
			
			if(decideThrowee < cuttableList.size()){//자를 수 있는 폭탄
				throweeList.add(cuttableList.get(decideThrowee));
				cuttableList.set(decideThrowee,cuttableList.get(decideThrowee).getClone());//cuttable에서 현재 나간 객체를 빼고 새로 넣는다.
				cuttableList.get(decideThrowee).changeLocation();//새로 넣은 객체의 위치를 바꾼다.
			}
			else{// 자를 수 없는 폭탄
				throweeList.add(new Boom(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT));
			}
		}
	}
}
