package impl_tests;

import impl_model.GKAModel;
import interface_model.GraphHandlerModel;

import java.io.IOException;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GraphHandlerImplTest {

	GraphHandlerModel gh;
	
	@Before
	public void SetUp() {
		gh = GKAModel.graphHandler();
	}
	
	@Test
	public void test() {
		try {
			gh.load();
			String s = gh.get_graph().edgeSet().toString();
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}