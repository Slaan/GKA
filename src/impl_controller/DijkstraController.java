package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.Dijkstra;
import interface_model.DijkstraModel;
import interface_view.AlgorithmWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;

class DijkstraController implements Dijkstra {

	private		DijkstraModel						_dm;
	private		AlgorithmWindow						_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static Dijkstra create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new DijkstraController(graph);
	}
	
	private DijkstraController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_dm = GKAModel.dijkstra(_graph);
		_aw = GKAView.algorithmWindow("Dijkstra");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				ArrayList<String> result = _dm.start(source, target);
				_aw.setResultText(result.toString());
				_aw.setGraphAccesses(_dm.getGraphAccesses());
			}
		});
	}
	
}
