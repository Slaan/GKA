package test_model;

import static org.junit.Assert.*;
import impl_model.GKAModel;
import impl_model.NaechstgelegnerKnotenAlgModelImpl;
import impl_model.NamedWeightedEdge;
import interface_model.NaechstgelegnerKnotenAlgModel;

import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	@Test
	public void testGrossenBaum() {
		Graph<String,NamedWeightedEdge> graph = new WeightedPseudograph<>(NamedWeightedEdge.class);
		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addVertex("d");
		graph.addVertex("e");
		graph.addVertex("f");
		
		NamedWeightedEdge a_b = new NamedWeightedEdge();
		a_b.setWeight(12.0);
		NamedWeightedEdge a_c = new NamedWeightedEdge();
		a_c.setWeight(16.0);
		NamedWeightedEdge a_d = new NamedWeightedEdge();
		a_d.setWeight(13.0);
		NamedWeightedEdge a_e = new NamedWeightedEdge();
		a_e.setWeight(15.0);
		NamedWeightedEdge a_f = new NamedWeightedEdge();
		a_f.setWeight(17.0);
		NamedWeightedEdge b_c = new NamedWeightedEdge();
		b_c.setWeight(14.0);
		NamedWeightedEdge b_d = new NamedWeightedEdge();
		b_d.setWeight(10.0);
		NamedWeightedEdge b_e = new NamedWeightedEdge();
		b_e.setWeight(13.0);
		NamedWeightedEdge b_f = new NamedWeightedEdge();
		b_f.setWeight(17.0);
		NamedWeightedEdge c_d = new NamedWeightedEdge();
		c_d.setWeight(18.0);
		NamedWeightedEdge c_e = new NamedWeightedEdge();
		c_e.setWeight(12.0);
		NamedWeightedEdge c_f = new NamedWeightedEdge();
		c_f.setWeight(14.0);
		NamedWeightedEdge d_e = new NamedWeightedEdge();
		d_e.setWeight(17.0);
		NamedWeightedEdge d_f = new NamedWeightedEdge();
		d_f.setWeight(17.0);		
		NamedWeightedEdge e_f = new NamedWeightedEdge();
		e_f.setWeight(12.0);
		
		graph.addEdge("a", "b", a_b);
		graph.addEdge("a", "c", a_c);
		graph.addEdge("a", "d", a_d);
		graph.addEdge("a", "e", a_e);
		graph.addEdge("a", "f", a_f);
		graph.addEdge("b", "c", b_c);
		graph.addEdge("b", "d", b_d);
		graph.addEdge("b", "e", b_e);
		graph.addEdge("b", "f", b_f);
		graph.addEdge("c", "d", c_d);
		graph.addEdge("c", "e", c_e);
		graph.addEdge("c", "f", c_f);
		graph.addEdge("d", "e", d_e);
		graph.addEdge("d", "f", d_f);
		graph.addEdge("e", "f", e_f);
//		1, 4, 2, 5, 3, 6, 1
//		weight 77
		ArrayList<String> expected_result;
		expected_result = new ArrayList<>(Arrays.asList("a", "b", "d", "e", "c", "f", "a"));

		_nka = GKAModel.naechstgelegnerKnotenAlg(graph);
		ArrayList<String> actual_result = _nka.start("a","a");
		assertEquals(expected_result, actual_result);
//		assertTrue(_msh.getWeight()==8.0);
		
	}
	

}