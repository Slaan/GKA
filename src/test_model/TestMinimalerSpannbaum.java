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

}