package test_model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import impl_model.GKAModel;
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
	public void testSuccessfullDirectedGraphBFS() {
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
		assertEquals(result, expected);
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
	public void testSuccessfullUndirectedGraphBFS() {
		Graph<String, DefaultEdge> directed_graph;
		directed_graph = new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
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
		assertEquals(result, expected);
	}
	
	@Test
	public void testNotConnectedUndirectedGraph() {
		Graph<String, DefaultEdge> directed_graph;
		directed_graph = new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
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
	
}
