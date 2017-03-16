package billOrganizer;

public class DuplicateDataException extends Exception {
	public DuplicateDataException() {
		super("Duplicate Data...bill not added");
	}

}