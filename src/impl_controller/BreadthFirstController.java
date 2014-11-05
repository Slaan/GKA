package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.BreadthFirst;
import interface_model.BreadthFirstModel;
import interface_view.AlgorithmWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;

class BreadthFirstController implements BreadthFirst {

	private		BreadthFirstModel 					_bfsm;
	private		AlgorithmWindow						_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static BreadthFirst create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new BreadthFirstController(graph);
	}
	
	private BreadthFirstController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_bfsm = GKAModel.breadthFirst(_graph);
		_aw = GKAView.algorithmWindow("Breadth First");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				ArrayList<String> result = _bfsm.start(source, target);
				_aw.setResultText(result.toString());
				_aw.setGraphAccesses(_bfsm.getGraphAccesses(), _bfsm.getTotalGraphAccesses());
			}
		});
	}
	
}
