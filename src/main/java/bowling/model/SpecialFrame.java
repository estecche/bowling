package bowling.model;

/**
 * This class represents the 10th frame if it becomes an special frame like when
 * the player has a third shot.
 */
public class SpecialFrame extends Frame {

	/**
	 * Class constructor. The number is not sent here since this frame can only be
	 * used as the 10th frame.
	 */
	public SpecialFrame() {
		super(10);
	}

	/**
	 * Method that adds the second roll.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	@Override
	public void addSecondRoll(int pinsKnockedOver) {
		if (isFirstRollStrike()) {
			rolls.put(2, new Roll(pinsKnockedOver));
		} else {
			super.addSecondRoll(pinsKnockedOver);
		}
	}

	/**
	 * Method that adds the third roll in the 10th frame if any.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addThirdRoll(int pinsKnockedOver) {
		rolls.put(3, new Roll(pinsKnockedOver));
		if (isSecondRollStrike())
			setSpare(2, pinsKnockedOver);
	}

	/**
	 * Method that finds if the second roll was a strike or not.
	 * 
	 * @return if the second roll was a strike or not.
	 */
	public boolean isSecondRollStrike() {
		return rolls.get(2).isStrike();
	}
	
	public boolean isThirdRollStrike() {
		return rolls.get(3).isStrike();
	}
	
	public int getThirdPinsKnockedOver() {
		return rolls.get(3).getPinsKnockedOver();
	}
}