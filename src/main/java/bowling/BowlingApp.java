package bowling;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.model.Frame;
import bowling.model.Line;
import bowling.source.ScoresDataSource;
import bowling.source.SingleLineData;

/**
 * This is the app in which we will take what we need and produce the output.
 */
public class BowlingApp {

	/**
	 * Attribute that defines the number of frames allowed in a game.
	 */
	public static int NUMBER_OF_FRAMES = 10;

	/**
	 * Attribute that defines the number of pins allowed in a game.
	 */
	public static int NUMBER_OF_PINS = 10;

	private Logger logger = LogManager.getLogger(BowlingApp.class);

	/**
	 * These are the lines in a single bowling match.
	 */
	private HashMap<String, Line> lstLines;
	
	/**
	 * The source of the data.
	 */
	private ScoresDataSource scoresDS;

	/**
	 * Class constructor.
	 * 
	 * @param scoresDS The source of the data to generate all the frames for each player.
	 */
	public BowlingApp(ScoresDataSource scoresDS) {
		this.scoresDS = scoresDS;
		lstLines = new HashMap<String, Line>();
	}

	/**
	 * Method that creates a new Frame and adds the related info.
	 * 
	 * @param frameNumber The number of the frame.
	 * @param line The line for the player in which the frame will be added.
	 * @param singleLineData The source with the data.
	 */
	private void createFrame(int frameNumber, Line line, SingleLineData singleLineData) {
		Frame frame = new Frame(frameNumber);
		line.addFrame(frame);
		frame.addFirstRollPins(singleLineData.getPinsKnockedOver());
	}

	/**
	 * Method that creates a new line for a player.
	 * 
	 * @param singleLineData The source with the data.
	 * @return The line created.
	 */
	private Line createLine(SingleLineData singleLineData) {
		Line line = new Line(singleLineData.getPlayerName());
		lstLines.put(singleLineData.getPlayerName(), line);
		return line;
	}
	
	public boolean generateFrames() {
		int frameNumber = 1;
		
		for (int i = 0; i < scoresDS.getLstScores().size(); i++) {
			SingleLineData singleLineData = scoresDS.getLstScores().get(i);
			if (!singleLineData.isValidInfo()) {
				logger.error("There is an error with the data loaded! Please check the info a run the program again!");
				return false;
			}
			
			Line line = lstLines.get(singleLineData.getPlayerName());
			if (line == null)
				line = createLine(singleLineData);
			
			Frame frame = line.getFrame(frameNumber);
			
			if (frame != null && ((frame.getFirstRoll() != null && frame.getFirstRoll().isStrike())
					|| (frame.getFirstRoll() != null && frame.getSecondRoll() != null))) {
				//This means that the player we've got is an existing one and the roll belongs to the next frame.
				frameNumber++;
				frame = line.getFrame(frameNumber);
			}
			
			if (frame == null) {
				createFrame(frameNumber, line, singleLineData);				
				continue;
			}
			
			if (frame.getSecondRoll() == null)
				frame.addSecondRollPins(singleLineData.getPinsKnockedOver());
			
			//QQQ remember that the 10th frame is very different.
		}
		return true;
	}
	
	/**
	 * Method that generates the bowling scoring table.
	 */
	public boolean generateScoringTable() {
		if (scoresDS == null)
			return false;
		
		return generateFrames();
	}
	
	public HashMap<String, Line> getLstLines() {
		return lstLines;
	}
}