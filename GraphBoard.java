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
	public void MakeWall(int Xpos, int Ypos, WallDirections d) {
		// TODO Auto-generated method stub
		int edge1[] = new int[2], edge2[] = new int[2];
		int NWSquare = Ypos*(Game.N_ROWS) + Xpos;
		
		if (d == WallDirections.h) {
			edge1[2] = NWSquare + Game.N_ROWS; //edges being broken are vertical
			edge2[1] = NWSquare + 1;
			edge2[2] = NWSquare + 1 + Game.N_ROWS;
		} else if (d == WallDirections.v) {
			edge2[1] = NWSquare + Game.N_ROWS; //edges being broken are horizontal
			edge1[2] = NWSquare + 1;
			edge2[2] = NWSquare + 1 + Game.N_ROWS;
		}
		
		b.removeEdge(edge1[1], edge1[2]);//remove both edges from graph
		b.removeEdge(edge2[1], edge2[2]);
	}

	public boolean isWall (int x1, int y1, int x2, int y2) {
		int s1 = y1*Game.N_ROWS + x1;
		int s2 = y2*Game.N_ROWS + x2;
		return !b.isEdge(s1, s2);
	}


	@Override
	public String toString() {
		// for printing purposes
		String s = "";
		int i, j, k;
		for (i=0; i<Game.N_ROWS; i++) {
			for (j=0; j<Game.N_ROWS; j++) {
				/* check for player's position and indicate if player there
				if (player there) {
					s += "something";
				} else {*/
					s += "."; // these are cells
				//}
				if (j!=Game.N_ROWS-1) {
					if (this.isWall(j, i, j+1, i)) {
						s += "|"; // vertical walls
					} else {
						s += " ";
					}
				}
			}
			s += "\n";
			for (k=0; (k<Game.N_ROWS) && (i!=Game.N_ROWS-1); k++) {
				if (this.isWall(k, i, k, i+1)) {
					s += "- "; // horizontal walls
				} else {
					s += "  ";
				}
			}
			s += "\n";
		}
		return s;
	}
}
