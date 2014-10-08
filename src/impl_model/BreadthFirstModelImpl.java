package impl_model;

import interface_model.BreadthFirstModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;

class BreadthFirstModelImpl implements BreadthFirstModel {

	private		ListenableGraph<String, NamedWeightedEdge> 	_graph;
	private		String										_start;
	private		String										_target;
	
	// Creation
	public static BreadthFirstModel create(ListenableGraph lg) {
		if(lg == null) throw new NullPointerException();
		return new BreadthFirstModelImpl(lg);
	}
	
	private BreadthFirstModelImpl(ListenableGraph lg) {
		if(lg == null) throw new NullPointerException();
	}

	@Override
	public void setStart(String start_vertex) {
		if(start_vertex == null) throw new NullPointerException();
		_start = start_vertex;
	}

	@Override
	public void setTarget(String target_vertex) {
		if(target_vertex == null) throw new NullPointerException();
		_target = target_vertex;
	}
	
	@Override
	public ArrayList<String> getResult() {
		if(_graph == null) throw new NullPointerException();
		if(_start == null) throw new NullPointerException();
		if(_target == null) throw new NullPointerException();
		// init algorithm
		ArrayList<String> accu;
		if(isDirected()) {
			accu = bf_directed();
		} else if(isUndirected()) {
			accu = bf_undirected();
		} else {
			accu = new ArrayList<>();
		}
		return accu;
	}
	
	private ArrayList<String> bf_directed() {
		ArrayList<String> accu;
		accu = new ArrayList<>();
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet();
		ListenableDirectedGraph<String, NamedWeightedEdge> g;
		g = (ListenableDirectedGraph<String, NamedWeightedEdge>) _graph;
		queue.add(_start);
		while(!queue.isEmpty()) {
			visited.add(queue.peek());
			Set<NamedWeightedEdge> neighbour = g.outgoingEdgesOf(queue.peek());
			neighbour.removeAll(visited);
			
		}
		return accu;
	}
	
	private ArrayList<String> bf_undirected() {
		return null;
	}
	
	private	boolean isDirected() {
		if(_graph == null) throw new NullPointerException();
		if(_graph instanceof ListenableDirectedGraph) {
			return true;
		}
		return false;
	}
	
	private boolean isUndirected() {
		if(_graph == null) throw new NullPointerException();
		if(_graph instanceof ListenableUndirectedGraph) {
			return true;
		}
		return false;
	}
}
