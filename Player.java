package project;

public class Player {
	
	private int x;
	private int y;
	private int goal;
	private int wallsLeft;
	public Player opponent;
	protected Player (int n) {
		goal = Game.N_ROWS -n-1;
		x = 4;
		y = n;
		wallsLeft = 10;
	}
	
	public void setOpponent(Player p) {
		this.opponent = p;
	}
	public int getXpos() {
		return this.x;
	}
	public int getYpos() {
		return this.y;
	}
	public void setXpos(int x) {
		this.x = x;
	}
	public void setYpos(int y) {
		this.y = y;
	}
	public int nWallsLeft() {
		return this.wallsLeft;
	}
	public void makeWall() {
		this.wallsLeft--;
	}
	public boolean hasWon() {
		if (y == goal) {
			return true;
		} else {
			return false;
		}
	}

}
