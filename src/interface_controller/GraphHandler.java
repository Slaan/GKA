package interface_controller;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public interface GraphHandler {

	public void 		save();
	public void		 	load();
	public ListenableGraph<?,?>	getGraph();
}
