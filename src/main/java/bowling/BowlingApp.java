package bowling;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.file.ProcessStream;
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

	/**
	 * Attribute that defines name of the file to be processed.
	 */
	private String fileName;
	
	private Logger logger = LogManager.getLogger(BowlingApp.class);

	/**
	 * These are the lines in a single bowling match.
	 */
	private HashMap<String, Line> lstLines;

	/**
	 * Class constructor.
	 * 
	 * @param fileName The name of the file with the data.
	 */
	public BowlingApp(String fileName) {
		this.fileName = fileName;
		lstLines = new HashMap<String, Line>();
	}

	/**
	 * Method that prints the bowling scoring table.
	 */
	public HashMap<String, Line> getScoringTable() {
		ProcessStream processStream = new ProcessStream(fileName);
		ScoresDataSource scoresDS = processStream.extractData();
		if (scoresDS == null)
			return null;
		
		generateFrames(scoresDS);
		
		return lstLines;
	}
	
	private void createFrame(int frameNumber, Line line, SingleLineData singleLineData) {
		Frame frame = new Frame(frameNumber);
		line.addFrame(frame);
		frame.addFirstRollPins(singleLineData.getPinsKnockedOver());
	}
	
	private Line createLine(SingleLineData singleLineData) {
		Line line = new Line(singleLineData.getPlayerName());
		lstLines.put(singleLineData.getPlayerName(), line);
		return line;
	}
	
	public void generateFrames(ScoresDataSource scoresDS) {
		int frameNumber = 1;
		
		for (int i = 0; i < scoresDS.getLstScores().size(); i++) {
			SingleLineData singleLineData = scoresDS.getLstScores().get(i);
			
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
		
		for (String playerName : lstLines.keySet()) {
			Line line = lstLines.get(playerName);
			
			for (int i = 1; i <= 10; i++) {
				Frame frame = line.getFrame(i);
				if (frame == null)
					break;
				
				logger.info("Player: {} - Frame {} - pins: {} / {}", 
						line.getPlayerName(),
						frame.getNumber(), 
						frame.getFirstRoll().getPinsKnockedOver(),
						(frame.getSecondRoll() == null ? 0 : frame.getSecondRoll().getPinsKnockedOver()));
			}
		}
	}
}