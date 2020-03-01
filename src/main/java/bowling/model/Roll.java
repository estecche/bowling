package bowling.model;

/**
 * This class will model a single Roll for a player.
 */
public abstract class Roll {

	/**
	 * This defines the number of pins knocked over.
	 */
	private int pinsKnockedOver;

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
	}
}