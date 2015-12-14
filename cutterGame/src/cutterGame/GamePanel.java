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
	private int gameWidth, gameHeight;
	private BufferStrategy bufferStrategy;
	private LinkedList<Throwee> throweeList;
	private Thread physicsCalculation;
	private Canvas canvas;
	private int mX, mY;
	private Thrower thrower;
	
	public GamePanel(int gameWidth, int gameHeight){
		mX = 0;
		mY = 0;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		
		canvas = new Canvas();
		
		canvas.setBackground(Color.white);
		canvas.setSize(this.gameWidth,this.gameHeight);
		
		this.add(canvas);
	}
	public void start(){
		throweeList = new LinkedList<>();
		canvas.createBufferStrategy(2);//visible 이전에 하면 안됨
		bufferStrategy = canvas.getBufferStrategy();
		
		physicsCalculation = new Thread(taskSetkRunnable());
		physicsCalculation.start();
		thrower = new Thrower(Thrower.EASY, throweeList);
		
		drawImage();
	}
	
	private void drawImage(){
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		canvas.addMouseMotionListener(taskSetMouseMotionListener());//test code
		thrower.run();
		
		while(true){//draw Throwee
			g.clearRect(0, 0, gameWidth, getHeight());
			g.drawString("FPS: " + Math.random()*100, 0, 15);//be changed score
			
			for (Throwee throwee : throweeList) {
				int x1 = (int)throwee.getX();
				int y1 = (int)throwee.getY();
				int x2 = x1 + 15;
				int y2 = y1 + 15;
				
				if(x1 < mX && x2 > mX && y1 < mY && y2 > mY){
					throweeList.remove(throwee);
					break;
				}
				
				g.fillOval(x1, y1, 15, 15); //test code
				System.out.println("x1 : " + x1 + " y1 : " + y1);
				System.out.println("mX : " + mX + " mY : " + mY);
			}
			
			if(!bufferStrategy.contentsLost()) bufferStrategy.show();
		}
		
	}
	
	private Runnable taskSetkRunnable(){
		
		return ()->{
			while(true){//move Throwee location
				for (Throwee throwee : throweeList) {
					throwee.setVx(throwee.getVx() - throwee.getVx() * 0.0003);
					throwee.setVy(throwee.getVy() - throwee.getVy() * 0.0003);
					
					throwee.setVy(throwee.getVy() + 0.001d);
					throwee.setY(throwee.getY() + throwee.getVy());
					
					throwee.setX(throwee.getX() + throwee.getVx());
					
					if(throwee.getX() < 0){
						throwee.setX(0+15);
						throwee.setVx(throwee.getVx() * -0.8);
					}
					if(throwee.getX() + 15 > gameWidth){
						throwee.setX(gameWidth - 15);
						throwee.setVx(throwee.getVx() * -0.8);
					}
					if(throwee.getY() + 15 > gameHeight){
						throwee.setY(gameHeight -15);
						throwee.setVy(throwee.getVy() * -0.8);
					}
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
			}
		};
		
		return listener;
	}
}
