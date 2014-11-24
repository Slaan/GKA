package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.FordFulkerson;
import interface_model.FordFulkersonModel;
import interface_view.AlgorithmWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.Graph;

class FordFulkersonController implements FordFulkerson {
	
	private		FordFulkersonModel					_ffm;
	private		AlgorithmWindow						_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static FordFulkerson create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FordFulkersonController(graph);
	}
	
	private FordFulkersonController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_ffm = GKAModel.fordFulkerson(_graph);
		_aw = GKAView.algorithmWindow("Ford Fulkerson");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				Double result = _ffm.start(source, target);
				_aw.setResultText("Max. flow: " + result.toString());
				_aw.setGraphAccesses(_ffm.getGraphAccesses(), _ffm.getTotalGraphAccesses());
				_aw.setTime(_ffm.getTime());
			}
		});
	}
}
