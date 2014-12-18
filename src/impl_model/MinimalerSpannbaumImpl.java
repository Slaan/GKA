package impl_model;

import interface_model.MinimalerSpannbaumModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

public class MinimalerSpannbaumImpl implements MinimalerSpannbaumModel {
	
	private Graph<String, NamedWeightedEdge> _graph;
	private Graph<String, NamedWeightedEdge> _minimalgraph;
	private double							 _time = 0.0;
	private Integer							 _graph_accesses = 0; 

	public static MinimalerSpannbaumModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new IllegalArgumentException("Graph is null.");
		return new MinimalerSpannbaumImpl(graph);
	}
	
	private MinimalerSpannbaumImpl(Graph<String, NamedWeightedEdge> graph) {
		_graph 			= graph;
		_minimalgraph 	= minimalerSpannBaum(graph);
	}
	
	private Graph<String, NamedWeightedEdge> minimalerSpannBaum(Graph<String, NamedWeightedEdge> graph) {
		_time = System.nanoTime();
		ArrayList<NamedWeightedEdge> 	 edges 		= new ArrayList<>(graph.edgeSet());
		_graph_accesses++;
		Graph<String, NamedWeightedEdge> g  		= new WeightedPseudograph<>(NamedWeightedEdge.class);
		// ensure all vertexes are in minimal tree
		_graph_accesses++;
		for(String vertex : graph.vertexSet())
			g.addVertex(vertex);
		ArrayListSort s = new ArrayListSort(edges);
		edges = s.quicksort();
		Set<String> vertexInGraph = new HashSet<>();
		for(Iterator<NamedWeightedEdge> i = edges.iterator(); i.hasNext(); ) {
			NamedWeightedEdge smallestEdge = i.next();
			String source = graph.getEdgeSource(smallestEdge);
			String target = graph.getEdgeTarget(smallestEdge);
			if(source != target) {
				if(!vertexInGraph.contains(source) || !vertexInGraph.contains(target)) {
					NamedWeightedEdge edge = new NamedWeightedEdge();
					edge.setWeight(smallestEdge.getthisWeight());
					g.addEdge(source, target, edge);
					vertexInGraph.add(source);
					vertexInGraph.add(target);
				}				
			}
		}
		_time = System.nanoTime() - _time;
		return g;
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

	@Override
	public Graph<String, NamedWeightedEdge> minimalerSpannbaum() {
		return _minimalgraph;
	}

	@Override
	public Graph<String, NamedWeightedEdge> graph() {
		return _graph;
	}

}
