package impl_model;

import interface_model.BreadthFirstModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

class BreadthFirstModelImpl implements BreadthFirstModel {

	private		Graph<String, NamedWeightedEdge> _graph;
	private		Integer							 _graph_accesses;
	
	// Creation
	public static BreadthFirstModel create(Graph<String, NamedWeightedEdge> g) {
		if(g == null) throw new NullPointerException();
		return new BreadthFirstModelImpl(g);
	}
	
	private BreadthFirstModelImpl(Graph<String, NamedWeightedEdge> g) {
		if(g == null) throw new NullPointerException();
		_graph = g;
	}
	
	/**
	 * @param node 
	 * @return all accessable adjacent nodes
	 */
	private Set<String> directedAdjacentNodes(String node) {
		if(node == null) throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		Set<NamedWeightedEdge> edges = ((DirectedGraph) _graph).outgoingEdgesOf(node);
		for(NamedWeightedEdge e : edges) {
			_graph_accesses++;
			String neigbour = _graph.getEdgeTarget(e);
			accu.add(neigbour);
		}
		return accu;
	}
	
	/**
	 * @param node 
	 * @return all accessable adjacent nodes
	 */
	private Set<String> undirectedAdjacentNodes(String node) {
		if(node == null) throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		Set<NamedWeightedEdge> edges = _graph.edgesOf(node);
		for(NamedWeightedEdge e : edges) {
			String source = _graph.getEdgeSource(e);
			String target = _graph.getEdgeTarget(e);
			_graph_accesses++;
			if(source == node) {
				accu.add(target);
			} else {
				accu.add(source);
			}
		}
		return accu;
	}
	
	/**
	 * @return all adjacent nodes which are accessable from given node
	 * It adds outgoing, adjacent nodes!
	 */
	public Set<String> adjacentNodes(String node) {
		if(node == null) 	throw new NullPointerException();
		if(_graph == null)	throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		if(isDirected()) {
			accu.addAll(directedAdjacentNodes(node));
		} else if(isUndirected()) {
			accu.addAll(undirectedAdjacentNodes(node));
		}
		return accu; 
	}
	
	/**
	 * @return all adjacent nodes which are accessable from given node
	 * It adds outgoing, adjacent nodes!
	 */
	public Set<String> backwardsAdjacentNodes(String node) {
		if(node == null) 	throw new NullPointerException();
		if(_graph == null)	throw new NullPointerException();
		Set<String> accu = new HashSet();
		if(isDirected()) {
			Set<NamedWeightedEdge> edges = ((DirectedGraph) _graph).incomingEdgesOf(node);
			for(NamedWeightedEdge e : edges) {
				String neigbour = _graph.getEdgeSource(e);
				_graph_accesses++;
				accu.add(neigbour);
			}
		} else if(isUndirected()) {
			accu.addAll(undirectedAdjacentNodes(node));
		}
		return accu; 
	}
	
	/**
	 * @param aMap has a set as value
	 * @return a union of all sets as values
	 */
	private <K, V> Set<V> mergeValueSets(Map<K, Set<V>> aMap) {
		if(aMap == null) throw new NullPointerException();
		Set<V> accu = new HashSet<>();
		for(Entry<K, Set<V>> kvp : aMap.entrySet()) {
			Set<V> valueSet = kvp.getValue();
			accu.addAll(valueSet);
		}
		return accu;
	}
	
	/**
	 * @see BreadthFirstModel
	 */
	@Override
	public ArrayList<String> start(String source, String target) {
		if(_graph == null) throw new NullPointerException("Graph is null.");
		if(source == null) throw new NullPointerException("Source is null.");
		if(target == null) throw new NullPointerException("Target is null.");
		if(source == target) {
			// empty list means source == target
			ArrayList<String> accu = new ArrayList<>();
			accu.add(source);
			return accu;
		}
		_graph_accesses = 0;
		Map<String, Integer> 	labeling = new HashMap<>();
		Queue<String> 			queue = new LinkedList<>();
		Set<String>		visited_nodes = new HashSet<>();
		labeling.put(source, 0);
		queue.add(source);
		visited_nodes.add(source);
		while(!queue.isEmpty()) {
			String current_node = queue.poll();
			int distance = labeling.get(current_node);
			Set<String> neighbours = adjacentNodes(current_node);
			neighbours.removeAll(visited_nodes);
			queue.addAll(neighbours);
			visited_nodes.addAll(neighbours);
			for(String neighbour : neighbours) {
				labeling.put(neighbour, distance+1);
			}
		}
		if(!visited_nodes.contains(target)) {
			// Since we didn't visited the target,
			// we can't reach it. It's not accessible!
			return new ArrayList<>();
		}
		System.out.println(isDirected() + " " + labeling);
		// shortest way
		ArrayList<String> shortest_way = new ArrayList<>();
		String current_node = target;
		shortest_way.add(current_node);
		for(int i = labeling.get(target) ; i > 0 ; i--) {
			Set<String> neighbours = backwardsAdjacentNodes(current_node);
			for(String neighbour : neighbours) {
				if(labeling.get(neighbour) == i-1) {
					shortest_way.add(neighbour);
					current_node = neighbour;
					break;
				}
			}
		}
		return reverse(shortest_way);
	}
	
	/**
	 * Get amount of nodes visited
	 */
	public int getGraphAccesses() {
		return _graph_accesses;
	}
	
	/**
	 * @param aList to reverse
	 * @return a reversed duplicate of aList 
	 */
	private <A> ArrayList<A> reverse(ArrayList<A> aList) {
		if(aList == null) throw new NullPointerException();
		ArrayList<A> accu = new ArrayList<>();
		for(ListIterator<A> i = aList.listIterator(aList.size()) ; i.hasPrevious() ; ) {
			accu.add(i.previous());
		}
		return accu;
	}
	
	private	boolean isDirected() {
		if(_graph == null) throw new NullPointerException();
		if(_graph instanceof DirectedGraph) {
			return true;
		}
		return false;
	}
	
	private boolean isUndirected() {
		if(_graph == null) throw new NullPointerException();
		if(_graph instanceof UndirectedGraph) {
			return true;
		}
		return false;
	}
}
