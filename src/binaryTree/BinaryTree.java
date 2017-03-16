package binaryTree;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
	private BNode<T> root;
	private boolean found;

	public BinaryTree() {
		this.root = null;
	}

	public boolean insert(T data) {
		BNode<T> curr = root;
		BNode<T> parent = root;
		BNode<T> newNode;
		if (root == null) {
			root = new BNode<T>(data);
		} else {
			while (curr != null) {
				if (data.compareTo(curr.getData()) < 0) {
					parent = curr;
					curr = curr.getLC();
				} else if (data.compareTo(curr.getData()) > 0) {
					parent = curr;
					curr = curr.getRC();
				} else
					return false; // duplicate value
			}
			newNode = new BNode<T>(data);
			if (newNode.compareTo(parent) < 0) {
				parent.setLC(newNode);
			} else
				parent.setRC(newNode);
		}
		return true;
	}

	// recursive insert
	public boolean insertRecur(T data) {
		BNode<T> tree = root;
		if (root == null) {
			root = new BNode<T>(data);
			return true;
		} else {
			return insertTryAgain(tree, data);
		}
	}

	// for recursive insert
	private boolean insertTryAgain(BNode<T> root, T data) {
		if (data.compareTo(root.getData()) < 0) {
			if (root.getLC() == null) {
				root.setLC(new BNode<T>(data));
				return true;
			} else {
				return insertTryAgain(root.getLC(), data);
			}
		} else if (data.compareTo(root.getData()) > 0) {
			if (root.getRC() == null) {
				root.setRC(new BNode<T>(data));
				return true;
			} else {
				return insertTryAgain(root.getRC(), data);
			}
		} else
			return false; // duplicate value
	}

	// recursive remove
	public boolean removeRec(T value) {
		root = removeNode(value, root);
		return found;
	}

	// used by remove methods
	private BNode<T> removeNode(T value, BNode<T> tree) {
		if (tree == null)
			found = false;
		else if (value.compareTo(tree.getData()) < 0)
			tree.setLC(removeNode(value, tree.getLC()));
		else if (value.compareTo(tree.getData()) > 0)
			tree.setRC(removeNode(value, tree.getRC()));
		else {
			tree = removeData(tree);
			found = true;
		}
		return tree;
	}

	// used by remove node - to remove the data
	private BNode<T> removeData(BNode<T> tree) {
		if (tree.getLC() == null)
			return tree.getRC();
		else if (tree.getRC() == null)
			return tree.getLC();
		else {
			T data = findPredecessor(tree.getLC());
			tree.setData(data);
			tree.setLC(removeNode(data, tree.getLC()));
			return tree;
		}
	}

	// used by remove
	private T findPredecessor(BNode<T> tree) {
		while (tree.getRC() != null) {
			tree = tree.getRC();
		}
		return tree.getData();
	}

	public BNode<T> get(T value) {
		BNode<T> currentNode = root;
		while (currentNode != null) {
			if (currentNode.getData().compareTo(value) > 0) {
				currentNode = currentNode.getLC();
			} else if (currentNode.getData().compareTo(value) < 0) {
				currentNode = currentNode.getRC();
			} else {
				return currentNode;
			}
		}
		return null;
	}

	// recursive get
	public T getRec(T value) {
		return getRec(value, root);
	}

	// used by recursive get
	private T getRec(T value, BNode<T> tree) {
		if (tree == null)
			return null;
		else if (value.compareTo(tree.getData()) < 0)
			return getRec(value, tree.getLC());
		else if (value.compareTo(tree.getData()) > 0)
			return getRec(value, tree.getRC());
		else
			return tree.getData();

	}

	public void traversePreOrder() {
		System.out.println(root.getData());
		traverseP(root.getLC());
		traverseP(root.getRC());
	}

	private void traverseP(BNode<T> root) {
		if (root == null)
			return; // anchor case
		System.out.println(root.getData());
		traverseP(root.getLC());
		traverseP(root.getRC());
	}

	// re-did the the traverse to return array list of ordered tree
	private ArrayList<T> traverseInOrder() {
		ArrayList<T> newList = new ArrayList<T>();
		traverseI(root.getLC(), newList);
		newList.add(root.getData());
		traverseI(root.getRC(), newList);
		return newList;
	}

	// used by the traverse to get the ordered list
	private void traverseI(BNode<T> root, ArrayList<T> newList) {
		if (root == null) {
			return;
		}
		traverseI(root.getLC(), newList);
		newList.add(root.getData());
		traverseI(root.getRC(), newList);
	}

	private void balanceTree(int low, int high, ArrayList<T> newList) {
		// left and right child
		if (low + 1 == high) {
			insert(newList.get(low));
			insert(newList.get(high));
		} else if (low == high) {
			insert(newList.get(low));
		} else {
			int mid = (low + high) / 2;
			insert(newList.get(mid));
			// get mid / insert the 'parent'
			// divide the list to get left and right recursively
			balanceTree(low, mid - 1, newList);
			balanceTree(mid + 1, high, newList);
		}

	}

	// first method called
	// recursively
	public void balanceTree() {
		ArrayList<T> sorted = traverseInOrder();
		root = null;
		// give it the tree and low high values
		balanceTree(0, sorted.size() - 1, sorted);
	}

}
