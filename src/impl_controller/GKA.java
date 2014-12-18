package impl_controller;

import impl_model.NamedWeightedEdge;
import interface_controller.BattleController;
import interface_controller.BreadthFirst;
import interface_controller.Dijkstra;
import interface_controller.EdmondKarp;
import interface_controller.FileHandler;
import interface_controller.FloydWarshall;
import interface_controller.FordFulkerson;
import interface_controller.Generator;
import interface_controller.GraphHandler;
import interface_controller.MinimalSpannbaumHeuristik;
import interface_controller.NaechstgelegnerKnotenAlg;

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
	
	public static FordFulkerson fordFulkerson(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return FordFulkersonController.create(graph);
	}
	
	public static EdmondKarp edmondKarp(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return EdmondKarpController.create(graph);
	}
	
	public static MinimalSpannbaumHeuristik minimalerSpannbaumHeuristik(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return MinimalerSpannbaumHeuristikController.create(graph);
	}
	
	public static NaechstgelegnerKnotenAlg naechstgelegenerKnotenAlg(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		return NaechstgelegnerKnotenAlgController.create(graph);
	}
	
	public static BattleController battle() {
		return BattleControllerImpl.create();
	}
	
}
