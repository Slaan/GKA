package interface_controller;

import impl_model.NamedWeightedEdge;

import org.jgrapht.Graph;

public interface GraphHandler {

	/**
	 * Open save dialog.
	 */
	public void 		save(Graph<String, NamedWeightedEdge> graph);
	
	/**
	 * Open load dialog. 
	 */
	public void		 	load();
	
	/**
	 * Get loaded or stored graph
	 */
	public Graph<String, NamedWeightedEdge>	getGraph();
	
	/**
	 * Get path from file
	 */
	public String getPath();
	
}
