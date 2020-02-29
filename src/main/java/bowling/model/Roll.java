package bowling.model;

/**
 * This class will model a single Roll for a player.
 */
public abstract class Roll {

	/**
	 * This defines the number of pins knocked over.
	 */
	private int pinsKnockedOver;

	public int getPinsKnockedOver() {
		return pinsKnockedOver;
	}

	public void setPinsKnockedOver(int pinsKnockedOver) {
		this.pinsKnockedOver = pinsKnockedOver;
	}
}