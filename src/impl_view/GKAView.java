package impl_view;

import interface_model.BreadthFirstModel;
import interface_view.AlgorithmWindow;
import interface_view.MainWindow;

public final class GKAView {
	
	/**
	 * @returns a new MainMenu
	 */
	public static MainWindow mainWindow() {
		return MainWindowImpl.create();
	}
	
	/**
	 * Creates a new algorithmWindow
	 */
	public static AlgorithmWindow algorithmWindow(String title) {
		if(title == null) throw new NullPointerException();
		return AlgorithmWindowImpl.create(title);
	}
}
