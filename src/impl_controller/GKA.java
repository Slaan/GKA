package impl_controller;

import impl_model.NamedWeightedEdge;
import interface_controller.BreadthFirst;
import interface_controller.Dijkstra;
import interface_controller.FileHandler;
import interface_controller.FloydWarshall;
import interface_controller.Generator;
import interface_controller.GraphHandler;

import org.jgrapht.Graph;

public final class GKA {

	/**
	 * @return new FileHandler
	 */
	public static FileHandler fileHandler() {
		return FileHandlerController.create();
	}
	
	public static Generator generator() {
		return GeneratorController.create();
	}
	
	/**
	 * @return new GraphHandler
	 */
	public static GraphHandler graphHandler() {
		return GraphHandlerController.create();
	}
	
	/**
	 * @return new BreadthFirst
	 */
	public static BreadthFirst breadthFirst(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return BreadthFirstController.create(graph);
	}
	
	public static Dijkstra dijkstra(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return DijkstraController.create(graph);
	}
	
	public static FloydWarshall floydWarshall(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return FloydWarshallController.create(graph);
	}
}
