package bowling.model;

/**
 * This class represents the first roll for a frame. Since there is no way
 * for a player to hit a spare in the first roll, we are just including if it's
 * a strike.
 */
public final class FirstRoll extends Roll {
	
	/**
	 * This defines if the roll was a strike or not. It can only happen in the first roll.
	 */
	private boolean strike;

	public boolean isStrike() {
		return strike;
	}

	@Override
	public void setPinsKnockedOver(int pinsKnockedOver) {
		super.setPinsKnockedOver(pinsKnockedOver);
		strike = true;
	}	
}