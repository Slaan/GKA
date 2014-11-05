package test_model;

import static org.junit.Assert.assertTrue;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.GraphHandlerModel;

import java.util.ArrayList;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedPseudograph;
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
		
		// undirected graph
		Pseudograph<String, DefaultEdge> ug1;
		ug1 = new Pseudograph(DefaultEdge.class);
		ug1.addVertex("a");
		ug1.addVertex("b");
		ug1.addVertex("c");
		ug1.addEdge("a", "b");
		_ug = new DefaultListenableGraph<>(ug1);
		
	}
	
	
//	@Test
//	public void GraphGeneratorTest() {
//		Graph<String, NamedWeightedEdge> dg = new DirectedPseudograph<>(NamedWeightedEdge.class);
//		dg = _gh.generateGraph(10, 50);
//		Set<String> vertexes = dg.vertexSet();
//		Set<NamedWeightedEdge> edges = dg.edgeSet();
//		System.out.println(dg);
//		assertTrue(vertexes.size()==10);
//		assertTrue(edges.size()==50);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test
//	public void DirectedGraphTest() {
//		Graph<String, NamedWeightedEdge> dg = new DirectedPseudograph<>(NamedWeightedEdge.class);
//		Graph<String, NamedWeightedEdge> dg_created = new DirectedPseudograph<>(NamedWeightedEdge.class);
//		ArrayList<String> array_dg = new ArrayList<>();
//		
//		array_dg.add("a->b;");
//		array_dg.add("a->a;"); //test loops
//		array_dg.add("b->c;");
//		array_dg.add("c->d;");
//		
//		dg.addVertex("a");
//		dg.addVertex("b");
//		dg.addVertex("c");
//		dg.addVertex("d");
//		NamedWeightedEdge edge1 = dg.addEdge("a", "b");
//		NamedWeightedEdge edge2 = dg.addEdge("a", "a");
//		NamedWeightedEdge edge3 = dg.addEdge("b", "c");
//		NamedWeightedEdge edge4 = dg.addEdge("c", "d");
//		edge1.setWeight(1.0);
//		edge2.setWeight(1.0);
//		edge3.setWeight(1.0);
//		edge4.setWeight(1.0);
//		dg_created = _gh.to_graph(array_dg);
//		//System.out.println(dg_created);
//		//System.out.println(dg);
//		assertTrue(dg_created.equals(dg));
//	}
//	
//	@Test
//	public void UndirectedGraphTest() {
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
//		//System.out.println(dg_created);
//		//System.out.println(dg);
//		assertTrue(dg_created.equals(dg));
//	}
}


