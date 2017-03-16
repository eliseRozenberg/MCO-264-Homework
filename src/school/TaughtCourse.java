package school;

public class TaughtCourse extends Course {

	private Integer teacherID;
	private Integer year;
	private Semester semesterID;
	private Section sectionID;

	public TaughtCourse(String courseID, String description, String departmentID, int numCredits, Integer teacherID,
			Integer year, String semesterID, String sectionID) throws InvalidDataException {
		super(courseID, description, departmentID, numCredits);
		if (teacherID == null || year == null || semesterID == null || sectionID == null)
			throw new InvalidDataException();
		if (teacherID.toString().length()!=5)
			throw new InvalidDataException();
		this.teacherID = teacherID;
		this.year = year;
		this.semesterID = getSemesterCode(semesterID);
		if (this.semesterID == null)
			throw new InvalidDataException();
		this.sectionID = getSectionCode(sectionID);
		if (this.sectionID == null)
			throw new InvalidDataException();
	}

	private static Semester getSemesterCode(String id) {
		for (Semester theSemester : Semester.values()) {
			if (theSemester.name().equalsIgnoreCase(id))
				return theSemester;
		}
		return null; // couldn't find the major
	}

	private static Section getSectionCode(String sectionID) {
		for (Section theSection : Section.values()) {
			if (theSection.name().equalsIgnoreCase(sectionID))
				return theSection;
		}
		return null;
	}

	public Integer getTeacherID() {
		return teacherID;
	}

	public Integer getYear() {
		return year;
	}

	public Semester getSemesterID() {
		return semesterID;
	}

	public Section getSectionID() {
		return sectionID;
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append("\n\t" + super.toString());
		info.append(" Year: " + year);
		info.append(" Semester ID: " + semesterID);
		info.append(" Section ID: " + sectionID);
		info.append(" Teacher ID: " + teacherID);
		return info.toString();
	}
}
