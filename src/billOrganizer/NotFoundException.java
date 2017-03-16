package billOrganizer;

public class NotFoundException extends Exception {
	public NotFoundException() {
		super("Node Not Found");
	}
}
