package impl_model;

import org.jgrapht.Graph;

import interface_model.MinimalerSpannbaumModel;

public class MinimalerSpannbaumImpl implements MinimalerSpannbaumModel {
	
	private Graph<String, NamedWeightedEdge> _graph;
	private Graph<String, NamedWeightedEdge> _minimalgraph;

	public static MinimalerSpannbaumModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new IllegalArgumentException("Graph is null.");
		return new MinimalerSpannbaumImpl(graph);
	}
	
	private MinimalerSpannbaumImpl(Graph<String, NamedWeightedEdge> graph) {
		_graph 			= graph;
		_minimalgraph 	= minimalerSpannBaum(graph);
	}
	
	private Graph<String, NamedWeightedEdge> minimalerSpannBaum(Graph<String, NamedWeightedEdge> graph) {
		// TODO: Implement me!
		return null;
	}
	
	@Override
	public int getGraphAccesses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalGraphAccesses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Graph<String, NamedWeightedEdge> minimalerSpannbaum() {
		return _minimalgraph;
	}

	@Override
	public Graph<String, NamedWeightedEdge> graph() {
		return _graph;
	}

}
