package school;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ManageSchool {

	// fix exceptions()
	// fix equals to equalsignorcase when needed
	// add breaks
	// try to neaten it up
	// make sure to have all options of constructors
	// check that x return object- calendar

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String first, last, street, city, state, zip, phone, fax, major, date, DOB, degree, ssn, departID, courseID,
				empType, description, grade, section, semester;
		char mid, gender;
		double salary, percent;
		int choice, id, credits, amount, year;
		Teacher teacher;
		Student student;
		Course course;
		Department department;
		Address address;

		try {
			School school = new School("BYDRH", new Address("235 N Main St", "Spring Valley", "NY", "10977"),
					"8453716750", "./teachers.txt", "./students.txt", "./departments.txt", "./courses.txt");
			System.out.println(school);

			do {
				choice = menu();
				try {
					switch (choice) {
					case 1:// add teacher
						System.out.println("Enter Teacher Information: ");
						System.out.print(" ID#:");
						id = input.nextInt();
						System.out.print(" First name: ");
						first = input.next();
						System.out.print(" Last name:");
						last = input.next();
						System.out.print("Do you have a Middle Initial ? (1) Yes (2) No");
						choice = input.nextInt();
						if (choice == 1) {
							System.out.print(" Mid Initial: ");
							mid = input.next().charAt(0);
						} else
							mid = ' ';
						System.out.println(" Enter Address: ");
						input.nextLine();
						System.out.print("  Street: ");
						street = input.nextLine();
						System.out.print("  City: ");
						city = input.nextLine();
						System.out.print("  State: ");
						state = input.nextLine();
						System.out.print("  Zip:");
						zip = input.next();
						System.out.print("Do you have a Phone# ? (1) Yes (2) No");
						choice = input.nextInt();
						if (choice == 1) {
							System.out.print(" Phone#: ");
							phone = input.next();
						} else
							phone = "N/A";
						System.out.print(" Gender: ");
						gender = input.next().charAt(0);
						System.out.print(" Date Hired: (mm/dd/yyyy)");
						date = input.next();
						System.out.print(" Date of Birth: (mm/dd/yyyy)");
						DOB = input.next();
						System.out.print(" Employee Type: ");
						empType = input.next();
						System.out.print(" Department ID: ");
						departID = input.next();
						System.out.print(" SSN#");
						ssn = input.next();
						System.out.print(" Degree: ");
						degree = input.next();
						System.out.print(" Major: ");
						major = input.next();
						System.out.print(" Salary: ");
						salary = input.nextDouble();
						teacher = new Teacher(id, first, last, mid, street, city, state, zip, phone, gender, date, DOB,
								empType, departID, ssn, degree, major, salary);
						school.addTeacher(teacher);
						System.out.println("Teacher added");
						break;
					case 2:// add student
						System.out.println("Enter Student Information: ");
						System.out.print(" ID#:");
						id = input.nextInt();
						System.out.print(" First name: ");
						first = input.next();
						System.out.print(" Last name: ");
						last = input.next();
						System.out.print("Do you have a Middle Initial ? (1) Yes (2) No");
						choice = input.nextInt();
						if (choice == 1) {
							System.out.print(" Mid Initial: ");
							mid = input.next().charAt(0);
						} else
							mid = ' ';
						System.out.println(" Enter Address: ");
						input.nextLine();
						System.out.print("  Street: ");
						street = input.nextLine();
						System.out.print("  City: ");
						city = input.nextLine();
						System.out.print("  State: ");
						state = input.nextLine();
						System.out.print("  Zip:");
						zip = input.next();
						System.out.print("Do you have a Phone# ? (1) Yes (2) No");
						choice = input.nextInt();
						if (choice == 1) {
							System.out.print(" Phone#: ");
							phone = input.next();
						} else
							phone = "N/A";
						System.out.print(" Gender: ");
						gender = input.next().charAt(0);
						input.nextLine();
						System.out.print(" Major: ");
						major = input.nextLine();
						System.out.print(" Date Enrolled (mm/dd/yyyy):");
						date = input.next();
						System.out.print(" Date of Birth: ");
						DOB = input.next();
						System.out.print(" SSN#");
						ssn = input.next();
						student = new Student(id, first, last, mid, street, city, state, zip, phone, gender, major,
								date, DOB, ssn);
						school.addStudent(student);
						System.out.println("Student added");

						break;
					case 3:// add course
						System.out.println("Enter Course Information: ");
						System.out.print(" ID#:");
						courseID = input.next().toUpperCase();
						input.nextLine();
						System.out.print(" Description: ");
						description = input.nextLine();
						System.out.print(" Number of credits: ");
						credits = input.nextInt();
						input.nextLine();
						System.out.print(" Department ID: ");
						departID = input.next().toUpperCase();
						course = new Course(courseID, description, departID, credits);
						school.addCourse(course);
						System.out.println("Course added");

						break;
					case 4:// add department
						System.out.println("Enter Department Information: ");
						System.out.print(" ID#:");
						departID = input.next().toUpperCase();
						input.nextLine();
						System.out.print(" Name: ");
						first = input.nextLine();
						System.out.print("Add Further Information ? (1) yes (2) no");
						choice = input.nextInt();
						input.nextLine();
						if (choice == 1) {
							System.out.print(" Location: ");
							street = input.nextLine();
							System.out.print(" Phone#: ");
							phone = input.next();
							System.out.print(" Fax#: ");
							fax = input.next();
							System.out.print(" Department Chair ID#: ");
							id = input.nextInt();
							department = new Department(departID, first, street, phone, fax, id);
						} else
							department = new Department(departID, first);
						school.addDepartment(department);
						System.out.println("Department added");

						break;
					case 5:// remove teacher
						System.out.print("Enter Teacher ID#: ");
						id = input.nextInt();
						school.removeTeacher(id);
						System.out.println("Teacher Removed");

						break;
					case 6:// remove student
						System.out.print("Enter student ID#: ");
						id = input.nextInt();
						school.removeStudent(id);
						System.out.println("Student Removed");

						break;
					case 7:// remove course
						System.out.print("Enter course ID#: ");
						courseID = input.next().toUpperCase();
						school.removeCourse(courseID);
						System.out.println("Course Removed");

						break;
					case 8:// modify teachers last name
						System.out.println("Enter Information: ");
						System.out.print(" Teacher ID#: ");
						id = input.nextInt();
						System.out.print(" New last name: ");
						last = input.next();
						school.modifyTeacherLastName(id, last);
						System.out.println("Record modified");

						break;
					case 9:// modify teachers address
						System.out.println("Enter Teacher Information: ");
						System.out.print(" ID#: ");
						id = input.nextInt();
						System.out.println(" Enter Address: ");
						input.nextLine();
						System.out.print("  Street: ");
						street = input.nextLine();
						System.out.print("  City: ");
						city = input.nextLine();
						System.out.print("  State: ");
						state = input.next();
						System.out.print("  Zip:");
						zip = input.next();
						address = new Address(street, city, state, zip);
						school.modifyTeacherAddress(id, address);
						System.out.println("Record modified");
						break;
					case 10:// modify teachers degree
						System.out.println("Enter Teacher Information: ");
						System.out.print("ID#: ");
						id = input.nextInt();
						System.out.print(" New degree: ");
						degree = input.next();
						input.nextLine();
						System.out.print("  Major: ");
						major = input.nextLine();
						school.modifyTeacherDegree(id, degree, major);
						System.out.println("Record modified");

						break;
					case 11:// Give Teacher Raise
						System.out.print("Enter Teacher ID#: ");
						id = input.nextInt();
						System.out.println("Select: (1) percent (2) amount");
						choice = input.nextInt();
						while (choice != 1 && choice != 2) {
							System.out.println(" Invalid Selection");
							System.out.println("Select: (1) percent (2) amount");
							choice = input.nextInt();
						}
						if (choice == 1) {
							System.out.print(" Enter Percent: (Decimal)");
							percent = input.nextDouble();
							school.giveTeacherRaise(id, percent);
							System.out.println("Raise Added");

						} else {
							System.out.print(" Enter Amount: ");
							amount = input.nextInt();
							school.giveTeacherRaise(id, amount);
							System.out.println("Raise Added");

						}
						break;
					case 12:// Modify Student Last Name
						System.out.println("Enter Student Information: ");
						System.out.print(" ID#: ");
						id = input.nextInt();
						System.out.print(" New last name: ");
						last = input.next();
						school.modifyStudentLastName(id, last);
						System.out.println("Record modified");

						break;
					case 13:// Modify Student Phone Number
						System.out.println("Enter Student Information: ");
						System.out.print("ID#: ");
						id = input.nextInt();
						System.out.print("New Phone#: ");
						phone = input.next();
						school.modifyStudentPhoneNumber(id, phone);
						System.out.println("Record modified");

						break;
					case 14:// Add Completed Course
						System.out.print("Enter student ID#: ");
						id = input.nextInt();
						System.out.print("Course ID: ");
						courseID = input.next().toUpperCase();
						System.out.print("Alphabetic grade: ");
						grade = input.next();
						System.out.print("Date Completed: (mm/dd/yyyy)");
						date = input.next();
						school.addCompletedCourse(id, courseID, grade, date);
						System.out.println("Course Added");

						break;
					case 15:// Get Student GPA
						System.out.print("Enter student ID#: ");
						id = input.nextInt();
						try {
							System.out.println("GPA: " + school.getStudentGPA(id));
						} catch (NullPointerException e) {
							System.out.println("invalid information...");
						}
						break;
					case 16:// Get Grade of Course
						System.out.print("Enter student ID#: ");
						id = input.nextInt();
						System.out.print("Course ID#: ");
						courseID = input.next();
						try {
							System.out.println("Grade: \t" + school.getGradeOfCourse(id, courseID).toString());
						} catch (NullPointerException e) {
							System.out.println("invalid information...");
						}
						break;
					case 17:// Get Courses By Department
						System.out.print("Enter student ID#: ");
						id = input.nextInt();
						System.out.print("Department ID#: ");
						departID = input.next();
						try {
							System.out.println(
									departID.toUpperCase() + " courses:" + school.getCoursesByDepartment(id, departID));
						} catch (NullPointerException e) {
							System.out.println("invalid information...");
						}
						break;
					case 18:// Get Courses By Grade
						System.out.print("Enter student ID#: ");
						id = input.nextInt();
						System.out.print("Alphabetic Grade: ");
						grade = input.next();
						try {
							System.out.println(grade + " Courses: \t" + school.getCoursesByGrade(id, grade));
						} catch (NullPointerException e) {
							System.out.println("invalid information...");
						}
						break;
					case 19:// Get Teachers Sorted By Name
						System.out.println("Teachers sorted by name: \n" + school.getTeachersSortedByName());
						break;
					case 20:// Get Teachers
						System.out.println("Teachers sorted by ID: \n" + school.getTeachers());

						break;
					case 21:// Get Students
						System.out.println("Students sorted by ID: \n" + school.getStudents());
						break;
					case 22:// Get Students By Name
						System.out.println("Students sorted by Name: \n" + school.getStudentSortedByName());
						break;
					case 23:// Add Taught Course
						System.out.print("Enter Teacher ID:");
						id = input.nextInt();
						System.out.print("Course ID:");
						courseID = input.next();
						System.out.print("Year: ");
						year = input.nextInt();
						System.out.print("Semester: ");
						semester = input.next();
						System.out.print("Section: ");
						section = input.next();
						school.addTaughtCourse(id, courseID, year, semester, section);
						System.out.println("Course added");
						break;
					case 24:// How Many Courses Per Semester
						System.out.print("Enter Teacher ID:");
						id = input.nextInt();
						System.out.print("Year: ");
						year = input.nextInt();
						System.out.print("Semester: ");
						semester = input.next();
						try {
							System.out.println("Courses per" + semester + " " + year + ": \n"
									+ school.howManyCoursesPerSemester(id, year, semester));
						} catch (NullPointerException e) {
							System.out.println("invalid information...");
						}
						break;
					case 25:
						System.out.println(school);
						break;
					case 0:
						input.close();
						System.out.println("Program shutting down...");
						System.exit(0);
						break;
					}
				} catch (InvalidDataException e) {
					System.out.println("Invalid Data entered...");

				}
			} while (true);
		} catch (FileNotFoundException e1) {
			System.out.println("File not found...");
		} catch (InvalidDataException e2) {
			System.out.println("Invalid Data entered...");
		}
		input.close();
	} // end main

	public static int menu() {
		System.out.println("\nMENU:");
		System.out.println(
				"1.  Add New Teacher \n2.  Add New Student \n3.  Add New Course \n4.  Add New Department \n5.  Remove Teacher "
						+ "\n6.  Remove Student \n7.  Remove Course \n8.  Modify Teacher's Last Name \n9.  Modify Teacher's Address \n10. Modify Teacher's Degree "
						+ "\n11. Give Teacher Raise \n12. Modify Student's LastName \n13. Modify Student's PhoneNumber \n14. Add Completed Course for Student"
						+ "\n15. Get Student's GPA \n16. Get Grade of Course \n17. Get Courses By Department \n18. Get Courses By Grade \n19. Get Teachers Sorted By Name "
						+ "\n20. Get Teachers \n21. Get Students \n22. Get Students Sorted By Name \n23. Add Taught Course to Teacher \n24. How Many Courses Per Semester for Teacher \n25. Get School \n0. EXI"
						+ "T\n");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		System.out.println("Select option from menu: ");
		int temp = in.nextInt();
		while (temp < 0 || temp > 25) {
			System.out.println("Invalid choice... " + "\tSelect option from menu: ");
			temp = in.nextInt();
		}
		return temp;
	}// end menu
}
