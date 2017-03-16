package MMnecklaceMidterm;

import java.util.Iterator;
import java.util.LinkedList;

public class NecklaceIterator<Bead> implements Iterator<Bead> {

	private LinkedList<Bead> list;
	private Bead current;

	public NecklaceIterator(LinkedList<Bead> temp) {
		this.list = temp;
		current = list.getFirst();
	}

	@Override
	public boolean hasNext() {
		if (current == null) {
			return false;
		}
		return true;
	}

	@Override
	public Bead next() {
		if (hasNext()) {
			Bead bead = current;
			list.removeFirst();
			current = list.getFirst();
			return bead;
		}
		return null;
	}

}
