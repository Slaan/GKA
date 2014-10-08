package impl_model;

import interface_model.FileHandlerModel;

import java.awt.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class FileHandlerModelImpl implements FileHandlerModel {

	private 		ArrayList<String>	_content;
	private final	Charset				_encoding;
	
	//Creation
	public static FileHandlerModel create() {
		
		return new FileHandlerModelImpl();
	}
	
	private FileHandlerModelImpl() {
		_encoding = StandardCharsets.UTF_8;
	}
	
	@Override
	public void save_file(ArrayList<String> content, String path) throws IOException {
		if(content == null) throw new NullPointerException("Content is null!");
		if(path  == null) 	throw new NullPointerException("Path is null!");
		_content = content;
		 Path java_path = Paths.get(path);
		 Files.write(java_path, _content, _encoding);
	}

	@Override
	public void load_file(String path) {
		if(path == null) throw new NullPointerException();
		Path java_path = Paths.get(path);
		try {
			_content = (ArrayList<String>) Files.readAllLines(java_path, _encoding);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<String> get_content() {
		if(_content == null) throw new NullPointerException("Content is null, read or save File first!");
		return new ArrayList<String>(_content);
	}

}
