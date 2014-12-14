package impl_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.Graph;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.Generator;
import interface_model.GeneratorModel;
import interface_view.GeneratorWindow;

class GeneratorController implements Generator {
	
	private Graph<String, NamedWeightedEdge> _graph;
	private	GeneratorModel					 _gm;
	private	GeneratorWindow					 _gw;
	
	public static Generator create() {
		return new GeneratorController();
	}
	
	private GeneratorController() {
		_gm = GKAModel.generator();
		_gw = GKAView.generatorWindow();
		_gw.setVisible();
		_gw.addGenerateListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent r) {
				int e = Integer.valueOf(_gw.getEdgeAmount());
				int v = Integer.valueOf(_gw.getVertexAmount());
				_graph = _gm.generateDirectedGraph(v, e);
			}
		});
	}
	
	@Override
	public Graph<String, NamedWeightedEdge> getGraph() {
		if(_graph == null) throw new NullPointerException("Generate graph first!");
		return _graph;
	}

	@Override
	public void generate() {
		_gw.setVisible();
	}

	@Override
	public void setGraphListener(ActionListener a) {
		_gw.addGenerateListener(a);
	}
}
