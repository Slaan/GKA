package test_model;

import static org.junit.Assert.assertEquals;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.DijkstraModel;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.junit.Test;

public class TestDijkstraModel {
	
	DijkstraModel   _dh;

	
	//Test for Loops
	@Test
	public void DirectedTest1() {
		Graph<String,NamedWeightedEdge> dir = new DirectedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> array_dir = new ArrayList<>();
		ArrayList<String> expected_result = new ArrayList<>();
		ArrayList<String> actual_result = new ArrayList<>();
		
		dir.addVertex("a");
		dir.addVertex("b");
		dir.addVertex("c");
		dir.addVertex("d");
		NamedWeightedEdge edge1 = dir.addEdge("a", "b");
		NamedWeightedEdge edge2 = dir.addEdge("a", "a");
		NamedWeightedEdge edge3 = dir.addEdge("b", "c");
		NamedWeightedEdge edge4 = dir.addEdge("c", "d");
		NamedWeightedEdge edge5 = dir.addEdge("a", "d");
		edge1.setWeight(2.0);
		edge2.setWeight(1.0);
		edge3.setWeight(3.0);
		edge4.setWeight(4.0);
		edge5.setWeight(10.0);
		
		_dh = (DijkstraModel) GKAModel.dijkstra(dir);
		
		expected_result.add("a");
		expected_result.add("b");
		expected_result.add("c");
		expected_result.add("d");
		actual_result = _dh.start("a", "d");
		System.out.println(actual_result);
		System.out.println(expected_result);
		assertEquals(actual_result,expected_result);
	}
	
	@Test
	public void DirectedTest2() {
		Graph<String,NamedWeightedEdge> dir = new DirectedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> expected_result = new ArrayList<>();
		ArrayList<String> actual_result = new ArrayList<>();

		dir.addVertex("a");
		dir.addVertex("b");
		dir.addVertex("c");
		dir.addVertex("d");
		NamedWeightedEdge edge1 = dir.addEdge("a", "b");
		NamedWeightedEdge edge2 = dir.addEdge("a", "a");
		NamedWeightedEdge edge3 = dir.addEdge("b", "c");
		NamedWeightedEdge edge4 = dir.addEdge("c", "d");
		NamedWeightedEdge edge5 = dir.addEdge("a", "d");
		edge1.setWeight(2.0);
		edge2.setWeight(1.0);
		edge3.setWeight(3.0);
		edge4.setWeight(4.0);
		edge5.setWeight(5.0);
		
		_dh = (DijkstraModel) GKAModel.dijkstra(dir);
		
		expected_result.add("a");
		expected_result.add("d");
		actual_result = _dh.start("a", "d");
		System.out.println(actual_result);
		System.out.println(expected_result);
		assertEquals(actual_result,expected_result);
	}
	//MultiedgeTest
	@Test
	public void DirectedTest3() {
		Graph<String,NamedWeightedEdge> dir = new DirectedPseudograph<>(NamedWeightedEdge.class);
		ArrayList<String> expected_result = new ArrayList<>();
		ArrayList<String> actual_result = new ArrayList<>();

		dir.addVertex("a");
		dir.addVertex("b");
		dir.addVertex("c");
		dir.addVertex("d");
		NamedWeightedEdge edge1 = dir.addEdge("a", "b");
		NamedWeightedEdge edge2 = dir.addEdge("a", "a");
		NamedWeightedEdge edge3 = dir.addEdge("b", "c");
		NamedWeightedEdge edge4 = dir.addEdge("c", "d");
		NamedWeightedEdge edge5 = dir.addEdge("a", "d");
		NamedWeightedEdge edge6 = dir.addEdge("a", "d");
		edge1.setWeight(2.0);
		edge2.setWeight(1.0);
		edge3.setWeight(3.0);
		edge4.setWeight(4.0);
		edge5.setWeight(10.0);
		edge6.setWeight(5.0);
		
		_dh = (DijkstraModel) GKAModel.dijkstra(dir);
		
		expected_result.add("a");
		expected_result.add("d");
		actual_result = _dh.start("a", "d");
		System.out.println(actual_result);
		System.out.println(expected_result);
		assertEquals(actual_result,expected_result);
	}

}
