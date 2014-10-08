package impl_controller;

import impl_model.GKAModel;
import interface_controller.FileHandler;
import interface_model.FileHandlerModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileHandlerController extends JFrame implements FileHandler {
	
	private 		FileHandlerModel 	_filehandler;
	private			JFileChooser		_filechooser;
	private			String				_path;
	
	// Creation
	public static FileHandler create() {
		return new FileHandlerController();
	}
	
	private FileHandlerController() {
		_filehandler = GKAModel.fileHandler();
		_filechooser = new JFileChooser();
	}

	// Accessors and Operations
	@Override
	public ArrayList<String> get_content() {
		if(_filehandler == null) throw new NullPointerException();
		return _filehandler.get_content();
	}

	@Override
	public void load() {
		if(_filehandler == null) throw new NullPointerException();
		if(_filechooser == null) throw new NullPointerException();
		_filechooser.showOpenDialog(this);
		_filechooser.setVisible(true);
		File file = _filechooser.getSelectedFile();
		_path	= file.getPath();
		_filehandler.load_file(_path); 
	}

	@Override
	public void save(ArrayList<String> content) {
		if(_filehandler == null) throw new NullPointerException();
		if(_filechooser == null) throw new NullPointerException();
		_filechooser.showSaveDialog(this);
		File file = _filechooser.getSelectedFile();
		_path = file.getPath();
		try {
			_filehandler.save_file(content, _path);
		} catch (IOException e) {
			System.err.println("Can not save File");
			e.printStackTrace();
		}
		
	}

	@Override
	public String get_path() {
		if(_path == null) throw new NullPointerException("Path is null, load or save file first");
		return _path;
	}
	
	// Test-purporses
	public static void main(String[] args) {
		FileHandler a = new FileHandlerController();
		a.load();
		System.out.println(a.get_content());
		// 'copy' file
		a.save(a.get_content());
	}
}
