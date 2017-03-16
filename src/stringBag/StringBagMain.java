package stringBag;

import java.util.Scanner;

public class StringBagMain {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter string bag information: ");
		System.out.println("Name: ");
		String name = input.nextLine();
		System.out.println("Capacity: ");
		int capacity = input.nextInt();
		input.nextLine();
		StringBag b = new StringBag(name, capacity);
		System.out.println("String Bag " + b.getName() + " was made.");

		boolean found;
		int choice;
		String temp;

		while (true) {
			choice = menu();
			switch (choice) {
			case 1:// insert
				if (b.isFull()) {
					System.out.println("Bag is full.");
				} else {
					System.out.println("Type the string to be inserted:");
					temp = input.nextLine();
					b.insert(temp);
				}
				break;
			case 2:// remove
				if (b.isEmpty()) {
					System.out.println("Bag is empty.");
				} else {
					System.out.println(b.remove() + " was removed.");
				}
				break;
			case 3:// get size
				System.out.println("String Bag " + b.getName() + " contains " + b.size() + " entries.");
				break;
			case 4:// check if contains
				if (b.isEmpty()) {
					System.out.println("Bag is empty.");
				} else {
					System.out.println("Type the string to be checked.");
					found = b.contains(input.nextLine());
					if (found)
						System.out.println("String was found.");
					else
						System.out.println("String was not found.");
				}
				break;
			case 5:// check if full
				found = b.isFull();
				if (found)
					System.out.println("Bag is full.");
				else
					System.out.println("Bag is not full.");
				break;
			case 6:// display
				System.out.println(b);
				break;
			case 7:// clear
				b.clear();
				System.out.println("Bag cleared.");
				break;
			case 0:// exit
				input.close();
				System.out.println("Application Shutting Down...");
				System.exit(0);
				break;
			}
		}
	}

	public static int menu() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int temp;
		System.out.println(
				"1. INSERT \n2. REMOVE \n3. Get bag size \n4. Check if bag contains a string \n5. Check if full \n6. DISPLAY \n7. CLEAR \n0. EXIT");
		do {
			System.out.print("Enter valid selection: ");
			temp = in.nextInt();
		} while (temp < 0 || temp > 7);
		return temp;
	}

}
