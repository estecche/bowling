package bowling.file;

import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.exceptions.ErrorCodes;
import bowling.source.ScoresDataSource;

/**
 * This class will process the stream sent, in this case an InputStream.
 */
public class ProcessStream {

	/**
	 * Attribute that defines the stream with the information.
	 */
	private Stream<String> dataStream;

	/**
	 * Attribute that defines the name of the file to be processed.
	 */
	private String fileName;

	private Logger logger = LogManager.getLogger(ProcessStream.class);

	/**
	 * Class constructor.
	 * 
	 * @param fileName The name of the file to be read.
	 */
	public ProcessStream(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Method that performs the splitting of the information per line.
	 * 
	 * @param line    The line as a string with all the info per player.
	 * @param scoreDS The list to put all the info in.
	 */
	public void createNewDS(String line, ScoresDataSource scoreDS) {
		StringTokenizer strToken = new StringTokenizer(line, "\t");

		if (!strToken.hasMoreElements())
			scoreDS.addNewLine("INVALID PLAYER", "-1");

		try {
			scoreDS.addNewLine((String) strToken.nextElement(), (String) strToken.nextElement());
		} catch (Exception e) {
			logger.error(ErrorCodes.WRONG_LINE_DATA.getMessage());
		}
	}

	/**
	 * Method that extracts the information from the file and converts it into a
	 * object-oriented structure.
	 * 
	 * @return An object-oriented data structure.
	 */
	public ScoresDataSource extractData() {
		if (dataStream == null)
			return null;

		ScoresDataSource scoreDS = new ScoresDataSource();
		dataStream.forEach(line -> createNewDS(line, scoreDS));

		return scoreDS;
	}

	/**
	 * Method that reads the file and assigns to a data stream the values found.
	 */
	public boolean readFile() {
		ReadFile readFile = new ReadFile(fileName);
		dataStream = readFile.readFile();
		return true;
	}
}