import java.awt.Color;
import java.awt.Point;

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
	
	public boolean containz(Point p) {
		return (p.getX()>this.x && p.getX()<=this.x+this.size && p.getY()>this.y && p.getY()<=this.y+this.size);
	}
	
	public void flipColor() {
		if (c == Color.BLACK) {
			c = Color.WHITE;
		}
		else {
			c = Color.BLACK;
		}
	}
}

