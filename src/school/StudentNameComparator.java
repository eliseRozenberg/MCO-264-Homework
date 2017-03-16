package school;

import java.util.Comparator;

public class StudentNameComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getLastName().equalsIgnoreCase(o2.getLastName()))
			return o1.getFirstName().compareTo(o2.getFirstName());
		else
			return o1.getLastName().compareTo(o2.getLastName());
	}

}
