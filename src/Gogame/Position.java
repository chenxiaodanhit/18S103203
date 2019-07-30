package Gogame;

/**
 * @author 25350
 *Calculate the Place of Chess
 */
public class Position {
	int x;
	int y;
	String name;
	public Position(String name,int x,int y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public String getn() {
		return name;
	}
}
