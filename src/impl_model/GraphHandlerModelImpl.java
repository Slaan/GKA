package impl_model;

import impl_controller.GKA;
import interface_controller.FileHandler;
import interface_model.GraphHandlerModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

class GraphHandlerModelImpl implements GraphHandlerModel {

	private final 		int 							DEFAULT_EDGE_WEIGHT = 1; 
	private 			FileHandler						_file_handler;
	private				ListenableGraph<String, ?> 		_graph;
	
	// Creation
	public static GraphHandlerModel create() {
		return new GraphHandlerModelImpl();
	}
	
	private GraphHandlerModelImpl() {
		_file_handler = GKA.fileHandler();
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		// 1) get type (directed/undirected) and set symbol (<- / --)
		// 2) is graph weighted? 
		// 3) is the edgename empty? 
		// 		yes -> skip brackets ; no -> add brackets and name
		// 4) is the edge a loop? 
		// 		print loops as "a -- a" or "a -> a"
		
	}

	/*
	 * (non-Javadoc)
	 * Gets a ArrayList<String> terminating with a semicolon
	 * and converts it into a ListenableDirctedGraph or
	 * ListenableUndirectedGraph
	 */
	
	@Override
	public void load() throws IOException {
		_file_handler.load();
		ArrayList<String> edges = _file_handler.get_content();
		ArrayList<String> formatted_edges = format_edges(edges);
		// Parse to ListenableGraph
		String sz = "[^\\-><:()]"; // Sonderzeichen
		Pattern reg = Pattern.compile("(?<v1>"+sz+"*)((?<richtung>[<-][->])(?<v2>"+sz+"*)"
				+ "(\\((?<edgename>"+sz+"*)\\))?(:(?<edgeweight>"+sz+"*))?)?;");
		System.out.println(formatted_edges);
		//Directed Graph
		if(contains_once(edges, "->")) {
					
			//Directed weighted graph
			if (contains_once(formatted_edges, ":")) {
				DirectedWeightedPseudograph<String,NamedWeightedEdge> dir_weighted = new DirectedWeightedPseudograph<>(NamedWeightedEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						String direction = m.group("richtung");
						String edgename = m.group("edgename");
						int edgeweight = DEFAULT_EDGE_WEIGHT;
						if (m.group("edgeweight") != null) {
							edgeweight = Integer.parseInt(m.group("edgeweight"));
						}
						if (node1 != null) {
							dir_weighted.addVertex(node1);
						}
						if (node2 != null) {
							dir_weighted.addVertex(node2);
						}
						NamedWeightedEdge e = null;
						e = dir_weighted.addEdge(node1, node2);
						dir_weighted.addEdge(node1, node2);
						if (e != null) {
							dir_weighted.setEdgeWeight(e, edgeweight);
						}
					} else {
						System.out.println("Nicht valide Zeile: " + s);
					}
				}
				ListenableGraph<String, NamedWeightedEdge> result = new ListenableDirectedWeightedGraph<String, NamedWeightedEdge>(dir_weighted);
				set_graph(result);
			}
			//Directed Graph
			else if (!contains_once(formatted_edges, ":")) {
				DirectedPseudograph<String,DefaultEdge> dir = new DirectedPseudograph<>(DefaultEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						String direction = m.group("richtung");
						if (node1 != null) {
							dir.addVertex(node1);
						}
						if (node2 != null) {
							dir.addVertex(node2);
						}
						NamedWeightedEdge e = null;
						if (node1 != null && node2 != null) {
							dir.addEdge(node1, node2);
						}
					}
					else {
						System.out.println("Nicht valide Zeile:" + s);
					}
				}
				ListenableGraph<String, DefaultEdge> result = new ListenableDirectedGraph<String, DefaultEdge>(dir);
				set_graph(result);	
			}
		//Undirected Graph	
		} else if(contains_once(edges, "--")) {
			//Undirected weighted graph
			if (contains_once(formatted_edges, ":")) {
				WeightedPseudograph<String,NamedWeightedEdge> undidr_weighted = new WeightedPseudograph<>(NamedWeightedEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						String direction = m.group("richtung");
						String edgename = m.group("edgename");
						int edgeweight = DEFAULT_EDGE_WEIGHT;						
						if (m.group("edgeweight") != null) {
							//String t = m.group("edgeweight").replace(" ", "");
							edgeweight = Integer.parseInt(m.group("edgeweight").replace(" ",""));
						}
						System.out.println("Node1:"+node1+"Node2"+node2);
						if (node1 != null) {
							undidr_weighted.addVertex(node1);
						}
						if (node2 != null) {
							undidr_weighted.addVertex(node2);
						}
						NamedWeightedEdge e = null;
						e = undidr_weighted.addEdge(node1, node2);
						undidr_weighted.addEdge(node1, node2);
						if (e != null) {
							undidr_weighted.setEdgeWeight(e, edgeweight);
						}
					} else {
						System.out.println("Nicht valide Zeile:" + s);
					}
				}
				ListenableGraph<String, NamedWeightedEdge> result = new ListenableUndirectedWeightedGraph<String, NamedWeightedEdge>(undidr_weighted);
				set_graph(result);
			}
			//Undirected Graph
			else if(!contains_once(formatted_edges, ":")) {
				Pseudograph<String,DefaultEdge> dir = new Pseudograph<>(DefaultEdge.class);
				for (String s : edges) {
					Matcher m = reg.matcher(s);
					if (m.matches()) {
						String node1 = m.group("v1");
						String node2 = m.group("v2");
						String direction = m.group("richtung");
						if (node1 != null) {
							dir.addVertex(node1);
						}
						if (node2 != null) {
							dir.addVertex(node2);
						}
						NamedWeightedEdge e = null;
						dir.addEdge(node1, node2);
					} else {
						System.out.println("Nicht valide Zeile:" + s);
					}
				}
				ListenableGraph<String, DefaultEdge> result = new ListenableUndirectedGraph<String, DefaultEdge>(dir);
				set_graph(result);	
			}	
		} 		
	}
	
	private ArrayList<String> format_edges(ArrayList<String> edges) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : edges) {
			String no_whitespace = s.replaceAll("\\s", "");
			String formatted = no_whitespace.replace(";","");
			result.add(formatted);
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

	@Override
	public void set_graph(ListenableGraph graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
	}

	@Override
	public ListenableGraph get_graph() {
		if(_graph == null) throw new NullPointerException("Intern graph is null, load or set a graph.");
		return _graph;
	}

	@Override
	public String get_path() {
		if(_file_handler == null) throw new NullPointerException();
		return _file_handler.get_path();
	}

}
