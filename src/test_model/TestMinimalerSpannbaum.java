package test_model;

import static org.junit.Assert.*;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.MinimalerSpannbaumModel;

import java.util.Arrays;

import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Test;

public class TestMinimalerSpannbaum {

	@Test
	public void testMinimalerSpannbaum() {
		Graph<String, NamedWeightedEdge> 	graph 	= new WeightedPseudograph<>(NamedWeightedEdge.class);
		Graph<String, NamedWeightedEdge> 	minimal	= new WeightedPseudograph<>(NamedWeightedEdge.class);		
		for(String vertex : Arrays.asList("1","2","3","4")) {
			graph.addVertex(vertex);
			minimal.addVertex(vertex);
		}
		NamedWeightedEdge e1 = new NamedWeightedEdge();
		e1.setWeight(1.0);
		graph.addEdge("1", "2", e1);
		minimal.addEdge("1", "2", e1);
		NamedWeightedEdge e2 = new NamedWeightedEdge();
		e2.setWeight(2.0);
		graph.addEdge("2", "3", e2);
		minimal.addEdge("2", "3", e2);
		NamedWeightedEdge e3 = new NamedWeightedEdge();
		e3.setWeight(3.0);
		graph.addEdge("3", "4", e3);
		minimal.addEdge("3", "4", e3);
		NamedWeightedEdge e4 = new NamedWeightedEdge();
		e4.setWeight(4.0);
		graph.addEdge("1", "3", e4);
		NamedWeightedEdge e5 = new NamedWeightedEdge();
		e5.setWeight(5.0);
		graph.addEdge("2", "4", e5);
		NamedWeightedEdge e6 = new NamedWeightedEdge();
		e6.setWeight(6.0);
		graph.addEdge("1", "4", e6);
		MinimalerSpannbaumModel m = GKAModel.minimalerSpannbaum(graph);
		assertEquals(minimal, m.minimalerSpannbaum());
	}

	@Test
	public void testGraph2() {
		Graph<String,NamedWeightedEdge>		graph 		= new WeightedPseudograph<>(NamedWeightedEdge.class);
		Graph<String, NamedWeightedEdge> 	result_tree = new WeightedPseudograph<>(NamedWeightedEdge.class);
		for(String vertex : Arrays.asList("a", "b", "c", "d")) {
			graph.addVertex(vertex);
			result_tree.addVertex(vertex);
		}
		NamedWeightedEdge a_b = new NamedWeightedEdge();
		a_b.setWeight(2.0);
		graph.addEdge("a", "b", a_b);
		result_tree.addEdge("a", "b", a_b);
		NamedWeightedEdge a_c = new NamedWeightedEdge();
		a_c.setWeight(4.0);
		graph.addEdge("a", "c", a_c);
		NamedWeightedEdge a_d = new NamedWeightedEdge();
		a_d.setWeight(3.0);
		graph.addEdge("a", "d", a_d);
		NamedWeightedEdge b_c = new NamedWeightedEdge();
		b_c.setWeight(2.0);
		graph.addEdge("b", "c", b_c);
		result_tree.addEdge("b", "c", b_c);
		NamedWeightedEdge b_d = new NamedWeightedEdge();
		b_d.setWeight(3.0);
		graph.addEdge("b", "d", b_d);
		NamedWeightedEdge c_d = new NamedWeightedEdge();
		c_d.setWeight(1.0);
		graph.addEdge("c", "d", c_d);
		result_tree.addEdge("c", "d", c_d);
		MinimalerSpannbaumModel msm = GKAModel.minimalerSpannbaum(graph);
		assertEquals(result_tree, msm.minimalerSpannbaum());
	}
	
	@Test
	public void testGrossenBaum() {
		Graph<String, NamedWeightedEdge> graph = new WeightedPseudograph<>(NamedWeightedEdge.class);
		Graph<String, NamedWeightedEdge> spann = new WeightedPseudograph<>(NamedWeightedEdge.class);
		for(String v : Arrays.asList("a", "b", "c", "d", "e", "f")) {
			graph.addVertex(v);
			spann.addVertex(v);
		}
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
		
		spann.addEdge("a", "b", a_b);
		spann.addEdge("b", "d", b_d);
		spann.addEdge("b", "e", b_e);
		spann.addEdge("c", "e", c_e);
		spann.addEdge("e", "f", e_f);
		
		MinimalerSpannbaumModel msm = GKAModel.minimalerSpannbaum(graph);
		assertEquals(spann, msm.minimalerSpannbaum());
	}
}