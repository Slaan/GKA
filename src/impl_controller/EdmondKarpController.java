package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.EdmondKarp;
import interface_model.EdmondKarpModel;
import interface_view.AlgorithmWeighWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.Graph;

class EdmondKarpController implements EdmondKarp {
	
	private		EdmondKarpModel						_ekm;
	private		AlgorithmWeighWindow				_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static EdmondKarp create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new EdmondKarpController(graph);
	}
	
	private EdmondKarpController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_ekm = GKAModel.edmondKarp(_graph);
		_aw = GKAView.algorithmWeighWindow("Edmond Karp");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				Double result = _ekm.start(source, target);
				_aw.setResultText("Max. flow: " + result.toString());
				_aw.setGraphAccesses(_ekm.getGraphAccesses(), _ekm.getTotalGraphAccesses());
				_aw.setTime(_ekm.getTime());
			}
		});
		
	}
}
