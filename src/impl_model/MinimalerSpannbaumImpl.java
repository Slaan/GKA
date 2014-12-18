
package impl_model;

import interface_model.MinimalerSpannbaumModel;

import java.util.ArrayList;
import java.util.Arrays;
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
		Set<Set<String>> combinations = new HashSet<>();
		for(String vertex : graph.vertexSet()) {
			combinations.add(new HashSet<>(Arrays.asList(vertex)));
		}
		ArrayList<NamedWeightedEdge> 	 edges 		= new ArrayList<>(graph.edgeSet());
		Graph<String, NamedWeightedEdge> g  		= new WeightedPseudograph<>(NamedWeightedEdge.class);
		// ensure all vertexes are in minimal tree
		for(String vertex : graph.vertexSet())
			g.addVertex(vertex);		
		ArrayListSort s = new ArrayListSort(edges);
		edges = s.quicksort();
		for(Iterator<NamedWeightedEdge> i = edges.iterator(); i.hasNext(); ) {
			NamedWeightedEdge edge = i.next();
			String source = _graph.getEdgeSource(edge);
			String target = _graph.getEdgeTarget(edge);
			if(!find_set(combinations, source).equals(find_set(combinations, target))) {
				g.addEdge(source, target, edge);
				combinations = union(combinations, source, target);
			}
		}
		_time = System.nanoTime() - _time;
		return g;
	}
	
	private Set<String> find_set(Set<Set<String>> combinations, String vertex) {
		for(Set<String> s : combinations) {
			if(s.contains(vertex)) 
				return new HashSet<>(s);
		}
		throw new Error("Unexpected Error!");
	}
	
	private Set<Set<String>> union(Set<Set<String>> combinations, String vertex1, String vertex2) {
		Set<String> v1_set = find_set(combinations, vertex1);
		Set<String> v2_set = find_set(combinations, vertex2);
		combinations.remove(v1_set);
		combinations.remove(v2_set);
		v1_set.addAll(v2_set);
		combinations.add(v1_set);
		return combinations;
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

	@Override
	public double getWeight() {
		double accu = 0.0;
		for(NamedWeightedEdge edge : _minimalgraph.edgeSet()) {
			accu += edge.getthisWeight();
		}
		return accu;
	}

}
