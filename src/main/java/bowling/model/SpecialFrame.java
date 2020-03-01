package bowling.model;

/**
 * This class represents the 10th frame if it becomes an special frame
 * like when the player has a third shot.
 */
public class SpecialFrame extends Frame {

	private AdditionalRoll additionalRoll;
	
	/**
	 * Class constructor. The number is not sent here since this frame
	 * can only be used as the 10th frame.
	 */
	public SpecialFrame() {
		super(10);
	}

	/**
	 * Method that adds the number of pins knocked over by the player.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addThirdRollPins(int pinsKnockedOver) {
		additionalRoll = new AdditionalRoll(pinsKnockedOver);
	}
}
