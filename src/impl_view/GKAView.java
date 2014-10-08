package impl_view;

import interface_model.BreadthFirstModel;
import interface_view.BreadthFirstWindow;
import interface_view.MainWindow;

public final class GKAView {
	
	/*
	 * @returns a new MainMenu
	 */
	public static MainWindow mainWindow() {
		return MainWindowImpl.create();
	}
	
	/*
	 * @returns new BreadthFirstWindow
	 */
	public static BreadthFirstWindow breadthFirstWindow() {
		return BreadthFirstWindowImpl.create();
	}
}
