package bowling.model;

import java.util.HashMap;

/**
 * Class that represents a line of 10 frames for a single player.
 */
public class Line {

	private HashMap<Integer, Frame> lstFrames;
	
	private String playerName;
	
	/**
	 * Class constructor.
	 * 
	 * @param playerName The name of the player.
	 */
	public Line(String playerName) {
		this.playerName = playerName;
		lstFrames = new HashMap<Integer, Frame>();
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public void addFrame(Frame frame) {
		lstFrames.put(frame.getNumber(), frame);
	}
	
	public Frame getFrame(int number) {
		return lstFrames.get(number);
	}
}