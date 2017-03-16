package billOrganizer;

import java.util.Comparator;

public class BillAmountComparator implements Comparator<Bill> {

	@Override
	public int compare(Bill o1, Bill o2) {
		if (o1.getAmountDue() > o2.getAmountDue())
			return 1;
		if (o1.getAmountDue() < o2.getAmountDue())
			return -1;
		else
			return 0;
	}

}
