package impl_model;

import interface_model.BreadthFirstModel;
import interface_model.FileHandlerModel;
import interface_model.GraphHandlerModel;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;

public final class GKAModel {

	/**
	 * FileHandlerModel allow to store ArrayList<String>
	 * to a file and load from them
	 * @returns New FileHandlerModel
	 */
	public static FileHandlerModel fileHandler() {
		return FileHandlerModelImpl.create();
	}

	/**
	 * GraphHandler converts ArrayList<String>
	 * to jgraphts Graph. 
	 * @returns new GraphHandler
	 */
	public static GraphHandlerModel graphHandler() {
		//return GraphHandlerModelImpl.create();
		return OtherGraphHandlerModelImpl.create();
	}
	
	/**
	 * BreadthFirstModel traverses through an jgrapht
	 * graph. It counts the amount of graph accesses and
	 * finds the shortes path. 
	 * @returns new BreadthFirst
	 */
	public static BreadthFirstModel breadthFirst(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return BreadthFirstModelImpl.create(graph);
	}
}
