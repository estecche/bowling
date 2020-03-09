package bowling.model;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LineTest {
	
	@Test
	public void testGetNextFrameSecondPinsKnockedOver() {
		Frame frame1 = new Frame(1);
		frame1.addFirstRoll(10);
		
		Frame frame2 = new Frame(2);
		frame2.addFirstRoll(5);
		frame2.addSecondRoll(5);
		
		Line testClass = new Line("Player 1");
		testClass.addFrame(frame1);
		testClass.addFrame(frame2);
		
		int nextFrameSecondsPins = testClass.getNextFrameSecondPinsKnockedOver(frame1, 1);
		
		assertThat(testClass.getPlayerName(), Is.is("Player 1"));
		assertThat(nextFrameSecondsPins, Is.is(5));
		assertThat(testClass.isNextFrameSpare(frame1, 1), Is.is(true));
		assertThat(testClass.isNextFrameStrike(frame1, 1), Is.is(false));
	}
	
	@Test
	public void testGetNextFrameSecondsPinsKnockedOver_10thFrame() {
		Frame frame9 = new Frame(9);
		frame9.addFirstRoll(10);
		
		SpecialFrame frame10 = new SpecialFrame();
		frame10.addFirstRoll(5);
		frame10.addSecondRoll(5);
		
		Line testClass = new Line("Player 2");
		testClass.addFrame(frame9);
		testClass.addFrame(frame10);
		
		int nextFrameSecondPins = testClass.getNextFrameSecondPinsKnockedOver(frame9, 9);
		
		assertThat(nextFrameSecondPins, Is.is(5));
		assertThat(testClass.isNextFrameSpare(frame10, 10), Is.is(true));
	}
	
	@Test
	public void testIsNextFrameStrike() {
		Frame frame1 = new Frame(1);
		frame1.addFirstRoll(10);
		
		Frame frame2 = new Frame(2);
		frame2.addFirstRoll(10);
		
		Line testClass = new Line("Player 4");
		testClass.addFrame(frame1);
		testClass.addFrame(frame2);
		
		assertThat(testClass.isNextFrameStrike(frame1, 1), Is.is(true));
		assertThat(testClass.isNextFrameSpare(frame1, 1), Is.is(false));
	}
	
	@Test
	public void testIsNextFrameStrike_10thFrame_NoSpare() {
		SpecialFrame frame = new SpecialFrame();
		frame.addFirstRoll(10);
		frame.addSecondRoll(10);
		frame.addThirdRoll(10);
		
		Line testClass = new Line("Player 4");
		testClass.addFrame(frame);
		
		assertThat(testClass.isNextFrameStrike(frame, 10), Is.is(true));
		assertThat(testClass.isNextFrameSpare(frame, 10), Is.is(false));
	}
	
	@Test
	public void testIsNextFrameStrike_10thFrame_WithSpare() {
		SpecialFrame frame = new SpecialFrame();
		frame.addFirstRoll(8);
		frame.addSecondRoll(2);
		frame.addThirdRoll(10);
		
		Line testClass = new Line("Player 10");
		testClass.addFrame(frame);
		
		assertThat(testClass.isNextFrameStrike(frame, 10, true), Is.is(true));
	}
	
	@Test
	public void testIsNextTwoFramesStrike() {
		Frame frame1 = new Frame(7);
		frame1.addFirstRoll(10);
		
		Frame frame2 = new Frame(8);
		frame2.addFirstRoll(10);
		
		Frame frame3 = new Frame(9);
		frame3.addFirstRoll(10);
		
		SpecialFrame frame4 = new SpecialFrame();
		frame4.addFirstRoll(5);
		frame4.addSecondRoll(4);
		
		Line testClass = new Line("Player 5");
		testClass.addFrame(frame1);
		testClass.addFrame(frame2);
		testClass.addFrame(frame3);
		testClass.addFrame(frame4);
		
		assertThat(testClass.isNextFrameStrike(frame1, frame1.getNumber()), Is.is(true));
		assertThat(testClass.isNextTwoFramesStrike(frame1, frame1.getNumber()), Is.is(true));
		assertThat(testClass.isNextTwoFramesStrike(frame2, frame2.getNumber()), Is.is(false));
		assertThat(testClass.isNextTwoFramesStrike(frame3, frame3.getNumber()), Is.is(false));
		assertThat(testClass.isNextTwoFramesStrike(frame4, 10), Is.is(false));
		assertThat(testClass.getNextTwoFramesFirstPinsKnockedOver(frame2, frame2.getNumber()), Is.is(5));
	}
	
	@Test
	public void testIsNextTwoFramesStrike_9thFrame() {
		Frame frame3 = new Frame(9);
		frame3.addFirstRoll(10);
		
		SpecialFrame frame4 = new SpecialFrame();
		frame4.addFirstRoll(10);
		frame4.addSecondRoll(4);
		frame4.addThirdRoll(3);
		
		Line testClass = new Line("Player 5");
		testClass.addFrame(frame3);
		testClass.addFrame(frame4);
		
		assertThat(testClass.getNextTwoFramesFirstPinsKnockedOver(frame3, frame3.getNumber()), Is.is(4));
		assertThat(testClass.getNextTwoFramesFirstPinsKnockedOver(frame4, frame4.getNumber()), Is.is(3));
	}
	
	@Test
	public void testGetNextFrameFirstPinsKnockedOver() {
		Frame frame3 = new Frame(9);
		frame3.addFirstRoll(10);
		
		SpecialFrame frame4 = new SpecialFrame();
		frame4.addFirstRoll(10);
		frame4.addSecondRoll(4);
		frame4.addThirdRoll(3);
		
		Line testClass = new Line("Player 5");
		testClass.addFrame(frame3);
		testClass.addFrame(frame4);
		
		assertThat(testClass.getNextFrameFirstPinsKnockedOver(frame3, frame3.getNumber()), Is.is(10));
		assertThat(testClass.getNextFrameFirstPinsKnockedOver(frame4, frame4.getNumber()), Is.is(4));
	}
}
