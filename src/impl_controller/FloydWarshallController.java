package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.FloydWarshall;
import interface_model.FloydWarshallModel;
import interface_view.AlgorithmWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;

class FloydWarshallController implements FloydWarshall {

	private		FloydWarshallModel 					_fwm;
	private		AlgorithmWindow						_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static FloydWarshall create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new FloydWarshallController(graph);
	}
	
	private FloydWarshallController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_fwm = GKAModel.floydWarshall(_graph);
		_aw = GKAView.algorithmWindow("Floyd Warshall");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				ArrayList<String> result = _fwm.start(source, target);
				_aw.setResultText(result.toString());
				_aw.setGraphAccesses(_fwm.getGraphAccesses());
			}
		});
	}
	
}
