package throwCollection;

public class BigCuttable extends CuttableObject{
	private static final int diameter = 30;
	
	public BigCuttable(int x, int y) {
		
		super(x, y, diameter);
	}

	@Override
	public CuttableObject getClone() {
		BigCuttable cloned = null;
		try {
			cloned = (BigCuttable) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloned;
	}

}
