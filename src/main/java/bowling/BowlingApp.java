package bowling;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.exceptions.ErrorCodes;
import bowling.model.Frame;
import bowling.model.Line;
import bowling.model.SpecialFrame;
import bowling.source.ScoresDataSource;
import bowling.source.SingleLineData;

/**
 * This is the app in which we will take what we need and produce the output.
 */
public class BowlingApp {

	/**
	 * The number of points awarded per spare.
	 */
	public static int ADDITIONAL_POINTS = 10;

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
	 * These are the lines in a single bowling match. The key for each line is the
	 * player name.
	 */
	private HashMap<String, Line> lstLines;

	/**
	 * The source of the data.
	 */
	private ScoresDataSource scoresDS;

	/**
	 * Class constructor.
	 * 
	 * @param scoresDS The source of the data to generate all the frames for each
	 *                 player.
	 */
	public BowlingApp(ScoresDataSource scoresDS) {
		this.scoresDS = scoresDS;
		lstLines = new HashMap<String, Line>();
	}

	/**
	 * Method that creates a new Frame and adds the related info.
	 * 
	 * @param frameNumber    The number of the frame.
	 * @param line           The line for the player in which the frame will be
	 *                       added.
	 * @param singleLineData The source with the data.
	 */
	private void createFrame(int frameNumber, Line line, SingleLineData singleLineData) {
		Frame frame = (frameNumber == BowlingApp.NUMBER_OF_FRAMES) ? new SpecialFrame() : new Frame(frameNumber);
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

	/**
	 * Method that generates all the Frames for each player in the data source.
	 * 
	 * @return If the frames were successfully generated.
	 */
	public boolean generateFrames() {
		if (!validateDataSource())
			return false;

		int frameNumber = 1;
		for (SingleLineData singleLineData : scoresDS.getLstScores()) {
			if (frameNumber > BowlingApp.NUMBER_OF_FRAMES) {
				logger.error(ErrorCodes.TOO_MUCH_DATA_AVAILABLE.getMessage());
				return false;
			}

			Line line = lstLines.get(singleLineData.getPlayerName());
			if (line == null)
				line = createLine(singleLineData);

			Frame frame = line.getFrame(frameNumber);

			if (frame != null && frameNumber == BowlingApp.NUMBER_OF_FRAMES) {
				if (frame.getSecondRoll() != null
						&& (frame.getFirstRoll().isStrike() || frame.getSecondRoll().isSpare())) {
					((SpecialFrame) frame).addThirdRollPins(singleLineData.getPinsKnockedOver());
					continue;
				}
			} else if (frame != null && ((frame.getFirstRoll() != null && frame.getFirstRoll().isStrike())
					|| (frame.getFirstRoll() != null && frame.getSecondRoll() != null))) {
				frameNumber++;
				frame = line.getFrame(frameNumber);
			}

			if (frame == null) {
				createFrame(frameNumber, line, singleLineData);
				continue;
			}

			// QQQ we are missing here when the 10th frames, all three are strikes. Also it
			// can be a strike
			// and then a spare (two more shots).
			if (frame.getSecondRoll() == null)
				frame.addSecondRollPins(singleLineData.getPinsKnockedOver());
		}
		return true;
	}

	public void generateScores() {
		for (String playerName : lstLines.keySet()) {
			Line line = lstLines.get(playerName);

			for (int i = 1; i <= BowlingApp.NUMBER_OF_FRAMES; i++) {
				Frame frame = line.getFrame(i);
				int previousScore = (i == 1) ? 0 : line.getFrame(i - 1).getScore();

				Frame nextFrame = line.getFrame(i + 1);
				Frame nextTwoFrames = line.getFrame(i + 2);

				if (frame.getFirstRoll().isStrike()) {
					if (nextFrame.getFirstRoll().isStrike() && nextTwoFrames.getFirstRoll().isStrike()) {
						frame.setScore((ADDITIONAL_POINTS * 3) + previousScore);
						continue;
					}

					if (nextFrame.getSecondRoll().isSpare()) {
						frame.setScore((ADDITIONAL_POINTS * 2) + previousScore);
						continue;
					}

					frame.setScore(ADDITIONAL_POINTS + previousScore + nextFrame.getFirstRoll().getPinsKnockedOver()
							+ nextFrame.getSecondRoll().getPinsKnockedOver());
					continue;
				}

				if (frame.getSecondRoll().isSpare()) {
					if (nextFrame.getFirstRoll().isStrike()) {
						frame.setScore(previousScore + ADDITIONAL_POINTS + ADDITIONAL_POINTS);
						continue;
					}

					frame.setScore(previousScore + ADDITIONAL_POINTS + nextFrame.getFirstRoll().getPinsKnockedOver());
					continue;
				}

				frame.setScore(previousScore + frame.getFirstRoll().getPinsKnockedOver()
						+ frame.getSecondRoll().getPinsKnockedOver());
			}
		}
	}

	/**
	 * Method that generates the bowling scoring table.
	 * 
	 * @return if the score table was generated or not.
	 */
	public boolean generateScoringTable() {
		if (scoresDS == null) {
			logger.error(ErrorCodes.NO_DATA_AVAILABLE.getMessage());
			return false;
		}

		if (scoresDS.getLstScores().size() < (BowlingApp.NUMBER_OF_FRAMES * 2)) {
			logger.error(ErrorCodes.MORE_DATA_EXPECTED.getMessage());
			return false;
		}

		if (!generateFrames())
			return false;

		generateScores();
		return true;
	}

	/**
	 * Method that returns the lines for all the players.
	 * 
	 * @return The lines for all players.
	 */
	public HashMap<String, Line> getLstLines() {
		return lstLines;
	}

	/**
	 * Method that validates if all the information found is valid.
	 * 
	 * @return if all the information is valid.
	 */
	public boolean validateDataSource() {
		SingleLineData singleLine = scoresDS.getLstScores().stream()
				.filter(singleLineData -> singleLineData.isValidInfo() == false).findAny().orElse(null);
		if (singleLine != null) {
			logger.error(ErrorCodes.WRONG_INFO_LOADED.getMessage());
			return false;
		}
		return true;
	}
}