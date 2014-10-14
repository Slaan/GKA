package impl_controller;

import impl_view.GKAView;
import interface_controller.BreadthFirst;
import interface_controller.GraphHandler;
import interface_view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.Graph;

public class MainController {
	
	private static 	MainController 				_instance;
	private			MainWindow 					_window;
	private			GraphHandler				_graphhandler;		
	private			Graph						_graph;
	
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
				_graph = _graphhandler.getGraph();
				_window.setGraph(_graph);
			}
		});
		_window.addSaveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_graphhandler.save();
				} catch(Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		// Since breadthFirst doesn't change something in mainwindow, we don't
		// need to store it as instance variable
		_window.addBreadthFirstListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GKA.breadthFirst(_graph);
			}
		});
	}
	
	public static void main(String[] args) {
		MainController.create();
	}
}
