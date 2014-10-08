package impl_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.ListenableGraph;

import impl_model.GKAModel;
import impl_model.NamedWeightedEdge;
import impl_view.GKAView;
import interface_model.GraphHandlerModel;
import interface_view.MainWindow;

public class MainController {
	
	private static 	MainController 								_instance;
	private			MainWindow 									_window;
	private			GraphHandlerModel							_graphhandler;
	
	// Creation
	public static MainController create() {
		if(_instance == null) {
			_instance = new MainController();
		}
		return _instance;
	}
	
	public MainController() {
		_window = GKAView.mainWindow();
		_graphhandler = GKAModel.graphHandler();
		_window.addLoadListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_graphhandler.load();
					_window.setGraph(_graphhandler.get_graph());
				} catch (IOException e1) {
					System.err.println("Failed to load Graph.");
					e1.printStackTrace();
				}
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
