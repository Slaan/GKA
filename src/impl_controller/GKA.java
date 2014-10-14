package impl_controller;

import org.jgrapht.Graph;

import interface_controller.BreadthFirst;
import interface_controller.FileHandler;
import interface_controller.GraphHandler;

public final class GKA {

	/**
	 * @return new FileHandler
	 */
	public static FileHandler fileHandler() {
		return FileHandlerController.create();
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
	public static BreadthFirst breadthFirst(Graph graph) {
		return BreadthFirstController.create(graph);
	}
}
