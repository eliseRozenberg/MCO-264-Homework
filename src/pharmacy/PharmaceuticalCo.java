package pharmacy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import school.InvalidDataException;

public class PharmaceuticalCo {
	private String companyCode;
	private String phoneNumber;
	private String companyName;

	public PharmaceuticalCo(String companyCode, String phoneNumber, String companyName) throws InvalidDataException {
		if (!(Verifier.isValidPhoneNumber(phoneNumber)))
			throw new InvalidDataException();
		this.companyCode = companyCode;
		this.phoneNumber = phoneNumber;
		this.companyName = companyName;
	}

	public PharmaceuticalCo(Scanner filename) throws FileNotFoundException, InvalidDataException {
		this.companyCode = filename.next();
		this.phoneNumber = filename.next();
		this.companyName = filename.nextLine();

		if (!(Verifier.isValidPhoneNumber(phoneNumber)))
			throw new InvalidDataException();
		filename.close();
	}

	public PharmaceuticalCo(RandomAccessFile raFile, Long location) throws IOException, InvalidDataException {
		raFile.seek(location);
		this.companyCode = raFile.readUTF().trim();
		this.phoneNumber = raFile.readUTF().trim();
		if (!Verifier.isValidPhoneNumber(phoneNumber)) {
			throw new InvalidDataException();
		}
		this.companyName = raFile.readUTF().trim();
	}

	public void setPhoneNumber(String number) throws InvalidDataException {
		if (number == null || number.length() != 10)
			throw new InvalidDataException();
		this.phoneNumber = number;
	}

	public Long writeToFile(RandomAccessFile raFile, Long location) throws IOException {
		raFile.seek(location);
		raFile.writeUTF(String.format("%-4s", this.companyCode));
		raFile.writeUTF(String.format("%-10s", this.phoneNumber));
		raFile.writeUTF(String.format("%-25s", this.companyName));
		return location;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int compareTo(PharmaceuticalCo co) {
		return this.companyCode.compareTo(co.getCompanyCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PharmaceuticalCo other = (PharmaceuticalCo) obj;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company Code: " + this.companyCode);
		builder.append(" Phone Number: " + this.phoneNumber);
		builder.append(" Company Name: " + this.companyName);

		return builder.toString();
	}
}