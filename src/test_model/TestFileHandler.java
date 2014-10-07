package test_model;

import static org.junit.Assert.*;
import impl_model.GKA;
import interface_model.FileHandler;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class TestFileHandler {

	@Test
	public void testFileHandlerCreation() {
		assertNotNull(GKA.fileHandler());
	}
	
	@Test
	public void saveAndLoad() throws IOException {
		ArrayList<String> 	graph 	= new ArrayList();
		graph.add("jojo");
		graph.add("test123");
		graph.add("321test");
		String				path	= "./test";
		FileHandler fh = GKA.fileHandler();
		fh.save_file(graph, path);
		fh.load_file(path);
		ArrayList loaded_graph = fh.get_content();
		assertTrue(graph.equals(loaded_graph));
	}

}
