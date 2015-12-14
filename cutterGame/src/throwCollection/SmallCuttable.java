package throwCollection;

public class SmallCuttable extends CuttableObject{
	private final static int diameter = 15;
	
	public SmallCuttable(int x, int y) {
		super(x, y, diameter);
	}

	@Override
	public CuttableObject getClone() {
		SmallCuttable cloned = null;
		try {
			cloned = (SmallCuttable) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloned;
	}

}
