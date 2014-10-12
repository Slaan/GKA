package impl_model;

import interface_model.BreadthFirstModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

class BreadthFirstModelImpl implements BreadthFirstModel {

	private		Graph<String, ? extends DefaultEdge> 	_graph;
	
	// Creation
	public static BreadthFirstModel create(Graph lg) {
		if(lg == null) throw new NullPointerException();
		return new BreadthFirstModelImpl(lg);
	}
	
	private BreadthFirstModelImpl(Graph lg) {
		if(lg == null) throw new NullPointerException();
		_graph = lg;
	}
	
	private Set<String> valueSet(Map<?, String> in) {
		if(in == null) throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		
		return accu;
	}
	
	private Set<String> adjacentNodes(String node) {
		if(node == null) 	throw new NullPointerException();
		if(_graph == null)	throw new NullPointerException();
		if(_graph instanceof DirectedGraph) {
			
		} else if(_graph instanceof UndirectedGraph) {
			
		}
	}
	
	/**
	 * @see BreadthFirstModel
	 */
	@Override
	public ArrayList<String> breadthFirst(String source, String target) {
		if(_graph == null) throw new NullPointerException("Graph is null.");
		if(source == null) throw new NullPointerException("Source is null.");
		if(target == null) throw new NullPointerException("Target is null.");
		// distance
		int i = 0;
		Map<Integer, String> nodes = new HashMap<>();
		
		return null;
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
