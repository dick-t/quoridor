package project;

//import project.Game.WallDirections;

public class AI {
	
	public static void AIMove (Board b, Player p, GameState gs) {
		int oppX = p.opponent.getXpos();
		int oppY = p.opponent.getYpos();
		/*boolean wallPlaced=false;
		if (p.getGoalY()==8) {
			wallPlaced = gs.placeWall(oppX, oppY-1, WallDirections.h);
			if (!wallPlaced) {
				System.out.println ("IRIS IS VERY HOMOSEXUAL LOL");
				wallPlaced = gs.placeWall(oppX-1, oppY-1, WallDirections.h);
			}
		} else {
			wallPlaced = gs.placeWall(oppX, oppY, WallDirections.h);
			if (!wallPlaced) {
				wallPlaced = gs.placeWall(oppX-1, oppY, WallDirections.h);
			}
		}
		if (wallPlaced) {
			System.out.println ("HI RICHARD I'M GAY 4 U LOL~");
		}
		if (!wallPlaced) {*/
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
							System.out.println("AI is broken 1");
							System.exit(1);
						}
					} else if (Rules.isLegalMove(p, oppX+1, oppY, b)) {
						gs.move(oppX+1, oppY);
					} else if (Rules.isLegalMove(p, oppX-1, oppY, b)) {
						gs.move(oppX+1, oppY);
					} else {
						System.out.println("AI is broken 2");
						System.exit(1);
					}
				} else if (oppY == p.getYpos()) {
					if (!b.isWall(oppX, oppY, 2*(oppX-p.getXpos())+p.getXpos(), oppY)) {
						move[1] = 2*(oppX-p.getXpos())+p.getXpos();
						if (Rules.isLegalMove(p, move[0], move[1], b)) {
							gs.move(move[0], move[1]);
						} else {
							System.out.println("AI is broken 3");
							System.exit(1);
						}
					} else if (Rules.isLegalMove(p, oppX+1, oppY, b)) {
						gs.move(oppX, oppY+1);
					} else if (Rules.isLegalMove(p, oppX-1, oppY, b)) {
						gs.move(oppX, oppY-1);
					} else {
						System.out.println("AI is broken 4");
						System.exit(1);
					}
				} else {
					System.out.println("AI is broken 5");
					System.exit(1);
				}
			//}
		}
	}

}
