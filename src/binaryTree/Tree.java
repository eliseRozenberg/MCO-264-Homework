package binaryTree;

import java.util.ArrayList;

public class Tree<T extends Comparable<T>> {

	private Node<T> root;

	public Tree() {
		this.root = null;
	}

	public T getRootData() {
		return root.getData();
	}

	public void insert(T value) {
		Node<T> newNode = new Node<T>(value);

		if (root == null) {
			root = newNode;
		} else {
			Node<T> currentNode = root;
			Node<T> parent;

			while (true) {

				parent = currentNode;
				if (value.compareTo(currentNode.data) < 0) {
					currentNode = currentNode.getLeftChild();

					if (currentNode == null) {
						parent.setLeftChild(newNode);
						return;
					}
				} else {

					currentNode = currentNode.getRightChild();
					if (currentNode == null) {
						parent.setRightChild(newNode);
						return;
					}
				}
			}
		}
	}

	public boolean remove(T data) {
		boolean found = false;
		root = removeNode(data, root);
		return found;
	}

	private Node<T> removeNode(T data, Node<T> tree) {
		if (tree != null) {
			if (data.compareTo(tree.getData()) < 0)
				tree.setLeftChild(removeNode(data, tree.getLeftChild()));
			else if (data.compareTo(tree.getData()) > 0)
				tree.setRightChild(removeNode(data, tree.getRightChild()));
			else {
				tree = removeData(tree);
			}
		}
		return tree;
	}

	private Node<T> removeData(Node<T> tree) {
		if (tree.getLeftChild() == null)
			return tree.getRightChild();
		else if (tree.getRightChild() == null)
			return tree.getLeftChild();
		else {
			T data = findPredecessor(tree.getLeftChild());
			tree.setData(data);
			tree.setLeftChild(removeNode(data, tree.getLeftChild()));
			return tree;
		}
	}

	private T findPredecessor(Node<T> tree) {
		while (tree.getRightChild() != null) {
			tree = tree.getRightChild();
		}
		return tree.getData();
	}

	// recursive get method
	public boolean getRecur(T data) {
		return getRecur(root, data);
	}

	private boolean getRecur(Node<T> node, T data) {
		if (node == null)
			return false;
		else if (node.getData().compareTo(data) == 0) {
			return true;
		} else if (node.getData().compareTo(data) > 0) {
			return getRecur(node.getLeftChild(), data);
		} else {
			return getRecur(node.getRightChild(), data);
		}
	}

	public void traversePreOrder() {
		System.out.println(root.getData());
		traverseP(root.getLeftChild());
		traverseP(root.getRightChild());
	}

	private void traverseP(Node<T> root) {
		if (root == null)
			return; // anchor case
		System.out.println(root.getData());
		traverseP(root.getLeftChild());
		traverseP(root.getRightChild());
	}

	// iterative get method
	public boolean getIter(T value) {
		if (get(value) == null) {
			return false;
		} else {
			return true;
		}
	}

	private Node<T> get(T value) {

		Node<T> currentNode = root;
		while (currentNode != null) {
			if (currentNode.getData().compareTo(value) > 0) {
				currentNode = currentNode.getLeftChild();
			} else if (currentNode.getData().compareTo(value) < 0) {
				currentNode = currentNode.getRightChild();
			} else {
				return currentNode;
			}
		}
		return null;
	}

	public void traverseInOrder() {
		traverseI(root.getLeftChild());
		System.out.println(root.getData());
		traverseI(root.getRightChild());
	}

	private void traverseI(Node<T> root) {
		if (root == null) {
			return;
		}
		traverseI(root.getLeftChild());
		System.out.println(root.getData());
		traverseI(root.getRightChild());
	}

	public void reorganizeTree() {

		root = arrayToBinaryTree(this.toArray());
	}

	private Node<T> arrayToBinaryTree(ArrayList<T> array) {
		if (array.size() == 0) {
			return null;
		}

		return arrayToBinaryTree(array, 0, array.size() - 1);
	}

	private Node<T> arrayToBinaryTree(ArrayList<T> array, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		Node<T> root = new Node<T>(array.get(mid));
		root.setLeftChild(arrayToBinaryTree(array, start, mid - 1));
		root.setRightChild(arrayToBinaryTree(array, mid + 1, end));

		return root;
	}

	private ArrayList<T> toArray() {
		ArrayList<T> array = new ArrayList<T>();
		return toArray(array, root);
	}

	private ArrayList<T> toArray(ArrayList<T> array, Node<T> root) {

		if (root == null) {
			return array;
		}
		toArray(array, root.getLeftChild());
		array.add(root.getData());
		toArray(array, root.getRightChild());
		return array;
	}

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>();

		tree.insert(100);
		tree.insert(20);
		tree.insert(3);
		tree.insert(400);
		tree.insert(5);
		tree.insert(1);
		tree.insert(1000);

		//boolean temp = tree.getIter(400);
		//System.out.println(temp);
		//System.out.println(tree.getRootData());
		tree.reorganizeTree();
		//System.out.println(tree.getRootData());

		tree.traversePreOrder();
		tree.insert(49);
		tree.insert(300);
		tree.insert(50);
		tree.insert(7);
		tree.reorganizeTree();
		//System.out.println(tree.getRootData());
		tree.traversePreOrder();
	}
}