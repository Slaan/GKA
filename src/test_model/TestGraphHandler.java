package test_model;

import static org.junit.Assert.*;

import java.io.IOException;

import impl_model.GKAModel;
import interface_model.GraphHandlerModel;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.Multigraph;
import org.junit.Before;
import org.junit.Test;

public class TestGraphHandler {

	GraphHandlerModel gh;
	ListenableGraph<String, DefaultEdge> dg;
	ListenableGraph<String, DefaultEdge> ug;
	
	@Before
	public void setUp() {
		gh = GKAModel.graphHandler();
		// directed graph
		DirectedMultigraph<String, DefaultEdge> dg1;
		dg1 = new DirectedMultigraph(DefaultEdge.class);
		dg1.addVertex("a");
		dg1.addVertex("b");
		dg1.addVertex("c");
		dg1.addEdge("a", "b");
		dg = new DefaultListenableGraph<>(dg1);
		// undirected graph
		Multigraph<String, DefaultEdge> ug1;
		ug1 = new Multigraph(DefaultEdge.class);
		ug1.addVertex("a");
		ug1.addVertex("b");
		ug1.addVertex("c");
		ug1.addEdge("a", "b");
		ug = new DefaultListenableGraph<>(ug1);
	}
	
	@Test
	public void testGraphHandlerCreation() {
		assertNotNull(GKAModel.graphHandler());
	}
	
	@Test
	public void testSetAndGetDirectedGraph() {
		gh.set_graph(dg);
		ListenableGraph out = gh.get_graph();
		assertTrue(out.equals(dg));
	}
	
	@Test
	public void testSetAndGetUndirectedGraph() {
		gh.set_graph(ug);
		ListenableGraph out = gh.get_graph();
		assertTrue(out.equals(ug));
	}
	
	@Test
	public void testStoreAndLoadDirectedGraph() {
		String path = "./graphs/test/directed_graph.gka";
		gh.set_graph(dg);
		gh.save();
		// new graphhandler for new graph
		GraphHandlerModel tmp_gh = GKAModel.graphHandler();
		try {
			tmp_gh.load();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
		ListenableGraph tmp = tmp_gh.get_graph();
		assertTrue(dg.equals(tmp));
	}
	
	@Test
	public void testStoreAndLoadUndirectedGraph() {
		String path = "./graphs/test/undirected_graph.gka";
		gh.set_graph(ug);
		gh.save();
		// new graphhandler for new graph
		GraphHandlerModel tmp_gh = GKAModel.graphHandler();
		try {
			tmp_gh.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListenableGraph tmp = tmp_gh.get_graph();
		assertTrue(ug.equals(tmp));
	}
}
