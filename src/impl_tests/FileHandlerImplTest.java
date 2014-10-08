package impl_tests;

import static org.junit.Assert.assertTrue;
import impl_controller.GKA;
import impl_model.FileHandlerImpl;
import impl_model.GKAModel;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileHandlerImplTest {
	
	private String _input_path;
	private String _output_path;
	private ArrayList<String> _example_graph;
	private FileHandler _fl;
	//To test:
	//public void 				save_file(ArrayList<String> graph, String path);
	//public void 				load_file(String path);
	//public ArrayList<String>	get_content();
	
	
	public FileHandlerImplTest() {
	     FileHandler _fl = GKAModel.fileHandler();
	}
	
	@Test
	public void testLoadFile() {
		String load_path = "../GKA/graphs/test_input.gka";
		ArrayList<String> expected = new ArrayList<String>();
		ArrayList<String> actual = new ArrayList<String>();
		expected.add("a->b");
		expected.add("b->c");
		expected.add("c->a");
		System.out.println(expected);
		_fl.load_file(load_path);
		actual = _fl.get_content();
		assertTrue(expected==actual);
	}
	
	@Rule public ExpectedException thrown = ExpectedException.none();
	
	@Test //(expected=NullPointerException.class)
	public void testLoadFileNullpointerExecption {
		thrown.expect(NullPointerException.class);
		_fl.load_file(null);
	}
	
	@Test
	public void testSaveFile() {
		String save_path = "../GKA/graphs/test_output.gka";
		ArrayList<String> test_graph = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		test_graph.add("a->b");
		test_graph.add("b->c");
		test_graph.add("c->a");
		_fl.save_file(test_graph, save_path);
		_fl.load_file(save_path);
		assertTrue(test_graph==_fl.get_content());
	}

}
