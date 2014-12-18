package impl_model;

import interface_model.HierholzerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

class HierholzerModelImpl implements HierholzerModel {

	private Graph<String, NamedWeightedEdge> _graph;
	private Set<NamedWeightedEdge>			 _touched_edges;
	private Double  						 _weight = 0.0;
	private double 							 _time = 0.0;
	private int 							 _graph_accesses = 0;
	
	public static HierholzerModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new IllegalArgumentException();
		return new HierholzerModelImpl(graph);
	}
	
	private HierholzerModelImpl(Graph<String, NamedWeightedEdge> graph) {
		_graph = graph;
		checkEvenNodes();
	}

	@Override
	public double getWeight() {
		return _weight;
	}

	private void checkEvenNodes() {
		for(String vertex : _graph.vertexSet()) {
			if(_graph.edgesOf(vertex).size() % 2 == 1) {
				throw new IllegalArgumentException("All nodes need to be even!");
			}
		}
	}
	
	@Override
	public ArrayList<String> start(String source, String target) {
		if(source == null) throw new IllegalArgumentException("source is null");
		if(target == null) throw new IllegalArgumentException("target is null");
		if(!source.equals(target)) throw new IllegalArgumentException("source has to be target");
		_graph_accesses = 0;
		_time = System.nanoTime();
		_touched_edges = new HashSet<>();
		ArrayList<ArrayList<String>> cycles = new ArrayList<>();
		cycles.add(findCycle(source));
		int i = 0;
		while(!_touched_edges.equals(_graph.edgeSet())) {
			_graph_accesses++;
			for(String vertex : cycles.get(i)) {
				if(incidentUntouchedFrom(vertex).size() > 0) {
					ArrayList<String> new_cycle = findCycle(vertex);
					cycles.add(new_cycle);
				}
			}
			i++;
		}
		_time = System.nanoTime() - _time;
		return cyclesToEuler(cycles);
	}
	
	private ArrayList<String> cyclesToEuler(ArrayList<ArrayList<String>> cycles) {
		ArrayList<String> accu = new ArrayList<>();
		Iterator<ArrayList<String>> i = cycles.iterator();
		accu.addAll(i.next());
		while(i.hasNext()) {
			ArrayList<String> cycle = i.next();
			for(Iterator<String> ai = accu.iterator(); ai.hasNext(); ) {
				String vertex_in_accu = ai.next();
				// cycle begins with an array in  
				if(cycle.get(0).equals(vertex_in_accu)) {
					accu = replace(accu, cycle);
					break; // performance break
				}
			}
		}
		return accu;
	}
	
	private ArrayList<String> replace(List<String> list, List<String> list2) {
		int first = list.indexOf(list2.get(0));
		ArrayList<String> accu = new ArrayList<>(list.subList(0, first));
		accu.addAll(list2);
		List<String> sub = list.subList(first+1, list.size());
		accu.addAll(sub);
		return accu;
	}
	
	private ArrayList<String> findCycle(String source) {
		String current = source;
		ArrayList<String> accu = new ArrayList<>();
		do {
			Set<NamedWeightedEdge> incident = incidentUntouchedFrom(current);
			if(incident.size() > 0) {
				NamedWeightedEdge untouched_edge = getAnyOf(incident);
				accu.add(current);
				current = getOtherNode(current, untouched_edge);
				_touched_edges.add(untouched_edge);
			}
		} while(!current.equals(source));
		accu.add(source);
		return accu;
	}
	
	private Set<NamedWeightedEdge> incidentUntouchedFrom(String vertex) {
		Set<NamedWeightedEdge> edges = new HashSet<>(_graph.edgesOf(vertex));
		edges.removeAll(_touched_edges);
		return edges;
	}
	
	private NamedWeightedEdge getAnyOf(Set<NamedWeightedEdge> set) {
		Iterator<NamedWeightedEdge> i = set.iterator();
		if(!i.hasNext())
			throw new Error("Unexpected Error!");
		return i.next();
	}
	
	private String getOtherNode(String node, NamedWeightedEdge edge) {
		String source = _graph.getEdgeSource(edge);
		String target = _graph.getEdgeTarget(edge);
		_graph_accesses++;
		if(node.equals(source)) {
			return target;
		} else if(node.equals(target)) {
			return source;
		} else {
			throw new Error("Unexpected Error!");
		} 
	}
	
	@Override
	public int getGraphAccesses() {
		return _graph_accesses;
	}

	@Override
	public int getTotalGraphAccesses() {
		return 0;
	}

	@Override
	public double getTime() {
		return _time;
	}
	
	public static void main(String[] args) {
		{	
			Graph<String, NamedWeightedEdge> graph; 
			graph = new WeightedPseudograph<String, NamedWeightedEdge>(NamedWeightedEdge.class);
			for(String vertex : Arrays.asList("a", "b", "c", "d")) {
				graph.addVertex(vertex);
			}
			NamedWeightedEdge a_b = new NamedWeightedEdge();
			graph.addEdge("a", "b", a_b);
			NamedWeightedEdge b_c = new NamedWeightedEdge();
			graph.addEdge("b", "c", b_c);
			NamedWeightedEdge a_c = new NamedWeightedEdge();
			graph.addEdge("a", "c", a_c);
			NamedWeightedEdge c_d = new NamedWeightedEdge();
			graph.addEdge("c", "d", c_d);
			NamedWeightedEdge d_c = new NamedWeightedEdge();
			graph.addEdge("d", "c", d_c);
			HierholzerModel a = new HierholzerModelImpl(graph);
//			System.out.println(a.start("a", "a"));
			
		}
//		System.out.println("####################");
		{		
			Graph<String, NamedWeightedEdge> g2;
			g2 = new WeightedPseudograph<>(NamedWeightedEdge.class);
			for(String vertex : Arrays.asList("a", "b", "c", "d", "e", "g")) {
				g2.addVertex(vertex);
			}
			NamedWeightedEdge a_b = new NamedWeightedEdge();
			g2.addEdge("a", "b", a_b);
			NamedWeightedEdge a_c = new NamedWeightedEdge();
			g2.addEdge("a", "c", a_c);
			NamedWeightedEdge b_c = new NamedWeightedEdge();
			g2.addEdge("b", "c", b_c);
			NamedWeightedEdge c_d = new NamedWeightedEdge();
			g2.addEdge("c", "d", c_d);
			NamedWeightedEdge d_c = new NamedWeightedEdge();
			g2.addEdge("d", "c", d_c);
			NamedWeightedEdge d_g = new NamedWeightedEdge();
			g2.addEdge("d", "g", d_g);
			NamedWeightedEdge g_d = new NamedWeightedEdge();
			g2.addEdge("g", "d", g_d);
			NamedWeightedEdge a_e = new NamedWeightedEdge();
			g2.addEdge("a", "e", a_e);
			NamedWeightedEdge e_a = new NamedWeightedEdge();
			g2.addEdge("e", "a", e_a);
			HierholzerModel a = new HierholzerModelImpl(g2);
//			System.out.println(a.start("a", "a"));
		}
	}
}
