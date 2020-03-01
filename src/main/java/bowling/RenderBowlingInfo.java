package bowling;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bowling.model.Frame;
import bowling.model.Line;

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
	 * Method that shows the lines per player.
	 */
	public void render() {
		for (String playerName : lstLines.keySet()) {
			Line line = lstLines.get(playerName);

			for (int i = 1; i <= 10; i++) {
				Frame frame = line.getFrame(i);
				if (frame == null)
					break;

				logger.info("Player: {} - Frame {} - pins: {} / {}", line.getPlayerName(), frame.getNumber(),
						frame.getFirstRoll().getPinsKnockedOver(),
						(frame.getSecondRoll() == null ? 0 : frame.getSecondRoll().getPinsKnockedOver()));
			}
		}
	}
}
