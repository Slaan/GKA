package test_model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.tree.ExpandVetoException;

import junit.framework.TestFailure;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.FloydWarshallModel;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestFloydWarshall {

	Graph<String, NamedWeightedEdge> _simple_directed_graph;
	FloydWarshallModel _sm;
	
	Graph<String, NamedWeightedEdge> _klaucks_graph;
	FloydWarshallModel _km;
	
	Graph<String, NamedWeightedEdge> _undirected_graph;
	FloydWarshallModel _um;
	
	@Before
	public void setUp() {
		// simple directed graph
		_simple_directed_graph = new DirectedWeightedPseudograph(NamedWeightedEdge.class);
		_simple_directed_graph.addVertex("a");
		_simple_directed_graph.addVertex("b");
		_simple_directed_graph.addVertex("c");
		NamedWeightedEdge edgeAtoB;
		edgeAtoB = new NamedWeightedEdge();
		edgeAtoB.setWeight(2.0);
		NamedWeightedEdge edgeBtoC;
		edgeBtoC = new NamedWeightedEdge();
		edgeBtoC.setWeight(3.0);
		_simple_directed_graph.addEdge("a", "b", edgeAtoB);
		_simple_directed_graph.addEdge("b", "c", edgeBtoC);
		_sm = GKAModel.floydWarshall(_simple_directed_graph);
		// klaucks graph
		_klaucks_graph = new DirectedWeightedPseudograph(NamedWeightedEdge.class);
		_klaucks_graph.addVertex("v1");
		_klaucks_graph.addVertex("v2");
		_klaucks_graph.addVertex("v3");
		_klaucks_graph.addVertex("v4");
		NamedWeightedEdge edgeV1toV2;
		edgeV1toV2 = new NamedWeightedEdge();
		edgeV1toV2.setWeight(1.0);
		NamedWeightedEdge edgeV1toV4;
		edgeV1toV4 = new NamedWeightedEdge();
		edgeV1toV4.setWeight(3.0);
		NamedWeightedEdge edgeV4toV1;
		edgeV4toV1 = new NamedWeightedEdge();
		edgeV4toV1.setWeight(2.0);
		NamedWeightedEdge edgeV4toV3;
		edgeV4toV3 = new NamedWeightedEdge();
		edgeV4toV3.setWeight(2.0);
		NamedWeightedEdge edgeV2toV4;
		edgeV2toV4 = new NamedWeightedEdge();
		edgeV2toV4.setWeight(1.0);
		NamedWeightedEdge edgeV2toV3;
		edgeV2toV3 = new NamedWeightedEdge();
		edgeV2toV3.setWeight(2.0);
		NamedWeightedEdge edgeV3toV2;
		edgeV3toV2 = new NamedWeightedEdge();
		edgeV3toV2.setWeight(2.0);
		_klaucks_graph.addEdge("v1", "v2", edgeV1toV2);
		_klaucks_graph.addEdge("v1", "v4", edgeV1toV4);
		_klaucks_graph.addEdge("v4", "v1", edgeV4toV1);
		_klaucks_graph.addEdge("v4", "v3", edgeV4toV3);
		_klaucks_graph.addEdge("v2", "v4", edgeV2toV4);
		_klaucks_graph.addEdge("v2", "v3", edgeV2toV3);
		_klaucks_graph.addEdge("v3", "v2", edgeV3toV2);
		_km = GKAModel.floydWarshall(_klaucks_graph);
	}

//	@Test(expected=IllegalArgumentException.class)
//	public void testErrorUndirectedGraph() {
//		Graph<String, NamedWeightedEdge> g;
//		g = new Pseudograph<>(NamedWeightedEdge.class);
//		FloydWarshallModel m = GKAModel.floydWarshall(g);
//	}
	
	@Test
	public void testDistanceMatrixInit() {
		ArrayList<Double> asource = new ArrayList<>();
		asource.add(0.0);
		asource.add(2.0);
		asource.add(5.0);
		ArrayList<Double> bsource = new ArrayList<>();
		bsource.add(Double.POSITIVE_INFINITY);
		bsource.add(0.0);
		bsource.add(3.0);
		ArrayList<Double> csource = new ArrayList<>();
		csource.add(Double.POSITIVE_INFINITY);
		csource.add(Double.POSITIVE_INFINITY);
		csource.add(0.0);
		ArrayList expected = new ArrayList<>();
		expected.addAll(Arrays.asList(asource, bsource, csource));
		assertEquals(expected, _sm.getDistanceMatrix());
	}
	
	@Test
	public void testGetTransitValue() {
		assertEquals(Integer.valueOf(4), _km.getTransitValue("v3", "v1"));
		assertEquals(Integer.valueOf(2), _km.getTransitValue("v3", "v4"));
		assertEquals(Integer.valueOf(0), _km.getTransitValue("v1", "v1"));
	}
	
	@Test
	public void testGetDistanceValue() {
		assertEquals(Double.valueOf(3.0), _km.getDistanceValue("v1", "v3"));
		assertEquals(Double.valueOf(5.0), _km.getDistanceValue("v3", "v1"));
		assertEquals(Double.valueOf(0.0), _km.getDistanceValue("v4", "v4"));
	}

	@Test
	public void testTransitMatrixSimple() {
		ArrayList<Integer> asource = new ArrayList<>();
		asource.add(0);
		asource.add(0);
		asource.add(2);
		ArrayList<Integer> bsource = new ArrayList<>();
		bsource.add(0);
		bsource.add(0);
		bsource.add(0);
		ArrayList<Integer> csource = new ArrayList<>();
		csource.add(0);
		csource.add(0);
		csource.add(0);
		ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
		expected.addAll(Arrays.asList(asource, bsource, csource));
		assertEquals(expected, _sm.getTransitMatrix());
	}
	
	@Test
	public void testDistanceMatrixKlauck() {
		ArrayList<Double> asource = new ArrayList<>();
		asource.add(0.0);
		asource.add(1.0);
		asource.add(3.0);
		asource.add(2.0);
		ArrayList<Double> bsource = new ArrayList<>();
		bsource.add(3.0);
		bsource.add(0.0);
		bsource.add(2.0);
		bsource.add(1.0);
		ArrayList<Double> csource = new ArrayList<>();
		csource.add(5.0);
		csource.add(2.0);
		csource.add(0.0);
		csource.add(3.0);
		ArrayList<Double> dsource = new ArrayList<>();
		dsource.add(2.0);
		dsource.add(3.0);
		dsource.add(2.0);
		dsource.add(0.0);
		ArrayList<ArrayList<Double>> expected;
		expected = new ArrayList<>();
		expected.addAll(Arrays.asList(asource, bsource, csource, dsource));
		assertEquals(expected, _km.getDistanceMatrix());
	}
	
	@Test
	public void testTransitMatrxKlauck() {
		ArrayList<Integer> asource = new ArrayList<>();
		asource.add(0);
		asource.add(0);
		asource.add(2);
		asource.add(2);
		ArrayList<Integer> bsource = new ArrayList<>();
		bsource.add(4);
		bsource.add(0);
		bsource.add(0);
		bsource.add(0);
		ArrayList<Integer> csource = new ArrayList<>();
		csource.add(4);
		csource.add(0);
		csource.add(0);
		csource.add(2);
		ArrayList<Integer> dsource = new ArrayList<>();
		dsource.add(0);
		dsource.add(1);
		dsource.add(0);
		dsource.add(0);
		ArrayList<ArrayList<Integer>> expected = new ArrayList<>();
		expected.addAll(Arrays.asList(asource, bsource, csource, dsource));
		assertEquals(expected, _km.getTransitMatrix());
	}
	
	@Test
	public void testSimplePaths() {
		assertEquals(Arrays.asList("a", "b"), _sm.start("a", "b")); 
		assertEquals(Arrays.asList("a", "b", "c"), _sm.start("a", "c"));  
	}
	
	@Test
	public void testKlauckPaths() {
		assertEquals(Arrays.asList("v4", "v1", "v2"), _km.start("v4", "v2"));
		assertEquals(Arrays.asList("v3", "v2", "v4", "v1"), _km.start("v3", "v1"));
	}
	
	@Test
	public void testUnaccessableGraphs() {
		
	}
	
}
