package impl_view;

import interface_view.MainWindow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jgrapht.ListenableGraph;

public class MainWindowImpl extends JFrame implements MainWindow {
	
	private	final	String				_title;
	private	final	Integer				_version;
	private			Dimension			_size;
	private 		JMenuBar 			_menubar;
	private			ActionListener 		_load_listener;
	private			ActionListener 		_save_listener;
	private			ActionListener		_breadth_listener;
	private 		ListenableGraph		_graph;
	
	// Creation
	public static MainWindow create() {
		return new MainWindowImpl();
	}
	
	private MainWindowImpl() {
		_version 	= 0;
		_title		= "GKA" + _version;
		_size		= new Dimension(600, 500);
		setTitle(_title);
		setSize(_size);
		initMenuBar();
	    setVisible(true);
	}
	
	private void initMenuBar() {
	    JMenuBar  menubar     = new JMenuBar();
	    JMenu     file_menu     = file_menu();
	    JMenu     edit_menu     = edit_menu();
	    JMenu     algo_menu     = algo_menu();
	    JMenu     version_menu  = version_menu();
	    edit_menu.setMnemonic(KeyEvent.VK_E);
	    file_menu.setMnemonic(KeyEvent.VK_F);
	    algo_menu.setMnemonic(KeyEvent.VK_A);
	    version_menu.setMnemonic(KeyEvent.VK_V);
	    menubar.add(file_menu);
	    menubar.add(edit_menu);
	    menubar.add(algo_menu);
	    menubar.add(version_menu);
	    setJMenuBar(menubar);
	}
	
	private JMenu file_menu() {
	    JMenu   file  = new JMenu("File");
	    // Close 
	    JMenuItem close = new JMenuItem("Close");
	    close.setMnemonic(KeyEvent.VK_E);
	    close.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	      }
	    });
	    // Load graph
	    JMenuItem   load = new JMenuItem("Load");
	    load.setMnemonic(KeyEvent.VK_L);
	    load.addActionListener(_load_listener);
	    // Save graph
	    JMenuItem   save = new JMenuItem("Save");
	    save.setMnemonic(KeyEvent.VK_S);
	    save.addActionListener(_save_listener);
	    file.add(load);
	    file.add(save);
	    file.add(close);
	    return file;
	  }
	
	private JMenu edit_menu() {
		JMenu edit = new JMenu("Edit");
		// file.add(add_vertex);
		// file.add(add_edge);
		// file.add(remove_vertex);
		// file.add(remove_edge);
		return edit;
	}
	
	private JMenu algo_menu() {
		JMenu algo = new JMenu("Algorithms");
		// breadth first 
		JMenuItem breadth = new JMenuItem("Breadth First");
		breadth.setMnemonic(KeyEvent.VK_B);
		breadth.addActionListener(_breadth_listener);
		algo.add(breadth);
		return algo;
	}
	
	private JMenu version_menu() {
		JMenu version = new JMenu("Version");
		JMenuItem detail = new JMenuItem("Version: " + _version);
		JMenuItem author1 = new JMenuItem("Alex Mantel");
		JMenuItem author2 = new JMenuItem("Daniel Hofmeister");
		version.add(detail);
		version.add(author1);
		version.add(author2);
		return version;
	}

	// file menu
	@Override
	public void addLoadListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		_load_listener = al;
	}

	@Override
	public void addSaveListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		_save_listener = al;
	}

	// edit menu
	// algorithm menu
	@Override
	public void addBreadthFirstListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		_breadth_listener = al;
	}

	// non-menu
	@Override
	public void setGraph(ListenableGraph graph) {
		if(graph == null) throw new NullPointerException();
		_graph = graph;
	}

	@Override
	public void setPath(String path) {
		if(path == null) throw new NullPointerException("Can't set path to null.");
		setTitle(_title + " - " + path);
	}

}
