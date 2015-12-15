package throwCollection;

import java.awt.Color;

public class SmallCuttable extends CuttableObject{
	private final static int diameter = 15;
	private final static int score = 50;
	private final static Color color = Color.gray;
	
	public SmallCuttable(int x, int y) {
		super(x, y, diameter, score,color);
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
