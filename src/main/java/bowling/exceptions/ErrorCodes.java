package bowling.exceptions;

/**
 * Class to keep error codes and its messages.
 */
public enum ErrorCodes {
	
	NO_DATA_AVAILABLE (1, "There is no data available! Please check the data source!"),
	
	NO_SCORE_FILE (2, "Score file not detected! Please include the name of the file with all the scores!"),
	
	WRONG_LINE_DATA (3, "An error ocurred while processing the line!"),
	
	TOO_MUCH_DATA_AVAILABLE (4, "There is more information than the one it should! Please check the data source!"),
	
	WRONG_INFO_LOADED (5, "There is an error with the data loaded! Please check the data a run the program again!"),
	
	MORE_DATA_EXPECTED (6, "There is less data than the expected! Please check the data source!");
	
	private int code;
	
	private String message;
	
	private ErrorCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}