package interface_model;

import java.io.IOException;
import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public interface GraphHandlerModel {
	
	public ListenableGraph<?, ?> 		 to_graph(ArrayList<String> edges);
	public ArrayList<String> from_graph(Graph<?, ?> graph);
}
