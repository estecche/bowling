package bowling.model;

import java.util.HashMap;

import bowling.BowlingApp;

/**
 * This represents each frame for a single player. The 10th frame can be a
 * normal Frame like this or a SpecialFrame in which a third roll is allowed.
 */
public class Frame {

	/**
	 * This defines the number of the frame.
	 */
	private int number;

	/**
	 * This defines the rolls thrown by a player. The maximum number of rolls is
	 * two, but the 10th frame can have a different rule.
	 */
	HashMap<Integer, Roll> rolls;

	/**
	 * The score for the frame.
	 */
	private int score;

	/**
	 * This defines is the frame was a spare or not. One thing to take into
	 * consideration is that the 10th frame can have a strike and also a spare.
	 */
	private boolean spare;

	/**
	 * Class constructor.
	 * 
	 * @param number The number of the frame.
	 */
	public Frame(int number) {
		this.number = number;
		rolls = new HashMap<Integer, Roll>();
	}

	/**
	 * Method that adds the number of pins knocked over in the first roll.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addFirstRoll(int pinsKnockedOver) {
		Roll roll = new Roll(pinsKnockedOver);
		rolls.put(1, roll);
	}

	/**
	 * Method that adds the number of pins knocked over in the second roll.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addSecondRoll(int pinsKnockedOver) {
		Roll roll = new Roll(pinsKnockedOver);
		rolls.put(2, roll);
		setSpare(1, pinsKnockedOver);
	}

	public int getNumber() {
		return number;
	}

	public int getScore() {
		return score;
	}

	public boolean firstRollExists() {
		return (rolls.get(1) != null);
	}

	public boolean secondRollExists() {
		return (rolls.get(2) != null);
	}

	public boolean isFirstRollStrike() {
		return rolls.get(1).isStrike();
	}

	public int getFirstPinsKnockedOver() {
		return rolls.get(1).getPinsKnockedOver();
	}

	public int getSecondPinsKnockedOver() {
		return rolls.get(2).getPinsKnockedOver();
	}

	public boolean isSpare() {
		return spare;
	}

	public void setScore(int score) {
		this.score = score;
	}

	void setSpare(int previousRollNumber, int pinsKnockedOver) {
		if (rolls.get(previousRollNumber) == null)
			spare = false;
		else
			spare = (rolls.get(previousRollNumber).getPinsKnockedOver() + pinsKnockedOver) == BowlingApp.NUMBER_OF_PINS
					? true
					: false;
	}
}
