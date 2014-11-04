package interface_model;

import impl_model.NamedWeightedEdge;

import java.util.ArrayList;

import org.jgrapht.Graph;

public interface GraphHandlerModel {
	
	public Graph<String, NamedWeightedEdge> to_graph(ArrayList<String> edges);
	public ArrayList<String> 				from_graph(Graph<String, NamedWeightedEdge> graph);
	public Graph<String, NamedWeightedEdge> generateGraph(int vertexes, int edges);
}
