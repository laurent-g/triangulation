package eu.georget.triangulation.core;

/**
 * Location provide information about a location : x and y position
 * 
 * @author laurent
 * 
 */
public class Location {

	/**
	 * x position
	 */
	private int x;

	/**
	 * y position
	 */
	private int y;

	/**
	 * 
	 * @param x x position
	 * @param y y position
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
