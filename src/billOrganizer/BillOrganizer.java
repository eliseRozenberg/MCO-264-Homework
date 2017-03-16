package billOrganizer;

//fix restore - see list bills - loose it and also to sting loose it 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;

import school.InvalidDataException;

public class BillOrganizer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PriorityQueue<Bill> date, amount, billType;
	private SortedLinkedList<Bill> billList;

	public BillOrganizer() {
		date = new PriorityQueue<Bill>(new BillDateComparator());
		amount = new PriorityQueue<Bill>(new BillAmountComparator());
		billType = new PriorityQueue<Bill>(new BillTypeComparator());
		billList = new SortedLinkedList<Bill>();
	}

	// my main doesn't use method but can be added as an option before new
	// organizer is created
	public BillOrganizer(Scanner in)
			throws FileNotFoundException, DuplicateDataException, InvalidTypeException, InvalidDataException {
		this();
		insertFile(in);
	}

	public BillOrganizer(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException, DuplicateDataException {
		this();
		Bill bill;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		SortedLinkedList<Bill> newList = (SortedLinkedList<Bill>) in.readObject();
		Iterator<Bill> itter = newList.iterator();
		while (itter.hasNext()) {
			bill = itter.next();
			bill = new Bill(bill.getBillID(), bill.getVendorName(), bill.getAmountDue(), bill.getBillType(),
					bill.getDateDue());
			insert(bill);
		}
		in.close();
	}

	public void insertFile(Scanner in)
			throws FileNotFoundException, DuplicateDataException, InvalidDataException, InvalidTypeException {
		while (in.hasNext()) {
			insert(new Bill(in));
		}
		in.close();
	}

	public boolean insert(Bill bill) throws DuplicateDataException {
		if (billList.contains(bill)) {
			throw new DuplicateDataException();
		}
		billList.insert(bill);
		date.engueue(bill);
		amount.engueue(bill);
		billType.engueue(bill);
		return true;
	}

	public Bill payByID(int ID) {
		Iterator<Bill> itter = billList.iterator();
		Bill bill = null;
		while (itter.hasNext()) {
			bill = itter.next();
			if (bill.getBillID().equals(ID)) {
				billList.remove(bill);
				amount.remove(bill);
				billType.remove(bill);
				date.remove(bill);
				return bill;
			}
		}
		return null;
	}

	public Bill payNextBill(BillCriteria criteria) {
		Bill bill = null;
		if (billList.isEmpty()) {
			return null;
		}
		if (criteria.equals(BillCriteria.BILLAMOUNT)) {
			bill = amount.dequeue();
			date.remove(bill);
			billType.remove(bill);
		}
		if (criteria.equals(BillCriteria.BILLDUEDATE)) {
			bill = date.dequeue();
			amount.remove(bill);
			billType.remove(bill);
		}
		if (criteria.equals(BillCriteria.BILLTYPE)) {
			bill = billType.dequeue();
			date.remove(bill);
			amount.remove(bill);
		}
		billList.remove(bill);
		return bill;
	}

	public void closeOranizer(File file) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(billList);
		out.close();
	}

	public double totalBills() {
		double amount = 0;
		Iterator<Bill> itter = billList.iterator();
		while (itter.hasNext()) {
			amount += itter.next().getAmountDue();
		}
		return amount;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		Iterator<Bill> itter = billList.iterator();
		while (itter.hasNext()) {
			builder.append(itter.next() + "\n");
		}

		return builder.toString();
	}

	public Iterator<Bill> iteratorByDate() {
		return date.iterator();
	}

	public Iterator<Bill> iteratorByAmount() {
		return amount.iterator();
	}

	public Iterator<Bill> iteratorByType() {
		return billType.iterator();
	}
}
