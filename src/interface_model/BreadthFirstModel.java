package interface_model;

import java.util.ArrayList;

public interface BreadthFirstModel {
	
	public void 			 setStart(String start_vertex);
	public void 			 setTarget(String target_vertex);
	
	/*
	 * @returns 
	 */
	public ArrayList<String> getResult();
}
