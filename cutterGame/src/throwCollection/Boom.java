package throwCollection;

import java.awt.Color;

public class Boom extends Throwee{
	private final static long sleepTime = 300;
	private final static int diameter = 30;
	private final static int score = -1;
	private final static Color color1 = Color.red;
	private final static Color color2 = Color.black;

	public Boom(int x, int y) {
		super(x, y, diameter, score, color1);
		Thread thread = new Thread(() ->{
			while(true){
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(getColor() == color1){
					setColor(color2);
				}
				else
					setColor(color1);
			}

		});
		thread.setDaemon(true);
		thread.start();
	}
	
}
