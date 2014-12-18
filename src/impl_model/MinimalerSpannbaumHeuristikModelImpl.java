package impl_model;

import interface_model.HierholzerModel;
import interface_model.MinimalerSpannbaumHeuristikModel;
import interface_model.MinimalerSpannbaumModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;

public class MinimalerSpannbaumHeuristikModelImpl implements MinimalerSpannbaumHeuristikModel {

	private Graph<String, NamedWeightedEdge> _graph;
	private Graph<String, NamedWeightedEdge> _spanning_tree;
	private double							 _time 				= 0.0;
	private Integer							 _graph_accesses 	= 0;
	private double							 _weight			= 0.0;
	
	private MinimalerSpannbaumHeuristikModelImpl(Graph<String, NamedWeightedEdge> graph) {
		_graph = graph;
	}

	public static MinimalerSpannbaumHeuristikModelImpl create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new MinimalerSpannbaumHeuristikModelImpl(graph);
	}
	
	private void doubleEdges() {
		Set<NamedWeightedEdge> edges = new HashSet<>(_spanning_tree.edgeSet());
		for(NamedWeightedEdge edge : edges) {
			String source = _spanning_tree.getEdgeSource(edge);
			String target = _spanning_tree.getEdgeTarget(edge);
			NamedWeightedEdge edge_cody = new NamedWeightedEdge();
			edge_cody.setWeight(edge.getthisWeight());
			_spanning_tree.addEdge(target, source, edge_cody);
		}
	}
	
	@Override
	public double getWeight() {
		return _weight;
	}

	@Override
	public ArrayList<String> start(String source, String target) {
		if(source == null) throw new IllegalArgumentException();
		_time = System.nanoTime();
		MinimalerSpannbaumModel mini_m = GKAModel.minimalerSpannbaum(_graph);
		_spanning_tree = mini_m.minimalerSpannbaum();
		doubleEdges();
		HierholzerModel hhm = GKAModel.hierholz(_spanning_tree);
		ArrayList<String> result = hhm.start(source, source);
		result = removeDubs(result);
		result.add(source);
		calculateWeight(result);
		_time = System.nanoTime() - _time;
		_graph_accesses += hhm.getGraphAccesses();
		return result;
	}

	private void calculateWeight(ArrayList<String> travel) {
		Iterator<String> i = travel.iterator();
		_weight = 0.0;
		if(!i.hasNext()) 
			throw new Error("unexpected error!");
		String current = i.next();
		while(i.hasNext()) {
			String next = i.next();
			Set<NamedWeightedEdge> edges = _spanning_tree.getAllEdges(current, next);
			// we know all edges between current and next have the 
			// same weight
			NamedWeightedEdge edge = _graph.getEdge(current, next);
			System.out.println(edge);
			_weight += edge.getthisWeight();
			current = next;
		}
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
		return _time/1000000;
	}

	private <E> ArrayList<E> removeDubs(ArrayList<E> aList) {
		Set<E> 		 in_list = new HashSet<>();
		ArrayList<E> accu 	= new ArrayList<>();
		for(E e : aList) {
			if(in_list.add(e))
				accu.add(e);
		}
		return accu;
	}
}
