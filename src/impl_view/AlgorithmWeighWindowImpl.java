package impl_view;

import interface_view.AlgorithmWeighWindow;
import interface_view.AlgorithmWindow;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class AlgorithmWeighWindowImpl extends JFrame implements AlgorithmWeighWindow {
	
	private			final	Dimension		_size = new Dimension(400, 150);
	private					JPanel			_panel;
	private					JButton			_startbutton;
	private					JTextField		_start_vertex_field;
	private					JTextField		_target_vertex_field;
	private					JTextArea		_resultTextArea;
	private					JTextArea		_accessesTextArea;
	private					JTextArea		_timeTextArea;
	private					JTextArea		_weightTextArea;
	
	// Creation
	public static AlgorithmWeighWindow create(String title) {
		return new AlgorithmWeighWindowImpl(title);
	}
	
	private AlgorithmWeighWindowImpl(String title) {
		setTitle(title);
		setSize(_size);
		_startbutton 			= new JButton("Start");
		_start_vertex_field 	= new JTextField(10);
		_target_vertex_field 	= new JTextField(10);
		_resultTextArea			= new JTextArea(1, 20);
		_accessesTextArea		= new JTextArea(1, 20);
		_timeTextArea			= new JTextArea(1, 20);
		_weightTextArea			= new JTextArea(1, 20);
		_resultTextArea.enable(false);
		_accessesTextArea.enable(false);		
		_weightTextArea.enable(false);
		FlowLayout layout = new FlowLayout();
		_panel					= new JPanel(layout);
		layout.setAlignment(FlowLayout.TRAILING);
		_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		_panel.add(_start_vertex_field);
		_panel.add(_target_vertex_field);
		_panel.add(_startbutton);
		_panel.add(_resultTextArea);
		_panel.add(_accessesTextArea);
		_panel.add(_weightTextArea);
		_panel.add(_timeTextArea);
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
		_resultTextArea.setText(result);
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

	@Override
	public void setGraphAccesses(int last, int total) {
		_accessesTextArea.setText("Total: " + total + " Last :" + last);
	}

	@Override
	public void setTime(Double time) {
		_timeTextArea.setText("Time: " + time/100000000);
		
	}

	@Override
	public void setWeight(Double weight) {
		_weightTextArea.setText("Weight: " + weight);
	}

}
