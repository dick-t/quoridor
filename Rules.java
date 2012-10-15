package project;

public class Rules extends Game {

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
				return true;
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
	
	public static Boolean isLegalWall (int Xpos, int Ypos, WallDirections d, Board b, int x1, int y1, int x2, int y2) {
		// check wall is on board
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
		if (!b.isPath(x1, y1, x2, y2, Xpos, Ypos, d)) {
			return false;
		}
		return true;
	}
	
	private static boolean isOnBoard (int Xpos, int Ypos) {
		if ((Xpos<0)||(Xpos>8)) {
			return false;
		}
		if ((Ypos<0)||(Ypos>8)) {
			return false;
		}
		return true;
	}
	
}
