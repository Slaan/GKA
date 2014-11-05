package interface_controller;

import java.awt.event.ActionListener;

import impl_model.NamedWeightedEdge;

import org.jgrapht.Graph;

public interface Generator {
	
	/**
	 * 
	 * @return
	 */
	public Graph<String, NamedWeightedEdge> getGraph();
	
	/**
	 * 
	 */
	public void generate();
	
	public void setGraphListener(ActionListener a);
}
