package impl_model;

import interface_model.BreadthFirstModel;
import interface_model.DijkstraModel;
import interface_model.FileHandlerModel;
import interface_model.FloydWarshallModel;
import interface_model.GeneratorModel;
import interface_model.GraphHandlerModel;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

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
	 * @returns new GraphHandlerModel
	 */
	public static GraphHandlerModel graphHandler() {
		//return GraphHandlerModelImpl.create();
		return OtherGraphHandlerModelImpl.create();
	}
	
	/**
	 * BreadthFirstModel traverses through an jgrapht
	 * graph. It counts the amount of graph accesses and
	 * finds the shortes path. 
	 * @returns new BreadthFirstModel functor
	 */
	public static BreadthFirstModel breadthFirst(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return BreadthFirstModelImpl.create(graph);
	}
	
	/**
	 * @param graph has to be directed
	 * @return new FolyWarshallModel functor
	 */
	public static FloydWarshallModel floydWarshall(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return FloydWarshallModelImpl.create(graph);
	}
	
	/**
	 * 
	 * @return new DijkstraModel functor
	 */
	public static DijkstraModel dijkstra(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return DijkstraModelImpl.create(graph);
	}
	
	public static GeneratorModel generator() {
		return GeneratorModelImpl.create();
	}
}
