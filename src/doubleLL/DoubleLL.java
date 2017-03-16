package doubleLL;

import java.io.Serializable;
import java.util.Iterator;

public class DoubleLL<T extends Serializable & Comparable<T>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DoubleLinkNode<T> head;
	private DoubleLinkNode<T> tail;

	public DoubleLL() {
		head = null;
		tail = null;
	}

	public void add(T data) {
		DoubleLinkNode<T> currentNode;
		DoubleLinkNode<T> prevNode;
		if (head == null) { // the first Node in the list points to the student
							// index record
			tail = head = new DoubleLinkNode<T>(data);
		} else {
			DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(data);
			// allocate a new Node
			// find the right spot for the Node inside the list
			currentNode = prevNode = head;

			while (currentNode != null && data.compareTo(currentNode.getData()) > 0) {
				prevNode = currentNode;
				currentNode = currentNode.getNext(); // move along to next Node
			}
			// found the right place , attach the links
			if (currentNode == head) {
				// new node will become the new head and point to current head
				newNode.setNext(head);
				head.setPrev(newNode);
				head = newNode;

			} else { // goes in between other node or at the end of the list
				prevNode.setNext(newNode);
				newNode.setPrev(prevNode);
				newNode.setNext(currentNode);

				if (newNode.getNext() == null) {
					tail = newNode;
				} else {
					currentNode.setPrev(newNode);
				}
			}

		}
	}

	public void remove(T data) throws NotFoundException {
		DoubleLinkNode<T> currentNode = head;
		DoubleLinkNode<T> prevNode = head;

		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				// this is the Node that must be removed
				if (currentNode == head) {
					head = head.getNext(); // must reset the head
					if (head == null) {
						tail = head;
					} else {
						head.setPrev(null);
						head.getNext().setPrev(head);
					}
					return;
				} else {
					prevNode.setNext(currentNode.getNext());
					currentNode.setPrev(null);
					if (prevNode.getNext() == null) {
						tail = prevNode;
					} else {
						prevNode.getNext().setPrev(prevNode);
					}
					return;
				}
			} else {
				// move further along in the list
				prevNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		throw new NotFoundException();
	}

	public T find(T data) throws NotFoundException {
		DoubleLinkNode<T> currentNode = head; // start to iterate through the
												// list
		while (currentNode != null) {
			if (currentNode.getData().equals(data)) {
				return currentNode.getData();
			}
			currentNode = currentNode.getNext(); // move further down the list
		}
		throw new NotFoundException();
	}

	public Iterator<T> iterator() {
		return new DoubleLLEIterator<T>(tail);
	}
}
