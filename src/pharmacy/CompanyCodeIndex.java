package pharmacy;

import java.io.Serializable;

import school.InvalidDataException;

public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String companyCode;
	private Long location;
	private boolean isActive;

	public CompanyCodeIndex(String companyCode, Long location) throws InvalidDataException {
		if (companyCode == null || location == null)
			throw new InvalidDataException();
		this.companyCode = companyCode;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public Long getLocation() {
		return location;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setNotActive() {
		this.isActive = false;
	}

	@Override
	public int compareTo(CompanyCodeIndex other) {
		return this.companyCode.compareTo(other.getCompanyCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyCodeIndex other = (CompanyCodeIndex) obj;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append("Company Code: " + this.companyCode);
		info.append(" Record Location: " + this.location);
		return info.toString();
	}

}
