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

	/**
	 * Class constructor.
	 * 
	 * @param pinsKnockedOver The pins knocked over.
	 */
	public SecondRoll(int pinsKnockedOver) {
		super(pinsKnockedOver);
	}
	
	public boolean isSpare() {
		return spare;
	}

	public void setSpare(boolean spare) {
		this.spare = spare;
	}
}