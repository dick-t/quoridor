package quoridor;

//import project.Game.WallDirections;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public class AI {
	
	/**
	 * @param b The current board
	 * @param p The player whose move it is
	 * @param gs The current gameState
	 */
	public static void AIMove (Board b, Player p, GameState gs) {
		int oppX = p.opponent.getXpos();
		int oppY = p.opponent.getYpos();
		int move[] = new int[2];
		move = b.findOptMove(p.getXpos(), p.getYpos(), p.getGoalY());
		if (Rules.isLegalMove(p, move[0], move[1], b)) {
			gs.move(move[0], move[1]);
		} else { 						// move is illegal because opponent is in that cell
			if (oppX == p.getXpos()) {
				if (!b.isWall(oppX, oppY, oppX, 2*(oppY-p.getYpos())+p.getYpos())) {
					move[1] = 2*(oppY-p.getYpos())+p.getYpos();
					if (Rules.isLegalMove(p, move[0], move[1], b)) {
						gs.move(move[0], move[1]);
					} else {
						System.out.println("Error");
						System.exit(1);
					}
				} else if (Rules.isLegalMove(p, oppX+1, oppY, b)) {
					gs.move(oppX+1, oppY);
				} else if (Rules.isLegalMove(p, oppX-1, oppY, b)) {
					gs.move(oppX+1, oppY);
				} else {
					System.out.println("Error");
					System.exit(1);
				}
			} else if (oppY == p.getYpos()) {
				if (!b.isWall(oppX, oppY, 2*(oppX-p.getXpos())+p.getXpos(), oppY)) {
					move[1] = 2*(oppX-p.getXpos())+p.getXpos();
					if (Rules.isLegalMove(p, move[0], move[1], b)) {
						gs.move(move[0], move[1]);
					} else {
						System.out.println("Error");
						System.exit(1);
					}
				} else if (Rules.isLegalMove(p, oppX+1, oppY, b)) {
					gs.move(oppX, oppY+1);
				} else if (Rules.isLegalMove(p, oppX-1, oppY, b)) {
					gs.move(oppX, oppY-1);
				} else {
					System.out.println("Error");
					System.exit(1);
				}
			} else {
				System.out.println("Error");
				System.exit(1);
			}
		}
	}

}
