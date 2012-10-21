package quoridor;

import quoridor.Game.WallDirections;

/**
 * @author Iris Uy and Richard Taylor
 *
 */
public interface Board {
	//abstract board for quoridor game
	
	/**
	 * creates a wall
	 * @param Xpos x coord of NW cell
	 * @param Ypos y coord of NW cell
	 * @param d   direction of the wall (h/v)
	 */
	public void MakeWall (int Xpos, int Ypos, WallDirections d);
	
	/**
	 * removes a wall
	 * @param Xpos x coord of NW cell
	 * @param Ypos y coord of NW cell
	 * @param d  direction of the wall (h/v)
	 */
	public void RemoveWall (int Xpos, int Ypos, WallDirections d);
	
	/**
	 * @param x1 x coord of cell 1
	 * @param y1 y coord of cell 1
	 * @param x2 x coord of cell 2
	 * @param y2 y coord of cell 2
	 * @return returns true if there is wall between those cells
	 */
	public boolean isWall (int x1, int y1, int x2, int y2);
	
	/**
	 * @param x1 x coord of player 1
	 * @param y1 y coord of player 1
	 * @param x2 x coord of player 2
	 * @param y2 y coord of player 2
	 */
	public void printBoard(int x1, int y1, int x2, int y2);
	
	/**
	 * @param x x coord of player
	 * @param y y coord of player
	 * @param goalY y coord of goal row
	 * @return returns true if a path is found
	 */
	public boolean isPath(int x, int y, int goalY);
	
	/**
	 * @param x x coord of player
	 * @param y y coord of player
	 * @param goalY y coord of goal row
	 * @return returns int array with coordinates of optimal move
	 */
	public int[] findOptMove (int x, int y, int goalY);
}
