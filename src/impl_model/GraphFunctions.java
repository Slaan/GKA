package impl_model;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

public final class GraphFunctions {
	
	/**
	 * @param node 
	 * @return all accessable adjacent nodes
	 */
	private static Set<String> directedAdjacentNodes(Graph<String, NamedWeightedEdge> graph, String node) {
		if(node == null) throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		Set<NamedWeightedEdge> edges = ((DirectedGraph<String, NamedWeightedEdge>) graph).outgoingEdgesOf(node);
		for(NamedWeightedEdge e : edges) {
			String neigbour = graph.getEdgeTarget(e);
			accu.add(neigbour);
		}
		return accu;
	}
	
	/**
	 * @param node 
	 * @return all accessable adjacent nodes
	 */
	private static Set<String> undirectedAdjacentNodes(Graph<String, NamedWeightedEdge> graph, String node) {
		if(node == null) throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		Set<NamedWeightedEdge> edges = graph.edgesOf(node);
		for(NamedWeightedEdge e : edges) {
			String source = graph.getEdgeSource(e);
			String target = graph.getEdgeTarget(e);
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
	
	/**
	 * Get all adjacent nodes of given graph and node
	 * @return all adjacent nodes
	 */
	public static Set<String> adjacentNodes(Graph<String, NamedWeightedEdge> graph, String node) {
		if(node == null) 	throw new NullPointerException();
		if(graph == null)	throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		if(isDirected(graph)) {
			accu.addAll(directedAdjacentNodes(graph, node));
		} else if(isUndirected(graph)) {
			accu.addAll(undirectedAdjacentNodes(graph, node));
		}
		return accu; 
	}
	
	/**
	 * @return all adjacent nodes which are accessable from given node
	 */
	public static Set<String> backwardsAdjacentNodes(Graph<String, NamedWeightedEdge> graph, String node) {
		if(node == null) 	throw new NullPointerException();
		if(graph == null)	throw new NullPointerException();
		Set<String> accu = new HashSet<>();
		if(isDirected(graph)) {
			Set<NamedWeightedEdge> edges;
			edges = ((DirectedGraph<String, NamedWeightedEdge>) graph).incomingEdgesOf(node);
			for(NamedWeightedEdge e : edges) {
				String neigbour = graph.getEdgeSource(e);
				accu.add(neigbour);
			}
		} else if(isUndirected(graph)) {
			accu.addAll(undirectedAdjacentNodes(graph, node));
		}
		return accu; 
	}
	
	public static boolean isDirected(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		if(graph instanceof DirectedGraph) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param graph
	 * @return
	 */
	public static boolean isUndirected(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		if(graph instanceof UndirectedGraph) {
			return true;
		}
		return false;
	}
	
}
