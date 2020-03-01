package bowling;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.file.ReadFile;

/**
 * This is the app in which we will take what we need and produce the output.
 */
public class BowlingApp {
	
	/**
	 * The number of frames allowed in a game.
	 */
	public static int NUMBER_OF_FRAMES = 10;
	
	/**
	 * The number of pins allowed in a game.
	 */
	public static int NUMBER_OF_PINS = 10;
	
	/**
	 * This is the stream with all the information to be processed.
	 */
	private String fileName;
	
	private Logger logger = LogManager.getLogger(BowlingApp.class);

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