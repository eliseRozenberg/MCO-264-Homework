package billOrganizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import school.InvalidDataException;

public class Driver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		BillOrganizer organizer = null;
		File file = null;
		String fileName = null;
		int choice = menu1(in);
		switch (choice) {
		case 1:
			organizer = new BillOrganizer();
			break;
		case 2:
			System.out.println("Enter the file name: ");
			fileName = in.next();
			file = new File(fileName);
			try {
				organizer = new BillOrganizer(file);
				System.out.println("Organizer Restored");
			} catch (ClassNotFoundException | IOException | DuplicateDataException e1) {
				System.out.println("error with your file...try again");
				e1.printStackTrace();
			}
			break;
		}

		Bill bill;
		Iterator<Bill> itter;
		BillCriteria criteria;

		int ID;
		String name, date, type, fname;
		double amount;
		boolean added;
		do {
			choice = menu(in);
			switch (choice) {
			case 1:
				System.out.println("Enter info:");
				System.out.println(" Vendor Name: ");
				name = in.next();
				System.out.println(" Date: (xx/xx/xxxx) ");
				date = in.next();
				System.out.println(" Bill Type: \n(CLOTHING, EDUCATION, FOOD, GROCERIES, PHONE, TRAVEL, UTILITIES)");
				type = in.next();
				System.out.println(" Amount (<0): ");
				amount = in.nextDouble();
				try {
					bill = new Bill(name, amount, date, type);
					added = organizer.insert(bill);
					if (!added)
						System.out.println("Bill already added to orginizer");

					else
						System.out.println("Bill Added\n" + bill);
				} catch (DuplicateDataException | InvalidTypeException | InvalidDataException e) {
					e.printStackTrace();
				}
				break;
			case 2:// View Bills
				choice = menu2(in);
				if (choice == 1) {// by date
					itter = organizer.iteratorByDate();
				} else if (choice == 2) {// by type
					itter = organizer.iteratorByType();
				} else {// by amount
					itter = organizer.iteratorByAmount();
				}
				while (itter.hasNext()) {
					System.out.println(itter.next().toString());
				}
				break;
			case 3:// Pay next upcoming bill
				choice = menu2(in);
				if (choice == 1) {// by date
					criteria = BillCriteria.BILLDUEDATE;
				} else if (choice == 2) {// by type
					criteria = BillCriteria.BILLTYPE;
				} else {// by amount
					criteria = BillCriteria.BILLAMOUNT;
				}
				bill = organizer.payNextBill(criteria);
				if (bill == null) {
					System.out.println("There are no more bills left.");
				} else {
					System.out.println(bill);
				}
				break;
			case 4:// Pay bill by ID
				System.out.println("Bill Id:");
				ID = in.nextInt();
				bill = organizer.payByID(ID);
				if (bill == null) {
					System.out.println("There is no record of this bill.");
				} else {
					System.out.println(bill);
				}
				break;
			case 5:
				System.out.println("$" + organizer.totalBills());
				break;
			case 6:
				System.out.println("Enter the file name: ");
				fname = in.next();
				try {
					organizer.insertFile(new Scanner(new File(fname)));
					System.out.println("Bills added");
				} catch (FileNotFoundException | DuplicateDataException | InvalidDataException
						| InvalidTypeException e1) {
					e1.printStackTrace();
				}

				break;
			case 7:// Save the bill
				if (fileName == null) {
					System.out.println("Enter the file name: ");
					fileName = in.next();
					file = new File(fileName);
				}
				try {
					organizer.closeOranizer(file);
					System.out.println("Organizer saved...");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 0:// exit
				System.out.println("Goodbye");
				System.exit(0);
				break;
			case 8:
				System.out.println(organizer);
				break;
			}
		} while (true);
	}

	public static int menu1(Scanner in) {
		System.out.println("Select an option from the menu:\n 1.Create New Organizer\n 2.Restore Orginizer");
		int temp = in.nextInt();
		while (temp < 1 || temp > 2) {
			System.out.println("Invalid choice... " + "\tSelect option from menu: ");
			temp = in.nextInt();
		}
		return temp;
	}

	public static int menu(Scanner in) {
		System.out.println(
				"1.Add Bill \n2.View Bills \n3.Pay next upcoming bill \n4.Pay bill by ID \n5.View Total Amount \n6.Read bills from file \n7.Save "
						+ "Bill Organizer \n0. Exit");

		System.out.println("Select option from menu: ");
		int temp = in.nextInt();
		while (temp < 1 || temp > 8) {
			System.out.println("Invalid choice... " + "\tSelect option from menu: ");
			temp = in.nextInt();
		}
		return temp;
	}

	public static int menu2(Scanner in) {
		System.out.println("Select method: \n" + "	1. By Date: \n	2. By Type:\n	3. By Amount:	");
		int temp = in.nextInt();
		while (temp < 1 || temp > 3) {
			System.out.println("Invalid choice... " + "\tSelect option from menu: ");
			temp = in.nextInt();
		}
		return temp;
	}

	public void getBillInfo() {

	}
}
