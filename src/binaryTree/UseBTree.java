package binaryTree;

public class UseBTree {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		tree.insert(4);
		tree.insert(6);
		tree.insert(7);
		tree.insert(8);
		tree.insert(9);
		tree.insert(10);
		tree.insert(1000);

		System.out.println("PreOrder Before Balance");
		tree.traversePreOrder();
		tree.balanceTree();
		System.out.println("PreOrder After Balance Tree Recursive");
		tree.traversePreOrder();
		
		tree.removeRec(9);
		System.out.println("Pre-order after removing 9");
		tree.traversePreOrder();
		
		
		System.out.println("Print 9 if exists");
		System.out.println(tree.get(9));
		System.out.println("Print 10 if exists");
		System.out.println(tree.get(10).getData());
		
		tree.insert(49);
		tree.insert(300);
		tree.insert(50);
		tree.insert(7);

		System.out.println("PreOrder Before Balance");
		tree.traversePreOrder();
		tree.balanceTree();
		System.out.println("PreOrder After Balance Tree Recursive");
		tree.traversePreOrder();

	}
}