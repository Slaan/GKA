package impl_controller;

import interface_controller.FileHandler;

public final class GKA {

	/*
	 * @returns a new FileHandler
	 */
	public static FileHandler fileHandler() {
		return FileHandlerController.create();
	}
}
