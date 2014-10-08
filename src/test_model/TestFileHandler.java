package test_model;

import static org.junit.Assert.*;
import impl_model.GKAModel;
import interface_model.FileHandlerModel;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class TestFileHandler {

	@Test
	public void testFileHandlerCreation() {
		assertNotNull(GKAModel.fileHandler());
	}
	
	@Test
	public void saveAndLoad() throws IOException {
		ArrayList<String> 	graph 	= new ArrayList();
		graph.add("test of filehandler");
		graph.add("jojo");
		graph.add("test123");
		graph.add("321test");
		FileHandlerModel fh1 = GKAModel.fileHandler();
		String				path	= "./graphs/test/test_fh";
		fh1.save_file(graph, path);
		// Read with other FileHandler
		FileHandlerModel fh2 = GKAModel.fileHandler();
		fh2.load_file(path);
		ArrayList loaded_graph = fh2.get_content();
		assertTrue(graph.equals(loaded_graph));
	}

}
