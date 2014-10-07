package impl_model;

import interface_model.FileHandler;
import interface_model.GraphHandler;

public final class GKAModel {

	/*
	 * @returns New FileHandler
	 */
	public static FileHandler fileHandler() {
		return FileHandlerImpl.create();
	}

	/*
	 * @returns New GraphHandler
	 */
	public static GraphHandler graphHandler() {
		return GraphHandlerImpl.create();
	}
}
