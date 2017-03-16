package ticTacToe;

import java.util.Scanner;

import school.InvalidDataException;

public class GameMain {

	public static void main(String[] args) throws InvalidDataException {
		Scanner in = new Scanner(System.in);
		String name1, name2;
		int choice;
		boolean win;

		System.out.println("Enter information for player X:");
		System.out.print("Name: ");
		name1 = in.nextLine();

		System.out.println("Enter information for player Y:");
		System.out.print("Name: ");
		name2 = in.nextLine();
		Table table = new Table(name1, name2);

		do {
			choice = menu();
			switch (choice) {
			case 1:
				System.out.println(table.displayTable());
				win = false;
				while (table.getTurn() <= 9) {
					turn(name1, table);
					System.out.println(table.displayTable());
					win = table.checkTable(name1);
					if (win) {
						System.out.println(name1 + " wins!");
						break;
					}
					if (table.getTurn() >= 9)
						break;
					turn(name2, table);
					System.out.println(table.displayTable());
					win = table.checkTable(name2);
					if (win) {
						System.out.println(name2 + " wins!");
						break;
					}
				} // end while
				if (!win) {
					System.out.println("Tie Game - No Winner");
				}
				table.clearTable();
				break;
			case 2:
				System.out.println(table.toString());
				break;
			case 3:
				in.close();
				System.out.print("\nThank you for playing! \nApplication Shutting Down...");
				System.exit(0);

			}// end switch
		} while (true);
	}

	public static void turn(String player, Table table) throws InvalidDataException {
		boolean bool = false;
		int row, col;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		do {
			System.out.println("Enter turn for " + player);
			System.out.println("Row:");
			row = in.nextInt();
			System.out.println("Column:");
			col = in.nextInt();
			bool = table.turn(row, col, player);
			if (!bool)
				System.out.println("Invalid move, try again");
		} while (!bool);
	}

	public static int menu() {
		int choice;
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("\nMenu:");
		System.out.println("1. Play New Game \n2. Display Game Information \n3. EXIT");
		do {
			System.out.print("Enter Valid Menu Selection: ");
			choice = in.nextInt();
		} while (choice < 1 || choice > 3);
		return choice;
	}

}
