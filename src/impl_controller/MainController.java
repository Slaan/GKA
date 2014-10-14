package impl_controller;

import impl_view.GKAView;
import interface_controller.GraphHandler;
import interface_view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.ListenableGraph;

public class MainController {
	
	private static 	MainController 				_instance;
	private			MainWindow 					_window;
	private			GraphHandler				_graphhandler;
	private			ListenableGraph				_graph;
	
	// Creation
	public static MainController create() {
		if(_instance == null) {
			_instance = new MainController();
		}
		return _instance;
	}
	
	public MainController() {
		_window = GKAView.mainWindow();
		_graphhandler = GKA.graphHandler();
		_window.addLoadListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_graphhandler.load();
				_window.setGraph(_graphhandler.getGraph());
			}
		});
		_window.addSaveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//_graphhandler.set_graph(_graph);
					_graphhandler.save();
				} catch(Exception exception) {
					exception.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		MainController.create();
	}
}
