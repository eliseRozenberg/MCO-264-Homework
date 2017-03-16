package billOrganizer;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T>implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void insert(T data) throws DuplicateDataException {
		if (contains(data)) {
			throw new DuplicateDataException();
		}
		if (this.isEmpty()) {
			add(data);
			return;
		}
		int index = 0;
		Iterator<T> itter = this.iterator();
		while (itter.hasNext()) {
			if (data.compareTo(itter.next()) <= 0) {
				add(index, data);
				return;
			}
			index++;
		}
		addLast(data);
	}

	public void insert(T data, Comparator<T> comparator) throws DuplicateDataException {
		if (contains(data))
			throw new DuplicateDataException();
		if (this.isEmpty()) {
			add(data);
			return;
		}
		int index = 0;
		Iterator<T> itter = this.iterator();
		while (itter.hasNext()) {
			if (comparator.compare(data, itter.next()) <= 0) {
				add(index, data);
				return;
			}
			index++;
		}
		addLast(data);
	}

	public boolean contains(T data) {
		Iterator<T> itter = this.iterator();
		while (itter.hasNext()) {
			if (itter.next().compareTo(data) == 0)
				return true;
		}
		return false;
	}
}
