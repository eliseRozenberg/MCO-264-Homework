package school;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student extends Person implements Comparable<Student> {

	private Major major;
	private GregorianCalendar dateEnrolled;
	private GregorianCalendar dateOfBirth;
	private Double gpa;
	private Integer creditsEarned;
	private String ssn;
	private ArrayList<CompletedCourse> courses;

	// no midInitial
	public Student(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, String phoneNumber, Character gender, String major, String dateEnrolled, String dateOfBirth,
			String ssn) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, phoneNumber, gender, major, dateEnrolled,
				dateOfBirth, ssn);
	}

	// no phone number
	public Student(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, Character gender, String major, String dateEnrolled, String dateOfBirth,
			String ssn) throws InvalidDataException {
		this(id, firstName, lastName, midInitial, street, city, state, zipCode, null, gender, major, dateEnrolled,
				dateOfBirth, ssn);
	}

	// no major
	public Student(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, String phoneNumber, Character gender, String dateEnrolled, String dateOfBirth,
			String ssn) throws InvalidDataException {
		this(id, firstName, lastName, midInitial, street, city, state, zipCode, phoneNumber, gender, null, dateEnrolled,
				dateOfBirth, ssn);
	}

	// no phone number or midInitial
	public Student(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, Character gender, String major, String dateEnrolled, String dateOfBirth, String ssn) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, null, gender, major, dateEnrolled,
				dateOfBirth, ssn);
	}

	// no midInitial or major
	public Student(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, String phoneNumber, Character gender, String dateEnrolled, String dateOfBirth, String ssn) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, phoneNumber, gender, null, dateEnrolled,
				dateOfBirth, ssn);
	}

	// no phone number or major
	public Student(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, Character gender, String dateEnrolled, String dateOfBirth, String ssn) throws InvalidDataException {
		this(id, firstName, lastName, midInitial, street, city, state, zipCode, null, gender, null, dateEnrolled,
				dateOfBirth, ssn);
	}

	// all fields
	public Student(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, String phoneNumber, Character gender, String major, String dateEnrolled,
			String dateOfBirth, String ssn) throws InvalidDataException {
		super(id, firstName, lastName, midInitial, street, city, state, zipCode, phoneNumber, gender);
		if (major == null)
			this.major = Major.UDCD;
		else
			this.major = getMajorCode(major);
		if (this.major == null)
			throw new InvalidDataException();
		String[] tokens = dateEnrolled.split("/");
		if (tokens.length != 3)
			throw new InvalidDataException();
		int month = Integer.parseInt(tokens[0]);
		int date = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		this.dateEnrolled = new GregorianCalendar(year, month - 1, date);
		if (tokens.length != 3)
			throw new InvalidDataException();
		tokens = dateOfBirth.split("/");
		month = Integer.parseInt(tokens[0]);
		date = Integer.parseInt(tokens[1]);
		year = Integer.parseInt(tokens[2]);
		this.dateOfBirth = new GregorianCalendar(year, month - 1, date);
		this.gpa = null;
		this.creditsEarned = 0;
		if (ssn.length() != 9 && ssn.length() != 8) // in your files students
													// have 8 while teachers
													// have 9
			throw new InvalidDataException();
		this.ssn = ssn;
		this.courses = new ArrayList<CompletedCourse>();
	}

	public void setMajor(String major) throws InvalidDataException {
		if (major == null)
			this.major = Major.UDCD;
		else
			this.major = getMajorCode(major);
		if (this.major == null)
			throw new InvalidDataException();
	}

	public void setGpa(Double grade, Integer credits) throws InvalidDataException {
		if (grade == null || credits == null)
			throw new InvalidDataException();
		if (grade < 0 || grade > 4.0)
			throw new InvalidDataException();
		if (credits < 0 || credits > 4)
			throw new InvalidDataException();
		double points = 0;
		for (CompletedCourse course : courses) {
			points += course.getGrade().getGrade() * course.getNumCredits();
		}
		gpa = (points / (this.creditsEarned * 4.0)) * 4.0;
		// figure out the equation
	}

	public ArrayList<CompletedCourse> getCoursesByDepartement(String departmentID) throws InvalidDataException {
		if (departmentID == null)
			throw new InvalidDataException();
		ArrayList<CompletedCourse> temp = new ArrayList<CompletedCourse>();
		for (CompletedCourse course : courses) {
			if (course.getDepartmentID().equalsIgnoreCase(departmentID)) {
				temp.add(course);
			}
		}
		return temp;
	}

	public ArrayList<CompletedCourse> getCoursesByGrade(String grade) throws InvalidDataException {
		if (grade == null)
			throw new InvalidDataException();
		ArrayList<CompletedCourse> temp = new ArrayList<CompletedCourse>();
		for (CompletedCourse course : courses) {
			// fix grade
			if (course.getGrade().toString().equalsIgnoreCase(grade)) {
				temp.add(course);
			}
		}
		return temp;
	}

	public Grade getGradeOfCourse(String courseID) throws InvalidDataException {
		if (courseID == null)
			throw new InvalidDataException();
		for (CompletedCourse course : courses) {
			if (course.getCourseID().equalsIgnoreCase(courseID))
				return course.getGrade();
		}
		return null;
	}

	public void setCreditsEarned(Integer creditsEarned) throws InvalidDataException {
		if (creditsEarned == null)
			throw new InvalidDataException();
		if (creditsEarned < 0 || creditsEarned > 7)
			throw new InvalidDataException();
		else
			this.creditsEarned += creditsEarned;
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

	public void CompleteCourse(Course course, String grade, String dateCompleted) throws InvalidDataException {
		if (course == null || grade == null || dateCompleted == null)
			throw new InvalidDataException();
		CompletedCourse temp = new CompletedCourse(course.getCourseID(), course.getDescription(),
				course.getDepartmentID(), course.getNumCredits(), this.getId(), dateCompleted, grade);
		this.courses.add(temp);
		setCreditsEarned(course.getNumCredits());
		setGpa(temp.getGrade().getGrade(), course.getNumCredits());

	}

	public CompletedCourse findCompletedCourse(String courseID) throws InvalidDataException {
		if (courseID == null)
			throw new InvalidDataException();
		for (CompletedCourse course : courses) {
			if (course.getCourseID().equalsIgnoreCase(courseID))
				return course;
		}
		return null;
	}

	public Major getMajor() {
		return major;
	}

	public GregorianCalendar getDateEnrolled() {
		GregorianCalendar dateEnrolledCopy = new GregorianCalendar(dateEnrolled.get(Calendar.YEAR),
				dateEnrolled.get(Calendar.MONTH), dateEnrolled.get(Calendar.DATE));
		return dateEnrolledCopy;
	}

	public GregorianCalendar getDateOfBirth() {
		GregorianCalendar dateOfBirthCopy = new GregorianCalendar(dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH), dateOfBirth.get(Calendar.DATE));
		return dateOfBirthCopy;
	}

	public double getGpa() {
		return gpa;
	}

	public Integer getCreditsEarned() {
		return creditsEarned;
	}

	public String getSsn() {
		return ssn;
	}

	public ArrayList<CompletedCourse> getCourses() {
		ArrayList<CompletedCourse> coursesCopy = new ArrayList<CompletedCourse>();
		for (CompletedCourse course : courses) {
			coursesCopy.add(course);
		}
		return coursesCopy;
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");
		info.append("Student");
		info.append(super.toString());
		info.append(" Major: " + major.name());
		info.append(" Date Enrolled: " + form.format(dateEnrolled.getTime()));
		info.append(" Date of Birth: " + form.format(dateOfBirth.getTime()));
		info.append(" GPA: " + gpa);
		info.append(" Credits Earned: " + creditsEarned);
		info.append(" SSN: " + ssn);
		info.append(" Completed Course: " + courses.toString() + "\n");
		return info.toString();
	}

	@Override
	public int compareTo(Student o) {
		return this.getId().compareTo(o.getId());
	}
}
