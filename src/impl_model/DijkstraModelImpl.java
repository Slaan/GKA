package impl_model;

import interface_model.AlgorithmModel;
import interface_model.DijkstraModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

class DijkstraModelImpl implements DijkstraModel {

	private		Integer									_graph_accesses = 0; 
	public 		Graph<String,NamedWeightedEdge> 		_graph;
	private 	Set<String> 							_finishedNodes;
	private 	Set<String> 							_openNodes;
	private		Map<String, String> 					_pred;
	private 	Map<String, Double> 					_dist;
	
	// Creation
	public static DijkstraModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new DijkstraModelImpl(graph);
	}
	
	private DijkstraModelImpl(Graph<String, NamedWeightedEdge> graph) {
		this._graph = graph;
	}
	
	/**
	 * @see AlgorithmModel
	 * start ist der Hauptaufruf dieses Algorithmus, hiermit wird der kürzeste Weg eines
	 * Graphen bestimmt.
	 */
	@Override
	public ArrayList<String> start(String source, String target) {
		assert source != null : "Vorbedingung verletzt: source != null";
		assert target != null : "Vorbedingung verletzt: target != null";
		assert _graph != null : "Vorbedingung verletzt: _graph != null";
		Set<String> vertex_set = _graph.vertexSet();
		assert vertex_set.contains(source) : "Vorbedingung verletzt: _graph.vertexSet().contains(source)";
		assert vertex_set.contains(target) : "Vorbedingung verletzt: _graph.vertexSet().contains(target)";
		
		//Initailisierung der benötigten Datenstrukturen
		List<String> result = new ArrayList<>();
		_finishedNodes = new HashSet<>();
		_openNodes = new HashSet<>();
		_pred = new HashMap<>();
		_dist = new HashMap<>();
		
		//Setzen des ersten Wertes
		_pred.put(source, null);
		_dist.put(source, 0.0);
		_openNodes.add(source);
		  
		//So lange wir das Ziel nicht mit dem Algorithmus komplett abgehandelt wurde,
		//es also nicht der kleinste Wert gewesen ist, wird der Algorithmus weiter verfolgt.
		while (!_finishedNodes.contains(target)) {
			String closest_vertex = getMinimum(_openNodes);
			update(closest_vertex);
		    _finishedNodes.add(closest_vertex);
		    _openNodes.remove(closest_vertex); 
		}
		
		//Ermittlung des kürzesten Weges
	    String step = target;
	    if (_pred.get(step) == null) {
	      return null;
	    }
	    result.add(step);
	    while (_pred.get(step) != null) {
	      step = _pred.get(step);
	      result.add(step);
	    }
	    Collections.reverse(result);
		return (ArrayList<String>) result;
	}

	private void update(String closest_vertex) {
		assert closest_vertex != null : "Vorbedingung verletzt: closest_vertex != null";
		Set<String> all_adjacent = adjacentNodes(closest_vertex);
		Set<String> adjacent = new HashSet<>();
		for (String s : all_adjacent) {
			if (!_finishedNodes.contains(s)) {
				adjacent.add(s);
			}
		}
		for (String s : adjacent) {
			if (!s.equals(closest_vertex)) {
			NamedWeightedEdge edge = _graph.getEdge(closest_vertex, s);
			Double weight = edge.getthisWeight();
			Double cur_dist = _dist.get(s);
			Double optional_dist = (_dist.get(closest_vertex)+weight);
			if (cur_dist==null) {
				cur_dist=Double.MAX_VALUE;
			}
			if (cur_dist>optional_dist) {
				_dist.put(s, optional_dist);
				_pred.put(s, closest_vertex);
			}
			_openNodes.add(s);
			}
		}		
	}

	private String getMinimum(Set<String> openNodes) {
		assert openNodes != null : "Vorbedingung verletzt: openNodes != null";
		String result = null;
		for (String s : openNodes) {
			if (result==null) {
				result = s;
			} else {
				if (getDistance(s) < getDistance(result)) {
					result = s;
				}
			}
		}
		return result;
	}
	
	  private double getDistance(String destination) {
		  assert destination != null : "Vorbedingung verletzt: destination != null";
		    Double d = _dist.get(destination);
		    if (d == null) {
		      return Double.MAX_VALUE;
		    } else {
		      return d;
		    }
		  }

	/**
	 * @see AlgorithmModel
	 */
	@Override
	public int getGraphAccesses() {
		return _graph_accesses;
	}

	/**
	 * @see AlgorithmModel
	 */
	@Override
	public double getTime() {
		return 0;
	}
	
	/**
	 * @return all adjacent nodes which are accessible from given node
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
	 * @param node 
	 * @return all accessable adjacent nodes
	 */
	private Set<String> directedAdjacentNodes(String node) {
		if(node == null) throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		Set<NamedWeightedEdge> edges = ((DirectedGraph<String, NamedWeightedEdge>) _graph).outgoingEdgesOf(node);
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
			if(source.equals(node)) {
				accu.add(target);
			} else if(target.equals(node)){
				accu.add(source);
			} else {
				throw new Error("DUDE!");
			}
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
