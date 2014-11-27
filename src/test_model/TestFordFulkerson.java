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
	Graph<String, NamedWeightedEdge> 	_graph;
	
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
	public void testKlauckFlow1() {
		_ffm = GKAModel.fordFulkerson(_klauck_graph);
		assertTrue(_ffm.start("q", "s") == 4.0);
	}
	
	@Test
	public void testKlauckFlow2() {
		_ffm = GKAModel.fordFulkerson(_klauck_graph);
		assertTrue(_ffm.start("v2", "s") == 2.0);
	}
	
	@Test
	public void testKlauckFlow3() {
		_ffm = GKAModel.fordFulkerson(_klauck_graph);
		assertTrue(_ffm.start("v4", "s") == 4.0);
	}
	
	@Test
	public void testSimpleNetwork() {
		_ffm = GKAModel.fordFulkerson(_simple_graph); 
		assertTrue(_ffm.start("a", "d") == 1.0);
		assertTrue(_ffm.start("d", "a") == 0.0);
		assertTrue(_ffm.start("c", "d") == 4.0);
	}

	@Test
	public void EdmondsKarpTest1() {
		_graph = new DirectedWeightedPseudograph<String, NamedWeightedEdge>(NamedWeightedEdge.class);
		_graph.addVertex("s");
		_graph.addVertex("L");
		_graph.addVertex("F");
		_graph.addVertex("A");
		_graph.addVertex("C");
		_graph.addVertex("G");
		_graph.addVertex("B");
		_graph.addVertex("M");
		_graph.addVertex("t");
		
		NamedWeightedEdge e1 = new NamedWeightedEdge();
		e1.setWeight(12.0);
		NamedWeightedEdge e2 = new NamedWeightedEdge();
		e2.setWeight(13.0);
		NamedWeightedEdge e3 = new NamedWeightedEdge();
		e3.setWeight(2.0);
		NamedWeightedEdge e4 = new NamedWeightedEdge();
		e4.setWeight(3.0);
		NamedWeightedEdge e5 = new NamedWeightedEdge();
		e5.setWeight(6.0);
		NamedWeightedEdge e6 = new NamedWeightedEdge();
		e6.setWeight(4.0);
		NamedWeightedEdge e7 = new NamedWeightedEdge();
		e7.setWeight(8.0);
		NamedWeightedEdge e8 = new NamedWeightedEdge();
		e8.setWeight(2.0);
		NamedWeightedEdge e9 = new NamedWeightedEdge();
		e9.setWeight(2.0);
		NamedWeightedEdge e10 = new NamedWeightedEdge();
		e10.setWeight(5.0);
		NamedWeightedEdge e11 = new NamedWeightedEdge();
		e11.setWeight(7.0);
		NamedWeightedEdge e12 = new NamedWeightedEdge();
		e12.setWeight(11.0);
		NamedWeightedEdge e13 = new NamedWeightedEdge();
		e13.setWeight(1.0);
		
		_graph.addEdge("s", "L", e1);
		_graph.addEdge("s", "C", e3);
		_graph.addEdge("L", "F", e2);
		_graph.addEdge("F", "A", e4);
		_graph.addEdge("F", "G", e6);
		_graph.addEdge("A", "t", e5);
		_graph.addEdge("C", "G", e10);
		_graph.addEdge("C","B", e11);
		_graph.addEdge("G","A", e7);
		_graph.addEdge("B","M", e12);
		_graph.addEdge("M","G", e13);
		_graph.addEdge("M","t", e9);
		_graph.addEdge("G","t", e8);
		
		_ffm = GKAModel.fordFulkerson(_graph);
		assertTrue(_ffm.start("s", "t") == 9.0);
	}
}
