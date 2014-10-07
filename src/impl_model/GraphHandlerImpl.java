package impl_model;

import org.jgrapht.ListenableGraph;

import interface_model.FileHandler;
import interface_model.GraphHandler;

class GraphHandlerImpl implements GraphHandler {

	private FileHandler					_file_handler;
	private	ListenableGraph<String, ?> 	_graph;
	
	// Creation
	public static GraphHandler create() {
		return new GraphHandlerImpl();
	}
	
	private GraphHandlerImpl() {
		_file_handler = GKA.fileHandler();
	}
	
	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set_graph(ListenableGraph graph) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ListenableGraph get_graph() {
		// TODO Auto-generated method stub
		return null;
	}

}
