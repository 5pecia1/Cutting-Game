package throwCollection;

import java.awt.Color;

public class Boom extends Throwee{
	private final static int diameter = 30;
	private final static int score = -1;
	private final static Color color = Color.red;
	
	public Boom(int x, int y) {
		super(x, y, diameter, score, color);
	}
}
