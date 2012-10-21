package quoridor;

import java.util.List;

import quoridor.Game.WallDirections;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public class Validator {

	// TODO complete this class using your project code
	// you must implement the no-arg constructor and the check method
	
	// you may add extra fields and methods to this class
	// but the ProvidedTests code only calls the specified methods
	
	public Validator() {
		// no fields required, constructor isn't required
	}

	/**
	 * Check the validity of a given list of moves.
	 * Each move is represented by a string.
	 * The list is valid if and only if each move in the list is valid,
	 * after applying the preceding moves in the list, assuming they are valid,
	 * starting from the initial position of the game.
	 * When the game has been won, no further moves are valid.
	 * @param moves a list of successive moves
	 * @return validity of the list of moves
	 */
	public boolean check(List<String> moves) {
		GameState gs = new GameState();
		boolean retval=true;
		boolean wall; // true when wall, false when move
		int x, y;
		WallDirections dir;
		for (String curMoves:moves) {
			if (gs.isGameOver()) {
				retval = false;
			}
			if (retval) {
			// convert string into coordinates and move/wall
				if (curMoves.length()==2) {
					wall=false;
				} else {
					wall=true;
				}
				x= ((int) curMoves.charAt(0))-((int) 'a');
				y= ((int) curMoves.charAt(1))-((int) '1');
				if (wall) {
					if (curMoves.charAt(2)=='h') {
						dir = WallDirections.h;
					} else {
						dir = WallDirections.v;
					}
					if (!gs.placeWall(x, y, dir)) {
						retval = false;
					}
				} else {
					if (!gs.move(x, y)) {
						retval = false;
					}
				}
			}
		}
		return retval;
	}

}
