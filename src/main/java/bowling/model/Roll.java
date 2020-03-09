package bowling.model;

import bowling.BowlingApp;

/**
 * This class will model a single Roll for a player.
 */
public class Roll {

	/**
	 * This defines the number of pins knocked over.
	 */
	private int pinsKnockedOver;
	
	/**
	 * This defines if the roll was a strike or not.
	 */
	private boolean strike;
	
	/**
	 * Class constructor.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public Roll(int pinsKnockedOver) {
		this.pinsKnockedOver = pinsKnockedOver;
		setPinsKnockedOver(pinsKnockedOver);
	}
	
	public int getPinsKnockedOver() {
		return pinsKnockedOver;
	}

	void setPinsKnockedOver(int pinsKnockedOver) {
		this.pinsKnockedOver = pinsKnockedOver;
		strike = (pinsKnockedOver == BowlingApp.NUMBER_OF_PINS) ? true : false;
	}
	
	public boolean isStrike() {
		return strike;
	}
}