package project;

public class Player {
	
	private int x;
	private int y;
	private int goal;
	private int wallsLeft;
	protected Player (int n) {
		goal = Game.N_ROWS -n;
		x = 4;
		y = n;
		wallsLeft = 10;
	}
	
	public int getXpos() {
		return this.x;
	}
	public int getYpos() {
		return this.y;
	}
	public int nWallsLeft() {
		return wallsLeft;
	}
	public boolean hasWon() {
		if (y == goal) {
			return true;
		} else {
			return false;
		}
	}

}
