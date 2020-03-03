package bowling.model;

import java.util.HashMap;

import bowling.BowlingApp;

/**
 * This class represents the 10th frame if it becomes an special frame like when
 * the player has a third shot.
 */
public class SpecialFrame extends Frame {

	private HashMap<Integer, Roll> lstRolls;

	/**
	 * Class constructor. The number is not sent here since this frame can only be
	 * used as the 10th frame.
	 */
	public SpecialFrame() {
		super(10);
		lstRolls = new HashMap<Integer, Roll>();
	}

	/**
	 * Method that adds the first roll in the 10th frame.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addFirstRoll(int pinsKnockedOver) {
		lstRolls.put(1, new FirstRoll(pinsKnockedOver));
	}
	
	@Override
	public void addFirstRollPins(int pinsKnockedOver) {
		addFirstRoll(pinsKnockedOver);
	}

	/**
	 * Method that adds a new roll.
	 * 
	 * @param rollNumber      if it's the second or third roll.
	 * @param pinsKnockedOver
	 */
	private void addRoll(int rollNumber, int pinsKnockedOver) {
		if (((FirstRoll) lstRolls.get(1)).isStrike()) {
			lstRolls.put(rollNumber, new FirstRoll(pinsKnockedOver));
		} else {
			lstRolls.put(rollNumber, new SecondRoll(pinsKnockedOver));
			boolean isSpare = (((FirstRoll) lstRolls.get(1)).getPinsKnockedOver()
					+ pinsKnockedOver) == BowlingApp.NUMBER_OF_PINS ? true : false;
			((SecondRoll) lstRolls.get(2)).setSpare(isSpare);
		}
	}

	/**
	 * Method that adds the second roll in the 10th frame.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addSecondRoll(int pinsKnockedOver) {
		addRoll(2, pinsKnockedOver);
	}

	@Override
	public void addSecondRollPins(int pinsKnockedOver) {
		addSecondRoll(pinsKnockedOver);
	}

	/**
	 * Method that adds the third roll in the 10th frame.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addThirdRoll(int pinsKnockedOver) {
		addRoll(3, pinsKnockedOver);
	}

	public HashMap<Integer, Roll> getLstRolls() {
		return lstRolls;
	}

	/**
	 * Method that returns a specific roll.
	 * 
	 * @param rollNumber The number of the roll.
	 * @return A single roll.
	 */
	public Roll getRoll(int rollNumber) {
		return lstRolls.get(rollNumber);
	}
}