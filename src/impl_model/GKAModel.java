package impl_model;

import interface_model.BreadthFirstModel;
import interface_model.FileHandlerModel;
import interface_model.GraphHandlerModel;

import org.jgrapht.ListenableGraph;

public final class GKAModel {

	/*
	 * @returns New FileHandler
	 */
	public static FileHandlerModel fileHandler() {
		return FileHandlerModelImpl.create();
	}

	/*
	 * @returns New GraphHandler
	 */
	public static GraphHandlerModel graphHandler() {
		return GraphHandlerModelImpl.create();
	}
	
	/*
	 * @returns New BreadthFirst
	 */
	public static BreadthFirstModel breadthFirst(ListenableGraph lg) {
		if(lg == null) throw new NullPointerException();
		return BreadthFirstModelImpl.create(lg);
	}
}
