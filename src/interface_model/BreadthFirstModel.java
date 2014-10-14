package interface_model;

import java.util.ArrayList;

public interface BreadthFirstModel {
	
	/**
	 * Breadth first search on graph you gave in constructor
	 * @param	source is start node
	 * @param	target is goal node
	 * @return 	Null if the is no solution or an ArrayList<String>
	 */
	public ArrayList<String> start(String source, String target);
	
	/**
	 * @return Amount of nodes, visited
	 */
	public int getGraphAccesses();
}
