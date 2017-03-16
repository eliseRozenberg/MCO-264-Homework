package doubleLL;

import java.util.Iterator;

public class DoubleLLMain {

	public static void main(String[] args) throws NotFoundException {
		DoubleLL<Integer> list = new DoubleLL<Integer>();
		list.add(5);
		list.add(10);
		list.add(15);
		list.add(20);
		list.add(21);
		list.add(22);
		list.add(23);
		list.add(7);
		list.add(50);
		list.add(24);
		list.add(25);
		list.add(30);

		Iterator<Integer> itter = list.iterator();
		while (itter.hasNext()) {
			System.out.println(itter.next());
		}
		System.out.println("__________");
		list.remove(10);

		((DoubleLLEIterator<Integer>) itter).reset();
		while (itter.hasNext()) {
			System.out.println(itter.next());
		}
		System.out.println("__________");
		list.remove(5);

		((DoubleLLEIterator<Integer>) itter).reset();
		while (itter.hasNext()) {
			System.out.println(itter.next());
		}
		System.out.println("__________");

		list.remove(30);
		((DoubleLLEIterator<Integer>) itter).reset();
		while (itter.hasNext()) {
			System.out.println(itter.next());
		}

		DoubleLL<Integer> list2 = new DoubleLL<Integer>();
		list2.add(44);
		list2.remove(44);
		((DoubleLLEIterator<Integer>) itter).reset();
		while (itter.hasNext()) {
			System.out.println(itter.next());
		}
	}

}
