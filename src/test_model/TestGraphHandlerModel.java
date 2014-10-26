package test_model;

import static org.junit.Assert.*;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.GraphHandlerModel;

import java.util.ArrayList;

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
		array_dg.add("a->b");
		array_dg.add("a->b"); //test Multiedges
		array_dg.add("a->a"); //test loops
		array_dg.add("b->c");
		array_dg.add("c->d");
		
		dg.addVertex("a");
		dg.addVertex("b");
		dg.addVertex("c");
		dg.addVertex("d");
		dg.addEdge("a", "b");
		dg.addEdge("a", "b");
		dg.addEdge("a", "a");
		dg.addEdge("b", "c");
		dg.addEdge("c", "d");
		
		dg_created = GraphHandlerModel.to_graph(array_dg);
		assertTrue(dg_created.equals(dg));
	}
	
}

