package interface_model;

import org.jgrapht.ListenableGraph;

public interface GraphHandler {
	
	public void 				save(String path);
	public void 				load(String path);
	public void					set_graph(ListenableGraph graph);
	public ListenableGraph		get_graph();
}
