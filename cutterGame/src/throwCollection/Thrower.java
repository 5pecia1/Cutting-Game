package throwCollection;

import java.util.ArrayList;
import java.util.LinkedList;

import cutterGame.CutterGame;

public class Thrower {
	public final static int EASY = 25;//�����°��� ������ ��ź�� ������ ���ڿ� ����Ѵ�. 
	public final static int NORMAL = 40; 
	public final static int HARD = 80; 
	
	private final int numberOfThrowee;
	private final int randomSize = 100000;
	private ArrayList<CuttableObject> cuttableList;//�ڸ� �� �ִ� ��ź��
	private LinkedList<Throwee> throweeList;
	private int seed;
	
	public Thrower(int seed, LinkedList<Throwee> throweeList) {
		this.seed = seed;
		this.throweeList = throweeList;
		cuttableList = new ArrayList<>();
		cuttableList.add(new BigCuttable(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT));
		cuttableList.add(new SmallCuttable(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT));
		
		numberOfThrowee = cuttableList.size() * 2;//��ź�� �ڸ� �� �ִ°��� *2��ŭ ������.
	}

	public void run() {
		int decideThrow = (int)(Math.random() * randomSize +1);//������ ���� ���� Ȯ��
		
		if(decideThrow < seed){
			int decideThrowee = (int)(Math.random()*numberOfThrowee);
			
			if(decideThrowee < cuttableList.size()){//�ڸ� �� �ִ� ��ź
				throweeList.add(cuttableList.get(decideThrowee));
				cuttableList.set(decideThrowee,cuttableList.get(decideThrowee).getClone());//cuttable���� ���� ���� ��ü�� ���� ���� �ִ´�.
				cuttableList.get(decideThrowee).changeLocation();//���� ���� ��ü�� ��ġ�� �ٲ۴�.
			}
			else{// �ڸ� �� ���� ��ź
				throweeList.add(new Boom(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT));
			}
		}
	}
}
