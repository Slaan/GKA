package impl_controller;

import impl_model.GKAModel;
import impl_view.GKAView;
import interface_controller.BreadthFirst;
import interface_model.BreadthFirstModel;
import interface_view.BreadthFirstWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;

class BreadthFirstController implements BreadthFirst {

	private		BreadthFirstModel 	_bfsm;
	private		BreadthFirstWindow	_bfsw;
	private 	Graph	_graph;
	
	// Creation
	public static BreadthFirst create(Graph graph) {
		if(graph == null) throw new NullPointerException();
		return new BreadthFirstController(graph);
	}
	
	private BreadthFirstController(Graph graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_bfsm = GKAModel.breadthFirst(_graph);
		_bfsw = GKAView.breadthFirstWindow();
		_bfsw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _bfsw.getStartVertex();
				String target = _bfsw.getTargetVertex();
				ArrayList<String> result = _bfsm.breadthFirst(source, target);
				_bfsw.setResultText(result.toString());
			}
		});
	}
	
}
