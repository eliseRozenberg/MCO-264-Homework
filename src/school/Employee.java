package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {

	// it said that id was independent of Person- but person has an id
	// major isn't mentioned as an independent field and isn't a field in person
	// but is mentioned as an option so i added it
	private GregorianCalendar hireDate;
	private GregorianCalendar dateOfBirth;
	private EmployeeType employeeTypeID;

	// no phoneNumber
	public Employee(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, Character gender, String hireDate, String dateOfBirth, String employeeType)
					throws InvalidDataException {
		this(id, firstName, lastName, midInitial, street, city, state, zipCode, null, gender, hireDate, dateOfBirth,
				employeeType);
	}

	// no midInitial
	public Employee(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, String phoneNumber, Character gender, String hireDate, String dateOfBirth,
			String employeeType) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, phoneNumber, gender, hireDate, dateOfBirth,
				employeeType);
	}

	// no midInitial or phoneNumber
	public Employee(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, Character gender, String hireDate, String dateOfBirth, String employeeType)
					throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, null, gender, hireDate, dateOfBirth,
				employeeType);
	}

	// all fields
	public Employee(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, String phoneNumber, Character gender, String hireDate, String dateOfBirth,
			String employeeType) throws InvalidDataException {
		super(id, firstName, lastName, midInitial, street, city, state, zipCode, phoneNumber, gender);
		String[] tokens = hireDate.split("/");
		if (tokens.length != 3)
			throw new InvalidDataException();
		int month = Integer.parseInt(tokens[0]);
		int date = Integer.parseInt(tokens[1]);
		int year = Integer.parseInt(tokens[2]);
		this.hireDate = new GregorianCalendar(year, month - 1, date);

		tokens = dateOfBirth.split("/");
		if (tokens.length != 3)
			throw new InvalidDataException();
		month = Integer.parseInt(tokens[0]);
		date = Integer.parseInt(tokens[1]);
		year = Integer.parseInt(tokens[2]);
		this.dateOfBirth = new GregorianCalendar(year, month - 1, date);

		if ((this.hireDate.get(Calendar.YEAR) < this.dateOfBirth.get(Calendar.YEAR)) ||
				// verify if earlier year
				(this.hireDate.get(Calendar.YEAR) == this.dateOfBirth.get(Calendar.YEAR) &&
						// verify if same year earlier month
						this.hireDate.get(Calendar.MONTH) < this.dateOfBirth.get(Calendar.MONTH))
				|| (this.hireDate.get(Calendar.YEAR) == this.dateOfBirth.get(Calendar.YEAR) &&
						// verify if same year and same month but earlier or
						// same date
		this.hireDate.get(Calendar.MONTH) == this.dateOfBirth.get(Calendar.MONTH)
						&& this.hireDate.get(Calendar.DAY_OF_MONTH) <= this.dateOfBirth.get(Calendar.DAY_OF_MONTH)))
			throw new InvalidDataException();

		if ((this.dateOfBirth.get(Calendar.YEAR) + 18) > this.hireDate.get(Calendar.YEAR))
			throw new InvalidDataException();

		this.employeeTypeID = getEmployeeType(employeeType);
		if (this.employeeTypeID == null)
			throw new InvalidDataException();
	}

	public static EmployeeType getEmployeeType(String employeeType) {
		for (EmployeeType theEmployeeType : EmployeeType.values()) {
			if (theEmployeeType.name().equalsIgnoreCase(employeeType))
				return theEmployeeType;
		}
		return null; // couldn't find the employeeType
	}

	public GregorianCalendar getHiredDate() {
		GregorianCalendar hiredDateCopy = new GregorianCalendar(hireDate.get(Calendar.YEAR),
				hireDate.get(Calendar.MONTH), hireDate.get(Calendar.DATE));
		return hiredDateCopy;
	}

	public GregorianCalendar getDateOfBirth() {
		GregorianCalendar dateOfBirthCopy = new GregorianCalendar(dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH), dateOfBirth.get(Calendar.DATE));
		return dateOfBirthCopy;
	}

	public EmployeeType getEmployeeTypeID() {
		return employeeTypeID;
	}

	public void setEmployeeTypeID(String employeeTypeID) throws InvalidDataException {
		this.employeeTypeID = getEmployeeType(employeeTypeID);
		if (this.employeeTypeID == null)
			throw new InvalidDataException();
	}

	public String toString() {
		StringBuffer info = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		info.append("Employee");
		info.append(super.toString());
		info.append(" Hire Date: " + formatter.format(hireDate.getTime()));
		info.append(" Date of Birth: " + formatter.format(dateOfBirth.getTime()));
		info.append(" Employee Type: " + employeeTypeID.name());
		return info.toString();
	}
}
