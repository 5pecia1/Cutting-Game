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

public class GamePanel extends JPanel{
	private int gameWidth, gameHeight;
	private BufferStrategy bufferStrategy;
	private LinkedList<Throwee> throweeList;
	private Thread physicsCalculation;
	private Canvas canvas;
	private int mX, mY;
	
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
		
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		canvas.addMouseMotionListener(taskSetMouseMotionListener());//test code
		Throwee throw1 = new Throwee(10,10){}; //test code
		throweeList.add(throw1);
		
		while(true){
			g.clearRect(0, 0, gameWidth, getHeight());
			g.drawString("FPS: " + Math.random()*100, 0, 15);//change score
			
			for (Throwee throwee : throweeList) {
				int x1 = (int)throwee.x;
				int y1 = (int)throwee.y;
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
			/*
			 * test code
			 * */
			while(true){
				for (Throwee throwee : throweeList) {
					throwee.Vx -= throwee.Vx * 0.0003;
					throwee.Vy -= throwee.Vy * 0.0003;
					
					throwee.Vy += 0.001d;
					throwee.y += throwee.Vy;
					
					throwee.x += throwee.Vx;
					
					if(throwee.x < 0){
						throwee.x = 0+15;
						throwee.Vx *= -0.8;
					}
					if(throwee.x + 15 > gameWidth){
						throwee.x = gameWidth - 15;
						throwee.Vx *= -0.8;
					}
					if(throwee.y + 15 > gameHeight){
						throwee.y = gameHeight -15;
						throwee.Vy *= -0.8;
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
