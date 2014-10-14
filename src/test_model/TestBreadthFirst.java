package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import impl_controller.GKA;
import impl_model.GKAModel;
import interface_controller.GraphHandler;
import interface_model.BreadthFirstModel;

import java.util.ArrayList;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Test;

public class TestBreadthFirst {
	
	/*
	 * Test of directed graphs
	 */
	@Test
	public void testSuccessfullDirectedGraph() {
		Graph<String, DefaultEdge> directed_graph;
		directed_graph = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
		directed_graph.addVertex("1");
		directed_graph.addVertex("2");
		directed_graph.addVertex("3");
		directed_graph.addVertex("5");
		directed_graph.addVertex("6");
		directed_graph.addVertex("7");
		directed_graph.addEdge("1", "2");
		directed_graph.addEdge("2", "3");
		directed_graph.addEdge("2", "6");
		directed_graph.addEdge("3", "5");
		directed_graph.addEdge("5", "7");
		directed_graph.addEdge("6", "7");
		directed_graph.addEdge("7", "1");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("1");
		expected.add("2");
		expected.add("6");
		expected.add("7");
		BreadthFirstModel bfs = GKAModel.breadthFirst(directed_graph);
		ArrayList result = bfs.breadthFirst("1", "7");
		assertEquals(expected, result);
	}
	
	@Test
	public void testDirectedGraphBFS() {
		Graph<String,DefaultEdge> dir_graph;
		dir_graph = new DirectedPseudograph<>(DefaultEdge.class);
		dir_graph.addVertex("a");
		dir_graph.addVertex("b");
		dir_graph.addVertex("c");
		dir_graph.addVertex("d");
		dir_graph.addVertex("e");
		dir_graph.addEdge("a","b");
		dir_graph.addEdge("b","c");
		dir_graph.addEdge("c","c");
		dir_graph.addEdge("c","a");
		dir_graph.addEdge("c","d");
		dir_graph.addEdge("d","e");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("a");
		expected.add("b");
		expected.add("c");
		expected.add("d");
		expected.add("e");
		BreadthFirstModel bfs = GKAModel.breadthFirst(dir_graph);
		ArrayList result = bfs.breadthFirst("a", "e");
		assertEquals(expected, result);
	}
	
	@Test
	public void testNotConnectedDirectedGraph() {
		Graph<String, DefaultEdge> directed_graph;
		directed_graph = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
		directed_graph.addVertex("1");
		directed_graph.addVertex("2");
		directed_graph.addVertex("3");
		directed_graph.addVertex("4");
		directed_graph.addEdge("1", "2");
		directed_graph.addEdge("2", "3");
		BreadthFirstModel bfs = GKAModel.breadthFirst(directed_graph);
		ArrayList result = bfs.breadthFirst("1", "4");
		// null expected
		assertNull(result);
	}
	
	@Test
	public void testUnreachableConnectedDirectedGraph() {
		Graph<String, DefaultEdge> directed_graph;
		directed_graph = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
		directed_graph.addVertex("1");
		directed_graph.addVertex("2");
		directed_graph.addVertex("3");
		directed_graph.addVertex("4");
		directed_graph.addEdge("1", "2");
		directed_graph.addEdge("2", "3");
		directed_graph.addEdge("4", "3");
		BreadthFirstModel bfs = GKAModel.breadthFirst(directed_graph);
		ArrayList result = bfs.breadthFirst("1", "4");
		// null expected
		assertNull(result);
	}
	
	/*
	 * Test of undirected graphs
	 */
	@Test
	public void testSuccessfullUndirectedGraph() {
		Graph<String, DefaultEdge> undirected_graph;
		undirected_graph = new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
		undirected_graph.addVertex("1");
		undirected_graph.addVertex("2");
		undirected_graph.addVertex("3");
		undirected_graph.addVertex("5");
		undirected_graph.addVertex("6");
		undirected_graph.addVertex("7");
		undirected_graph.addEdge("1", "2");
		undirected_graph.addEdge("2", "3");
		undirected_graph.addEdge("2", "6");
		undirected_graph.addEdge("3", "5");
		undirected_graph.addEdge("5", "7");
		undirected_graph.addEdge("6", "7");
		undirected_graph.addEdge("7", "1");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("1");
		expected.add("7");
		BreadthFirstModel bfs = GKAModel.breadthFirst(undirected_graph);
		ArrayList result = bfs.breadthFirst("1", "7");
		assertEquals(expected, result);
	}
	
	@Test
	public void testNotConnectedUndirectedGraph() {
		Graph<String, DefaultEdge> undirected_graph;
		undirected_graph = new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
		undirected_graph.addVertex("1");
		undirected_graph.addVertex("2");
		undirected_graph.addVertex("3");
		undirected_graph.addVertex("4");
		undirected_graph.addEdge("1", "2");
		undirected_graph.addEdge("2", "3");
		BreadthFirstModel bfs = GKAModel.breadthFirst(undirected_graph);
		ArrayList result = bfs.breadthFirst("1", "4");
		// null expected
		assertNull(result);
	}
	
}
