package project;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import project.Game.WallDirections;

public class GameState {
	
	private Board board;
	private Player curPlayer, player1, player2;
	private Stack<int[]> undoStack;
	private Stack<int[]> redoStack;
	
	public GameState () {
		undoStack = new Stack<int[]>();
		redoStack = new Stack<int[]>();
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
	
	public void undo() {
		int[] undo;
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
	
	
	public void redo() {
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
