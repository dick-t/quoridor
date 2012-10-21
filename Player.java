package quoridor;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public class Player {
	
	private int x;
	private int y;
	private int goal;
	private int wallsLeft;
	public Player opponent;
	private boolean isAI;
	protected Player (int n) {
		goal = Game.N_ROWS -n-1;
		x = 4;
		y = n;
		wallsLeft = 10;
		isAI = false;
	}
	
	/**
	 *  sets this player to be an AI
	 */
	public void makeAI() {
		this.isAI = true;
	}
	
	/**
	 * @return returns true if this player if AI
	 */
	public boolean isAI() {
		return isAI;
	}
	
	/**
	 * @param p this player's opponent
	 */
	public void setOpponent(Player p) {
		this.opponent = p;
	}
	
	/**
	 * @return y coord of this player's goal row
	 */
	public int getGoalY () { 
		return this.goal;
	}
	
	/**
	 * @return current x coords of player
	 */
	public int getXpos() {
		return this.x;
	}
	
	/**
	 * @return current y coords of player
	 */
	public int getYpos() {
		return this.y;
	}
	
	/**
	 * @param x x coord to set player to
	 */
	public void setXpos(int x) {
		this.x = x;
	}
	
	/**
	 * @param y y coord to set player to
	 */
	public void setYpos(int y) {
		this.y = y;
	}
	
	/**
	 * @return number of walls left for player
	 */
	public int nWallsLeft() {
		return this.wallsLeft;
	}
	
	/**
	 * player makes wall, so decrease number of walls left
	 */
	public void makeWall() {
		this.wallsLeft--;
	}
	
	/**
	 * @return returns true if the player has won
	 */
	public boolean hasWon() {
		if (y == goal) {
			return true;
		} else {
			return false;
		}
	}

}
