import java.awt.Color;

public class Cell {
	private int x, y;
	private double size;
	private Color c;
	
	public Cell(int x, int y, double size, Color c) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.c = c;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getSize() {
		return size;
	}
	
}
