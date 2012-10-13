package project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameRunner {
	
	public static void runGame () {
		GameState gs = new GameState();
		BufferedReader is = new BufferedReader (new InputStreamReader(System.in));
		while (!gs.isGameOver()) {
			boolean madeMove = false;
			boolean wasInvalid = false;
			String input = "";
			int x=-1;
			int y=-1;
			while (!madeMove) {
				gs.print(); //print board
				if (wasInvalid) {
					System.out.println("\"" + input + "\" was invalid");
				}
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
					//print help info
					System.out.println("help info");
				}
				// give 'move' as coordinates to spot you want to move to
				String[] tokens = input.split(" ");
				if (tokens.length == 2) {
					try {
						x = Integer.parseInt(tokens[0]);
						y = Integer.parseInt(tokens[1]);
					} catch (Exception e) {
						wasInvalid = true;
					}
				} else {
					wasInvalid = true;
				}
				if (!wasInvalid) {
					madeMove = gs.move(x, y);
					wasInvalid = !madeMove;
				}
			}
			//Game ends
		}
	}

}
