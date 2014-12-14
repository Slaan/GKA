package interface_view;

import impl_model.NamedWeightedEdge;

import java.awt.event.ActionListener;

import org.jgrapht.Graph;

public interface MainWindow {

	// files menu
	public void addLoadListener(ActionListener al);
	public void addSaveListener(ActionListener al);
	public void addGenerateListener(ActionListener al);
	public void addGenerateNetworkListener(ActionListener al);
	public void addGenerateTSPListener(ActionListener al);
	// edit menu
	// algorithm menu
	public void addDijkstraListener(ActionListener al);
	public void addBreadthFirstListener(ActionListener al);
	public void addFloydWarshallListener(ActionListener al);
	public void addFordFulkersonListener(ActionListener al);
	public void addEdmondsandKarpListener(ActionListener al);
	public void addMinimalerSpannbaumHeuristikListener(ActionListener al);
	public void addNaechstgelegnerKnotenListener(ActionListener al);
	// non-menu
	public void setGraph(Graph<String, NamedWeightedEdge> graph);
	public void setPath(String path);
}
