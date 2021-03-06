package interface_model;

import java.util.ArrayList;

public interface ShortestWayModel extends AlgorithmModel {

	/**
	 * Search a shortest way from source to target on graph you gave in constructor. 
	 * @param	Source is start node
	 * @param	Target is goal node
	 * @return 	Null if the is no solution or an ArrayList<String>
	 */
	public ArrayList<String> start(String source, String target);
	
}
