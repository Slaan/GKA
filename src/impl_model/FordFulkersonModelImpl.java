package impl_model;

import interface_model.FordFulkersonModel;

import org.jgrapht.Graph;

import static impl_model.GraphFunctions.*;

class FordFulkersonModelImpl implements FordFulkersonModel {

	public static FordFulkersonModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FordFulkersonModelImpl(graph);
	}
	
	private FordFulkersonModelImpl(Graph<String, NamedWeightedEdge> graph) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @see FordFulkersonModel
	 */
	@Override
	public double start(String source, String target) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 * @see FordFulkersonModel
	 */
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

	/**
	 * 
	 * @see FordFulkersonModel
	 */
	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
