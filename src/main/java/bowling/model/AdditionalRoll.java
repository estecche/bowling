package bowling.model;

/**
 * This class defines the additional roll a player can have
 * in the 10th frame. It can represent a strike.
 */
public class AdditionalRoll extends FirstRoll {

	/**
	 * Class constructor.
	 * 
	 * @param pinsKnockedOver The number of pins knocked over.
	 */
	public AdditionalRoll(int pinsKnockedOver) {
		super(pinsKnockedOver);
	}
}