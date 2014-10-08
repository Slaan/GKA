package interface_controller;

import interface_model.FileHandlerModel;

import java.util.ArrayList;

public interface FileHandler {
	
		public String				get_path();
		public ArrayList<String> 	get_content();
		public void 				load();
		public void 				save(ArrayList<String> content);
}
