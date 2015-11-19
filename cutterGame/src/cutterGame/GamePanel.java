package cutterGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	private int gameWidth, gameHeight;
	private BufferStrategy bufferStrategy;
	private LinkedList<Throwee> throweeList;
	private Thread physicsCalculation;
	private Canvas canvas;
	
	public GamePanel(int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		
		canvas = new Canvas();
		
		canvas.setBackground(Color.white);
		canvas.setSize(gameWidth,gameHeight);
		
		this.add(canvas);
	}
	public void start(){
		throweeList = new LinkedList<>();
		canvas.createBufferStrategy(2);//visible 이전에 하면 안됨
		bufferStrategy = canvas.getBufferStrategy();
		
		physicsCalculation = new Thread(randomTaskRunnable());
		physicsCalculation.start();
		
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		while(true){
			g.clearRect(0, 0, gameWidth, getHeight());
			g.drawString("FPS: " + Math.random()*100, 0, 15);//change score
			
			for (Throwee throwee : throweeList) {
				
			}
			
			if(!bufferStrategy.contentsLost()) bufferStrategy.show();
		}
		
	}
	private Runnable randomTaskRunnable(){
		
		return ()->{
			
		};
	}
}
