package billOrganizer;

public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
		super("invalid data...bill not added");
	}

}
