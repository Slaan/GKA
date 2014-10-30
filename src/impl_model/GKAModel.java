package impl_model;

import interface_model.BreadthFirstModel;
import interface_model.FileHandlerModel;
import interface_model.GraphHandlerModel;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public final class GKAModel {

	/*
	 * @returns New FileHandler
	 */
	public static FileHandlerModel fileHandler() {
		return FileHandlerModelImpl.create();
	}

	/*
	 * @returns New GraphHandler
	 */
	public static GraphHandlerModel graphHandler() {
//		return GraphHandlerModelImpl.create();
		return OtherGraphHandlerModelImpl.create();
	}
	
	/*
	 * @returns New BreadthFirst
	 */
	public static BreadthFirstModel breadthFirst(Graph<String, NamedWeightedEdge> directed_graph) {
		if(directed_graph == null) throw new NullPointerException();
		return BreadthFirstModelImpl.create(directed_graph);
	}
}
