package throwCollection;

public abstract class CuttableObject extends Throwee implements Cloneable{
	public CuttableObject(int x, int y, int diameter, int score) {
		super(x,y, diameter, score);
	}
	
	public abstract CuttableObject getClone();
}
