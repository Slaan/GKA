package impl_model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.WeightedPseudograph;
import org.junit.Test;

public class TestOtherGraphHandlerModel {

	@Test
	public void testRemoveWhiteSpace() {
		OtherGraphHandlerModelImpl gh;
		gh = (OtherGraphHandlerModelImpl) OtherGraphHandlerModelImpl.create();
		assertEquals("abc", gh.remove_whitespaces("a b c"));
		assertEquals("ac", gh.remove_whitespaces("a \t c"));
	}

	@Test
	public void testFittingGraphDirected() {
		OtherGraphHandlerModelImpl gh;
		gh = (OtherGraphHandlerModelImpl) OtherGraphHandlerModelImpl.create();
		ArrayList<String> edges1 = new ArrayList<>();
		edges1.add("a->b");
		edges1.add("b->a");
		Graph actual = gh.fittingGraph(edges1);
		assertEquals(new DirectedWeightedPseudograph<>(NamedWeightedEdge.class), actual);
	}
	
	@Test
	public void testFittingGraphUndirected() {
		OtherGraphHandlerModelImpl gh;
		gh = (OtherGraphHandlerModelImpl) OtherGraphHandlerModelImpl.create();
		ArrayList<String> edges1 = new ArrayList<>();
		edges1.add("a--b");
		edges1.add("b--a");
		Graph actual = gh.fittingGraph(edges1);
		assertEquals(new WeightedPseudograph<>(NamedWeightedEdge.class), actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFittingGraphBoth() {
		OtherGraphHandlerModelImpl gh;
		gh = (OtherGraphHandlerModelImpl) OtherGraphHandlerModelImpl.create();
		ArrayList<String> edges1 = new ArrayList<>();
		edges1.add("a--b");
		edges1.add("b->a");
		Graph actual = gh.fittingGraph(edges1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFittingGraphNeither() {
		OtherGraphHandlerModelImpl gh;
		gh = (OtherGraphHandlerModelImpl) OtherGraphHandlerModelImpl.create();
		ArrayList<String> edges1 = new ArrayList<>();
		edges1.add("a-=b");
		edges1.add("b/=a");
		Graph actual = gh.fittingGraph(edges1);
	}
}
