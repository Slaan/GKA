package test_model;

import static org.junit.Assert.*;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.FordFulkersonModel;

import java.util.Arrays;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestFordFulkerson {

	Graph<String, NamedWeightedEdge> 	_klauck_graph;
	Graph<String, NamedWeightedEdge>	_simple_graph;
	
	FordFulkersonModel 					_ffm;
	
	@Before
	public void setUp() {
		_klauck_graph = new DirectedWeightedPseudograph<>(NamedWeightedEdge.class);
		for(String vertex : Arrays.asList("q", "v2", "v3", "v4", "v5", "s")) {
			_klauck_graph.addVertex(vertex);			
		}
		NamedWeightedEdge q_v2  = new NamedWeightedEdge();
		q_v2.setWeight(3.0);
		_klauck_graph.addEdge("q", "v2", q_v2);
		NamedWeightedEdge q_v4  = new NamedWeightedEdge();
		q_v4.setWeight(2.0);
		_klauck_graph.addEdge("q", "v4", q_v4);
		NamedWeightedEdge v4_v2 = new NamedWeightedEdge();
		v4_v2.setWeight(2.0);
		_klauck_graph.addEdge("v4", "v2", v4_v2);
		NamedWeightedEdge v4_v5 = new NamedWeightedEdge();
		v4_v5.setWeight(2.0);
		_klauck_graph.addEdge("v4", "v5", v4_v5);
		NamedWeightedEdge v2_v3 = new NamedWeightedEdge();
		v2_v3.setWeight(2.0);
		_klauck_graph.addEdge("v2", "v3", v2_v3);
		NamedWeightedEdge v3_v4 = new NamedWeightedEdge();
		v3_v4.setWeight(1.0);
		_klauck_graph.addEdge("v3", "v4", v3_v4);
		NamedWeightedEdge v5_v3 = new NamedWeightedEdge();
		v5_v3.setWeight(1.0);
		_klauck_graph.addEdge("v5", "v3", v5_v3);
		NamedWeightedEdge v3_s  = new NamedWeightedEdge();
		v3_s.setWeight(2.0);
		_klauck_graph.addEdge("v3", "s", v3_s);
		NamedWeightedEdge v5_s  = new NamedWeightedEdge();
		v5_s.setWeight(4.0);
		_klauck_graph.addEdge("v5", "s", v5_s);
		// simple graph
		_simple_graph = new DirectedWeightedPseudograph<String, NamedWeightedEdge>(NamedWeightedEdge.class);
		for(String vertex : Arrays.asList("a", "b", "c", "d")) {
			_simple_graph.addVertex(vertex);
		}
		NamedWeightedEdge a_b = new NamedWeightedEdge();
		a_b.setWeight(3.0);
		_simple_graph.addEdge("a", "b", a_b);
		NamedWeightedEdge b_c = new NamedWeightedEdge();
		b_c.setWeight(1.0); // bottle head
		_simple_graph.addEdge("b", "c", b_c);
		NamedWeightedEdge c_d = new NamedWeightedEdge();
		c_d.setWeight(4.0);
		_simple_graph.addEdge("c", "d", c_d);
	}
	
	@Test
	public void testKlauckFlow() {
		_ffm = GKAModel.fordFulkerson(_klauck_graph);
		assertTrue(_ffm.start("q", "s") == 4.0);
		assertTrue(_ffm.start("v2", "s") == 2.0);
		assertTrue(_ffm.start("v4", "s") == 4.0);
	}
	
	@Test
	public void testSimpleNetwork() {
		_ffm = GKAModel.fordFulkerson(_simple_graph);
		assertTrue(_ffm.start("a", "d") == 1.0);
		assertTrue(_ffm.start("d", "a") == 0.0);
		assertTrue(_ffm.start("c", "d") == 4.0);
	}

}
