import java.awt.Color;

public class NullCell extends GenCell {
	private static NullCell nullCell;
	
	private NullCell(double x, double y, int size, Color c) {
		//does nothing
		super(x,y,size,c);
	}
	
	public static NullCell getNullCell() {
		if (nullCell == null) {
			nullCell = new NullCell(0,0,0,null);
		}
		return nullCell;
	}
	
	//any method that would be called by a gencell will be here and do nothing
}
