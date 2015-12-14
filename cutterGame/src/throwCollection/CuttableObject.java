package throwCollection;

public abstract class CuttableObject extends Throwee implements Cloneable{
	public CuttableObject(int x, int y, int diameter) {
		super(x,y, diameter);
	}
	
	public abstract CuttableObject getClone();
}
