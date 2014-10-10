package impl_controller;

import interface_controller.FileHandler;
import interface_controller.GraphHandler;

public final class GKA {

	/*
	 * @returns a new FileHandler
	 */
	public static FileHandler fileHandler() {
		return FileHandlerController.create();
	}
	
	public static GraphHandler graphHandler() {
		return GraphHandlerController.create();
	}
}
