package impl_view;

import interface_view.BreadthFirstWindow;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class BreadthFirstWindowImpl extends JFrame implements BreadthFirstWindow {
	
	private			final	String			_title = "Breadth First";
	private			final	Dimension		_size = new Dimension(400, 100);
	private					JPanel			_panel;
	private					JButton			_startbutton;
	private					JTextField		_start_vertex_field;
	private					JTextField		_target_vertex_field;
	private					JTextArea		_result;
	
	// Creation
	public static BreadthFirstWindow create() {
		return new BreadthFirstWindowImpl();
	}
	
	private BreadthFirstWindowImpl() {
		setTitle(_title);
		setSize(_size);
		_startbutton 			= new JButton("Start");
		_start_vertex_field 	= new JTextField(10);
		_target_vertex_field 	= new JTextField(10);
		_result					= new JTextArea(2, 20);
		FlowLayout layout = new FlowLayout();
		_panel					= new JPanel(layout);
		layout.setAlignment(FlowLayout.TRAILING);
		_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		_panel.add(_start_vertex_field);
		_panel.add(_target_vertex_field);
		_panel.add(_startbutton);
		_panel.add(_result);
		
		add(_panel);
		setVisible(true);
	}

	@Override
	public void addStartButtonListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		_startbutton.addActionListener(al);
	}

	@Override
	public void setResultText(String result) {
		if(result == null) throw new NullPointerException();
		_result.setText(result);
	}

	@Override
	public String getStartVertex() {
		if(_start_vertex_field == null) throw new NullPointerException("Start vertex not initilized!");
		return _start_vertex_field.getText();
	}

	@Override
	public String getTargetVertex() {
		if(_target_vertex_field == null) throw new NullPointerException("Target vertex not initilized!");
		return _target_vertex_field.getText();
	}

}
