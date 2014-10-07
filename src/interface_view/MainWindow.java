package interface_view;

import java.awt.event.ActionListener;

import org.jgrapht.ListenableGraph;

public interface MainWindow {

	public void addLoadListener(ActionListener al);
	public void addSaveListener(ActionListener al);
	public void addBreadthFirstListener(ActionListener al);
	public void setGraph(ListenableGraph graph);
}
