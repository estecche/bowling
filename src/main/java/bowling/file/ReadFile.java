package bowling.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to read the file based on the name provided.
 */
public class ReadFile {

	/**
	 * The name of the file.
	 */
	private String fileName;
	
	private Logger logger = LogManager.getLogger(ReadFile.class);
	
	/**
	 * Class constructor.
	 * 
	 * @param fileName The name of the file.
	 */
	public ReadFile(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Method to read the file and return the stream.
	 */
	public InputStream readFile() {
		try {
			FileInputStream inputStream = new FileInputStream(fileName);
			return inputStream;
		} catch (FileNotFoundException e) {
			logger.error("The file {} can't be read! Please double check the path and name of the file!", fileName);
			return null;
		}
	}
}
