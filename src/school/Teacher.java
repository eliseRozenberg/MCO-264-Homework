package school;

import java.util.ArrayList;

public class Teacher extends Employee implements Comparable<Teacher> {

	private String departmentId;
	private String ssn;
	private Degree degree;
	private Major major;
	private Double salary;
	private ArrayList<TaughtCourse> taughtCourses;

	// no midInitial or phone number
	public Teacher(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, Character gender, String hireDate, String dateOfBirth, String employeeType,
			String departmentID, String ssn, String degree, String major, Double salary) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, null, gender, hireDate, dateOfBirth,
				employeeType, departmentID, ssn, degree, major, salary);
	}

	// no midInitial
	public Teacher(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, String phoneNumber, Character gender, String hireDate, String dateOfBirth,
			String employeeType, String departmentID, String ssn, String degree, String major, Double salary)
					throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, phoneNumber, gender, hireDate, dateOfBirth,
				employeeType, departmentID, ssn, degree, major, salary);
	}

	// no phoneNumber
	public Teacher(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, Character gender, String hireDate, String dateOfBirth, String employeeType,
			String departmentID, String ssn, String degree, String major, Double salary) throws InvalidDataException {
		this(id, firstName, lastName, midInitial, street, city, state, zipCode, null, gender, hireDate, dateOfBirth,
				employeeType, departmentID, ssn, degree, major, salary);
	}

	// all fields
	public Teacher(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, String phoneNumber, Character gender, String hireDate, String dateOfBirth,
			String employeeType, String departmentID, String ssn, String degree, String major, Double salary)
					throws InvalidDataException {
		super(id, firstName, lastName, midInitial, street, city, state, zipCode, phoneNumber, gender, hireDate,
				dateOfBirth, employeeType);
		if (departmentID.length() != 4)
			throw new InvalidDataException();
		this.departmentId = departmentID;
		if (ssn.length() != 9) // some in your file have 8
			throw new InvalidDataException();
		this.ssn = ssn;
		this.degree = getDegreeID(degree);
		if (this.degree == null)
			throw new InvalidDataException();
		if (salary < 100 || salary > 455300000)
			throw new InvalidDataException();
		this.major = getMajorCode(major.trim());
		if (this.major == null)
			throw new InvalidDataException();
		if (salary < 1000)
			throw new InvalidDataException();
		this.salary = salary;
		this.taughtCourses = new ArrayList<TaughtCourse>();
	}

	private static Major getMajorCode(String major) throws InvalidDataException {
		if (major == null)
			throw new InvalidDataException();
		for (Major theMajor : Major.values()) {
			if (theMajor.name().equalsIgnoreCase(major) || theMajor.getName().equalsIgnoreCase(major))
				return theMajor;
		}
		return null; // couldn't find the major
	}

	private Degree getDegreeID(String degree) throws InvalidDataException {
		if (degree == null)
			throw new InvalidDataException();
		for (Degree theDegree : Degree.values()) {
			if (theDegree.name().equalsIgnoreCase(degree))
				return theDegree;
		}
		return null; // couldn't find the major
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(String major) throws InvalidDataException {
		if (major == null)
			throw new InvalidDataException();
		this.major = getMajorCode(major);
		if (this.major == null)
			throw new InvalidDataException();
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getSsn() throws InvalidDataException {
		if (ssn.length() != 9)
			throw new InvalidDataException();
		return ssn;
	}

	public Degree getDegree() {
		return degree;
	}

	public Double getSalary() {
		return salary;
	}

	public void setDegree(String degree, String major) throws InvalidDataException {
		if (degree == null || major == null)
			throw new InvalidDataException();
		this.degree = getDegreeID(degree);
		if (this.degree == null)
			throw new InvalidDataException();
		setMajor(major);
	}

	public void applyRaise(Double percent) throws InvalidDataException {
		if (percent == null)
			throw new InvalidDataException();
		if (percent < 0)
			throw new InvalidDataException();
		this.salary += (salary * percent);
	}

	public void applyRaise(Integer amount) throws InvalidDataException {
		if (amount == null)
			throw new InvalidDataException();
		if (amount < 1)
			throw new InvalidDataException();
		this.salary += amount;
	}

	public void taughtCourse(Course c, Integer year, String semester, String sectionID) throws InvalidDataException {
		if (c == null || year == null || semester == null || sectionID == null)
			throw new InvalidDataException();
		if (year.toString().length() != 4)
			throw new InvalidDataException();
		TaughtCourse course = new TaughtCourse(c.getCourseID(), c.getDescription(), this.getDepartmentId(),
				c.getNumCredits(), super.getId(), year, semester, sectionID);
		if (!taughtCourses.contains(course))
			taughtCourses.add(course);
	}

	public int howManyCoursesPerSemester(Integer year, Semester semesterID) throws InvalidDataException {
		if (year == null || semesterID == null)
			throw new InvalidDataException();
		if (year.toString().length() != 4)
			throw new InvalidDataException();
		int num = 0;
		for (TaughtCourse course : taughtCourses) {
			if (course.getYear() == year && course.getSemesterID() == semesterID)
				num++;
		}
		return num;
	}

	public int howManyDifferentCourses() {
		ArrayList<String> courseIDs = new ArrayList<String>();
		for (TaughtCourse course : taughtCourses) {
			if (!courseIDs.contains(course.getCourseID())) {
				courseIDs.add(course.getCourseID());
			}
		}
		return courseIDs.size();
	}

	public ArrayList<TaughtCourse> getTaughtCourses() {
		ArrayList<TaughtCourse> temp = new ArrayList<TaughtCourse>();
		for (TaughtCourse course : taughtCourses) {
			temp.add(course);
		}
		return temp;
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(super.toString());
		info.append(" Department ID: " + departmentId);
		info.append(" SSN: " + ssn);
		info.append(" Degree: " + degree.name());
		info.append(" Major: " + major.name());
		info.append(" Salary: " + salary);
		info.append(" Taught Courses: " + taughtCourses + "\n");
		return info.toString();
	}

	@Override
	public int compareTo(Teacher o) {
		return this.getId().compareTo(o.getId());
	}
}
