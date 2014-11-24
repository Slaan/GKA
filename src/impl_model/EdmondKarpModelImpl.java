package impl_model;

import interface_model.EdmondKarpModel;

import org.jgrapht.Graph;

public class EdmondKarpModelImpl implements EdmondKarpModel {

	public static EdmondKarpModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new EdmondKarpModelImpl(graph);
	}
	
	private EdmondKarpModelImpl(Graph<String, NamedWeightedEdge> graph) {
		// TODO implement me
	}
	
	@Override
	public double start(String source, String target) {
		// TODO Auto-generated method stub
		return 0;
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
