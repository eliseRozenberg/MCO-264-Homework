package billOrganizer;


import java.util.Comparator;
import java.util.Iterator;

public class PriorityQueue<T extends Comparable<T>> {
	private Comparator<T> comparator;
	private SortedLinkedList<T> list;


	public PriorityQueue(Comparator<T> comparator) {
		this.comparator = comparator;
		list = new SortedLinkedList<T>();
	}

	public void engueue(T data) throws DuplicateDataException {
		list.insert(data, comparator);
	}

	public T dequeue() {
		return list.remove();
	}

	public T peek() {
		return list.getFirst();
	}

	public boolean remove(T data) {
		Iterator<T> itter = list.iterator();
		while (itter.hasNext()) {
			if (itter.next().compareTo(data) == 0) {
				itter.remove();
				return true;
			}
		}
		return false;
	}

	public Iterator<T> iterator() {
		return list.iterator();

	}
}
