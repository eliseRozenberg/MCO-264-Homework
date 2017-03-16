package billOrganizer;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import school.InvalidDataException;

public class Bill implements Comparable<Bill>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer billID;
	private String vendorName;
	private double amountDue;
	private LocalDate dateDue;
	private BillType billType;
	private static int lastID = 0;

	public Bill(String name, double amount, String date, String type)
			throws InvalidTypeException, InvalidDataException {
		billID = lastID++;
		vendorName = name;
		if (amount <= 0) {
			throw new InvalidDataException();
		}
		amountDue = amount;
		String[] tokens = date.split("/");
		if (tokens.length != 3)
			throw new InvalidDataException();
		this.dateDue = LocalDate.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[0]),
				Integer.parseInt(tokens[1]));
		billType = getBillType(type);
		if (billType == null)
			throw new InvalidTypeException();
	}
	
	//for serialized
	public Bill(Integer billID, String vendorName, Double amountDue,
			BillType billType, LocalDate dateDue) {
		this.billID = billID;
		Bill.lastID = billID + 1;
		this.vendorName = vendorName;
		this.amountDue = amountDue;
		this.dateDue = dateDue;
		this.billType = billType;
	}

	public Bill(Scanner in) throws FileNotFoundException, InvalidDataException, InvalidTypeException {
		billID = lastID++;
		vendorName = in.next();
		amountDue = in.nextDouble();
		if (amountDue <= 0) {
			throw new InvalidDataException();
		}
		String[] tokens = in.next().split("/");
		billType = getBillType(in.next());
		if (tokens.length != 3)
			throw new InvalidDataException();
		this.dateDue = LocalDate.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[0]),
				Integer.parseInt(tokens[1]));
		if (billType == null)
			throw new InvalidTypeException();

	}

	public static BillType getBillType(String billType) {
		for (BillType theBillType : BillType.values()) {
			if (theBillType.name().equalsIgnoreCase(billType))
				return theBillType;
		}
		return null;
	}

	public String getVendorName() {
		return vendorName;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public LocalDate getDateDue() {
		return dateDue;
	}

	public BillType getBillType() {
		return billType;
	}

	public Integer getBillID() {
		return billID;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bill ID: " + billID + " Vendor Name: " + vendorName + " Amount Due: " + amountDue
				+ " Date Due: " + dateDue + " Date Due: " + billType.toString());
		return builder.toString();
	}

	@Override
	public int compareTo(Bill o) {
		return this.getBillID().compareTo(o.billID);
	}

}
