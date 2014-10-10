package impl_controller;

import impl_model.GKAModel;
import interface_controller.FileHandler;
import interface_controller.GraphHandler;
import interface_model.GraphHandlerModel;

import java.util.ArrayList;

import org.jgrapht.Graph;

public class GraphHandlerController implements GraphHandler {

	private	GraphHandlerModel	_ghm;
	private	FileHandler			_fh;
	private	Graph				_graph;
	
	// Creation
	public static GraphHandler create() {
		return new GraphHandlerController();
	}
	
	private GraphHandlerController() {
		_fh = GKA.fileHandler();
		_ghm = GKAModel.graphHandler();
	}

	@Override
	public void save() {
		ArrayList<String> graph_in_strings;
		graph_in_strings = _ghm.from_graph(_graph);
		_fh.save(graph_in_strings);
	}

	@Override
	public Graph load() {
		// open load dialog
		_fh.load();
		ArrayList<String> graph_in_strings; 
		graph_in_strings = _fh.get_content();
		// convert to graph
		return _ghm.to_graph(graph_in_strings);
	}

	@Override
	public Graph getGraph() {
		if(_graph == null) throw new NullPointerException();
		return _graph;
	}
}
