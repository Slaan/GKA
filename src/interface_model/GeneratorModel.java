package interface_model;

import impl_model.NamedWeightedEdge;

import org.jgrapht.Graph;

public interface GeneratorModel {
	
	/**
	 * 
	 * @param vertex
	 * @param edges
	 * @return
	 */
	public Graph<String, NamedWeightedEdge> generateDirectedGraph(int vertex, int edges);

	Graph<String, NamedWeightedEdge> genereateCompleteUndirectedGraph(
			int vertexes); 
}
