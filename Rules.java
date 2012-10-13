package project;

public class Rules extends Game {

	public static Boolean isLegalMove (Player p, int Xpos, int Ypos, Board b) {
		int curX = p.getXpos();
		int curY = p.getYpos();
		if (!isOnBoard(Xpos, Ypos)) {
			return false;
		}
		boolean adjacent=false;
		if (Xpos == curX) {
			if ((Ypos==curY-1)||(Ypos==curY+1)) {
				adjacent = true;
			}
		} else if (Ypos == curY) {
			if ((Xpos==curX-1)||(Xpos==curX+1)) {
				adjacent = true;
			}
		}
		if (!adjacent) {
			return false;
			// need to change for when other player is there
		}
		if (b.isWall(curX, curY, Xpos, Ypos)) {
			return false;
		}
		return true;
	}
	
	public Boolean isLegalWall (int Xpos, int Ypos, WallDirections d, Board b) {
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
		/*if (player is blocked) {
			return false;
		} */
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
