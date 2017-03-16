package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompletedCourse extends Course {

	private int studentID;
	private Grade grade;
	private GregorianCalendar completedDate;

	public CompletedCourse(String courseID, String description, String departmentID, Integer numCredits,
			Integer studentID, String completedDate, String grade) throws InvalidDataException {
		super(courseID, description, departmentID, numCredits);
		if (studentID == null || grade == null || completedDate == null)
			throw new InvalidDataException();
		if (studentID.toString().length() != 5)
			throw new InvalidDataException();
		this.studentID = studentID;
		String[] tokens = completedDate.split("/");
		if (tokens.length != 3)
			throw new InvalidDataException();
		int month = Integer.parseInt(tokens[0]);
		int date = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		this.completedDate = new GregorianCalendar(year, month - 1, date);
		if (grade.length() > 1)
			throw new InvalidDataException();
		this.grade = getGradeCode(grade);
		if (this.grade == null)
			throw new InvalidDataException();
	}

	// get the number of the letter course - method?
	public static Grade getGradeCode(String grade) {
		for (Grade theGrade : Grade.values()) {
			if (theGrade.name().equalsIgnoreCase(grade))
				return theGrade;
		}
		return null;
	}

	public int getStudentID() {
		return studentID;
	}

	public GregorianCalendar getCompletedDate() {
		GregorianCalendar temp = new GregorianCalendar(completedDate.get(Calendar.YEAR),
				completedDate.get(Calendar.MONTH), completedDate.get(Calendar.DATE));
		return temp;

	}

	public Grade getGrade() {
		return grade;
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		SimpleDateFormat form = new SimpleDateFormat("MM/dd/yy");
		info.append("\n\t" + super.toString());
		info.append(" Grade : " + grade);
		info.append(" Date Completed: " + form.format(completedDate.getTime()));
		return info.toString();
	}
}
