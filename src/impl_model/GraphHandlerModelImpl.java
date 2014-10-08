package impl_model;

import impl_controller.GKA;
import interface_controller.FileHandler;
import interface_model.GraphHandlerModel;
import interface_model.NamedEdge;

import java.io.IOException;
import java.util.ArrayList;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;

class GraphHandlerModelImpl implements GraphHandlerModel {

	private FileHandler						_file_handler;
	private	ListenableGraph<String, ?> 		_graph;
	
	// Creation
	public static GraphHandlerModel create() {
		return new GraphHandlerModelImpl();
	}
	
	private GraphHandlerModelImpl() {
		_file_handler = GKA.fileHandler();
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		// 1) get type (directed/undirected) and set symbol (<- / --)
		// 2) is graph weighted? 
		// 3) is the edgename empty? 
		// 		yes -> skip brackets ; no -> add brackets and name
		// 4) is the edge a loop? 
		// 		print loops as "a -- a" or "a -> a"
		
	}

	@Override
	public void load() throws IOException {
		_file_handler.load();
		ArrayList<String> edges = _file_handler.get_content();
		// Parse to ListenableGraph
		if(contains_once(edges, "->")) {
			DirectedMultigraph<String, NamedEdge> accu;
		} else if(contains_once(edges, "--")) {
			Multigraph<String, NamedEdge> accu;
		} else {
			throw new IOException("Read unknown graph type.");
		}		
	}
	
	private boolean contains_once(ArrayList<String> edges, String sign) {
		if(edges == null) throw new NullPointerException();
		for(String edge : edges) {
			if(edge == null) throw new NullPointerException();
			if(edge.contains(sign))
				return true;
		}
		return false;
	}

	@Override
	public void set_graph(ListenableGraph graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
	}

	@Override
	public ListenableGraph get_graph() {
		if(_graph == null) throw new NullPointerException("Intern graph is null, load or set a graph.");
		return _graph;
	}

}
