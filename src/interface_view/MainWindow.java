package interface_view;

import java.awt.event.ActionListener;

import org.jgrapht.Graph;

public interface MainWindow {

	// files menu
	public void addLoadListener(ActionListener al);
	public void addSaveListener(ActionListener al);
	// edit menu
	// algorithm menu
	public void addBreadthFirstListener(ActionListener al);
	// non-menu
	public void setGraph(Graph graph);
	public void setPath(String path);
}
