package interface_model;

import java.io.IOException;

import org.jgrapht.ListenableGraph;

public interface GraphHandlerModel {
	
	public void 				save();
	public void 				load() throws IOException;
	public void					set_graph(ListenableGraph graph);
	public ListenableGraph		get_graph();
	public String				get_path();
}
