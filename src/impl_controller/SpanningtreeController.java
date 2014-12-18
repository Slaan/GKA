package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_model.MinimalerSpannbaumModel;
import interface_view.DialogWindow;

import org.jgrapht.Graph;

class SpanningtreeController {
	
	private MinimalerSpannbaumModel _msm;
	private DialogWindow 			_dw;
	
	public SpanningtreeController(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new IllegalArgumentException();
		_msm 	= GKAModel.minimalerSpannbaum(graph);
		_dw 	= GKAView.dialogWindow("Spanning tree"); 
		double weight = _msm.getWeight();
		_dw.setText("Weight: " + 2*weight + " ( 2*" + weight + ")");
	}
}
