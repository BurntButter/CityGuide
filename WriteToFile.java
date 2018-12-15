package google;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteToFile {

	public static String library = "SavedEvent-" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".json";

	public static void write(iSerialiazable n) throws IOException {
		
		String str = n.serialize();
		Charset utf8 = StandardCharsets.UTF_8;
		Files.write(Paths.get(String.format("/outputFiles/%s", library)), str.getBytes(utf8),
	    		StandardOpenOption.CREATE,StandardOpenOption.APPEND);
		
	}
	
	
}
