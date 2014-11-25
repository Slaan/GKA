package test_model;

import impl_model.NamedWeightedEdge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestGraphFunctions {

	Graph<String, NamedWeightedEdge> _graph;
	
	@Before
	public void setUp() {
		_graph = new DirectedWeightedPseudograph<>(NamedWeightedEdge.class);
		for(String vertex : Arrays.asList("a", "b", "c", "d")) {
			_graph.addVertex(vertex);
		}
		NamedWeightedEdge a_b1 = new NamedWeightedEdge();
		a_b1.setWeight(1.0);
		_graph.addEdge("a", "b", a_b1);
		NamedWeightedEdge a_b2 = new NamedWeightedEdge();
		a_b2.setWeight(3.0);
		_graph.addEdge("a", "b", a_b2);
		NamedWeightedEdge a_b3 = new NamedWeightedEdge();
		a_b3.setWeight(3.0);
		_graph.addEdge("a", "b", a_b3);
		
	}
	
	@Test
	public void testDirectedMultiedges() {
		System.out.println(_graph.edgesOf("a"));
		
	}

}
