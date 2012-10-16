package project;

import java.util.*;

public class Graph {
	private boolean array[][];
	private int size;
	protected Graph(int s) {
		size = s*s; // s is the 'side length' of the graph, which represents a 2D square quoridor board for our purposes
		array = new boolean[size][size];
		int n=0;
		while (n < size) { //place edges between adjacent squares
			if (n % s < s - 1) {
				array[n][n+1] = true;
				array[n+1][n] = true;
			}
			if (n / s < s - 1) {
				array[n][n+s] = true;
				array[n+s][n] = true;
			}
			n++;
		}
	}
	
	public void removeEdge (int n1, int n2) { //remove edge between nodes n1 and n2
		array[n1][n2] = false;
		array[n2][n1] = false;
	}
	
	public void insertEdge (int n1, int n2) {
		array[n1][n2] = true;
		array[n2][n1] = true;
	}
	
	public boolean isEdge (int n1, int n2) {
		if ((n1>=size)||(n2>=size)||(n1<0)||(n2<0)) {
			return false;
		} else {
			return array[n1][n2];
		}
	}
	
	public boolean searchGoal(int n, int goalY) {
		Queue<Integer> q = new LinkedList<Integer>();
		int t;
		int c;
		int count = 0;
		int visited[] = new int[size];
		boolean isV;
		
		q.add(n);
		visited[count] = n;
		count++;
		while (q.size() > 0) { //modified BFS
			t = q.remove();
			if ((t>=goalY*Math.sqrt(size))&&(t<(goalY+1)*Math.sqrt(size))) {
				return true;
			}
			c = 0;
			while (c < size) {
				if (array[t][c]) {
					isV = false;
					for (int v : visited) {
						if (v == c) {
							isV = true;
						}
					}
					if (!isV) {
						visited[count] = c;
						count++;
						q.add(c);
					}
				}
				c++;
			}
		}
		return false;
	}
	
}
