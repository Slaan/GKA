package interface_model;

import java.io.IOException;
import java.util.ArrayList;

import org.jgrapht.Graph;

public interface GraphHandlerModel {
	
	public Graph<?, ?> 		 to_graph(ArrayList<String> edges);
	public ArrayList<String> from_graph(Graph<?, ?> graph);
}
