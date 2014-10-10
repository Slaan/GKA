package test_model;

import impl_model.GKAModel;
import interface_model.GraphHandlerModel;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.Multigraph;
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
	public void testOne() {
		
	}
	
}

