package impl_model;

import interface_model.AlgorithmModel;
import interface_model.DijkstraModel;

import java.util.ArrayList;

import org.jgrapht.Graph;

public class DijkstraModelImpl implements DijkstraModel {

	private		Integer		_graph_accesses = 0; 
	
	// Creation
	public static DijkstraModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new DijkstraModelImpl(graph);
	}
	
	private DijkstraModelImpl(Graph<String, NamedWeightedEdge> graph) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see AlgorithmModel
	 */
	@Override
	public ArrayList<String> start(String source, String target) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see AlgorithmModel
	 */
	@Override
	public int getGraphAccesses() {
		return _graph_accesses;
	}

	/**
	 * @see AlgorithmModel
	 */
	@Override
	public double getTime() {
		return 0;
	}
	
}
