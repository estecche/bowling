package bowling.file;

import static org.junit.Assert.assertThat;

import java.net.URL;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for the read file class.
 */
@RunWith(JUnit4.class)
public class ReadFileTest {

	@Test
	public void testReadFile_nonExistingFile() {
		String fileName = "./resources/non_existing_file.txt";
		ReadFile testClass = new ReadFile(fileName);
		
		assertThat(testClass.readFile(), IsNull.nullValue());
	}
	
	@Test
	public void testReadFile_existingFile() {
		URL url = getClass().getResource("/scores3.txt");
		assertThat(url, IsNull.notNullValue());
		
		String file = url.getFile();
		assertThat(file, IsNull.notNullValue());
		
		ReadFile testClass = new ReadFile(file);
		assertThat(testClass.readFile(), IsNull.notNullValue());
	}
}
