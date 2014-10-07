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
		_file_handler = GKAModel.fileHandler();
	}
	
	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		// 1) get type (directed/undirected) and set symbol (<- / --)
		// 2) is graph weighted? 
		// 3) is the edgename empty? 
		// 		yes -> skip brackets ; no -> add brackets and name
		// 4) is the edge a loop? 
		// 		print loops as "a -- a" or "a -> a"
		
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		// 1) is graph completly directed/undirected?
		// 		no -> throw exception ;
		//  
		// 2) 
		// 
		
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
