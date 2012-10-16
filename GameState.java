package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		if (Rules.isLegalWall(x, y, d, board, curPlayer)) {
			board.MakeWall(x, y, d);
			curPlayer.makeWall();
			curPlayer = curPlayer.opponent;
			return true;
		} else {
			return false;
		}
	}
	
	public void print () {
		board.printBoard(player1.getXpos(), player1.getYpos(), player2.getXpos(), player2.getYpos());
		System.out.print("Player 1: (");
		System.out.print(player1.getXpos()+", "+player1.getYpos());
		System.out.println(")  Walls Left: "+ player1.nWallsLeft());
		System.out.print("Player 2: (");
		System.out.print(player2.getXpos()+", "+player2.getYpos());
		System.out.println(")  Walls Left: "+ player2.nWallsLeft());
	}
	
	private Player createPlayer (int n) {
		Player p = new Player(n, false);
		String input = "";
		boolean pMade=false;
		while (!pMade) {
			System.out.println("Is player human? [y|n]");
			BufferedReader is = new BufferedReader (new InputStreamReader(System.in));
			try {
				input = is.readLine();
			} catch (IOException e) {
				System.out.println("Could not read input");
				System.exit(1);
			}
			if (input.equals("y")) { 
				pMade = true;
			} else if (input.equals("n")) {
				p.makeAI();
				pMade = true;
			}
		}
		return p;
	}
	
	public void switchPlayers () {
		curPlayer = curPlayer.opponent;
	}

}
