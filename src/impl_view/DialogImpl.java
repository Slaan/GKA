package impl_view;

import interface_view.AlgorithmWindow;
import interface_view.DialogWindow;

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

class DialogImpl extends JFrame implements DialogWindow {
	
	private			final	Dimension		_size = new Dimension(400, 150);
	private					JPanel			_panel;
	private					JButton			_startbutton;
	private					JTextField		_start_vertex_field;
	private					JTextField		_target_vertex_field;
	private					JTextArea		_resultTextArea;
	private					JTextArea		_accessesTextArea;
	private					JTextArea		_timeTextArea;
	
	// Creation
	public static DialogWindow create(String title) {
		return new DialogImpl(title);
	}
	
	private DialogImpl(String title) {
		setTitle(title);
		setSize(_size);
		_resultTextArea			= new JTextArea(1, 20);	
		_resultTextArea.enable(false);
		FlowLayout layout = new FlowLayout();
		_panel					= new JPanel(layout);
		layout.setAlignment(FlowLayout.TRAILING);
		_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		_panel.add(_resultTextArea);
		add(_panel);
		setVisible(true);
	}

	@Override
	public void setText(String text) {
		_resultTextArea.setText(text);
	}

}
