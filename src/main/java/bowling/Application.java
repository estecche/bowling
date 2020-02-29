package bowling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
			logger.warn("### Score file not detected! Please include the name of the file with all the scores!");
			System.exit(1);
		}
		logger.warn("### Initiating the application... reading the file {}", args[0]);
		BowlingApp bowlingApp = new BowlingApp(args[0]);
		bowlingApp.showScoringTable();
	}
}