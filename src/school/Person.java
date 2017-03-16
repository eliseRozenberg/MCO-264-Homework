package school;

public class Person {

	private Integer id;
	private String firstName;
	private String lastName;
	private Character midInitial;
	private Address address;
	private String phoneNumber;
	private Character gender;

	// no midInitial
	public Person(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, String phoneNumber, Character gender) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, phoneNumber, gender);
	}

	// no number
	public Person(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, Character gender) throws InvalidDataException {
		this(id, firstName, lastName, midInitial, street, city, state, zipCode, null, gender);
	}

	// no number and midInitial
	public Person(Integer id, String firstName, String lastName, String street, String city, String state,
			String zipCode, Character gender) throws InvalidDataException {
		this(id, firstName, lastName, null, street, city, state, zipCode, null, gender);
	}

	// all
	public Person(Integer id, String firstName, String lastName, Character midInitial, String street, String city,
			String state, String zipCode, String phoneNumber, Character gender) throws InvalidDataException {
		if (id == null || firstName == null || lastName == null || gender == null || street == null || city == null
				|| state == null || zipCode == null)
			throw new InvalidDataException();
		if (id.toString().length() != 5)
			throw new InvalidDataException();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midInitial = midInitial;
		this.address = new Address(street, city, state, zipCode.toString());
		this.phoneNumber = phoneNumber;
		if (gender.equals('f') || gender.equals('F') || gender.equals('M') || gender.equals('m'))
			this.gender = gender;
		else
			throw new InvalidDataException();
	}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Character getMidInitial() {
		return midInitial;
	}

	public Address getAddress() throws InvalidDataException {
		Address addressCopy = new Address(address.getStreet(), address.getCity(), address.getState().name(),
				address.getZipCode());
		return addressCopy;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Character getGender() {
		return gender;
	}

	public void setLastName(String lastName) throws InvalidDataException {
		if (lastName == null)
			throw new InvalidDataException();
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAddress(String street, String cityState, String zipCode) throws InvalidDataException {
		if (street == null || cityState == null || zipCode == null)
			throw new InvalidDataException();
		String[] tokens = cityState.split(",");
		this.address.setStreet(street);
		this.address.setCity(tokens[0]);
		this.address.setState(tokens[1]);
		this.address.setZipCode(zipCode);
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidDataException {
		if (phoneNumber == null)
			throw new InvalidDataException();
		if (phoneNumber.length() != 10)
			throw new InvalidDataException();
		this.phoneNumber = phoneNumber;
	}

	public int compareTo(Person other) throws InvalidDataException {
		if (other == null)
			throw new InvalidDataException();
		return this.id.compareTo(other.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(" ID: " + id);
		info.append(" First Name: " + firstName);
		if (midInitial == null||midInitial == ' ')
			info.append(" Mid Initial: N/A");
		else
			info.append(" Mid Initial: " + midInitial);
		info.append(" Last Name: " + lastName);
		info.append(" Address: " + address);
		if (phoneNumber == null)
			info.append(" Phone Number: N/A");
		else
			info.append(" Phone Number: " + phoneNumber);
		info.append(" Gender: " + gender);
		return info.toString();
	}
}
