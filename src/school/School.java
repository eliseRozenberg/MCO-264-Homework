package school;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class School {

	private String schoolName;
	private Address address;
	private String phoneNumber;
	private ArrayList<Person> people;
	private ArrayList<Course> courses;
	private ArrayList<Department> departments;

	// make it the main and add a this reference to the rest
	public School(String schoolName, Address address, String phoneNum) throws InvalidDataException {
		if (schoolName == null || address == null || phoneNum == null)
			throw new InvalidDataException();
		this.schoolName = schoolName;
		this.address = address;
		this.phoneNumber = phoneNum;
		people = new ArrayList<Person>();
		courses = new ArrayList<Course>();
		departments = new ArrayList<Department>();
	}

	public School(String schoolName, Address address, String phoneNum, String teachFileName, String studentFileName,
			String departmentFileName, String courseFileName) throws FileNotFoundException, InvalidDataException {
		this(schoolName, address, phoneNum);
		if (teachFileName == null || studentFileName == null || departmentFileName == null || courseFileName == null)
			throw new InvalidDataException();
		String first, last, street, city, state, zip, phone, major, hireDate, DOB, degree, ssn, departID, empType;
		String[] temp;
		int id;
		char mid, gender;
		double salary;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(new File(teachFileName));

		while (input.hasNext()) {
			id = input.nextInt();
			first = input.next();
			last = input.next();
			input.nextLine();
			street = input.nextLine();
			temp = input.nextLine().split(",");
			if (temp.length != 2)
				throw new InvalidDataException();
			city = temp[0];
			state = temp[1].trim();
			zip = input.next();
			phone = input.next();
			gender = input.next().charAt(0);
			hireDate = input.next();
			DOB = input.next();
			empType = input.next();
			departID = input.next();
			ssn = input.next();
			degree = input.next();
			major = input.next();
			salary = input.nextDouble();
			people.add(new Teacher(id, first, last, street, city, state, zip, phone, gender, hireDate, DOB, empType,
					departID, ssn, degree, major, salary));
		}

		input = new Scanner(new File(studentFileName));
		while (input.hasNext()) {

			id = input.nextInt();
			last = input.next();
			first = input.next();
			mid = input.next().charAt(0);
			input.nextLine();
			street = input.nextLine();
			temp = input.nextLine().split(",");
			if (temp.length != 2)
				throw new InvalidDataException();
			city = temp[0];
			state = temp[1].trim();
			zip = input.next();
			phone = input.next();
			gender = input.next().charAt(0);
			major = input.next();
			hireDate = input.next(); // date enrolled
			DOB = input.next();
			ssn = input.next();
			people.add(new Student(id, first, last, mid, street, city, state, zip, phone, gender, major, hireDate, DOB,
					ssn));
		}
		// check length to see if have all departments and maybe assign to
		// variables before putting into...
		input = new Scanner(new File(departmentFileName));
		while (input.hasNext()) {
			String data = input.nextLine();
			temp = data.split(";");
			if (temp.length != 6)
				throw new InvalidDataException();
			departments.add(new Department(temp[0], temp[1], temp[2], temp[3], temp[4], Integer.parseInt(temp[5])));

		}
		input = new Scanner(new File(courseFileName));
		while (input.hasNext()) {
			temp = input.nextLine().split(";");
			if (temp.length != 4)
				throw new InvalidDataException();
			courses.add(new Course(temp[0], temp[1], temp[2], Integer.parseInt(temp[3])));
		}
	}

	public void addTeacher(Teacher teacher) throws InvalidDataException {
		if (teacher == null)
			throw new InvalidDataException();
		if (this.people.contains(teacher))
			throw new InvalidDataException();
		else
			this.people.add(teacher);
	}

	public void addStudent(Student student) throws InvalidDataException {
		if (student == null)
			throw new InvalidDataException();
		if (this.people.contains(student))
			throw new InvalidDataException();
		else
			this.people.add(student);
	}

	public void addCourse(Course course) throws InvalidDataException {
		if (course == null)
			throw new InvalidDataException();

		if (this.courses.contains(course))
			throw new InvalidDataException();
		else
			this.courses.add(course);
	}

	public void addDepartment(Department department) throws InvalidDataException {
		if (department == null)
			throw new InvalidDataException();
		if (this.departments.contains(department))
			throw new InvalidDataException();
		else
			this.departments.add(department);
	}

	public void removeTeacher(Teacher teacher) throws InvalidDataException {
		if (teacher == null)
			throw new InvalidDataException();
		if (people.contains(teacher))
			people.remove(teacher);
		else
			throw new InvalidDataException();
	}

	public void removeTeacher(Integer teacherID) throws InvalidDataException {
		if (teacherID == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Teacher)
				if (person.getId().equals(teacherID)) {
					found = true;
					people.remove(person);
					break;
				}
		}
		if (found == false)
			throw new InvalidDataException();
	}

	public void removeStudent(Student student) throws InvalidDataException {
		if (student == null)
			throw new InvalidDataException();
		if (people.contains(student))
			people.remove(student);
		else
			throw new InvalidDataException();
	}

	public void removeStudent(Integer studentID) throws InvalidDataException {
		if (studentID == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Student)
				if (person.getId().equals(studentID)) {
					found = true;
					people.remove(person);
					break;
				}
		}
		if (found == false)
			throw new InvalidDataException();
	}

	public void removeCourse(String courseID) throws InvalidDataException {
		if (courseID == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Course course : courses)
			if (course.getCourseID().equalsIgnoreCase(courseID)) {
				found = true;
				courses.remove(course);
				break;
			}
		if (found == false)
			throw new InvalidDataException();
	}

	public void removeCourse(Course course) throws InvalidDataException {
		if (course == null)
			throw new InvalidDataException();
		if (courses.contains(course))
			courses.remove(course);
		else
			throw new InvalidDataException();
	}

	public void modifyTeacherLastName(Integer teacherID, String newLastName) throws InvalidDataException {
		if (teacherID == null || newLastName == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Teacher)
				if (person.getId().equals(teacherID)) {
					person.setLastName(newLastName);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public void modifyTeacherAddress(Integer teacherID, Address address) throws InvalidDataException {
		if (teacherID == null || address == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Teacher)
				if (person.getId().equals(teacherID)) {
					person.setAddress(address);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public void modifyTeacherDegree(Integer teacherID, String degree, String major) throws InvalidDataException {
		if (teacherID == null || degree == null || major == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Teacher)
				if (person.getId().equals(teacherID)) {
					((Teacher) person).setDegree(degree, major);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public void giveTeacherRaise(Integer teacherID, Double percent) throws InvalidDataException {
		if (teacherID == null || percent == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Teacher)
				if (person.getId().equals(teacherID)) {
					((Teacher) person).applyRaise(percent);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public void giveTeacherRaise(Integer teacherID, Integer amount) throws InvalidDataException {
		if (teacherID == null || amount == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Teacher)
				if (person.getId().equals(teacherID)) {
					((Teacher) person).applyRaise(amount);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public void modifyStudentLastName(Integer studentID, String newLastName) throws InvalidDataException {
		if (studentID == null || newLastName == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Student)
				if (person.getId().equals(studentID)) {
					person.setLastName(newLastName);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public void modifyStudentPhoneNumber(Integer studentID, String newPhoneNumber) throws InvalidDataException {
		if (studentID == null || newPhoneNumber == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Person person : people) {
			if (person instanceof Student)
				if (person.getId().equals(studentID)) {
					person.setPhoneNumber(newPhoneNumber);
					found = true;
					break;
				}
		}
		if (!found)
			throw new InvalidDataException();
	}

	// if date completed is not today
	public void addCompletedCourse(Integer studentID, String courseID, String grade, String dateComp) throws InvalidDataException {
		if (studentID == null || courseID == null || grade == null || dateComp == null)
			throw new InvalidDataException();
		boolean found = false;
		for (Course course : courses) {
			if (course.getCourseID().equalsIgnoreCase(courseID)) {
				for (Person person : people) {
					if (person instanceof Student)
						if (person.getId().equals(studentID)) {
							((Student) person).CompleteCourse(course, grade, dateComp);
							found = true;
							break;
						}
				}
			}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public Double getStudentGPA(Integer studentID) throws InvalidDataException {
		if (studentID == null)
			throw new InvalidDataException();
		for (Person person : people) {
			if (person instanceof Student) {
				if (person.getId().equals(studentID)) {
					return ((Student) person).getGpa();
				}
			}
		}
		return null;
	}

	public Grade getGradeOfCourse(Integer studentID, String courseID) throws InvalidDataException {
		if (studentID == null || courseID == null)
			throw new InvalidDataException();
		for (Person person : people) {
			if (person instanceof Student)
				if (person.getId().equals(studentID)) {
					return ((Student) person).getGradeOfCourse(courseID);
				}
		}
		return null;
	}

	public ArrayList<CompletedCourse> getCoursesByDepartment(Integer studentID, String departmentID) throws InvalidDataException {
		if (studentID == null || departmentID == null)
			throw new InvalidDataException();
		for (Person person : people) {
			if (person instanceof Student)
				if (person.getId().equals(studentID)) {
					return ((Student) person).getCoursesByDepartement(departmentID);
				}
		}
		return null;
	}

	public ArrayList<CompletedCourse> getCoursesByGrade(Integer studentID, String g) throws InvalidDataException {
		if (studentID == null || g == null)
			throw new InvalidDataException();
		for (Person person : people) {
			if (person instanceof Student)
				if (person.getId().equals(studentID)) {
					return ((Student) person).getCoursesByGrade(g);
				}
		}
		return null;
	}

	public ArrayList<Teacher> getTeachersSortedByName() {
		ArrayList<Teacher> temp = new ArrayList<Teacher>();
		for (Person person : people) {
			if (person instanceof Teacher)
				temp.add((Teacher) person);
		}
		Collections.sort(temp, new TeacherNameComparator());
		return temp;
	}

	public ArrayList<Student> getStudentSortedByName() {
		ArrayList<Student> temp = new ArrayList<Student>();
		for (Person person : people) {
			if (person instanceof Student)
				temp.add((Student) person);
		}

		Collections.sort(temp, new StudentNameComparator());
		return temp;
	}

	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> temp = new ArrayList<Teacher>();
		for (Person person : people) {
			if (person instanceof Teacher)
				temp.add((Teacher) person);
		}

		Collections.sort(temp);
		return temp;
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> temp = new ArrayList<Student>();
		for (Person person : people) {
			if (person instanceof Student) {
				temp.add((Student) person);
			}
		}

		Collections.sort(temp);
		return temp;
	}

	public void addTaughtCourse(Integer teacherID, String courseID, Integer year, String semester, String section) throws InvalidDataException {
		if (teacherID == null || courseID == null || year == null || semester == null || section == null)
			throw new InvalidDataException();
		Course c = null;
		boolean found = false;
		for (Course course : courses) {
			if (course.getCourseID().equalsIgnoreCase(courseID)) {
				c = course;
				break;
			}
		}
		if (c == null)
			throw new InvalidDataException();
		for (Person person : people) {
			if (person instanceof Teacher) {
				if (person.getId().equals(teacherID)) {
					((Teacher) person).taughtCourse(c, year, semester, section);
					found = true;
					break;
				}
			}
		}
		if (!found)
			throw new InvalidDataException();
	}

	public int howManyCoursesPerSemester(Integer teacherID, Integer year, String semester) throws InvalidDataException {
		if (teacherID == null || year == null || semester == null)
			throw new InvalidDataException();
		int many = 0;
		for (Person person : people) {
			if (person.getId().equals(teacherID)) {
				for (TaughtCourse course : ((Teacher) person).getTaughtCourses()) {
					if (course.getYear().equals(year) && course.getSemesterID().name().equalsIgnoreCase(semester))
						many++;
				}
				break;
			}
		}
		return many;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidDataException {
		if (phoneNumber == null)
			throw new InvalidDataException();
		if (phoneNumber.length() != 10)
			throw new InvalidDataException();
		this.phoneNumber = phoneNumber;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public Address getAddress() throws InvalidDataException {
		Address ad = new Address(address.getStreet(), address.getCity(), address.getState().name(),
				address.getZipCode());
		return ad;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public ArrayList<Person> getPeople() {
		ArrayList<Person> temp = new ArrayList<Person>();
		for (Person person : people) {
			temp.add(person);
		}
		return temp;
	}

	public ArrayList<Course> getCourses() {
		ArrayList<Course> temp = new ArrayList<Course>();
		for (Course course : courses) {
			temp.add(course);
		}
		return temp;
	}

	public ArrayList<Department> getDepartments() {
		ArrayList<Department> temp = new ArrayList<Department>();
		for (Department department : departments) {
			temp.add(department);
		}
		return temp;
	}

	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("School: " + this.schoolName);
		builder.append(" Address: " + this.address);
		builder.append(" Phone Number: " + this.phoneNumber);
		builder.append("\nPeople: \n" + this.people);
		builder.append("\nCourses: \n");
		for (Course course : courses) {
			builder.append(course + "\n");
		}
		builder.append("\nDepartments: \n" + this.departments);
		return builder.toString();
	}
}
