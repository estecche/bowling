package bowling.source;

/**
 * This class represents a single data for a player, meaning the name of the
 * player and the number of pins knocked over.
 */
public class SingleLineData {

	/**
	 * Attribute that defines the number of pins knocked over.
	 */
	private int pinsKnockedOver;
	
	/**
	 * Attribute that defines the name of the player.
	 */
	private String playerName;
	
	/**
	 * Attribute that defines if the line of information is valid or not.
	 */
	private boolean validInfo;

	public int getPinsKnockedOver() {
		return pinsKnockedOver;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean isValidInfo() {
		return validInfo;
	}

	public void setPinsKnockedOver(int pinsKnockedOver) {
		this.pinsKnockedOver = pinsKnockedOver;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setValidInfo(boolean validInfo) {
		this.validInfo = validInfo;
	}	
}