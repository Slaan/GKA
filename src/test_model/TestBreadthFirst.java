package test_model;

import static org.junit.Assert.assertTrue;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.BreadthFirstModel;

import java.util.ArrayList;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestBreadthFirst {

	private ListenableGraph<String, NamedWeightedEdge> _simple_directed_graph;
	private ListenableGraph<String, NamedWeightedEdge> _complex_undirected_graph;
	
	@Before
	public void setUp() {
		DirectedPseudograph<String, NamedWeightedEdge> simple_graph;
		simple_graph = new DirectedPseudograph(NamedWeightedEdge.class);
		simple_graph.addVertex("a");
		simple_graph.addVertex("b");
		simple_graph.addVertex("c");
		simple_graph.addVertex("d");
		simple_graph.addEdge("a", "b");
		simple_graph.addEdge("b", "c");
		simple_graph.addEdge("c", "d");
		_simple_directed_graph = new DefaultListenableGraph<>(simple_graph);
		// 
		Pseudograph<String, NamedWeightedEdge> complex_graph;
		complex_graph = new Pseudograph<>(NamedWeightedEdge.class);
		complex_graph.addVertex("a");
		complex_graph.addVertex("b");
		complex_graph.addVertex("c");
		complex_graph.addVertex("d");
		complex_graph.addVertex("e");
		complex_graph.addEdge("a", "b");
		complex_graph.addEdge("b", "b");
		complex_graph.addEdge("c", "b");
		complex_graph.addEdge("d", "e");
		complex_graph.addEdge("c", "e");
		_complex_undirected_graph = new DefaultListenableGraph<>(complex_graph);
	}
	
	
	@Test
	public void testEasyDirectedGraph() {
		BreadthFirstModel bf = GKAModel.breadthFirst(_simple_directed_graph);
		bf.setStart("a");
		bf.setTarget("d");
		ArrayList<String> result = bf.getResult();
		ArrayList<String> expected = new ArrayList<>();
		expected.add("a");
		expected.add("b");
		expected.add("c");
		expected.add("d");
		assertTrue(result.equals(expected));
	}
	
	@Test
	public void testComplexUndirectedGraph() {
		BreadthFirstModel bf = GKAModel.breadthFirst(_complex_undirected_graph);
		bf.setStart("a");
		bf.setStart("e");
		ArrayList<String> result 	= bf.getResult();
		ArrayList<String> expected 	= new ArrayList<>();
		expected.add("a");
		expected.add("b");
		expected.add("c");
		expected.add("e");
		expected.add("d");
		assertTrue(result.equals(expected));
	}

}
