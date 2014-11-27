package impl_model;

import static impl_model.GraphFunctions.incomingEdgesOf;
import static impl_model.GraphFunctions.outGoingEdgesOf;
import interface_model.FordFulkersonModel;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

class Marking {
	
	// pos is true, when we came normally
	// from edge. when negative we came backwards.
	public Boolean	pos;
	
	// node we came from
	public String	previous;
	
	public Double 	flow;
	
	public String toString() {
		String p 	= (String) ((pos == null) ? "null" : pos.toString());
		String prev	= (String) ((previous == null) ? "null" : previous.toString());
		String f 	= (String) ((flow == null) ? "null" : flow.toString());
		return 	"{" + p + ", " + prev + ", " + f + "}"; 
	}
}

class FordFulkersonModelImpl implements FordFulkersonModel {
	
	private Graph<String, NamedWeightedEdge> _graph;
	private Map<NamedWeightedEdge, Double>	 _edge_flow;
	private Map<String, Marking>			 _marking;
	private	Set<String>						 _inspected_vertexes;
	private long							 _time 		= 0;

	public static FordFulkersonModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FordFulkersonModelImpl(graph);
	}
	
	private FordFulkersonModelImpl(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		if(graph instanceof UndirectedGraph) throw new IllegalArgumentException("Undireced Graphs are not allowed");
		_graph 				= graph;
	}
	
	private void init() {
		_inspected_vertexes = new HashSet<>();
		_edge_flow			= new HashMap<>();
		_marking 			= new HashMap<>();
	}
	private void initEdgeFlow() {
		for(NamedWeightedEdge edge : _graph.edgeSet()) {
			_edge_flow.put(edge, 0.0);
		}
	}
	
	/**
	 * 
	 * @param vertex
	 * @return current delta (flow) on vertex
	 */
	private Double deltaOf(String vertex) {
		Marking a = _marking.get(vertex);
		return a.flow;
	}
	
	/**
	 * Check if vertex is true
	 * @param vertex
	 * @return true when is marked
	 */
	private Boolean isMarked(String vertex) {
		return _marking.keySet().contains(vertex);
	}
	
	/**
	 * Get random vertex which is marked but not inspected!
	 * @return
	 * @throws UnexpectedException 
	 * @throws IllegalAccessException 
	 */
	private String getRandom() throws IllegalAccessException {
		Set<String> marked = new HashSet<>();
		marked.addAll(_marking.keySet());
		marked.removeAll(_inspected_vertexes);
		Iterator<String> i = marked.iterator();
		if(!i.hasNext()) {
			throw new IllegalAccessException();
		}
		return i.next();
	}	
	
	/**
	 * Increases all used edges so far.
	 * @param vertex which is sink of network
	 */
	private void increaseEdgeFlow(String vertex, Double flow) {
		Marking m = _marking.get(vertex);
		if(m.previous == null) {
			// pred is null at start vertex
			return;
		}
		// probably buggy: 	we should ensure that the edge is
		//					the correct one.
		NamedWeightedEdge edge = _graph.getEdge(m.previous, vertex);
		if(m.pos) {
			// increase edge flow
			_edge_flow.put(edge, flow + _edge_flow.get(edge));			
		} else if(!m.pos) {
			// reduce edge flow
			_edge_flow.put(edge, _edge_flow.get(edge) - flow);
		}
		increaseEdgeFlow(m.previous, flow);
	}
	
	/**
	 * @see FordFulkersonModel
	 */
	@Override
	public double start(String source, String target) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		init();
		double d = 0.0;
		// 1) initialization
		initEdgeFlow();
		Marking q_mark = new Marking();
		q_mark.flow 	= Double.POSITIVE_INFINITY;
		q_mark.previous = null;
		q_mark.pos		= null;
		_marking.put(source, q_mark);
		// 2) inspect and mark
		while(!_inspected_vertexes.equals(_marking.keySet())) {
			String current;
			try {
				current = getRandom();
			} catch (Exception e) {
				e.printStackTrace();
				return -1.0;
			}
			_inspected_vertexes.add(current);
			Boolean sink_is_marked = false;
			// forward edge
			for(NamedWeightedEdge f_edge : outGoingEdgesOf(_graph, current)) {
				String edge_source = _graph.getEdgeSource(f_edge);
				String edge_target = _graph.getEdgeTarget(f_edge);
				Double c = f_edge.getthisWeight();
				if(!(isMarked(edge_target)) && _edge_flow.get(f_edge) < c) {
					Marking m = new Marking();
					m.pos 		= true;
					m.previous	= edge_source;
					m.flow 		= Math.min(c - _edge_flow.get(f_edge), deltaOf(edge_source));
					_marking.put(edge_target, m);
					if(edge_target.equals(target)) 
						sink_is_marked = true;
				}
			}
			// backward edge
			for(NamedWeightedEdge o_edge : incomingEdgesOf(_graph, current)) {
				String edge_source = _graph.getEdgeSource(o_edge);
				String edge_target = _graph.getEdgeTarget(o_edge);
				if(!(isMarked(edge_target)) && _edge_flow.get(o_edge) > 0.) {
					Marking m = new Marking();
					m.pos 		= false;
					m.previous 	= edge_target;
					m.flow		= Math.min(_edge_flow.get(o_edge), deltaOf(edge_source));
					_marking.put(edge_source, m);
					if(edge_source.equals(target)) 
						sink_is_marked = true;
				}
			}
			// 3) increase flow
			if(sink_is_marked) {
				Double increment = _marking.get(target).flow;
				increaseEdgeFlow(target, increment);
				d += increment;
				System.out.println("increment: " + increment);
				System.out.println("marking:   " + _marking);
				System.out.println("inspected: " + _inspected_vertexes);
				System.out.println("edge_flow: " + _edge_flow);
				_marking 			= new HashMap<>();
				_inspected_vertexes = new HashSet<>(); 
				// we've still q_mark from init
				_marking.put(source, q_mark);
			}
		}
		// 4) result
		return d;
	}

	/**
	 * 
	 * @see FordFulkersonModel
	 */
	@Override
	public int getGraphAccesses() {
		return 0;
	}

	@Override
	public int getTotalGraphAccesses() {
		return 0;
	}

	/**
	 * 
	 * @see FordFulkersonModel
	 */
	@Override
	public double getTime() {
		return _time;
	}

}
