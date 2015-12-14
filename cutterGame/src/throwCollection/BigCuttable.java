package throwCollection;

public class BigCuttable extends CuttableObject{
	private static final int diameter = 30;
	private static final int score = 10;
	
	public BigCuttable(int x, int y) {
		
		super(x, y, diameter, score);
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
