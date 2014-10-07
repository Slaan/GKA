package interface_model;

import java.util.ArrayList;

public interface FileHandler {

	public void 				save_file(ArrayList<String> graph, String path);
	public void 				load_file(String path);
	public ArrayList<String>	get_content();
	
}
