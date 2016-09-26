
public class NullCell extends GenCell {
	private static NullCell nullCell;
	
	private NullCell() {
		//does nothing
	}
	
	public static NullCell getNullCell() {
		if (nullCell == null) {
			nullCell = new NullCell();
		}
		return nullCell;
	}
	
	//everything that a cell extending gencell would do, does nothing following this
}
