package throwCollection;

import java.awt.Color;

public abstract class CuttableObject extends Throwee implements Cloneable{
	public CuttableObject(int x, int y, int diameter, int score, Color color) {
		super(x,y, diameter, score, color);
	}
	
	public abstract CuttableObject getClone();
}
