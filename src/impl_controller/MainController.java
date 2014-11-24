package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.Generator;
import interface_controller.GraphHandler;
import interface_model.GeneratorModel;
import interface_view.GeneratorWindow;
import interface_view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.Graph;

public class MainController {

	private static MainController _instance;
	private MainWindow _window;
	private GraphHandler _graphhandler;
	private Graph<String, NamedWeightedEdge> _graph;
	private	GeneratorModel					 _gm;
	private	GeneratorWindow					 _gw;

	// Creation
	public static MainController create() {
		if (_instance == null) {
			_instance = new MainController();
		}
		return _instance;
	}

	public MainController() {
		_window = GKAView.mainWindow();
		_graphhandler = GKA.graphHandler();
		_gm = GKAModel.generator();
		_gw = GKAView.generatorWindow();
		_gw.setInvisible();
		_gw.addGenerateListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent r) {
				int e = Integer.valueOf(_gw.getEdgeAmount());
				int v = Integer.valueOf(_gw.getVertexAmount());
				_graph = _gm.generateDirectedGraph(v, e);
				_window.setGraph(_graph);
				_gw.setInvisible();
			}
		});
		_window.addGenerateListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_gw.setVisible();
			}
		});
		_window.addLoadListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_graphhandler.load();
				_graph = _graphhandler.getGraph();
				_window.setPath(_graphhandler.getPath());
				_window.setGraph(_graph);
			}
		});
		_window.addSaveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_graphhandler.save(_graph);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		// Since algorithms don't change, we don't
		// need to store it as instance variable
		_window.addBreadthFirstListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GKA.breadthFirst(_graph);
			}
		});
		_window.addDijkstraListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GKA.dijkstra(_graph);
			}
		});
		_window.addFloydWarshallListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GKA.floydWarshall(_graph);
			}
		});
	}

	public static void main(String[] args) {
		MainController.create();
	}
}
