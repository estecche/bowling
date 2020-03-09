package bowling.file;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import bowling.BowlingApp;
import bowling.source.ScoresDataSource;
import bowling.source.SingleLineData;

@RunWith(JUnit4.class)
public class ProcessStreamTest {

	@Mock
	private ReadFile readFileMocked;
	
	@Before
	public void setup() {
		readFileMocked = mock(ReadFile.class);
	}
	
	@Test
	public void testReadFile_anyFile() {
		when(readFileMocked.readFile()).thenReturn(null);
		
		ProcessStream testClass = new ProcessStream("");
		boolean result = testClass.readFile();
		
		assertThat(result, Is.is(true));
	}
	
	@Test
	public void testExtractData_NoData() {
		ReadFile readFile = new ReadFile("");
		ReadFile spyReadFile = spy(readFile);
		when(spyReadFile.readFile()).thenReturn(null);
		
		ProcessStream testClass = new ProcessStream("/scores3.txt");
		boolean result = testClass.readFile();
		assertThat(result, Is.is(true));
		
		ScoresDataSource scoresDS = testClass.extractData();
		assertThat(scoresDS, IsNull.nullValue());
	}
	
	@Test
	public void testExtractData_withData() {
		ArrayList<String> testData = new ArrayList<String>();
		testData.add("Player1	10");
		testData.add("Player2	10");
		Stream<String> streamString = testData.stream();

		URL url = getClass().getResource("/scores3.txt");
		ReadFile readFile = new ReadFile(url.getFile());
		ReadFile spyReadFile = spy(readFile);
		when(spyReadFile.readFile()).thenReturn(streamString);
		
		ProcessStream testClass = new ProcessStream(url.getFile());
		boolean result = testClass.readFile();
		assertThat(result, Is.is(true));
		
		ScoresDataSource scoresDS = testClass.extractData();
		assertThat(scoresDS, IsNull.notNullValue());
		assertThat(scoresDS.getLstScores(), IsNull.notNullValue());
		assertThat(scoresDS.getLstScores().size(), Is.is(35));
	}
	
	@Test
	public void testCreateNewDS_invalidPlayer() {
		String line = "Player1 | 10";
		String wrongLine = "";
		ScoresDataSource scoresDS = new ScoresDataSource();
		
		ProcessStream testClass = new ProcessStream("");
		testClass.createNewDS(line, scoresDS);
		testClass.createNewDS(wrongLine, scoresDS);
		
		assertThat(scoresDS.getLstScores().size(), Is.is(1));
		SingleLineData singleLine = scoresDS.getLstScores().get(0);
		assertThat(singleLine, IsNull.notNullValue());
		assertThat(singleLine.getPlayerName(), Is.is("INVALID PLAYER"));
	}
	
	@Test
	public void testCreateNewDS_validPlayer() {
		String line = "Player3	20";
		ScoresDataSource scoresDS = new ScoresDataSource();
		
		ProcessStream testClass = new ProcessStream("");
		testClass.createNewDS(line, scoresDS);
		
		assertThat(scoresDS.getLstScores().size(), Is.is(1));
	}
}
