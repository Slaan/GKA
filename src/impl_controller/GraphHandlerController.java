package impl_controller;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_controller.FileHandler;
import interface_controller.GraphHandler;
import interface_model.GraphHandlerModel;
import interface_view.GeneratorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public class GraphHandlerController implements GraphHandler {

	private	GraphHandlerModel	_ghm;
	private	FileHandler			_fh;
	private	Graph				_graph;
	private	GeneratorWindow		_gw;
	
	// Creation
	public static GraphHandler create() {
		return new GraphHandlerController();
	}
	
	private GraphHandlerController() {
		// initiate graphs
		_fh  = GKA.fileHandler();
		_ghm = GKAModel.graphHandler();
	}

	@Override
	public void save(Graph<String, NamedWeightedEdge> graph) {
		System.out.println(graph);
		_graph = graph;
		ArrayList<String> graph_in_strings;
		graph_in_strings = _ghm.from_graph(_graph);
		System.out.println(graph_in_strings);
		_fh.save(graph_in_strings);
	}

	@Override
	public void load() {
		// open load dialog
		_fh.load();
		ArrayList<String> graph_in_strings; 
		graph_in_strings = _fh.get_content();
		// convert to graph
		_graph = _ghm.to_graph(graph_in_strings);
	}

	@Override
	public Graph<String, NamedWeightedEdge> getGraph() {
		if(_graph == null) throw new NullPointerException();
		return _graph;
	}

	@Override
	public String getPath() {
		return _fh.get_path();
	}

}
