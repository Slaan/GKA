package test_model;

import static org.junit.Assert.*;
import impl_model.NaechstgelegnerKnotenAlgModelImpl;
import impl_model.NamedWeightedEdge;
import interface_model.NaechstgelegnerKnotenAlgModel;

import org.junit.Assert.*;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Test;

public class TestNaechstgelegenerKnotenAlg {

	private NaechstgelegnerKnotenAlgModel _nka;
	
	
	@Test
	public void testNKA1() {
		Graph<String,NamedWeightedEdge> graph = new WeightedPseudograph<>(NamedWeightedEdge.class);
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addVertex("d");
		
		NamedWeightedEdge e1 = new NamedWeightedEdge();
		e1.setWeight(2.0);
		NamedWeightedEdge e2 = new NamedWeightedEdge();
		e2.setWeight(4.0);
		NamedWeightedEdge e3 = new NamedWeightedEdge();
		e3.setWeight(3.0);
		NamedWeightedEdge e4 = new NamedWeightedEdge();
		e4.setWeight(2.0);
		NamedWeightedEdge e5 = new NamedWeightedEdge();
		e5.setWeight(3.0);
		NamedWeightedEdge e6 = new NamedWeightedEdge();
		e6.setWeight(1.0);
		
		graph.addEdge("a", "b", e1);
		graph.addEdge("a", "c", e2);
		graph.addEdge("a", "d", e3);
		graph.addEdge("b", "c", e4);
		graph.addEdge("b", "d", e5);
		graph.addEdge("c", "d", e6);
		
		ArrayList<String> expected_result = new ArrayList<>();
		expected_result.add("a");
		expected_result.add("b");
		expected_result.add("c");
		expected_result.add("d");
		expected_result.add("a");

		_nka = new NaechstgelegnerKnotenAlgModelImpl(graph);
		ArrayList<String> actual_result = _nka.start("a",null);
		assertTrue(actual_result.equals(expected_result));
		assertTrue(_nka.getWeight()==8.0);
	}
}
