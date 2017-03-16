
package pharmacy;

import java.io.Serializable;

import school.InvalidDataException;

public class CompanyNameIndex implements Comparable<CompanyNameIndex>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyName;
	private Long location;
	private Boolean isActive;

	public CompanyNameIndex(String companyName, Long location) throws InvalidDataException {
		if (companyName == null | location == null)
			throw new InvalidDataException();
		this.companyName = companyName;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Long getLocation() {
		return location;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setNotActive() {
		this.isActive = false;
	}

	@Override
	public int compareTo(CompanyNameIndex other) {
		return this.companyName.compareTo(other.getCompanyName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyNameIndex other = (CompanyNameIndex) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append("Company Name: " + this.companyName);
		info.append(" Record Location: " + this.location);
		return info.toString();
	}

}
