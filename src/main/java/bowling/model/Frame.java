package bowling.model;

import bowling.BowlingApp;

/**
 * This represents each frame for a single player. The 10th frame can be a normal
 * Frame like this or a SpecialFrame in which a third roll is allowed.
 */
public class Frame {

	/**
	 * The first roll in a frame.
	 */
	private FirstRoll firstRoll;
	
	/**
	 * This defines the number of the frame.
	 */
	private int number;

	/**
	 * The score for the frame.
	 */
	private int score;

	/**
	 * The second roll in a frame.
	 */
	private SecondRoll secondRoll;
	
	public Frame(int number) {
		this.number = number;
	}
	
	/**
	 * Method to add the number of pins knocked over in the first roll.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public void addFirstRollPins(int pinsKnockedOver) {
		firstRoll = new FirstRoll(pinsKnockedOver);
	}
	
	public void addSecondRollPins(int pinsKnockedOver) {
		secondRoll = new SecondRoll(pinsKnockedOver);
		secondRoll.setSpare((firstRoll.getPinsKnockedOver() + pinsKnockedOver) == BowlingApp.NUMBER_OF_PINS ? true : false);
	}
	
	public FirstRoll getFirstRoll() {
		return firstRoll;
	}

	public int getNumber() {
		return number;
	}

	public int getScore() {
		return score;
	}

	public SecondRoll getSecondRoll() {
		return secondRoll;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
