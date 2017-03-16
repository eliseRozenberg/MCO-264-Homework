package stringBag;

public class StringBagTest {
	public static void main(String[] args) {
		StringBag b = new StringBag("Name", 5);
		b.insert("Jeannine");
		b.insert("Dora");
		b.insert("Dora");
		b.insert("Jo-Anne");
		System.out.println(b.getLastIndex());
		if (b.isFull())
			System.out.println("full");
		else
			System.out.println(" not full");

		b.insert("Elise");
		System.out.println(b.getLastIndex());

		if (b.isFull())
			System.out.println("full");
		else
			System.out.println(" not full");
		System.out.println(b);

		while (!b.isEmpty()) {
			System.out.println(b.remove());
		}
		if (b.size() == 0)
			System.out.println("Bag Empty");
	}
}
