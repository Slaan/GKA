package impl_tests;

import static org.junit.Assert.assertEquals;
import impl_model.GKAModel;
import interface_model.BreadthFirstModel;

import java.util.ArrayList;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.junit.Test;

public class BreadthFirstImplTest {
	
	private ListenableGraph<String,DefaultEdge> lg;
	
	
	public BreadthFirstImplTest() {

	}

	@Test
	public void getResutDirectedTest1() {
		//TestGraphbauen
		lg = new ListenableDirectedGraph<String,DefaultEdge>(DefaultEdge.class);
		lg.addVertex("a");
		lg.addVertex("b");
		lg.addVertex("c");
		lg.addVertex("d");
		lg.addVertex("e");
		lg.addEdge("a", "b");
		lg.addEdge("a", "e");
		lg.addEdge("b", "a");
		lg.addEdge("b", "c");
		lg.addEdge("c", "d");
		lg.addEdge("d", "c");
		lg.addEdge("e", "d");
		
		BreadthFirstModel bfi = GKAModel.breadthFirst(lg);
		bfi.setStart("a");
		bfi.setTarget("d");
		ArrayList<String> actual = new ArrayList<>();
		actual = bfi.getResult();
		ArrayList<String> expected = new ArrayList<>();
		expected.add("2");
		expected.add("a,e,d");
		assertEquals(actual,expected);
		System.out.println(expected);
	}
	@Test
	public void getResutUnDirectedTest1() {
		//TestGraphbauen
		lg = new ListenableUndirectedGraph<String,DefaultEdge>(DefaultEdge.class);
		lg.addVertex("a");
		lg.addVertex("b");
		lg.addVertex("c");
		lg.addVertex("d");
		lg.addVertex("e");
		lg.addEdge("a", "b");
		lg.addEdge("a", "c");
		lg.addEdge("b", "c");
		lg.addEdge("d", "c");
		
		BreadthFirstModel bfi = GKAModel.breadthFirst(lg);
		bfi.setStart("a");
		bfi.setTarget("d");
		ArrayList<String> actual = new ArrayList<>();
		actual = bfi.getResult();
		ArrayList<String> expected = new ArrayList<>();
		expected.add("2");
		expected.add("a,c,d");
		assertEquals(actual,expected);
		System.out.println(expected);
	}	
}
