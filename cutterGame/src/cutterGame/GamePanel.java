package cutterGame;

import java.awt.Canvas;
import java.awt.Color;
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
		throweeList = new LinkedList<>();
		canvas = new Canvas();
		
		canvas.setBackground(Color.white);
		canvas.setSize(gameWidth,gameHeight);
		
		this.add(canvas);
	}
	public void start(){
		canvas.createBufferStrategy(2);//visible 이전에 하면 안됨
		bufferStrategy = canvas.getBufferStrategy();
	}
}
