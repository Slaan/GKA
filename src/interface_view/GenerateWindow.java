package interface_view;

import java.awt.event.ActionListener;

public interface GenerateWindow {

	/**
	 * 
	 * @param al is the listener for the generate button
	 */
	public void 	addGenerateListener(ActionListener al);
	
	/**
	 * 
	 * @return amount of vertexes to generate
	 */
	public String getVertexAmount();
	
	/**
	 * 
	 * @return amount of edges to generate
	 */
	public String getEdgeAmount();
	
}
