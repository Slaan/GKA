package test_model;

import static org.junit.Assert.assertTrue;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.GraphHandlerModel;

import java.util.ArrayList;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.Pseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestGraphHandlerModel {

	GraphHandlerModel 						_gh;
	ListenableGraph<String, DefaultEdge> 	_dg;
	ListenableGraph<String, DefaultEdge> 	_ug;
	
	@Before
	public void setUp() {
		_gh = GKAModel.graphHandler();
		
		// directed graph
		DirectedPseudograph<String, DefaultEdge> dg;
		dg = new DirectedPseudograph<>(DefaultEdge.class);
		dg.addVertex("a");
		dg.addVertex("b");
		dg.addVertex("c");
		dg.addEdge("a", "b");
		_dg = new DefaultListenableGraph<>(dg);
		
		// directed weighted graph
		
		// undirected graph
		Pseudograph<String, DefaultEdge> ug1;
		ug1 = new Pseudograph(DefaultEdge.class);
		ug1.addVertex("a");
		ug1.addVertex("b");
		ug1.addVertex("c");
		ug1.addEdge("a", "b");
		_ug = new DefaultListenableGraph<>(ug1);
		
		// undirected weighted graph
		
	}
	
	@Test
	public void DirectedGraphTest() {
		Graph<String, NamedWeightedEdge> dg = new DirectedPseudograph<>(NamedWeightedEdge.class);
		Graph<String, NamedWeightedEdge> dg_created = new DirectedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> array_dg = new ArrayList<>();
		
		array_dg.add("a->b;");
		array_dg.add("a->a;"); //test loops
		array_dg.add("b->c;");
		array_dg.add("c->d;");
		
		dg.addVertex("a");
		dg.addVertex("b");
		dg.addVertex("c");
		dg.addVertex("d");
		dg.addEdge("a", "b");
		dg.addEdge("a", "a");
		dg.addEdge("b", "c");
		dg.addEdge("c", "d");
		
		dg_created = _gh.to_graph(array_dg);
		assertTrue(dg_created.edgeSet().equals(dg.edgeSet()));
	}
	
	@Test
	public void UndirectedGraphTest() {
		Graph<String, NamedWeightedEdge> dg = new Pseudograph<>(NamedWeightedEdge.class);
		Graph<String, NamedWeightedEdge> dg_created = new Pseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> array_dg = new ArrayList<>();
		
		array_dg.add("a--b;");
		array_dg.add("a--a;"); //test loops
		array_dg.add("b--c;");
		array_dg.add("c--d;");
		
		dg.addVertex("a");
		dg.addVertex("b");
		dg.addVertex("c");
		dg.addVertex("d");
		dg.addEdge("a", "b");
		dg.addEdge("a", "a");
		dg.addEdge("b", "c");
		dg.addEdge("c", "d");
		
		dg_created = _gh.to_graph(array_dg);
		assertTrue(dg_created.edgeSet().equals(dg.edgeSet()));
	}
	
	@Test
	public void DirectedWeightedGraphTest() {
		DirectedWeightedPseudograph<String, NamedWeightedEdge> dg = new DirectedWeightedPseudograph<>(NamedWeightedEdge.class);
		DirectedWeightedPseudograph<String, NamedWeightedEdge> dg_created = new DirectedWeightedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> array_dg = new ArrayList<>();
		
		array_dg.add("a->b:5;");
		array_dg.add("a->a:1;"); //test loops
		array_dg.add("b->c:3;");
		array_dg.add("c->d:2;");
		
		dg.addVertex("a");
		dg.addVertex("b");
		dg.addVertex("c");
		dg.addVertex("d");
		dg.addEdge("a", "b");
		dg.addEdge("a", "a");
		dg.addEdge("b", "c");
		dg.addEdge("c", "d");
		
		NamedWeightedEdge e = null;
		e = dg.addEdge("a", "b");
		e.setWeight(5.0);
	    ((DirectedWeightedPseudograph) dg).setEdgeWeight(e, 5.0);
		
	    NamedWeightedEdge e1 = null;
		e = dg.addEdge("a", "a");
		e.setWeight(1.0);
	    ((DirectedWeightedPseudograph) dg).setEdgeWeight(e, 1.0);
	    
	    NamedWeightedEdge e2 = null;
		e = dg.addEdge("b", "c");
		e.setWeight(3.0);
	    ((DirectedWeightedPseudograph) dg).setEdgeWeight(e, 3.0);
	    
	    NamedWeightedEdge e3 = null;
		e = dg.addEdge("c", "d");
		e.setWeight(2.0);
	    ((DirectedWeightedPseudograph) dg).setEdgeWeight(e, 2.0);
	    
		dg_created = _gh.to_graph(array_dg);
		System.out.println("test1: created "+dg_created.edgeSet());
		System.out.println("test2: dg"+dg.edgeSet());
		assertTrue(dg_created.edgeSet().equals(dg.edgeSet()));
//		Set<NamedWeightedEdge> edges = new HashSet<>();
//		edges = dg_created.edgeSet();
//		for (NamedWeightedEdge edge : edges) {
//			double weight1, weight2;
//			weight1 = dg.getEdgeWeight(edge);
//			weight2 = dg_created.getEdgeWeight(edge);
//			assertTrue(weight1==weight2);
//		}
	}
	
//	@Test
//	public void UndirectedWeightedGraphTest() {
//		Graph<String, NamedWeightedEdge> dg = new Pseudograph<>(NamedWeightedEdge.class);
//		Graph<String, NamedWeightedEdge> dg_created = new Pseudograph<>(NamedWeightedEdge.class);
//		ArrayList<String> array_dg = new ArrayList<>();
//		
//		array_dg.add("a--b;");
//		array_dg.add("a--a;"); //test loops
//		array_dg.add("b--c;");
//		array_dg.add("c--d;");
//		
//		dg.addVertex("a");
//		dg.addVertex("b");
//		dg.addVertex("c");
//		dg.addVertex("d");
//		dg.addEdge("a", "b");
//		dg.addEdge("a", "a");
//		dg.addEdge("b", "c");
//		dg.addEdge("c", "d");
//		
//		dg_created = _gh.to_graph(array_dg);
//		assertTrue(dg_created.edgeSet().equals(dg.edgeSet()));
//	}
//	
}

