//Author:	Nick Corrado
//Date:		09/11/16
//Desc:		Pair class for representing the coordinates in the graph of Level 1
//Title:	Pair

public class Pair<K, V> {
	private K x;
	private V y;
	
	public Pair(K x, V y) {
		this.x = x;
		this.y = y;
	}

	public K getX() {
		return x;
	}
	
	public V getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		//if the object is a pair, then return whether x is x and y is y
//		if (obj instanceof Pair) {
//			if (((Pair) obj).getX() == x && ((Pair) obj).getY() == y) {
//				return true;
//			}
//		}
//		return false;
		
		//this actually works, believe it or not.
		if (!(obj instanceof Pair)) {
			return false;
		}
		if (obj instanceof Pair<?, ?>) {
			return (((Pair<?, ?>) obj).getX() == x && ((Pair<?, ?>) obj).getY() == y);
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.getX().toString() + ", " + this.getX().toString() + ")";
	}
}
