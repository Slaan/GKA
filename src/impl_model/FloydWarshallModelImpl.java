package impl_model;

import interface_model.FloydWarshallModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

class FloydWarshallModelImpl implements FloydWarshallModel {

	private Integer							_total_graph_accesses = 0;
	private	Integer							_graph_accesses = 0;
	private	Double							_weight = 0.0;
	private Map<String, Integer> 			_vertexindex;
	private ArrayList<ArrayList<Double>> 	_distance_matrix;
	private ArrayList<ArrayList<Integer>> 	_transit_matrix;

	// Creation
	public static FloydWarshallModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FloydWarshallModelImpl(graph);
	}
	
	private FloydWarshallModelImpl(Graph<String, NamedWeightedEdge> graph) {
		_vertexindex = new HashMap<>();
		// vertex to index 
		int v = 0;
		for(String vertex : graph.vertexSet()) {
			_graph_accesses++;
			_vertexindex.put(vertex, v);
			v++;
		}
		_distance_matrix = new ArrayList<>();
		// set all fields to zero or inifinty
		_graph_accesses++;
		for(int i = 0; i < graph.vertexSet().size(); i++) {
			_graph_accesses++;
			ArrayList<Double> trans = new ArrayList<>();
			for(int j = 0; j < graph.vertexSet().size(); j++) {
				if(i == j) {
					trans.add(j, 0.0);
				} else {
					trans.add(j, Double.POSITIVE_INFINITY);
				}
			}
			_distance_matrix.add(i, trans);
		}

		for(NamedWeightedEdge edge : graph.edgeSet()) {
			String source = graph.getEdgeSource(edge);
			String target = graph.getEdgeTarget(edge);
			_graph_accesses++;
			setDistanceValue(source, target, edge.getthisWeight());
			if(graph instanceof UndirectedGraph) {
				// Differentiate between undirected and directed graphs
				// so we add edges into both directions for undirected
				setDistanceValue(target, source, edge.getthisWeight());
			}
		}			
		_transit_matrix = new ArrayList<>();
		// build transitmatrix
		for(String source : graph.vertexSet()) {
			_graph_accesses++;
			ArrayList<Integer> tran = new ArrayList<>();
			for(String target : graph.vertexSet()) {
				_graph_accesses++;
				tran.add(_vertexindex.get(target), 0);
			}
			_transit_matrix.add(_vertexindex.get(source), tran);
		}
		// iteration
		for(String j : graph.vertexSet()) {
			_graph_accesses++;
			for(String i : graph.vertexSet()) {
				_graph_accesses++;
				if(!(i.equals(j))) {
					for(String k : graph.vertexSet()) {
						_graph_accesses++;
						if(!(k.equals(j))) {
							Double ikweight = getDistanceValue(i, k);
							Double ijweight = getDistanceValue(i, j);
							Double jkweight = getDistanceValue(j, k);
							Double sum = jkweight + ijweight;
							if(sum < ikweight) {
								setDistanceValue(i, k, sum);
								setTransitValue(i, k, _vertexindex.get(j)+1);
							}
						}
					}					
				}
			}
		}
	}
	
	@Override
	public ArrayList<String> start(String source, String target) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		_total_graph_accesses += _graph_accesses;
		_graph_accesses = 0;
		ArrayList<String> accu = new ArrayList<>();
		if(0 == getTransitValue(source, target)) {
			accu.addAll(Arrays.asList(source, target));
			return accu;
		}
		String between = indexToVertex(getTransitValue(source, target));
		accu.addAll(start(source, between));
		// remove last item
		accu.remove(accu.size()-1);
		accu.addAll(start(between, target));
		return accu;
	}

	@Override
	public int getGraphAccesses() {
		return _graph_accesses;
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String indexToVertex(Integer i) {
		if(i == null) throw new NullPointerException();
		for(Map.Entry<String, Integer> kvp : _vertexindex.entrySet()) {
			// we need to subtract because of the 
			// index <-> vertex indexing starts with 0
			if(kvp.getValue() == i-1) {
				return kvp.getKey();
			}
		}
		return null;
	}
	
	@Override
	public Double getDistanceValue(String source, String target) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		Integer source_index = _vertexindex.get(source);
		Integer target_index = _vertexindex.get(target);
		ArrayList<Double> targets = _distance_matrix.get(source_index);
		return targets.get(target_index);
	}

	@Override
	public Integer getTransitValue(String source, String target) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		return _transit_matrix.get(_vertexindex.get(source)).get(_vertexindex.get(target));
	}

	@Override
	public void setTransitValue(String source, String target, Integer value) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		if(value == null) throw new NullPointerException();
		int source_index = _vertexindex.get(source);
		int target_index = _vertexindex.get(target);
		ArrayList<Integer> tran = _transit_matrix.get(source_index);
		tran.set(target_index, value);
		_transit_matrix.set(source_index, tran);
	}

	@Override
	public void setDistanceValue(String source, String target, Double value) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		if(value == null) throw new NullPointerException();
		int source_index = _vertexindex.get(source);
		int target_index = _vertexindex.get(target);
		ArrayList<Double> targets = _distance_matrix.get(source_index);
		targets.set(target_index, value);
		_distance_matrix.set(source_index, targets);
	}

	@Override
	public Map<String, Integer> getIndexMap() {
		return _vertexindex;
	}

	@Override
	public ArrayList<ArrayList<Double>> getDistanceMatrix() {
		return _distance_matrix;
	}

	@Override
	public ArrayList<ArrayList<Integer>> getTransitMatrix() {
		return _transit_matrix;
	}

	@Override
	public int getTotalGraphAccesses() {
		return _total_graph_accesses;
	}

	@Override
	public double getWeight() {
		return _weight;
	}


}
