package impl_model;

import interface_model.FordFulkersonModel;
import static impl_model.GraphFunctions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graph;

public class FordFulkerImpl implements FordFulkersonModel {

	public 	Graph<String, NamedWeightedEdge> _graph;
	public 	Map<String, Marking> 			_marking;
	public	Map<NamedWeightedEdge, Double>	_edge_flow;
	public 	Stack<String>					_to_inspect;
	public	Set<String>						_inspected;
	
	public static FordFulkersonModel create(Graph<String, NamedWeightedEdge> graph) {
		return new FordFulkerImpl(graph);
	}
	
	public FordFulkerImpl(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
	}
	
	private void init() {
		_marking 	= new HashMap<>();
		_edge_flow	= new HashMap<>();
		_inspected	= new HashSet<>();
		_to_inspect = new Stack<>();
	}
	
	private void initEdgeFlow() {
		for(NamedWeightedEdge edge : _graph.edgeSet()) {
			_edge_flow.put(edge, 0.0);
		}
	}
	
	private Boolean isMarked(String vertex) {
		if(vertex == null) throw new NullPointerException();
		return _to_inspect.contains(vertex);
	}
	
	@Override
	public double start(String source, String target) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		init();
		// 1) init
		initEdgeFlow();
		Marking q_mark = new Marking();
		q_mark.flow 	= Double.POSITIVE_INFINITY;
		q_mark.previous	= null;
		q_mark.pos		= null;
		_marking.put(source, q_mark);
		_to_inspect.push(source);
		// 2) inspection and marking
		while(!_to_inspect.isEmpty()) {
			String current = _to_inspect.pop();
			for(NamedWeightedEdge edge : _graph.edgeSet()) {
				String edge_source = _graph.getEdgeSource(edge);
				String edge_target = _graph.getEdgeTarget(edge);
				if(!isMarked(edge_target) && outGoingEdgesOf(_graph, current).contains(edge) && _edge_flow.get(edge) < edge.getthisWeight()) {
					Marking m 	= new Marking();
					m.flow 		= Math.min(edge.getthisWeight() - _edge_flow.get(edge), _marking.get(current).flow);
					m.previous	= edge_target;
					m.pos		= true;
				} else if(!isMarked(edge_target) && incomingEdgesOf(_graph, current).contains(edge) && _edge_flow.get(edge) > 0.0) {
					Marking m 	= new Marking();
//					m.flow		= Math.min()
				}
			}
		}
		return 0.0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	
}
