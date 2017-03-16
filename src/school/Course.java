package school;

public class Course {

	private String courseID;
	private String description;
	private String departmentID;
	private Integer numCredits;

	public Course(String courseID, String description, String departmentID, Integer numCredits)
			throws InvalidDataException {
		if (courseID == null || description == null || numCredits == null || departmentID == null)
			throw new InvalidDataException();
		if (courseID.length() != 6)
			throw new InvalidDataException();
		this.courseID = courseID;
		this.description = description;
		if (departmentID.length() != 4)
			throw new InvalidDataException();
		this.departmentID = departmentID;
		if (numCredits < 0 || numCredits > 4)
			throw new InvalidDataException();
		this.numCredits = numCredits;

	}

	public String getCourseID() {
		return courseID;
	}

	public String getDescription() {
		return description;
	}

	public int getNumCredits() {
		return numCredits;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public int compareTo(Course other) throws InvalidDataException {
		if (other == null)
			throw new InvalidDataException();
		return this.courseID.compareTo(other.courseID);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equals(other.courseID))
			return false;
		return true;
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(" Course ID: " + courseID);
		info.append(" Course Description: " + description);
		info.append(" Department ID: " + departmentID);
		info.append(" Credits: " + numCredits );
		return info.toString();
	}

}
