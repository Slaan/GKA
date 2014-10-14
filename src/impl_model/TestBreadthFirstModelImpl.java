package impl_model;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestBreadthFirstModelImpl {

	/**
	 * This test is for the helpermethods 
	 */
	
	Graph<String, DefaultEdge> directed;
	Graph<String, DefaultEdge> undirected;
	
	@Before
	public void setUp() {
		directed = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
		directed.addVertex("a");
		directed.addVertex("b");
		directed.addVertex("c");
		directed.addVertex("d");
		directed.addEdge("a", "b");
		directed.addEdge("a", "c");
		directed.addEdge("d", "a");
		undirected = new Pseudograph<>(DefaultEdge.class);
		undirected.addVertex("a");
		undirected.addVertex("b");
		undirected.addVertex("c");
		undirected.addVertex("d");
		undirected.addEdge("a", "b");
		undirected.addEdge("a", "c");
		undirected.addEdge("d", "a");
	}
	
	@Test
	public void testAdjacentNodesDirected() {
		BreadthFirstModelImpl bfs = (BreadthFirstModelImpl) BreadthFirstModelImpl.create(directed);
		Set<String> expected = new HashSet<>();
		expected.add("b");
		expected.add("c");
		assertEquals(expected, bfs.adjacentNodes("a"));
	}

	@Test
	public void testAdjacentNodesUndirected() {
		BreadthFirstModelImpl bfs = (BreadthFirstModelImpl) BreadthFirstModelImpl.create(undirected);
		Set<String> expected = new HashSet<>();
		expected.add("b");
		expected.add("c");
		expected.add("d");
		assertEquals(expected, bfs.adjacentNodes("a"));
	}
	
	@Test
	public void testBackwardsAdjacentNodesDirected() {
		BreadthFirstModelImpl bfs = (BreadthFirstModelImpl) BreadthFirstModelImpl.create(directed);
		Set<String> expected = new HashSet<>();
		expected.add("d");
		assertEquals(expected, bfs.backwardsAdjacentNodes("a"));
	}
	
}
