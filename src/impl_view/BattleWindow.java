package impl_view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BattleWindow {

	private JFrame				_jframe;
	private JLabel				_load_text;
	private JLabel				_load_label;
	private JLabel				_comp_bezeichner;
	private JLabel				_nka_bezeichner;
	private JLabel				_msh_bezeichner;
	private	JLabel				_comp_total_timer;
	private	JLabel				_nka_total_timer;
	private JLabel				_msh_total_timer;
	private JLabel				_comp_average_timer;
	private JLabel				_nka_average_timer;
	private JLabel				_msh_average_timer;
	private JLabel				_comp_total_distance;
	private JLabel				_nka_total_distance;
	private JLabel				_msh_total_distance;
	private JLabel				_comp_average_distance;
	private JLabel				_nka_average_distance;
	private JLabel				_msh_average_distance;
	private JButton				_start;
	private JButton				_close;
	
	public static BattleWindow create() {
		return new BattleWindow();
	}
	
	public BattleWindow() {
		_jframe = new JFrame("Battle Royale with Cheese");
		_jframe.setSize(600, 400);
		_jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = _jframe.getContentPane();
		con.setLayout(new GridLayout(0,3));
		_load_text = new JLabel("Ladefortschritt:");
		con.add(_load_text);
		_load_label = new JLabel(0+"%");
		con.add(_load_label);
		con.add(new JLabel(""));
		_comp_bezeichner = new JLabel("");
		con.add(_comp_bezeichner);
		_nka_bezeichner = new JLabel("NächstgelenerKnotenAlg");
		con.add(_nka_bezeichner);
		_msh_bezeichner = new JLabel("MinimalerSpannbaumAlg");
		con.add(_msh_bezeichner);
		_comp_total_timer = new JLabel("Gesamtzeit: ");
		con.add(_comp_total_timer);
		_nka_total_timer = new JLabel("0");
		con.add(_nka_total_timer);
		_msh_total_timer = new JLabel("0");
		con.add(_msh_total_timer);
		_comp_average_timer = new JLabel("Durchschnittszeit: ");
		con.add(_comp_average_timer);
		_nka_average_timer = new JLabel("0");
		con.add(_nka_average_timer);
		_msh_average_timer = new JLabel("0");
		con.add(_msh_average_timer);
		_comp_total_distance = new JLabel("Gesamtdistanz: ");
		con.add(_comp_total_distance);
		_nka_total_distance = new JLabel("0");
		con.add(_nka_total_distance);
		_msh_total_distance = new JLabel("0");
		con.add(_msh_total_distance);
		_comp_average_distance = new JLabel("Durchschnittsdistanz: ");
		con.add(_comp_average_distance);
		_nka_average_distance = new JLabel("0");
		con.add(_nka_average_distance);
		_msh_average_distance = new JLabel("0");
		con.add(_msh_average_distance);
		_start = new JButton("Start");
		con.add(_start);
		_close = new JButton("Close");
		con.add(_close);
		_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
	}
	
	public void setNKATotalTime(Double time) {
		_nka_total_timer.setText(time+"ms");
	}
	
	public void setMSHTotalTime(Double time) {
		_msh_total_timer.setText(time+"ms");
	}
	
	public void setNKAAverageTime(Double time) {
		_nka_average_timer.setText(time+"ms");
	}
	
	public void setMSHAverageTime(Double time) {
		_msh_average_timer.setText(time+"ms");
	}
	
	public void setNKATotalDistance(Double distance) {
		_nka_total_distance.setText(""+distance);
	}
	
	public void setMSHTotalDistance(Double distance) {
		_msh_total_distance.setText(""+distance);
	}
	
	public void setNKAAverageDistance(Double distance) {
		_nka_average_distance.setText(""+distance);
	}

	public void setMSHAverageDistance(Double distance) {
		_msh_average_distance.setText(""+distance);
	}
	
	public void setCounter(Integer i) {
		_load_label.setText(i+"%");
	}
	
	public void visible() {
		_jframe.setVisible(true);
	}
	
	public void Invisible() {
		_jframe.setVisible(false);
	}
	
	public void close() {
		_jframe.dispose();
	}
	
	public JButton getStartButton() {
		return _start;
	}
	
	public void update() {
		_jframe.revalidate();
		_jframe.repaint();
	}
}
