package bowling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.exceptions.ErrorCodes;
import bowling.file.ProcessStream;

/**
 * Main application.
 */
public class Application {

	/**
	 * Application logger.
	 */
	private static Logger logger = LogManager.getLogger(Application.class);
	
	public static void main(String[] args) {
		if (args.length == 0) {
			logger.error(ErrorCodes.NO_SCORE_FILE.getMessage());
			System.exit(1);
		}
		logger.warn("### Initiating the application... reading the file {}", args[0]);
		
		ProcessStream processStream = new ProcessStream(args[0]);
		
		BowlingApp bowlingApp = new BowlingApp(processStream.extractData());
		if (bowlingApp.generateScoringTable()) {
			new RenderBowlingInfo(bowlingApp.getLstLines()).render();
		}
	}
}