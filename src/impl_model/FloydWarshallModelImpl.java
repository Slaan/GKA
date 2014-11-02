package impl_model;

import interface_model.FloydWarshallModel;

import java.util.ArrayList;

import org.jgrapht.Graph;

class FloydWarshallModelImpl implements FloydWarshallModel {

	// Creation
	public static FloydWarshallModel create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FloydWarshallModelImpl(graph);
	}
	
	private FloydWarshallModelImpl(Graph<String, NamedWeightedEdge> graph) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ArrayList<String> start(String source, String target) {
		if(source == null) throw new NullPointerException();
		if(target == null) throw new NullPointerException();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGraphAccesses() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
