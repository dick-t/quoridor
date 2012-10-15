package project;

import project.Game.WallDirections;

public interface Board {
	//abstract board for quoridor game
	
	public void MakeWall (int Xpos, int Ypos, WallDirections d);
	public void RemoveWall (int Xpos, int Ypos, WallDirections d);

	public boolean isUnique (Board newBoard);
	
	public boolean isWall (int x1, int y1, int x2, int y2);
	
	public String toString();
	
	public void printBoard(int x1, int y1, int x2, int y2);
	
	public boolean isPath(int x, int y, int goalY);
}
