package project;

import project.Game.WallDirections;

public class GraphBoard implements Board {

	//graph-based implementation of a board
	
	Graph b;
	protected GraphBoard () {
		b = new Graph(Game.N_ROWS);
	}
	

	@Override
	public boolean isUnique(Board newBoard) { //compare 
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void MakeWall(int Xpos, int YPos, WallDirections d) {
		// TODO Auto-generated method stub
		
	}

}
