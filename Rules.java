package project;

public class Rules extends Game {

	public Boolean isLegalMove (int Xpos, int Ypos, Directions d, Board b) {
		int newX = Xpos;
		int newY = Ypos;
		if (d==Directions.UP) {
			newY--;
		} else if (d==Directions.DOWN) {
			newY++;
		} else if (d==Directions.LEFT) {
			newX--;
		} else if (d==Directions.RIGHT) {
			newX++;
		}
		if (!isOnBoard(newX, newY)) {
			return false;
		}
		if (b.isWall(Xpos, Ypos, newX, newY)) {
			return false;
		}
		// if other player in the way
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
	
	private Boolean isOnBoard (int Xpos, int Ypos) {
		if ((Xpos<0)||(Xpos>8)) {
			return false;
		}
		if ((Ypos<0)||(Ypos>8)) {
			return false;
		}
		return true;
	}
	
}
