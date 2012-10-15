package project;

import project.Game.WallDirections;

public class GameState {
	
	private Board board;
	private Player curPlayer, player1, player2;
	
	public GameState () {
		board = new GraphBoard();
		player1 = createPlayer(Game.N_ROWS-1); 	// player at bottom
		player2 = createPlayer(0); 				// player at top
		curPlayer = player1;
		player1.setOpponent(player2);
		player2.setOpponent(player1);
	}
	
	public boolean isAITurn () {
		return curPlayer.isAI();
	}
	
	public boolean isGameOver() {
		if (player1.hasWon()||player2.hasWon()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean move(int x, int y) {
		if (Rules.isLegalMove(curPlayer, x, y, board)) {
			curPlayer.setXpos(x);
			curPlayer.setYpos(y);
			curPlayer = curPlayer.opponent;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean placeWall (int x, int y, WallDirections d) {
		if (curPlayer.nWallsLeft()>0 && Rules.isLegalWall(x, y, d, board, player1.getXpos(), player1.getYpos(), player2.getXpos(), player2.getYpos())) {
			board.MakeWall(x, y, d);
			curPlayer.makeWall();
			curPlayer = curPlayer.opponent;
			return true;
		} else {
			return false;
		}
	}
	
	public void print () {
		board.printBoard(curPlayer.getXpos(), curPlayer.getYpos(), curPlayer.opponent.getXpos(), curPlayer.opponent.getYpos());
	}
	
	private Player createPlayer (int n) {
		Player p;
		System.out.println("Is player human? [y|n]");
		//if (human) 
			p = new Player (n, false);
		// else
			// p = new Player (n, true);
		return p;
	}

}
