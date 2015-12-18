package cutterGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JPanel;

import throwCollection.Throwee;
import throwCollection.Thrower;

public class GamePanel extends JPanel{
	private CutterGame cutterGame;
	private BufferStrategy bufferStrategy;
	private LinkedList<Throwee> throweeList;
	private Thread physicsCalculation;
	private Canvas canvas;
	private int mX, mY;
	private int score;
	private Thrower thrower;
	
	private boolean progressGame;
	
	public GamePanel(CutterGame cutterGame){
		this.cutterGame = cutterGame;
		
		canvas = new Canvas();
		
		canvas.setBackground(Color.white);
		canvas.setSize(CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT);
		
		this.add(canvas);
	}
	public void start(){
		score = 0;
		mX = 0;
		mY = 0;
		progressGame = true;
		throweeList = new LinkedList<>();
		canvas.createBufferStrategy(2);//visible 이전에 하면 안됨
		bufferStrategy = canvas.getBufferStrategy();
		
		physicsCalculation = new Thread(taskSetkRunnable());
		physicsCalculation.start();
		thrower = new Thrower(Thrower.HARD, throweeList);
		
		drawImage();
	}
	
	protected void endStep() {
		cutterGame.setEndCardLayout(score);// end로 넘어감
		
	}
	
	private void drawImage(){
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		canvas.addMouseMotionListener(taskSetMouseMotionListener());//test code
		
		while(progressGame){//draw Throwee
			g.clearRect(0, 0,CutterGame.GAMEWIDTH, CutterGame.GAMEHEIGHT);
			g.setColor(Color.black);
			g.drawString("SCORE : " + score, 0, 15);
			
			thrower.run();
			foreachList(g);
			
			if(!bufferStrategy.contentsLost()) bufferStrategy.show();
		}
		if(!bufferStrategy.contentsLost()) bufferStrategy.show();
		
		endStep();
	}
	
	private synchronized void foreachList(){//점수 계산
		for (Throwee throwee : throweeList) {
			int x1 = (int)throwee.getX();
			int y1 = (int)throwee.getY();
			int x2 = x1 + throwee.getDiameter();
			int y2 = y1 + throwee.getDiameter();
			
			if(x1 < mX && x2 > mX && y1 < mY && y2 > mY){
				if(throwee.getScore() == -1){// game end~
					progressGame = false;
					throweeList.remove(throwee);
					break;
				}
				score += throwee.getScore();
				throweeList.remove(throwee);
				break;
			}
		}
	}
	private synchronized void foreachList(Graphics2D g){//그림 계산
		for (Throwee throwee : throweeList) {
			int x1 = (int)throwee.getX();
			int y1 = (int)throwee.getY();
			int x2 = x1 + throwee.getDiameter();
			int y2 = y1 + throwee.getDiameter();
			
			if(throwee.getY() > CutterGame.GAMEHEIGHT  + throwee.getDiameter()){//객체가 본 크기보다 더 밑으로 내려가면 지운다
				throweeList.remove(throwee);
				break;
			}
			g.setColor(throwee.getColor());
			g.fillOval(x1, y1, throwee.getDiameter(), throwee.getDiameter());
		}
		System.out.println(throweeList.size());
	}
	
	private Runnable taskSetkRunnable(){
		
		return ()->{
			while(progressGame){//move Throwee location
				for (Throwee throwee : throweeList) {
					throwee.setVx(throwee.getVx() - throwee.getVx() * 0.0003);
					throwee.setVy(throwee.getVy() - throwee.getVy() * 0.0003);
					
					throwee.setVy(throwee.getVy() + 0.001d);
					throwee.setY(throwee.getY() + throwee.getVy());
					
					throwee.setX(throwee.getX() + throwee.getVx());
					
				}
				
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	private MouseMotionListener taskSetMouseMotionListener(){
		MouseMotionListener listener = new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				mX = e.getX();
				mY = e.getY();
				foreachList();
				mY = 0;
				mX = 0;
			}
		};
		
		return listener;
	}
}
