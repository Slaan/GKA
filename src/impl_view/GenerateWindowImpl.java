package impl_view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import interface_view.GenerateWindow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GenerateWindowImpl extends JFrame implements GenerateWindow {

	private			final	Dimension		_size = new Dimension(400, 150);
	private					JPanel			_panel;
	private					JButton			_generate_button;
	private					JTextField		_vertex_field;
	private					JTextField		_edges_field;
	private					ActionListener	_generate_listener;
	
	// Creation
	public static GenerateWindow create() {
		return new GenerateWindowImpl();
	}
	
	private GenerateWindowImpl() {
		setTitle("Generate Directed Graph");
		setSize(_size);
		_panel = new JPanel();
		_generate_button = new JButton("Generate");
		_vertex_field = new JTextField(10);
		_edges_field = new JTextField(10);
		_panel.add(_vertex_field);
		_panel.add(_edges_field);
		_panel.add(_generate_button);
		setVisible(true);
	}
	
	@Override
	public void addGenerateListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		_generate_listener = al;
	}

	@Override
	public String getVertexAmount() {
		return _vertex_field.getText();
	}

	@Override
	public String getEdgeAmount() {
		return _edges_field.getText();
	}

}
