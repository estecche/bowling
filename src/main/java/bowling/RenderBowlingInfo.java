package bowling;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.model.FirstRoll;
import bowling.model.Frame;
import bowling.model.Line;
import bowling.model.Roll;
import bowling.model.SecondRoll;
import bowling.model.SpecialFrame;

/**
 * Class that renders the score table per player.
 */
public class RenderBowlingInfo {

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
	
	/**
	 * This defines the separator to be used when printing the scores.
	 */
	private final String SEPARATOR = "\t";
	
	private final String DOUBLE_SEPARATOR = SEPARATOR + SEPARATOR;

	/**
	 * Method that shows the lines per player.
	 */
	public void render() {
		ArrayList<String> lstScores = new ArrayList<String>();
		lstScores.add("Frame" + SEPARATOR + "1" + DOUBLE_SEPARATOR + "2" + DOUBLE_SEPARATOR + "3"
				+ DOUBLE_SEPARATOR + "4" + DOUBLE_SEPARATOR + "5" + DOUBLE_SEPARATOR + "6" + DOUBLE_SEPARATOR
				+ "7" + DOUBLE_SEPARATOR + "8" + DOUBLE_SEPARATOR + "9" + DOUBLE_SEPARATOR + "10");

		for (String playerName : lstLines.keySet()) {
			Line line = lstLines.get(playerName);

			lstScores.add(line.getPlayerName());
			String frameInfo = "Pinfalls" + SEPARATOR;
			String scoreInfo = "Score" + SEPARATOR;

			for (int i = 1; i <= 9; i++) {
				Frame frame = line.getFrame(i);
				if (frame == null)
					break;

				if (frame.getFirstRoll().isStrike()) {
					frameInfo += SEPARATOR + "X" + SEPARATOR;
				} else if (frame.getSecondRoll().isSpare()) {
					frameInfo += frame.getFirstRoll().getPinsKnockedOver() + SEPARATOR + "/" + SEPARATOR;
				} else {
					frameInfo += frame.getFirstRoll().getPinsKnockedOver() + SEPARATOR
							+ frame.getSecondRoll().getPinsKnockedOver() + SEPARATOR;
				}
				scoreInfo += frame.getScore() + SEPARATOR + (frame.getFirstRoll().isStrike() ? "" : SEPARATOR);
			}

			SpecialFrame frame = (SpecialFrame) line.getFrame(10);
			for (Integer rollNumber : frame.getLstRolls().keySet()) {
				Roll roll = frame.getLstRolls().get(rollNumber);
				if (roll instanceof FirstRoll) {
					if (((FirstRoll) roll).isStrike()) {
						frameInfo += SEPARATOR + "X" + SEPARATOR;
					}
				} else if (roll instanceof SecondRoll) {
					if (frame.getLstRolls().get(rollNumber - 1) instanceof FirstRoll) {
						FirstRoll firstRoll = (FirstRoll) frame.getLstRolls().get(rollNumber - 1);
						SecondRoll secondRoll = (SecondRoll) roll;
						if (secondRoll.isSpare()) {
							frameInfo += firstRoll.getPinsKnockedOver() + SEPARATOR + "/";
						} else {
							frameInfo += firstRoll.getPinsKnockedOver() + SEPARATOR + secondRoll.getPinsKnockedOver() + SEPARATOR;
						}
					} else {
						SecondRoll secondRoll = (SecondRoll) frame.getLstRolls().get(rollNumber - 1);
						SecondRoll secondRollCurrent = (SecondRoll) roll;
						if (secondRollCurrent.isSpare()) {
							frameInfo += secondRoll.getPinsKnockedOver() + SEPARATOR + "/";
						} else {
							frameInfo += secondRoll.getPinsKnockedOver() + SEPARATOR + secondRollCurrent.getPinsKnockedOver()
									+ SEPARATOR;
						}
					}
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
