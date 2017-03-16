package school;

public class Department {
	private String departmentID;
	private String departmentName;
	private String location;
	private String phoneNumber;
	private String faxNumber;
	private Integer departmentChairPerson;

	// more constructors?
	public Department(String id, String name) throws InvalidDataException {
		this(id, name, null, null, null, null);

	}

	public Department(String id, String name, String loc, String phone, String fax, Integer chair)
			throws InvalidDataException {
		if (id == null || name == null)
			throw new InvalidDataException();
		if (id.length() != 4)
			throw new InvalidDataException();
		departmentID = id;
		departmentName = name;
		location = loc;
		if (phone != null && phone.length() != 10)
			throw new InvalidDataException();
		phoneNumber = phone;
		if (fax != null && fax.length() != 10)
			throw new InvalidDataException();
		faxNumber = fax;
		if (chair != null && chair.toString().length() != 5 && chair != null)
			throw new InvalidDataException();
		departmentChairPerson = chair;

	}

	public String getDepartmentID() {

		return departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) throws InvalidDataException {
		if (location == null)
			throw new InvalidDataException();

		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone) throws InvalidDataException {
		if (phone == null)
			throw new InvalidDataException();
		if (phone.length() != 10)
			throw new InvalidDataException();
		this.phoneNumber = phone;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String fax) throws InvalidDataException {
		if (fax == null)
			throw new InvalidDataException();
		if (fax.length() != 10)
			throw new InvalidDataException();
		this.faxNumber = fax;
	}

	public Integer getDepartmentChairPerson() {
		return departmentChairPerson;
	}

	public void setDepartmentChairPerson(Integer departmentChairPerson) throws InvalidDataException {
		if (departmentChairPerson == null)
			throw new InvalidDataException();
		if (departmentChairPerson.toString().length() != 5)
			throw new InvalidDataException();
		this.departmentChairPerson = departmentChairPerson;
	}

	public int compareTo(Department other) throws InvalidDataException {
		if (other == null)
			throw new InvalidDataException();
		return this.departmentID.compareTo(other.departmentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Department))
			return false;
		Department other = (Department) obj;
		if (departmentID == null) {
			if (other.departmentID != null)
				return false;
		} else if (!departmentID.equals(other.departmentID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(" Department ID: " + departmentID);
		info.append(" Department Name: " + departmentName);
		if (location == null) {
			info.append(" Location: N/A");
		} else {
			info.append(" Location: " + location);
		}
		if (phoneNumber == null) {
			info.append(" Phone Number: N/A");
		} else {
			info.append(" Phone Number: " + phoneNumber);
		}
		if (faxNumber == null) {
			info.append(" Fax Number: N/A");
		} else {
			info.append(" Fax Number: " + faxNumber);
		}
		if (departmentChairPerson == null) {
			info.append(" Department Chair Person: N/A");
		} else {
			info.append(" Department Chair ID: " + departmentChairPerson + "\n");
		}
		return info.toString();

	}

}
