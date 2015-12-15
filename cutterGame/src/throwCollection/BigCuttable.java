package throwCollection;

import java.awt.Color;

public class BigCuttable extends CuttableObject{
	private static final int diameter = 40;
	private static final int score = 10;
	private final static Color color = Color.yellow;
	
	public BigCuttable(int x, int y) {
		
		super(x, y, diameter, score,color);
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
