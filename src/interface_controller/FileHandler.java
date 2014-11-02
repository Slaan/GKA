package interface_controller;

import java.util.ArrayList;

public interface FileHandler {
	
		/**
		 * @return path given in constructor
		 */
		public String				get_path();
		
		/**
		 * @return content of file 
		 */
		public ArrayList<String> 	get_content();
		
		/**
		 * Opens load dialog
		 */
		public void 				load();
		
		/**
		 * 
		 * @param content to store
		 */
		public void 				save(ArrayList<String> content);
}
