package project;

public interface Board {
	//abstract board for quoridor game
	public enum WallDirections {
		h,
		v
	};
	
	public void MakeWall (int Xpos, int YPos, WallDirections d);


	public boolean isUnique (Board2 newBoard);
}
