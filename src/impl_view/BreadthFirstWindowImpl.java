package impl_view;

import interface_view.BreadthFirstWindow;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BreadthFirstWindowImpl extends JFrame implements BreadthFirstWindow {
	
	private			final	String			_title = "Breadth First";
	private					JButton			_startbutton;
	private					ActionListener 	_startbutton_listener;
	
	// Creation
	public static BreadthFirstWindow create() {
		return new BreadthFirstWindowImpl();
	}
	
	private BreadthFirstWindowImpl() {
		// nop
	}

	@Override
	public void addStartButtonListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		
	}

	@Override
	public void setResultText(String result) {
		if(result == null) throw new NullPointerException();
		
	}

	@Override
	public String getStartVertex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTargetVertex() {
		// TODO Auto-generated method stub
		return null;
	}

}
