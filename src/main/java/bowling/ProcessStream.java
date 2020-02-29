package bowling;

import java.io.InputStream;

/**
 * This class will process the stream sent, in this case an InputStream.
 */
public class ProcessStream {

	private InputStream inputStream;
	
	/**
	 * Class constructor.
	 * 
	 * @param inputStream The input stream with the information to be read.
	 */
	public ProcessStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
}
