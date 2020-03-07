package bowling;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.model.Frame;
import bowling.model.Line;
import bowling.model.SpecialFrame;

/**
 * Class that renders the score table per player.
 */
public class RenderBowlingInfo {
	
	/**
	 * This defines the separator to be used when printing the scores.
	 */
	private final String SEPARATOR = "  |  ";

	private final String DOUBLE_SEPARATOR = SEPARATOR + SEPARATOR;

	private Logger logger = LogManager.getLogger(RenderBowlingInfo.class);

	/**
	 * All the lines to be shown.
	 */
	private HashMap<String, Line> lstLines;

	/**
	 * Class constructor.
	 * 
	 * @param lstLines All the lines that will be rendered.
	 */
	public RenderBowlingInfo(HashMap<String, Line> lstLines) {
		this.lstLines = lstLines;
	}

	private String getFullLine(int firstPinsKnockedOver, int secondPinsKnockedOver) {
		return firstPinsKnockedOver + SEPARATOR + secondPinsKnockedOver + SEPARATOR;
	}

	private String getSpareLine(int firstPinsKnockedOver) {
		return firstPinsKnockedOver + SEPARATOR + "/" + SEPARATOR;
	}

	private String getStrikeLine() {
		return SEPARATOR + "X" + SEPARATOR;
	}

	/**
	 * Method that shows the lines per player.
	 */
	public void render() {
		ArrayList<String> lstScores = new ArrayList<String>();
		lstScores.add("Frame" + SEPARATOR + "1" + DOUBLE_SEPARATOR + "2" + DOUBLE_SEPARATOR + "3" + DOUBLE_SEPARATOR
				+ "4" + DOUBLE_SEPARATOR + "5" + DOUBLE_SEPARATOR + "6" + DOUBLE_SEPARATOR + "7" + DOUBLE_SEPARATOR
				+ "8" + DOUBLE_SEPARATOR + "9" + DOUBLE_SEPARATOR + "10");

		for (String playerName : lstLines.keySet()) {
			Line line = lstLines.get(playerName);

			lstScores.add(line.getPlayerName());
			String frameInfo = "Pinfalls" + SEPARATOR;
			String scoreInfo = "Score" + SEPARATOR;

			for (int i = 1; i <= 10; i++) {
				Frame frame = line.getFrame(i);
				if (frame == null)
					break;

				if (frame.isFirstRollStrike()) {
					frameInfo += getStrikeLine();
				} else if (frame.isSpare()) {
					frameInfo += getSpareLine(frame.getFirstPinsKnockedOver());
				} else {
					frameInfo += getFullLine(frame.getFirstPinsKnockedOver(), frame.getSecondPinsKnockedOver());
				}
				scoreInfo += frame.getScore() + SEPARATOR + (frame.isFirstRollStrike() ? "" : SEPARATOR);
			}

			SpecialFrame frame = (SpecialFrame) line.getFrame(10);
			if (frame.isFirstRollStrike()) {
				frameInfo += getStrikeLine();
				if (frame.isSecondRollStrike()) {
					frameInfo += getStrikeLine();
					if (frame.isThirdRollStrike()) {
						frameInfo += getStrikeLine();
					} else {
						frameInfo += SEPARATOR + frame.getThirdPinsKnockedOver();
					}
				} else if (frame.isSpare()) {
					frameInfo += getSpareLine(frame.getSecondPinsKnockedOver());
				} else {
					frameInfo += getFullLine(frame.getSecondPinsKnockedOver(), frame.getThirdPinsKnockedOver());
				}
			} else {
				if (frame.isSpare()) {
					frameInfo += frame.getFirstPinsKnockedOver() + SEPARATOR + "/" + SEPARATOR;
					frameInfo += SEPARATOR + frame.getThirdPinsKnockedOver();
				} else {
					frameInfo += getFullLine(frame.getFirstPinsKnockedOver(), frame.getSecondPinsKnockedOver());
				}
			}

			lstScores.add(frameInfo);
			lstScores.add(scoreInfo);
		}

		for (String line : lstScores) {
			logger.info(line);
		}
	}
}
