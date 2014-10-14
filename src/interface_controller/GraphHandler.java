package interface_controller;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public interface GraphHandler {

	/**
	 * Open save dialog.
	 */
	public void 		save();
	
	/**
	 * Open load dialog. 
	 */
	public void		 	load();
	
	/**
	 * Get loaded or stored graph
	 */
	public Graph<?, ?>	getGraph();
}
