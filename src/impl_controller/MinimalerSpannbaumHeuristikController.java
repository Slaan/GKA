package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.MinimalSpannbaumHeuristik;
import interface_model.MinimalerSpannbaumHeuristikModel;
import interface_view.AlgorithmWeighWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;

public class MinimalerSpannbaumHeuristikController implements MinimalSpannbaumHeuristik {

	private		MinimalerSpannbaumHeuristikModel	_mshm;
	private		AlgorithmWeighWindow				_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static MinimalSpannbaumHeuristik create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new MinimalerSpannbaumHeuristikController(graph);
	}
	
	private MinimalerSpannbaumHeuristikController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_mshm = GKAModel.minimalerSpannbaumHeuristik(_graph);
		_aw = GKAView.algorithmWeighWindow("Edmond Karp");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				ArrayList<String> result = _mshm.start(source, target);
				_aw.setResultText("Max. flow: " + result.toString());
				_aw.setGraphAccesses(_mshm.getGraphAccesses(), _mshm.getTotalGraphAccesses());
				_aw.setTime(_mshm.getTime());
				_aw.setWeight(_mshm.getWeight());
			}
		});
		
	}
}
