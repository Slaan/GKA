package impl_model;

import interface_model.GraphHandlerModel;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.WeightedPseudograph;

class GraphHandlerModelImpl implements GraphHandlerModel {

	private final int DEFAULT_EDGE_WEIGHT = 1; 
	
	// Creation
	public static GraphHandlerModel create() {
		return new GraphHandlerModelImpl();
	}
	
	private GraphHandlerModelImpl() {
		// nop
	}
	
	@Override
	public ArrayList<String> from_graph(Graph<?, ?> graph) {
		if(graph == null) throw new NullPointerException();
		// TODO Auto-generated method stub
		// 1) get type (directed/undirected) and set symbol (<- / --)
		// 2) is graph weighted? 
		// 3) is the edgename empty? 
		// 		yes -> skip brackets ; no -> add brackets and name
		// 4) is the edge a loop? 
		// 		print loops as "a -- a" or "a -> a"
		return null;
	}
	
	@Override
	public Graph<?,?> to_graph(ArrayList<String> edges) {
		if(edges == null) throw new NullPointerException();
		ArrayList<String> formatted_edges = format_edges(edges);
		// Parse to ListenableGraph
		String 	sz = "[^\\-><:()]"; // Sonderzeichen
		Pattern reg = Pattern.compile("(?<v1>"+sz+"*)((?<richtung>[<-][->])(?<v2>"+sz+"*)"
					  + "(\\((?<edgename>"+sz+"*)\\))?(:(?<edgeweight>"+sz+"*))?)?;");
		Graph<?,?> result = null;
		if(contains_once(edges, "->")) {
			//Directed weighted graph
			if (contains_once(formatted_edges, ":")) {
				DirectedWeightedPseudograph<String,DefaultWeightedEdge> dir_weighted = new DirectedWeightedPseudograph<>(DefaultWeightedEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						String edgename = m.group("edgename");
						int edgeweight = DEFAULT_EDGE_WEIGHT;
						if (m.group("edgeweight") != null) {
							edgeweight = Integer.parseInt(m.group("edgeweight"));
						}
						if (node1 != null && !dir_weighted.vertexSet().contains(node1)) {
							dir_weighted.addVertex(node1);
						}
						if (node2 != null && !dir_weighted.vertexSet().contains(node2)) {
							dir_weighted.addVertex(node2);
						}
						if (node1 != null && node2 != null) {
							DefaultWeightedEdge e = null;
							e = dir_weighted.addEdge(node1, node2);
						    if (e != null) {
						    	dir_weighted.setEdgeWeight(e, edgeweight);
						    }
						}
					} else {
						System.out.println("Nicht valide Zeile: " + s);
					}
				}
				result = new ListenableDirectedWeightedGraph<String, DefaultWeightedEdge>(dir_weighted);
			} else if (!contains_once(formatted_edges, ":")) {
				DirectedPseudograph<String,DefaultEdge> dir = new DirectedPseudograph<>(DefaultEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						if (node1 != null && !dir.vertexSet().contains(node1)) {
							dir.addVertex(node1);
						}
						if (node2 != null && !dir.vertexSet().contains(node2)) {
							dir.addVertex(node2); 
						}						
						if (node1 != null && node2 != null) {
							dir.addEdge(node1, node2);							
						}
						Set<?> set = dir.edgeSet();
						System.out.println(set);
						
					} else {
						System.out.println("Nicht valide Zeile:" + s);
					}
				}
				
				result = new ListenableDirectedGraph<String, DefaultEdge>(dir);
			} 
		//Undirected Graph	
		} else if(contains_once(edges, "--")) {
			//Undirected weighted graph
			if (contains_once(formatted_edges, ":")) {
				WeightedPseudograph<String,DefaultWeightedEdge> undir_weighted = new WeightedPseudograph<>(DefaultWeightedEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						String edgename = m.group("edgename");
						int edgeweight = DEFAULT_EDGE_WEIGHT;	
						if (m.group("edgeweight") != null) {
							edgeweight = Integer.parseInt(m.group("edgeweight").replace(" ",""));
						}
						if (node1 != null) {
							undir_weighted.addVertex(node1);
						}
						if (node2 != null) {
							undir_weighted.addVertex(node2);
						}
						DefaultWeightedEdge e = null;
						e = undir_weighted.addEdge(node1, node2);
						undir_weighted.addEdge(node1, node2);
						if (e != null) {
							undir_weighted.setEdgeWeight(e, edgeweight);
						}
					} else {
						System.out.println("Nicht valide Zeile:" + s);
					}
				}
				Set<DefaultWeightedEdge> set = undir_weighted.edgeSet();
				DefaultWeightedEdge one_edge = set.iterator().next();
				double edge_weight = undir_weighted.getEdgeWeight(one_edge);
				System.out.println(edge_weight);
				System.out.println(set);
				result = new ListenableUndirectedWeightedGraph<String, DefaultWeightedEdge>(undir_weighted);
			} else if(!contains_once(formatted_edges, ":")) {
				//Undirected Graph
				Pseudograph<String,DefaultEdge> dir = new Pseudograph<>(DefaultEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						if (node1 != null) {
							dir.addVertex(node1);
						}
						if (node2 != null) {
							dir.addVertex(node2);
						}
						DefaultWeightedEdge e = null;
						dir.addEdge(node1, node2);
					} else {
						System.out.println("Nicht valide Zeile:" + s);
					}
				}
				result = new ListenableUndirectedGraph<String, DefaultEdge>(dir);
			}	
		} else {
			result = new SimpleGraph<>(DefaultEdge.class);
		}
		if(result == null) throw new NullPointerException();
		return result;
	}
	
	private ArrayList<String> format_edges(ArrayList<String> edges) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : edges) {
			String no_whitespace = s.replaceAll("\\s", "");
			//String formatted = no_whitespace.replace(";","");
			//result.add(formatted);
			result.add(no_whitespace);
		}
		return result;		
	}

	private boolean contains_once(ArrayList<String> edges, String sign) {
		if(edges == null) throw new NullPointerException();
		for(String edge : edges) {
			if(edge == null) throw new NullPointerException();
			if(edge.contains(sign))
				return true;
		}
		return false;
	}

}
