package pharmacy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import school.InvalidDataException;

public class UsePharmacy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		PharmacyList list = null;
		PharmaceuticalCo co;
		Scanner in = new Scanner(System.in);
		int choice;
		String code, phone, name;

		try {
			list = new PharmacyList();
			Scanner file = new Scanner(new File("./pharmacies.txt"));
			while (file.hasNextLine()) {
				list.addCompany(file.next(), file.next(), file.nextLine());
			}
			file.close();
			System.out.println("file read...");
		} catch (FileNotFoundException ex1) {
			System.out.println("file not found");
			System.exit(1);
		} catch (InvalidDataException ex2) {
			System.out.println("invalid data");
			System.exit(1);
		} catch (DuplicateDataException ex3) {
			System.out.println("duplicate data ");
			System.exit(1);
		} catch (IOException ex4) {
			System.out.println("couldnt read data from file or write data to file, ioexception");
			System.exit(1);
		}

		while (true) {
			choice = menu(in);
			switch (choice) {
			case 1:// add co
				System.out.println("Enter Company Information: ");
				System.out.print(" Code: ");
				code = in.next().toUpperCase();
				do {
					System.out.print(" Phone Number - include area code, don't include - or ()");
					phone = in.next();
				} while (phone.length() != 10);
				in.nextLine();
				System.out.print(" Name: ");
				name = in.nextLine();
				try {
					list.addCompany(code, phone, name);
					System.out.println("Company added");
				} catch (DuplicateDataException ex1a) {
					System.out.println("department id is duplicate - reeneter data");
				} catch (InvalidDataException ex1b) {
					System.out.println("data invalid  ");
				} catch (IOException ex1d) {
					System.out.println(" technical problem with data file contact IT");
				}
				break;
			case 2:// remove co
				System.out.print(" Enter Company Code: ");
				code = in.next();
				try {
					list.removeCo(code);
					System.out.println("record removed");
				} catch (NotFoundException e) {
					System.out.println("Company not found");
				} catch (IOException e) {
					System.out.println(" technical problem with data file contact IT");

				}

				break;
			case 3:// modify co phone#
				System.out.println("Enter Company Information: ");
				System.out.print(" Code: ");
				code = in.next();
				System.out.println(" New Phone Number - include area code, don't include - or ()");
				phone = in.next();
				try {
					list.modifyCompanyPhone(code, phone);
					System.out.println("Number modified");
				} catch (IOException e) {
					System.out.println("technical problem with data file contact IT");
				} catch (NotFoundException e) {
					System.out.println("Information not found");
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:// displae co info
				System.out.println("Enter company Information: ");
				System.out.println(" Code/Name: ");
				in.nextLine();
				code = in.nextLine();
				try {
					if (code.length() == 3)
						System.out.println(list.findCompanyCode(code).toString());
					else
						System.out.println(list.findCompanyName(code).toString());

				} catch (IOException e) {
					System.out.println("technical problem with data file contact IT");
				} catch (NotFoundException e) {
					System.out.println("Information not found");
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 5:// list info about each co. file (alphabetically)
				for (CompanyCodeIndex index : list.getCompanyCodeIndexes()) {
					if (index.getIsActive() == true) {
						try {
							co = list.findCompanyCode(index.getCompanyCode());
							System.out.println(co.toString());
						} catch (IOException e) {
							System.out.println("technical problem with data file contact IT");
						} catch (NotFoundException e) {
							System.out.println("Information not found");
						} catch (InvalidDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case 6:// end app
				System.out.println("Exiting application");
				try {
					list.closeFiles();
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("pharmacies.ser"));
					output.writeObject(list);
					output.close();
					in.close();
					System.out.println("Data written out to file...application shutting down...");
					System.exit(0);
				} catch (IOException e) {
					System.out.println("Error occurred reading from file");
				}
				break;
			}// end switch

		}
	}// end main

	public static int menu(Scanner in) {
		System.out.println("Menu: ");
		System.out.println("1. Add a pharmaceutical company");
		System.out.println("2. Remove a company");
		System.out.println("3. Modify company phone number");
		System.out.println("4. Display company information");
		System.out.println("5. List information about each company on file, in alphabetical order");
		System.out.println("6. End the application");

		System.out.print("\nSelect option from menu: ");
		int temp = in.nextInt();
		while (temp < 0 || temp > 6) {
			System.out.print("Invalid choice... " + "\nSelect option from menu: ");
			temp = in.nextInt();
		}
		return temp;
	}
}// end class
