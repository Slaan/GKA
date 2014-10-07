package interface_model;

import java.io.IOException;
import java.util.ArrayList;

public interface FileHandler {

	/*
	 * @param graph string elements need to end with a semicolon
	 * @param path where file has to be
	 */
	public void 				save_file(ArrayList<String> graph, String path) throws IOException;
	
	/*
	 * @param path where file has to be
	 */
	public void 				load_file(String path);
	
	/*
	 * @returns content of opened file
	 */
	public ArrayList<String>	get_content();
	
}
