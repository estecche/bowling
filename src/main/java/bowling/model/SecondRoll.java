package bowling.model;

/**
 * Class that identifies the second roll for a frame. Since here you can't score a strike,
 * then only a spare, if any, is available.
 */
public class SecondRoll extends Roll {
	
	/**
	 * This specifies if the player hit a spare.
	 */
	private boolean spare;

	public boolean isSpare() {
		return spare;
	}
}