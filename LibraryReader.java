package google;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	
	public static String readFileAsString(String fileName) {
	    String text = "";
	    try {
	      text = new String(Files.readAllBytes(Paths.get(String.format("/outputFiles/%s", fileName))));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return text;
	  }
}
