package project;

public class Player {
	
	private int x;
	private int y;
	private int goal;
	protected Player (int n) {
		goal = Game.N_ROWS -n;
		x = 4;
		y = n;
	}
	
	int getXpos() {
		return this.x;
	}
	int getYpos() {
		return this.y;
	}
	
	boolean hasWon() {
		if (y == goal) {
			return true;
		} else {
			return false;
		}
	}

}
