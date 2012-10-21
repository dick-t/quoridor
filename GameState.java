package quoridor;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import quoridor.Game.WallDirections;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public class GameState {
	
	private Board board;
	private Player curPlayer, player1, player2;
	private Stack<int[]> undoStack;
	private Stack<int[]> redoStack;
	
	public GameState () {
		undoStack = new Stack<int[]>();
		redoStack = new Stack<int[]>();
		board = new GraphBoard();
		player1 = new Player(Game.N_ROWS-1); 	// player at bottom
		player2 = new Player(0); 				// player at top
		curPlayer = player1;
		player1.setOpponent(player2);
		player2.setOpponent(player1);
	}
	
	/**
	 * @return returns true if it is currently an AI's turn
	 */
	public boolean isAITurn () {
		return curPlayer.isAI();
	}
	
	/**
	 * asks the user if the players should be AIs
	 */
	public void makeAI() {
		boolean pMade=false;
		String input="";
		while (!pMade) {
			System.out.println("Is player 1 human? [y|n]");
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
				player1.makeAI();
				pMade = true;
			}
		}
		pMade = false;
		while (!pMade) {
			System.out.println("Is player 2 human? [y|n]");
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
				player2.makeAI();
				pMade = true;
			}
		}
	}
	
	
	/**
	 *  AI makes a move
	 */
	public void AITurn() {
		AI.AIMove(board, curPlayer, this);
	}
	
	/**
	 * @return returns true if a player has won
	 */
	public boolean isGameOver() {
		if (player1.hasWon()||player2.hasWon()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 *  undoes the last move
	 */
	public void undo() {
		int[] undo;
		if (undoStack.isEmpty()) {
			return;
		}
		if (curPlayer.opponent.isAI()&&curPlayer.isAI()) {
			System.out.println("Undo two AI players prohibited");
		} else {
			undo = undoStack.pop();
			if (undo[0] == 0) { //if undo info is for a move
				int[] redo = new int[3]; //store redo info
				redo[0] = 0;
				redo[1] = curPlayer.opponent.getXpos();
				redo[2] = curPlayer.opponent.getYpos();
				redoStack.push(redo);
				curPlayer.opponent.setXpos(undo[1]);
				curPlayer.opponent.setYpos(undo[2]);
			} else if (undo[0] == 1) { //horizontal wall
				int[] redo = new int[3]; //store redo info
				redo[0] = 1;
				redo[1] = undo[1];
				redo[2] = undo[2];
				redoStack.push(redo);
				board.RemoveWall(undo[1], undo[2], WallDirections.h);
			} else {
				int[] redo = new int[3]; //store redo info
				redo[0] = 2;
				redo[1] = undo[1];
				redo[2] = undo[2];
				redoStack.push(redo);
				board.RemoveWall(undo[1], undo[2], WallDirections.v);
			}
		}
		if (!curPlayer.opponent.isAI()) {
			curPlayer = curPlayer.opponent;
		} else {
			undo = undoStack.pop();
			if (undo[0] == 0) { //if undo info is for a move
				int[] redo = new int[3]; //store redo info
				redo[0] = 0;
				redo[1] = curPlayer.getXpos();
				redo[2] = curPlayer.getYpos();
				redoStack.push(redo);
				curPlayer.setXpos(undo[1]);
				curPlayer.setYpos(undo[2]);
			} else if (undo[0] == 1) { //horizontal wall
				int[] redo = new int[3]; //store redo info
				redo[0] = 1;
				redo[1] = undo[1];
				redo[2] = undo[2];
				redoStack.push(redo);
				board.RemoveWall(undo[1], undo[2], WallDirections.h);
			} else {
				int[] redo = new int[3]; //store redo info
				redo[0] = 2;
				redo[1] = undo[1];
				redo[2] = undo[2];
				redoStack.push(redo);
				board.RemoveWall(undo[1], undo[2], WallDirections.v);
			}
		}
	}	
	
	/**
	 *  redoes the next move
	 */
	public void redo() {
		if (curPlayer.opponent.isAI()&&curPlayer.isAI()) {
			System.out.println("Redo two AI players disallowed");
			return;
		}
		if (redoStack.isEmpty()) {
			return;
		}
		int[] redo;
		redo = redoStack.pop();
		if (redo[0] == 0) { //if undo info is for a move
			int[] undo = new int[3]; //store undo info
			undo[0] = 0;
			undo[1] = curPlayer.getXpos();
			undo[2] = curPlayer.getYpos();
			undoStack.push(undo);
			curPlayer.setXpos(redo[1]);
			curPlayer.setYpos(redo[2]);
		} else if (redo[0] == 1) { //horizontal wall
			int[] undo = new int[3]; //store undo info
			undo[0] = 1;
			undo[1] = redo[1];
			undo[2] = redo[2];
			undoStack.push(undo);
			board.MakeWall(redo[1], redo[2], WallDirections.h); //put wall back
		} else {
			int[] undo = new int[3]; //store redo info
			undo[0] = 2;
			undo[1] = redo[1];
			undo[2] = redo[2];
			undoStack.push(undo);
			board.MakeWall(redo[1], redo[2], WallDirections.v);
		}
		if (!curPlayer.opponent.isAI()) {
			curPlayer = curPlayer.opponent;
		} else {
			redo = redoStack.pop();
			if (redo[0] == 0) { //if undo info is for a move
				int[] undo = new int[3]; //store undo info
				undo[0] = 0;
				undo[1] = curPlayer.opponent.getXpos();
				undo[2] = curPlayer.opponent.getYpos();
				undoStack.push(undo);
				curPlayer.opponent.setXpos(redo[1]);
				curPlayer.opponent.setYpos(redo[2]);
			} else if (redo[0] == 1) { //horizontal wall
				int[] undo = new int[3]; //store undo info
				undo[0] = 1;
				undo[1] = redo[1];
				undo[2] = redo[2];
				undoStack.push(undo);
				board.MakeWall(redo[1], redo[2], WallDirections.h); //put wall back
			} else {
				int[] undo = new int[3]; //store redo info
				undo[0] = 2;
				undo[1] = redo[1];
				undo[2] = redo[2];
				undoStack.push(undo);
				board.MakeWall(redo[1], redo[2], WallDirections.v);
			}
		}
	}	
	
	/**
	 * @param x new x coord of player
	 * @param y new y coord of player
	 * @return returns true if move was successful
	 */
	public boolean move(int x, int y) {
		if (Rules.isLegalMove(curPlayer, x, y, board)) {
			int[] undo = new int[3]; //store move history
			undo[0] = 0;
			undo[1] = curPlayer.getXpos();
			undo[2] = curPlayer.getYpos();
			undoStack.push(undo);
			curPlayer.setXpos(x);
			curPlayer.setYpos(y);
			
			curPlayer = curPlayer.opponent;
			redoStack = new Stack<int[]>(); //clear redo info
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param x x coord of NW corner of wall
	 * @param y y coord of NW corner of wall
	 * @param d direction of the wall
	 * @return returns true if wall placement was successful
	 */
	public boolean placeWall (int x, int y, WallDirections d) {
		if (Rules.isLegalWall(x, y, d, board, curPlayer)) {
			board.MakeWall(x, y, d);
			int[] undo = new int[3]; //store undo information
			if (d == WallDirections.h) {
				undo[0] = 1;
			} else {
				undo[0] = 2;
			}

			undo[1] = x;
			undo[2] = y;
			undoStack.push(undo);
			curPlayer.makeWall();
			curPlayer = curPlayer.opponent;
			redoStack = new Stack<int[]>(); // clear redo info
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 *  prints the game state
	 */
	public void print () {
		if (curPlayer == player1) {
			System.out.println("It is player 1's turn");
		}
		else {
			System.out.println("It is player 2's turn");
		}
		board.printBoard(player1.getXpos(), player1.getYpos(), player2.getXpos(), player2.getYpos());
		System.out.print("Player 1: (");
		System.out.print(player1.getXpos()+", "+player1.getYpos());
		System.out.println(")  Walls Left: "+ player1.nWallsLeft());
		System.out.print("Player 2: (");
		System.out.print(player2.getXpos()+", "+player2.getYpos());
		System.out.println(")  Walls Left: "+ player2.nWallsLeft());
	}
	
	/**
	 *  prints board and winner
	 */
	public void printEndGame () {
		board.printBoard(player1.getXpos(), player1.getYpos(), player2.getXpos(), player2.getYpos());
		System.out.print("Player 1: (");
		System.out.print(player1.getXpos()+", "+player1.getYpos());
		System.out.println(")  Walls Left: "+ player1.nWallsLeft());
		System.out.print("Player 2: (");
		System.out.print(player2.getXpos()+", "+player2.getYpos());
		System.out.println(")  Walls Left: "+ player2.nWallsLeft());
		if (curPlayer == player1) {
			System.out.println("Player 2 wins!");
		} else {
			System.out.println("Player 1 wins!");
		}
	}
	
	/**
	 *  switch to next player
	 */
	public void switchPlayers () {
		curPlayer = curPlayer.opponent;
	}

}
