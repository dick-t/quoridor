package project;

public class Rules extends Game {

	public boolean isLegalMove (int Xpos, int Ypos, Directions d) {
		if (!(isOnBoard(Xpos, Ypos, d))) {
			return false;
		}
		return true;
		// not finished
	}
	
	public boolean isLegalWall (int Xpos, int Ypos, WallDirections d) {
		if (!(p.nWallsLeft()>0)) {
			return false;
		}
		/*if (player is blocked) {
			return false;
		} */
		/*if (walls cross) {
			return false;
		} */
		return true;
	}
	
	private boolean isOnBoard (int Xpos, int Ypos, Directions d) {
		int pos=-1;
		if (d == Directions.UP) {
			pos = Ypos+1;
		} else if (d==Directions.DOWN) {
			pos = Ypos-1;
		} else if (d==Directions.RIGHT) {
			pos = Xpos+1;
		} else if (d==Directions.LEFT) {
			pos = Xpos-1;
		}
		if ((pos>=0)&&(pos<9)) {
			return true;
		} else {
			return false;
		}
	}
	
}
