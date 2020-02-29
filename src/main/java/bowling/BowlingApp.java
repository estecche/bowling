package bowling;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.file.ReadFile;

/**
 * This is the app in which we will take what we need and produce the output.
 */
public class BowlingApp {
	
	private Logger logger = LogManager.getLogger(BowlingApp.class);
	
	/**
	 * This is the stream with all the information to be processed.
	 */
	private String fileName;

	/**
	 * Class constructor.
	 */
	public BowlingApp(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Method that prints the bowling scoring table.
	 */
	public void showScoringTable() {
		ReadFile readFile = new ReadFile(fileName);
		InputStream inputStream = readFile.readFile();
		if (inputStream == null)
			return;
		
		ProcessStream processStream = new ProcessStream(inputStream);
		
	}
}