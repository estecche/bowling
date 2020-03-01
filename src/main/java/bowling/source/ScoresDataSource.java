package bowling.source;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that represents the data source from which all the scores will be calculated.
 */
public class ScoresDataSource {

	private Logger logger = LogManager.getLogger(ScoresDataSource.class);
	
	/**
	 * Attribute that defines the scores for each player. This list is not sorted or
	 * limited somehow. It will be used to put all the information into an object-oriented
	 * structure.
	 */
	private ArrayList<SingleLineData> lstScores;
	
	/**
	 * Class constructor.
	 */
	public ScoresDataSource() {
		lstScores = new ArrayList<SingleLineData>();
	}
	
	/**
	 * Method that adds a new line of information into the list.
	 * 
	 * @param playerName The name of the player.
	 * @param pinsKnockedOver The number of 
	 */
	public void addNewLine(String playerName, String pinsKnockedOver) {
		SingleLineData singleLineData = new SingleLineData();
		singleLineData.setPlayerName(playerName);
		
		try {
			if (StringUtils.equals("F", pinsKnockedOver)) {
				singleLineData.setPinsKnockedOver(0);
			} else {
				singleLineData.setPinsKnockedOver(Integer.valueOf(pinsKnockedOver));
			}
			singleLineData.setValidInfo(singleLineData.getPinsKnockedOver() >= 0 && singleLineData.getPinsKnockedOver() <= 10);
		} catch (Exception e) {
			logger.error("Something went wrong when reading the information! {}", e.getMessage());
			singleLineData.setValidInfo(false);
		}
		lstScores.add(singleLineData);
	}

	/**
	 * Method that returns the list of all the available scores.
	 * 
	 * @return A list with singleLineData objects.
	 */
	public ArrayList<SingleLineData> getLstScores() {
		return lstScores;
	}
}
