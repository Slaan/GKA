package impl_model;

import interface_model.NaechstgelegnerKnotenAlgModel;

import java.util.ArrayList;
import java.util.Set;

import org.jgrapht.Graph;

public class NaechstgelegnerKnotenAlgModelImpl implements NaechstgelegnerKnotenAlgModel {

	private Graph<String,NamedWeightedEdge>  				_graph;
	private Double											_totalWeight;
	private ArrayList<String>								_visited_vertexes;
	private String											_start;
	private long											_time;
	
	public NaechstgelegnerKnotenAlgModelImpl(
			Graph<String, NamedWeightedEdge> graph) {
		_graph = graph;
	}

	public static NaechstgelegnerKnotenAlgModelImpl create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new NaechstgelegnerKnotenAlgModelImpl(graph);
	}
	
	@Override
	public ArrayList<String> start(String source, String target) {
		long startTime = System.nanoTime();
		_start = source;
		if(is_complete_graph(_graph)) {
			System.out.println("Vollständiger Graph");
		} else {
			System.out.println("Kein vollständiger Graph");
		}
		_totalWeight=0.0;
		Set<String> unvisisted_vertexes = _graph.vertexSet();
		ArrayList<String> unvisited_nodes = new ArrayList<>();
		_visited_vertexes = new ArrayList<>();
		String current_vertex= source;
		for (String s : unvisisted_vertexes) {
			unvisited_nodes.add(s);
		}
		_visited_vertexes.add(current_vertex);
		while(!unvisited_nodes.isEmpty()) {
			String new_vertex = get_closest_neightbour(current_vertex);
			_visited_vertexes.add(new_vertex);
			unvisited_nodes.remove(new_vertex);
			current_vertex = new_vertex;
		}
		long endTime = System.nanoTime();
		_time = endTime-startTime;
		return _visited_vertexes;
	}

	private String get_closest_neightbour(String current_vertex) {
		
		Set<NamedWeightedEdge> edges = _graph.edgesOf(current_vertex);
		Double current_highest_weight = Double.MAX_VALUE;
		NamedWeightedEdge shortest_edge = null;
		String result;
		for(NamedWeightedEdge e : edges) {
			if ((e.getthisWeight()<current_highest_weight) && 
				((!_visited_vertexes.contains(e.getSource())) ||
				(!_visited_vertexes.contains(e.getTarget())))) {
				shortest_edge = e;
				current_highest_weight = e.getthisWeight();
			}
		}
		if (!(shortest_edge==null)) {
			if (shortest_edge.getSource().equals(current_vertex)) {
				result = (String) shortest_edge.getTarget();
			} else {
				result = (String) shortest_edge.getSource();
			}
			_totalWeight += shortest_edge.getthisWeight();
		} else {
			result = null;
			_totalWeight += _graph.getEdge(current_vertex, _start).getthisWeight();
			result = _start;
		}
		
		return result;
	}

	private boolean is_complete_graph(Graph<String, NamedWeightedEdge> graph) {
		
		Set<String> vertexes = graph.vertexSet();
		Integer no_vertexes = vertexes.size();
		boolean result = true;
		for (String v : vertexes) {
			result = (result&&((no_vertexes-1)==(graph.edgesOf(v).size())));
		}
		
		return result;
	}
	
	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return _totalWeight;
	}
	
	@Override
	public int getGraphAccesses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalGraphAccesses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTime() {
		
		return _time;
	}

}
