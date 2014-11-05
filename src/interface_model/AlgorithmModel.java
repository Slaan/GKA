package interface_model;

import java.util.ArrayList;

public interface AlgorithmModel {

	/**
	 * Breadth first search from source to target on graph you gave in constructor. 
	 * @param	Source is start node
	 * @param	Target is goal node
	 * @return 	Null if the is no solution or an ArrayList<String>
	 */
	public ArrayList<String> start(String source, String target);
	
	/**
	 * @return Accesses on graph during last algorithm run. By default 0.
	 */
	public int getGraphAccesses();
	
	/**
	 * 
	 * @return amount of accesses on graph during all runs.
	 */
	public int getTotalGraphAccesses();
	
	/**
	 * @return Time used in milliseconds. By default 0.
	 */
	public double getTime();
	
}
