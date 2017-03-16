package doubleLL;

import java.io.Serializable;
import java.util.Iterator;

//use comparable also when it declares a certain  type
public class DoubleLLEIterator<T extends Serializable & Comparable<T>> implements Serializable, Iterator<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DoubleLinkNode<T> currentNode;
	private DoubleLinkNode<T> tail;

	public DoubleLLEIterator(DoubleLinkNode<T> tail) {
		this.tail = tail;
		currentNode = tail;
	}

	@Override
	public boolean hasNext() {
		if (currentNode == null)
			return false;
		else
			return true;
	}

	@Override
	public T next() {
		T data = currentNode.getData();
		currentNode = currentNode.getPrev();
		return data;
	}

	public void reset() {
		currentNode = tail;
	}

}
