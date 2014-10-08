package impl_model;

import interface_model.FileHandlerModel;
import interface_model.GraphHandlerModel;

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
}
