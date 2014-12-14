package impl_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.MinimalSpannbaumHeuristik;
import interface_controller.NaechstgelegnerKnotenAlg;
import interface_model.MinimalerSpannbaumHeuristikModel;
import interface_model.NaechstgelegnerKnotenAlgModel;
import interface_view.AlgorithmWeighWindow;

public class NaechstgelegnerKnotenAlgController implements NaechstgelegnerKnotenAlg {

	private		NaechstgelegnerKnotenAlgModel		_nkm;
	private		AlgorithmWeighWindow				_aw;
	private 	Graph<String, NamedWeightedEdge>	_graph;
	
	// Creation
	public static NaechstgelegnerKnotenAlg create(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return new NaechstgelegnerKnotenAlgController(graph);
	}
	
	private NaechstgelegnerKnotenAlgController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
		_nkm = GKAModel.naechstgelegnerKnotenAlg(_graph);
		_aw = GKAView.algorithmWeighWindow("Nächstgelegener Knoten Algorithmus");
		_aw.addStartButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String source = _aw.getStartVertex();
				String target = _aw.getTargetVertex();
				ArrayList<String> result = _nkm.start(source, target);
				_aw.setResultText("Max. flow: " + result.toString());
				_aw.setGraphAccesses(_nkm.getGraphAccesses(), _nkm.getTotalGraphAccesses());
				_aw.setTime(_nkm.getTime());
				_aw.setWeight(_nkm.getWeight());
			}
		});
		
	}

}
