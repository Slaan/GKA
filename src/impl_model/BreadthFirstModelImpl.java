package impl_model;

import interface_model.BreadthFirstModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.ListenableDirectedGraph;

class BreadthFirstModelImpl implements BreadthFirstModel {

	private		ListenableGraph<String, NamedWeightedEdge> 	_graph;
	
	// Creation
	public static BreadthFirstModel create(ListenableGraph lg) {
		if(lg == null) throw new NullPointerException();
		return new BreadthFirstModelImpl(lg);
	}
	
	private BreadthFirstModelImpl(ListenableGraph lg) {
		if(lg == null) throw new NullPointerException();
		_graph = lg;
	}
	
	@Override
	public ArrayList<String> breadthFirst(String source, String target) {
		if(_graph == null) throw new NullPointerException();
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
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
