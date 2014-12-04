package impl_model;

import java.util.ArrayList;

import org.jgrapht.Graph;

import interface_model.FordFulkersonModel;
import interface_model.NaechstgelegnerKnotenAlgModel;

public class NaechstgelegnerKnotenAlgModelImpl implements NaechstgelegnerKnotenAlgModel {

	public NaechstgelegnerKnotenAlgModelImpl(
			Graph<String, NamedWeightedEdge> graph) {
		// TODO Auto-generated constructor stub
	}

	public static NaechstgelegnerKnotenAlgModelImpl create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new NaechstgelegnerKnotenAlgModelImpl(graph);
	}
	
	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> start(String source, String target) {
		// TODO Auto-generated method stub
		return null;
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
