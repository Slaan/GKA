package impl_model;

import static impl_model.GraphFunctions.incomingEdgesOf;
import static impl_model.GraphFunctions.outGoingEdgesOf;
import interface_model.EdmondKarpModel;
import interface_model.FordFulkersonModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graph;

class FordImpl implements FordFulkersonModel {
	
	private Graph<String, NamedWeightedEdge> 					_graph;
	private Map<NamedWeightedEdge,Double>						_flow;
	private Map<NamedWeightedEdge,Boolean>						_edgeDirection; //true = forward, false = backward
	private long												_time;
	private double												_maxFlow;
	private Map<NamedWeightedEdge,NamedWeightedEdge> 			_pred;
	

	public static FordFulkersonModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FordImpl(graph);
	}
	
	private FordImpl(Graph<String, NamedWeightedEdge> graph) {
//		if (!(graph instanceof DirectedWeightedPseudograph<?, ?>)) {
//			throw new IllegalArgumentException();
//		}
		_graph = graph;
		
	}
	

	@Override
	public double start(String source, String target) {
		long startTime = System.nanoTime();
		ArrayList<NamedWeightedEdge> way = new ArrayList<>();
		Set<NamedWeightedEdge> all_edges = new HashSet<>();
		all_edges = _graph.edgeSet();
		_flow = new HashMap<NamedWeightedEdge, Double>();
		_edgeDirection = new HashMap<NamedWeightedEdge, Boolean>();
		_pred = new HashMap<NamedWeightedEdge,NamedWeightedEdge>();
		_maxFlow = 0.0;
		//initialisierung des flows
		for (NamedWeightedEdge e : all_edges) {
			_flow.put(e, 0.0);
		}
		way = getWay(source,target);
		while (!way.isEmpty()) {
			updateFlow(way);
			way = getWay(source,target);
		}
		long endTime = System.nanoTime();
		_time = (endTime - startTime);
		System.out.println(_maxFlow);
		System.out.println(_time);
		return _maxFlow;
	}

	private void updateFlow(ArrayList<NamedWeightedEdge> way) {
		Double localFlow;
		Set<Double> currentFlows = new HashSet<>();
		for (NamedWeightedEdge e : way) {
			Double capacity = e.getthisWeight();
			Double curFlow = _flow.get(e);
			if(_edgeDirection.get(e)) {
				currentFlows.add(capacity-curFlow);
			} else {
				currentFlows.add(curFlow);
			}
		}
		localFlow = getMinimum(currentFlows);
		for (NamedWeightedEdge e : way) {
			Double capacity = e.getthisWeight();
			Double curFlow = _flow.get(e);
			if(_edgeDirection.get(e)) {
				_flow.put(e, curFlow+localFlow);
			} else {
				_flow.put(e, curFlow-localFlow);
			}
			//Konsistenzpr??fung
			if (!((0.0 <= _flow.get(e)) && (_flow.get(e) <= capacity))) {
				try {
					throw new Exception();
				} catch (Exception e1) {
					System.out.println("Flow im inkonsistentem Zustand, Edge: " + e + "aktueller Flow:"
										+ _flow.get(e));
				}
			}
		}
		_maxFlow += localFlow;
	}

	private Double getMinimum(Set<Double> currentFlows) {
		Double result = Double.MAX_VALUE;
		for (Double d : currentFlows) {
			if (d<result) {
				result = d;
			}
		}
		return result;
	}

	private ArrayList<NamedWeightedEdge> getWay(String source, String target) {
		ArrayList<NamedWeightedEdge> result = new ArrayList<NamedWeightedEdge>();
		Stack<NamedWeightedEdge> stackEdges = new Stack<>();
		String currentNode = source;
		NamedWeightedEdge lastEdge = null;
		_edgeDirection.clear();
		
		for (NamedWeightedEdge e : outGoingEdgesOf(_graph,currentNode)) {
			if (e.getthisWeight() > _flow.get(e)) {
				stackEdges.push(e);
				_edgeDirection.put(e, true);
			}
		}
		for (NamedWeightedEdge e : incomingEdgesOf(_graph,currentNode)) {
			if (_flow.get(e)>0.0) {
				stackEdges.push(e);
				_edgeDirection.put(e, false);
			}
		}		
		while(!stackEdges.isEmpty()) {
			NamedWeightedEdge curEdge = stackEdges.pop();
			
			if (_edgeDirection.get(curEdge)) {
				currentNode = _graph.getEdgeTarget(curEdge);
				if (currentNode.equals(target)) {
					lastEdge = curEdge;
					break;
				}
			} else {
				currentNode = _graph.getEdgeSource(curEdge);
			}
			
			for (NamedWeightedEdge e : outGoingEdgesOf(_graph,currentNode)) {
				if (!(_edgeDirection.containsKey(e)) && e.getthisWeight() > _flow.get(e)) {
					stackEdges.push(e);
					_edgeDirection.put(e, true);
					_pred.put(e, curEdge);
				}
			}
			for (NamedWeightedEdge e : incomingEdgesOf(_graph,currentNode)) {
				if (!(_edgeDirection.containsKey(e)) && _flow.get(e)>0.0) {
					stackEdges.push(e);
					_edgeDirection.put(e, false);
					_pred.put(e, curEdge);
				}
			}
		}
		NamedWeightedEdge currentEdge = lastEdge;
		if (currentEdge==null) {
			return result;
		}
		result.add(currentEdge);
		while (!(_graph.getEdgeSource(currentEdge)).equals(source)) {
			currentEdge = _pred.get(currentEdge);
			result.add(currentEdge);
		}
		Collections.reverse(result);
		return result;
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
