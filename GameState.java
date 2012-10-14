package project;

import project.Game.WallDirections;

public class GameState {
	
	private Board board;
	private Player curPlayer, player1, player2;
	
	public GameState () {
		board = new GraphBoard();
		player1 = new Player(Game.N_ROWS-1); // player at bottom
		player2 = new Player(0); // player at top
		curPlayer = player1;
		player1.setOpponent(player2);
		player2.setOpponent(player1);
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
	
	public void placeWall (int x, int y, WallDirections d) {
		board.MakeWall(x, y, d);
		curPlayer.makeWall();
		curPlayer = curPlayer.opponent;
	}
	
	public void print () {
		board.printBoard(curPlayer.getXpos(), curPlayer.getYpos(), curPlayer.opponent.getXpos(), curPlayer.opponent.getYpos());
	}

}
