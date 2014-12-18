package impl_view;

import interface_view.AlgorithmWeighWindow;
import interface_view.AlgorithmWindow;
import interface_view.DialogWindow;
import interface_view.GeneratorWindow;
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
	
	/**
	 * 
	 * @param title
	 * @return a window for weighted graph algorithms
	 */
	public static AlgorithmWeighWindow algorithmWeighWindow(String title) {
		if(title == null) throw new NullPointerException();
		return AlgorithmWeighWindowImpl.create(title);
	}
	
	/**
	 * 
	 * @return
	 */
	public static GeneratorWindow generatorWindow() {
		return GenerateWindowImpl.create();
	}
	
	/**
	 * 
	 * @param title of dialog window
	 * @return a new dialog window
	 */
	public static DialogWindow dialogWindow(String title) {
		if(title == null) throw new IllegalArgumentException();
		return DialogImpl.create(title);
	}
	
	public static BattleWindow generateBattle() {
		return BattleWindow.create();
	}
}
