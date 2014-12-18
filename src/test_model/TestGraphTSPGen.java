package test_model;

import static org.junit.Assert.assertEquals;
import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import interface_model.GeneratorModel;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Before;
import org.junit.Test;

public class TestGraphTSPGen {

	private GeneratorModel					_gm;
	private Graph<String,NamedWeightedEdge> _graph;
	
	@Before
	public void setUp() {
		_gm = GKAModel.generator();
		_graph = _gm.genereateCompleteUndirectedGraph(10);
	}
	
	@Test
	public void TotalEdgesAndVertexTest() {
		assertEquals((9*9+9)/2,_graph.edgeSet().size());
		assertEquals(10,_graph.vertexSet().size());
	}
	
	@Test
	public void EdgesPerVertexTest() {
		for (String s : _graph.vertexSet()) {
			assertEquals(9,_graph.edgesOf(s).size());
		}
	}
}
