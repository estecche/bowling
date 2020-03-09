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
	
	public int getNextFrameSecondPinsKnockedOver(Frame frame, int i) {
		if (frame.getNumber() <= 8)
			return getFrame(i + 1).getSecondPinsKnockedOver();
		return ((SpecialFrame) getFrame(10)).getSecondPinsKnockedOver();
	}
	
	public boolean isNextFrameSpare(Frame frame, int i) {
		if (frame.getNumber() <= 9)
			return getFrame(i + 1).isSpare();
		return ((SpecialFrame) getFrame(10)).isSpare();
	}
	
	public boolean isNextFrameStrike(Frame frame, int i) {
		return isNextFrameStrike(frame, i, false);
	}
	
	public boolean isNextFrameStrike(Frame frame, int i, boolean isSpare) {
		if (frame.getNumber() <= 9) {
			return getFrame(i + 1).isFirstRollStrike();
		}
		SpecialFrame specialFrame = (SpecialFrame) getFrame(10);
		return (isSpare) ? specialFrame.isThirdRollStrike() : specialFrame.isSecondRollStrike();
	}
	
	public boolean isNextTwoFramesStrike(Frame frame, int i) {
		if (frame.getNumber() <= 8)
			return getFrame(i + 2).isFirstRollStrike();
		if (frame.getNumber() == 9)
			return ((SpecialFrame) getFrame(10)).isSecondRollStrike();
		return ((SpecialFrame) getFrame(10)).isThirdRollStrike();
	}
	
	public int getNextTwoFramesFirstPinsKnockedOver(Frame frame, int i) {
		if (frame.getNumber() <= 8)
			return getFrame(i + 2).getFirstPinsKnockedOver();
		if (frame.getNumber() == 9) {
			return ((SpecialFrame) getFrame(i + 1)).getSecondPinsKnockedOver();
		}
		return ((SpecialFrame) frame).getThirdPinsKnockedOver();
	}
	
	public int getNextFrameFirstPinsKnockedOver(Frame frame, int i) {
		if (frame.getNumber() <= 9)
			return getFrame(i + 1).getFirstPinsKnockedOver();
		return ((SpecialFrame) getFrame(10)).getSecondPinsKnockedOver();
	}
}