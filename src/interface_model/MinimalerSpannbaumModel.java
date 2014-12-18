package interface_model;

import impl_model.NamedWeightedEdge;

import org.jgrapht.Graph;

public interface MinimalerSpannbaumModel extends AlgorithmModel {
	
	/**
	 * 
	 * @return
	 */
	public Graph<String, NamedWeightedEdge> minimalerSpannbaum();
	
	/**
	 * @return graph given in constructor
	 */
	public Graph<String, NamedWeightedEdge> graph();
	
	/**
	 * @return sum of edge weights
	 */
	public double getWeight();
}
