package impl_model;

import interface_model.MinimalerSpannbaumHeuristikModel;
import interface_model.MinimalerSpannbaumModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;

public class MinimalerSpannbaumHeuristikModelImpl implements MinimalerSpannbaumHeuristikModel {

	private Graph<String, NamedWeightedEdge> _graph;
	private double							 _time 				= 0.0;
	private Integer							 _graph_accesses 	= 0;
	
	private MinimalerSpannbaumHeuristikModelImpl(Graph<String, NamedWeightedEdge> graph) {
		_graph = graph;
	}

	public static MinimalerSpannbaumHeuristikModelImpl create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new MinimalerSpannbaumHeuristikModelImpl(graph);
	}
	
	private void doubleEdges() {
		Set<NamedWeightedEdge> edges = new HashSet<>(_graph.edgeSet());
		for(NamedWeightedEdge edge : edges) {
			String source = _graph.getEdgeSource(edge);
			String target = _graph.getEdgeTarget(edge);
			NamedWeightedEdge edge_cody = new NamedWeightedEdge();
			edge_cody.setWeight(edge.getthisWeight());
			_graph.addEdge(source, target, edge_cody);
		}
	}
	
	@Override
	public double getWeight() {
		return 0;
	}

	@Override
	public ArrayList<String> start(String source, String target) {
		MinimalerSpannbaumModel mini_m = GKAModel.minimalerSpannbaum(_graph);
		Graph<String, NamedWeightedEdge> mini = mini_m.minimalerSpannbaum();
		doubleEdges();
		// TODO: implement eulertour
	}

	@Override
	public int getGraphAccesses() {
		return _graph_accesses;
	}

	@Override
	public int getTotalGraphAccesses() {
		return 0;
	}

	@Override
	public double getTime() {
		return _time;
	}

}
