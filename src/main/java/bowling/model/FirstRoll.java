package bowling.model;

/**
 * This class represents the first roll for a frame.
 */
public final class FirstRoll extends Roll {
	
	/**
	 * This defines if the roll was a strike or not. It can only happen in the first roll.
	 */
	private boolean strike;

	public boolean isStrike() {
		return strike;
	}

	public void setStrike(boolean strike) {
		this.strike = strike;
	}
}