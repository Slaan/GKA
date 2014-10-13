package interface_model;

import java.util.ArrayList;

public interface BreadthFirstModel {
	
	/**
	 * Breadth first search on graph you gave in constructor
	 * @param	source is start node
	 * @param	target is goal node
	 * @return 	null when there is no result. An empty ArrayList means, that 
	 * 			the source is the target.
	 */
	public ArrayList<String> breadthFirst(String source, String target);
}
