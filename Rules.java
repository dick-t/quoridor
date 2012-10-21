package quoridor;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public class Rules extends Game {

	/**
	 * @param p current player
	 * @param Xpos x coord of cell player wants to move to
	 * @param Ypos y coord of cell player wants to move to
	 * @param b current board
	 * @return returns true if the move is allowed
	 */
	public static Boolean isLegalMove (Player p, int Xpos, int Ypos, Board b) {
		int curX = p.getXpos();
		int curY = p.getYpos();
		int manD = Math.abs(Xpos - curX) + Math.abs(Ypos - curY); // Manhattan distance
		if (manD > 2) {
			return false;
		}
		if (!isOnBoard(Xpos, Ypos)) {
			return false;
		}
		int oppX = p.opponent.getXpos();
		int oppY = p.opponent.getYpos();
		if ((oppX==Xpos)&&(oppY==Ypos)) { // opponent is in square you want to move to
			return false;
		}
		if (manD ==2) {
			// non-wall affected jumps
			if ((curX+Xpos == 2*oppX) && (curY+Ypos == 2*oppY)) {
				if (b.isWall(oppX, oppY, Xpos, Ypos)||b.isWall(curX, curY, oppX, oppY)) {
					return false;
				} else {
					return true;
				}
			}
			// 	wall affected jumps
			if ((curX==oppX) && (oppY==Ypos)) {
				if (curY+1 == oppY) { // opponent is under player
					if (b.isWall(oppX, oppY, oppX, oppY+1)) {
						return true;
					} else {
						return false;
					}
				} else if (curY-1 == oppY) { // opponent is above player
					if (b.isWall(oppX, oppY, oppX, oppY-1)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if ((curY==oppY) && (oppX==Xpos)) {
				if (curX+1 == oppX) { // opponent is right of player
					if (b.isWall(oppX, oppY, oppX+1, oppY)) {
						return true;
					} else {
						return false;
					}
				} else if (curX-1 == oppX) { // opponent is left of player
					if (b.isWall(oppX, oppY, oppX-1, oppY)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else { // opponent not in right position for jump
				return false;
			}
		}
		// checks if there is wall, also takes care of non-adjacent squares
		if (b.isWall(curX, curY, Xpos, Ypos)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param Xpos x coord of NW corner of the wall
	 * @param Ypos y coord of NW corner of the wall
	 * @param d direction of the wall
	 * @param b current board
	 * @param p player wishing to place wall
	 * @return returns true if the wall is allowed
	 */
	public static Boolean isLegalWall (int Xpos, int Ypos, WallDirections d, Board b, Player p) {
		// check wall is on board
		if (p.nWallsLeft()<=0) {
			return false;
		}
		if ((Xpos>8)||(Ypos>8)) {
			return false;
		}

		if (d==WallDirections.h) {
			//existing wall
			if (b.isWall(Xpos, Ypos, Xpos, Ypos+1)||b.isWall(Xpos+1, Ypos, Xpos+1, Ypos+1)) {
				return false;
			}
			//crossing wall
			if ((b.isWall(Xpos, Ypos, Xpos+1, Ypos))&&(b.isWall(Xpos, Ypos+1, Xpos+1, Ypos+1))) {
				return false;
			}
		}
		if (d==WallDirections.v) {
			//existing wall
			if (b.isWall(Xpos, Ypos, Xpos+1, Ypos)||b.isWall(Xpos, Ypos+1, Xpos+1, Ypos+1)) {
				return false;
			}
			//crossing wall
			if ((b.isWall(Xpos, Ypos, Xpos, Ypos+1))&&(b.isWall(Xpos+1, Ypos, Xpos+1, Ypos+1))) {
				return false;
			}
		}
		if (!isPath(Xpos, Ypos, d, b, p)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param Xpos x coord of a cell
	 * @param Ypos y coord of a cell
	 * @return returns true if the cell is on the board
	 */
	private static boolean isOnBoard (int Xpos, int Ypos) {
		if ((Xpos<0)||(Xpos>8)) {
			return false;
		}
		if ((Ypos<0)||(Ypos>8)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param Xpos x coord of NW corner of the wall
	 * @param Ypos y coord of NW corner of the wall
	 * @param d direction of the wall
	 * @param b current board
	 * @param p player wishing to place wall
	 * @return returns true if the wall does not block player from the goal
	 */
	private static Boolean isPath (int Xpos, int Ypos, WallDirections d, Board b, Player p) {
		boolean valid;
		Player q = p.opponent;
		b.MakeWall(Xpos, Ypos, d);
		valid = b.isPath(p.getXpos(), p.getYpos(), p.getGoalY())&&b.isPath(q.getXpos(), q.getYpos(), q.getGoalY());
		b.RemoveWall(Xpos, Ypos, d);
		return valid;
	}
	
}
