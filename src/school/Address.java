package school;

public class Address {

	private String street;
	private String city;
	private USState state;
	private String zipCode;

	public Address(String street, String city, String state, String zipCode) throws InvalidDataException {
		if (street == null || city == null || state == null || zipCode == null)
			throw new InvalidDataException();
		if (!(zipCode.length() != 5 || zipCode.length() != 9))
			throw new InvalidDataException();
		this.street = street;
		this.city = city;
		this.state = getStateCode(state);
		if (this.state == null) // no corresponding state code could be
								// found
			throw new InvalidDataException();
		this.zipCode = zipCode;

	}

	// should check code or full name
	private static USState getStateCode(String state) {
		for (USState theState : USState.values()) {
			if (theState.name().equalsIgnoreCase(state) || theState.getName().equalsIgnoreCase(state)) { // error//
				return theState;
			}
		}
		return null;// couldn't find the state
	}

	public void setState(String state) throws InvalidDataException {
		if (state == null)
			throw new InvalidDataException();
		this.state = getStateCode(state);
		if (this.state == null) {
			throw new InvalidDataException();
		}
	}

	public void setStreet(String street) throws InvalidDataException {
		if (street == null)
			throw new InvalidDataException();
		this.street = street;
	}

	public void setCity(String city) throws InvalidDataException {
		if (city == null)
			throw new InvalidDataException();
		this.city = city;
	}

	public void setZipCode(String zipCode) throws InvalidDataException {
		if (zipCode == null)
			throw new InvalidDataException();
		if (!(zipCode.length() != 5 || zipCode.length() != 9))
			throw new InvalidDataException();
		this.zipCode = zipCode;
	}

	// getters

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public USState getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(" Street: " + street);
		info.append(" City: " + city);
		info.append(" State: " + state.name());
		info.append(" ZipCode: " + zipCode);
		return info.toString();
	}
}
