package billOrganizer;

import java.util.Comparator;

public class BillTypeComparator implements Comparator<Bill> {

	@Override
	public int compare(Bill o1, Bill o2) {
		return o1.getBillType().toString().compareTo(o2.getBillType().toString());
	}

}
