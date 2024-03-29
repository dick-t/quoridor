package quoridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import quoridor.Game.WallDirections;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public class GameRunner {
	
	static boolean madeMove;
	static boolean wasInvalid;
	static String input = "";
	
	/**
	 *  Basic game runner
	 */
	public static void runGame () {
		GameState gs = new GameState();
		gs.makeAI();
		while (!gs.isGameOver()) {
			madeMove = false;
			wasInvalid = false;
			input = "";
			while (!madeMove) {
				gs.print(); //print board and player locations
				if (!gs.isAITurn()) {
					humanTurn(gs);
				}  else {
				    gs.AITurn();
				    madeMove = true;
				}
			}
		}
		gs.printEndGame();
	}
	
	/**
	 *  Print help information for user
	 */
	public static void printHelp () {
		System.out.println("To make a move: x y m");
		System.out.println("Give coordinates of the space you wish to move to, followed by an m.");
		System.out.println("To place a wall: x y w[h|v]");
		System.out.print("Give the coordinates of the top left space involved in your wall, ");
		System.out.println("followed by wh for a horizonal wall or wv for a vertical wall.");
		System.out.println("To undo: u");
		System.out.println("To redo: r");
	}
	
	/**
	 * facilitates input for human's turn
	 * @param gs current gamestate
	 */
	public static void humanTurn (GameState gs) {
		int x=-1, y=-1;
		BufferedReader is = new BufferedReader (new InputStreamReader(System.in));
		if (wasInvalid) {
			System.out.println("\"" + input + "\" was invalid");
		}
		wasInvalid=false;
		System.out.println("Enter your move ('h' for help, 'q' to quit):");
		try {
			input = is.readLine();
		} catch (IOException e) {
			System.out.println("Could not read input");
			System.exit(1);
		}
		if (input.equals("q")) {
			System.exit(0);
		} else if (input.equals("h")) {
			printHelp();
		} else if (input.equals("u")) {
			gs.undo();
		} else if (input.equals("r")) {
			gs.redo();
		} else {
			String[] tokens = input.split(" ");
			if (tokens.length == 3) {
				try {
					x = Integer.parseInt(tokens[0]);
					y = Integer.parseInt(tokens[1]);
				} catch (Exception e) {
					wasInvalid = true;
				}
				if (tokens[2].charAt(0)=='m') { // move
					if (!wasInvalid) {
						madeMove = gs.move(x, y);
						wasInvalid = !madeMove;
					}		
				} else if (tokens[2].charAt(0)=='w') { // wall
					if (tokens[2].charAt(1)=='h') {
					//	horizontal wall
						madeMove = gs.placeWall(x, y, WallDirections.h);
						System.out.println(madeMove);
						wasInvalid = !madeMove;
					} else if (tokens[2].charAt(1)=='v') {
					//	vertical wall
						madeMove = gs.placeWall(x, y, WallDirections.v);
						wasInvalid = !madeMove;
					}
				} else {
					wasInvalid = true;
				}
			} else {
				wasInvalid = true;
			}
		}
	}

}
