package impl_model;

import interface_model.GraphHandlerModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.WeightedPseudograph;

public class OtherGraphHandlerModelImpl implements GraphHandlerModel {

	public static GraphHandlerModel create() {
		return new OtherGraphHandlerModelImpl();
	}
	
	private OtherGraphHandlerModelImpl() {
		// nop
	}
	
	/**
	 * Removes all whitespaces including tabultor, space and linebreak
	 * @param String to remove whitespaces
	 */
	String remove_whitespaces(String s) {
		if(s == null) throw new NullPointerException();
		return s.replaceAll("(\\s)", "");
	}
	
	/**
	 * This method is equal to strings contain method. It iterates over the
	 * list until it fits.
	 * @param aList
	 * @param sym
	 * @return true when symbol is found once.
	 */
	boolean containsOnce(ArrayList<String> aList, String sym) {
		if(aList == null || sym == null) throw new NullPointerException();
		for(String s : aList) {
			if(s.contains(sym)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * When parsing from file we need to differentiate between undirected 
	 * and directed graphs. 
	 * @param edges, an ArrayList over String which fits to the edge notation  
	 * @return Fitting graphs.
	 */
	Graph<String, NamedWeightedEdge> fittingGraph(ArrayList<String> edges) {
		if(edges == null) throw new NullPointerException();
		boolean is_undirected = containsOnce(edges, "--"); 
		boolean is_directed = containsOnce(edges, "->"); 
		if(is_undirected && is_directed) {
			throw new IllegalArgumentException("Graph conversation: contains undirected and directed edges!");
		}
		Graph<String, NamedWeightedEdge> accu;
		if(is_directed) {
			accu = new DirectedWeightedPseudograph(NamedWeightedEdge.class);
		} else if(is_undirected) {
			accu = new WeightedPseudograph<>(NamedWeightedEdge.class);
		} else {
			throw new IllegalArgumentException("Unknown edge types");
		}
		return accu;
	}
	
	/**
	 * @see GraphHandlerModel
	 */
	@Override
	public Graph<String, NamedWeightedEdge> to_graph(ArrayList<String> edges) {
		if(edges == null) throw new NullPointerException();
		Graph<String, NamedWeightedEdge> accu = fittingGraph(edges);
		String 	fs = "[^\\-><:()]"; // Forbidden symbols
		Pattern reg = Pattern.compile("(?<v1>"+fs+"*)((?<richtung>[<-][->])(?<v2>"+fs+"*)"
					  + "(\\((?<edgename>"+fs+"*)\\))?(:(?<edgeweight>"+fs+"*))?)?;");
		for(String edge_with_whitespace : edges) {
			if(edge_with_whitespace == null) throw new NullPointerException();
			String edge = remove_whitespaces(edge_with_whitespace);
			if(!edge.isEmpty()) {
				Matcher m = reg.matcher(edge);
				if(m.matches()) {
					String v1 	  = m.group("v1");
					if(v1 == null) {
						// source is a musthave
						throw new IllegalArgumentException("Missing source vertex");
					}
					accu.addVertex(v1);
	 				String v2 	  = m.group("v2");
	 				NamedWeightedEdge edge_accu;
	 				if(v2 == null) {
	 					// when there is no target vertex,
	 					// we've an node without edges
	 					// nop
	 				} else {
	 					accu.addVertex(v2);
	 					edge_accu = accu.addEdge(v1, v2);
	 					String name	  = m.group("edgename");
	 					if(name == null) {
	 						edge_accu.setName("");
	 					}
	 					String weight = m.group("edgeweight");
	 					if(weight == null) {
	 						// since all graphs are weighted, we need to 
	 						// add the default weight, when no weight is given
	 						edge_accu.setWeight(WeightedGraph.DEFAULT_EDGE_WEIGHT);
	 					} else {
	 						Double weight_value = Double.valueOf(weight);
	 						assert weight_value>0.0 : "Vorbedingung verletzt: weight_value>0.0";
	 						edge_accu.setWeight(Double.valueOf(weight));
	 					}
	 				}
				} else {
					throw new IllegalArgumentException("An edge ( " + edge + " ) has invalid format.");
				}
			}
		}
		return accu;
	}
	
	@Override
	public ArrayList<String> from_graph(Graph graph) {
		if(graph == null) throw new IllegalArgumentException();
		ArrayList<String> accu = new ArrayList<>();
		Set<NamedWeightedEdge> edge_set = graph.edgeSet();
		for (NamedWeightedEdge edge : edge_set) {
			// we can simply get the graph weight because
			// all graphs are weighted
			double 	weight = graph.getEdgeWeight(edge);
			String	source = (String) graph.getEdgeSource(edge);
			String 	target = (String) graph.getEdgeTarget(edge);
			if(graph instanceof DirectedGraph) {
				accu.add(source + "->" + target + ":" + Double.toString(weight) + ";");
			} else if(graph instanceof UndirectedGraph) {
				accu.add(source + "--" + target + ":" + Double.toString(weight) + ";");
			} 		
		}
		Set<String> vertex_set = new HashSet<>();
		vertex_set.addAll(graph.vertexSet());
		// get nodes without edges
		vertex_set.removeAll(nodesWithEdges(graph));
		for(String vertex : vertex_set) {
			accu.add(vertex + ";");
		}
		return accu;
	}
	
	/**
	 * 
	 * @param graph
	 * @return all nodes which has at least one edge
	 */
	private Set<String> nodesWithEdges(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new IllegalArgumentException();
		Set<String> accu = new HashSet<>();
		for(NamedWeightedEdge edge : graph.edgeSet()) {
			accu.add(graph.getEdgeSource(edge));
			accu.add(graph.getEdgeTarget(edge));
		}
		return accu;
	}
}
