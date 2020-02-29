package bowling.model;

/**
 * Class that identifies the second roll for a frame.
 */
public class SecondRoll extends Roll {
	
	private boolean spare;

	public boolean isSpare() {
		return spare;
	}

	public void setSpare(boolean spare) {
		this.spare = spare;
	}
	
}