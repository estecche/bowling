package bowling;

import static org.junit.Assert.assertThat;

import org.apache.commons.lang.math.RandomUtils;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import bowling.source.ScoresDataSource;

@RunWith(JUnit4.class)
public class BowlingAppTest {

	@Test
	public void testValidateDataSource_invalidDS() {
		ScoresDataSource scoresDS = new ScoresDataSource();
		scoresDS.addNewLine("Player 1", "a");
		scoresDS.addNewLine("Player 1", "6");
		
		BowlingApp testClass = new BowlingApp(scoresDS);
		boolean result = testClass.validateDataSource();
		assertThat(result, Is.is(false));
		assertThat(testClass.generateFrames(), Is.is(false));
	}
	
	@Test
	public void testValidateDataSource_validDS() {
		ScoresDataSource scoresDS = new ScoresDataSource();
		scoresDS.addNewLine("Player 1", "5");
		
		BowlingApp testClass = new BowlingApp(scoresDS);
		boolean result = testClass.validateDataSource();
		assertThat(result, Is.is(true));
	}
	
	@Test
	public void testGetLstLines() {
		String player1 = "Player 1";
		String player2 = "Player 2";
		ScoresDataSource scoresDS = new ScoresDataSource();
		
		for (int i = 1; i<= 10; i++) {
			int firstPins = RandomUtils.nextInt(10);
			scoresDS.addNewLine(player1, firstPins + "");
			if (firstPins != 10)
				scoresDS.addNewLine(player1, (10 - firstPins) + "");
			
			firstPins = RandomUtils.nextInt(10);
			scoresDS.addNewLine(player2, firstPins + "");
			if (firstPins != 10)
				scoresDS.addNewLine(player2, (10 - firstPins) + "");
		}
		
		BowlingApp testClass = new BowlingApp(scoresDS);
		boolean result = testClass.generateFrames();
		assertThat(result, Is.is(true));
	}
}
