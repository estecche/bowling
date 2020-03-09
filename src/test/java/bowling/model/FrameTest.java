package bowling.model;

import static org.junit.Assert.assertThat;

import org.apache.commons.lang.math.RandomUtils;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FrameTest {

	@Test
	public void testAddFirstRoll() {
		int pinsKnockedOver = RandomUtils.nextInt();
		Frame testClass = new Frame(1);
		testClass.addFirstRoll(pinsKnockedOver);
		
		assertThat(testClass.rolls, IsNull.notNullValue());
		assertThat(testClass.rolls.size(), Is.is(1));
		assertThat(testClass.rolls.get(1), IsNull.notNullValue());
		assertThat(testClass.rolls.get(2), IsNull.nullValue());
		
		Roll roll = testClass.rolls.get(1);
		assertThat(roll.getPinsKnockedOver(), Is.is(pinsKnockedOver));
	}
	
	@Test
	public void testAddSecondRoll() {
		int pinsKnockedOver = RandomUtils.nextInt();
		Frame testClass = new Frame(2);
		testClass.addSecondRoll(pinsKnockedOver);
		
		assertThat(testClass.rolls, IsNull.notNullValue());
		assertThat(testClass.rolls.size(), Is.is(1));
		assertThat(testClass.rolls.get(1), IsNull.nullValue());
		assertThat(testClass.rolls.get(2), IsNull.notNullValue());
		assertThat(testClass.firstRollExists(), Is.is(false));
		assertThat(testClass.secondRollExists(), Is.is(true));
		
		Roll roll = testClass.rolls.get(2);
		assertThat(roll.getPinsKnockedOver(), Is.is(pinsKnockedOver));
	}
	
	@Test
	public void testStrike() {
		int pinsKnockedOver = 10;
		Frame testClass = new Frame(9);
		testClass.addFirstRoll(pinsKnockedOver);
		
		assertThat(testClass.isFirstRollStrike(), Is.is(true));
		assertThat(testClass.isSpare(), Is.is(false));
		assertThat(testClass.firstRollExists(), Is.is(true));
		assertThat(testClass.secondRollExists(), Is.is(false));
	}
	
	@Test
	public void testAddSecondRoll_withSpare() {
		int pinsKnockedOver = RandomUtils.nextInt(10);
		int secondPinsKnockedOver = 10 - pinsKnockedOver;

		Frame testClass = new Frame(4);
		testClass.addFirstRoll(pinsKnockedOver);
		testClass.addSecondRoll(secondPinsKnockedOver);
		
		assertThat(testClass.getNumber(), Is.is(4));
		assertThat(testClass.rolls, IsNull.notNullValue());
		assertThat(testClass.rolls.size(), Is.is(2));
		assertThat(testClass.rolls.get(1), IsNull.notNullValue());
		assertThat(testClass.rolls.get(2), IsNull.notNullValue());
		assertThat(testClass.isSpare(), Is.is(true));
	}
	
	@Test
	public void testAddSecondRoll_noSpare() {
		int pinsKnockedOver = RandomUtils.nextInt(10);
		int secondPinsKnockedOver = 9 - pinsKnockedOver;

		Frame testClass = new Frame(6);
		testClass.addFirstRoll(pinsKnockedOver);
		testClass.addSecondRoll(secondPinsKnockedOver);
		
		assertThat(testClass.rolls, IsNull.notNullValue());
		assertThat(testClass.rolls.size(), Is.is(2));
		assertThat(testClass.rolls.get(1), IsNull.notNullValue());
		assertThat(testClass.rolls.get(2), IsNull.notNullValue());
		assertThat(testClass.isSpare(), Is.is(false));
		
		assertThat(testClass.getFirstPinsKnockedOver(), Is.is(pinsKnockedOver));
		assertThat(testClass.getSecondPinsKnockedOver(), Is.is(secondPinsKnockedOver));
	}
	
	@Test
	public void testScore() {
		Frame testClass = new Frame(4);
		testClass.setScore(10);
		
		assertThat(testClass.getScore(), Is.is(10));
	}
}
