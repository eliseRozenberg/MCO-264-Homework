package school;

import java.util.Comparator;

public class TeacherNameComparator implements Comparator<Teacher> {

	@Override
	public int compare(Teacher o1, Teacher o2) {
		if (o1.getLastName().equalsIgnoreCase(o2.getLastName()))
			return o1.getFirstName().compareTo(o2.getFirstName());
		else
			return o1.getLastName().compareTo(o2.getLastName());
	}

}
