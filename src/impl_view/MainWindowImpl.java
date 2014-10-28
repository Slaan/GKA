package impl_view;

import impl_model.NamedWeightedEdge;
import interface_view.MainWindow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;

public class MainWindowImpl extends JFrame implements MainWindow {
	
	private	final	String										_title;
	private	final	Integer										_version;
	private			Dimension									_size;
	private 		Graph<String, NamedWeightedEdge>			_graph;
	private			mxGraphComponent 							_adapter_compo; 
	private         JGraphXAdapter<String, NamedWeightedEdge>   _adapter;
	private			JScrollPane									_pane;
	// Menu
	private 		JMenuBar 									_menubar;
	private			JMenu										_file_menu;
	private			JMenuItem									_load_item;
	private			JMenuItem									_save_item;
	private			JMenu										_edit_menu;
	private			JMenu										_algo_menu;
	private			JMenuItem									_breadth_item;
	private			JMenu										_version_menu;
	
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
	    file_menu.setMnemonic(KeyEvent.VK_F);
	    menubar.add(file_menu);
//	    JMenu     edit_menu     = edit_menu();
//	    edit_menu.setMnemonic(KeyEvent.VK_E);
//	    menubar.add(edit_menu);
	    JMenu     algo_menu     = algo_menu();
	    algo_menu.setMnemonic(KeyEvent.VK_A);
	    menubar.add(algo_menu);
	    JMenu     version_menu  = version_menu();
	    version_menu.setMnemonic(KeyEvent.VK_V);
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
	    _load_item = new JMenuItem("Load");
	    _load_item.setMnemonic(KeyEvent.VK_L);
	    // Save graph
	    _save_item = new JMenuItem("Save");
	    _save_item.setMnemonic(KeyEvent.VK_S);
	    file.add(_load_item);
	    file.add(_save_item);
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
		_breadth_item = new JMenuItem("Breadth First");
		_breadth_item.setMnemonic(KeyEvent.VK_B);
		algo.add(_breadth_item);
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
		_load_item.addActionListener(al);
	}

	@Override
	public void addSaveListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		_save_item.addActionListener(al);
	}

	// algorithm menu
	@Override
	public void addBreadthFirstListener(ActionListener al) {
		if(al == null) throw new NullPointerException();
		if(_breadth_item == null) throw new NullPointerException();
		_breadth_item.addActionListener(al);
	}

	// non-menu
	@Override
	public void setGraph(Graph<String, NamedWeightedEdge> graph) {
		if(graph == null) throw new NullPointerException();
		// put graph in listenablegraph?
		_graph = graph;
		JPanel panel = new JPanel();
		if(_pane != null) {
			remove(_pane);
		}
		_adapter = new JGraphXAdapter<String, NamedWeightedEdge>(_graph);
		_adapter_compo = new mxGraphComponent(_adapter);
		// since jgrapht prints directed graph by default we need
		// to change the printstyle
		if(graph instanceof UndirectedGraph<?, ?>) {
			mxGraphModel graphModel  = (mxGraphModel)_adapter_compo.getGraph().getModel(); 
			Collection<Object> cells =  graphModel.getCells().values(); 
			mxUtils.setCellStyles(_adapter_compo.getGraph().getModel(), 
			    cells.toArray(), mxConstants.STYLE_ENDARROW, mxConstants.NONE);
		}
        panel.add(_adapter_compo);
        panel.setMaximumSize(panel.getPreferredSize());
        //add(_panel,BorderLayout.CENTER);
        _pane = new JScrollPane(panel);
        _pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        _pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(_pane);
        mxCircleLayout layout = new mxCircleLayout(_adapter);
        layout.execute(_adapter.getDefaultParent());
        pack();
	}

	@Override
	public void setPath(String path) {
		if(path == null) throw new NullPointerException("Can't set path to null.");
		setTitle(_title + " - " + path);
	}

}
