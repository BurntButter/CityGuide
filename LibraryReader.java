package google;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class LibraryReader {

	
	public static File LoadFiles(String s) {
		File f = new File("/outputFiles/%s", s);
		return f;
		
	}
	
	public static List<String> ReadFileNames(){
		File[] files = new File("/outputFiles/").listFiles();
		int length = files.length;
		List<String> fileNames = new LinkedList<String>();
		for(int i = 0; i < length; i++) {
			if(files[i].isFile()) {
				fileNames.add(files[i].getName());
			}
		}
		
		return fileNames;
	}
	
}
