package pharmacy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//Question:  Why did I choose to place the company name as the last item on each line?
//so that we can read it in as a .nextLine because it may have more than 1 word that .next() won't read

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import school.InvalidDataException;

public class PharmacyList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<CompanyCodeIndex> codeIndex;
	private ArrayList<CompanyNameIndex> nameIndex;

	// transient
	transient private RandomAccessFile companyData;

	public PharmacyList() throws FileNotFoundException {
		codeIndex = new ArrayList<CompanyCodeIndex>();
		nameIndex = new ArrayList<CompanyNameIndex>();
		companyData = new RandomAccessFile("pharmacies", "rw");
	}

	public void addCompany(String companyCode, String phoneNumber, String companyName)
			throws DuplicateDataException, IOException, InvalidDataException {
		if (companyCode == null || companyName == null || phoneNumber == null)
			throw new InvalidDataException();
		if (!Verifier.isValidPhoneNumber(phoneNumber))
			throw new InvalidDataException();
		if (codeIndex.contains(new CompanyCodeIndex(companyCode.toUpperCase(), 0L)))
			throw new DuplicateDataException();
		else {
			PharmaceuticalCo co = new PharmaceuticalCo(companyCode, phoneNumber, companyName);
			Long location = this.companyData.length();
			location = co.writeToFile(this.companyData, location);

			// fix???
			CompanyCodeIndex index = new CompanyCodeIndex(companyCode, location);
			this.codeIndex.add(index);
			CompanyNameIndex index2 = new CompanyNameIndex(companyName, location);
			this.nameIndex.add(index2);
			Collections.sort(codeIndex);
			Collections.sort(nameIndex);
		}
	}

	public PharmaceuticalCo findCompanyCode(String companyCode) throws NotFoundException, IOException, InvalidDataException {
		if (companyCode == null)
			throw new InvalidDataException();
		for (CompanyCodeIndex index : codeIndex) {
			if (index.getCompanyCode().equalsIgnoreCase(companyCode)) {
				if (index.getIsActive() == false)
					throw new NotFoundException();
				else {
					Long recLocation = index.getLocation();
					PharmaceuticalCo co = new PharmaceuticalCo(companyData, recLocation);
					return co;
				}
			}
		}
		throw new NotFoundException();
	}

	public PharmaceuticalCo findCompanyName(String companyName) throws NotFoundException, IOException, InvalidDataException {
		if (companyName == null)
			throw new InvalidDataException();
		for (CompanyNameIndex index : nameIndex) {
			if (index.getCompanyName().trim().equalsIgnoreCase(companyName)) {
				if (!(index.getIsActive()))
					throw new NotFoundException();
				else {
					Long recLocation = index.getLocation();
					PharmaceuticalCo co = new PharmaceuticalCo(companyData, recLocation);
					return co;
				}
			}
		}
		throw new NotFoundException();
	}

	public void modifyCompanyPhone(String companyCode, String newNumber) throws IOException, NotFoundException, InvalidDataException {
		if (companyCode == null || newNumber == null)
			throw new InvalidDataException();
		if (companyCode.length() != 3 || !Verifier.isValidPhoneNumber(newNumber))
			throw new InvalidDataException();
		boolean found = false;
		for (CompanyCodeIndex index : codeIndex) {
			if (index.getCompanyCode().equalsIgnoreCase(companyCode)) {
				if (!(index.getIsActive()))
					throw new NotFoundException();
				else {
					Long recLocation = index.getLocation();
					PharmaceuticalCo co = new PharmaceuticalCo(companyData, recLocation);
					co.setPhoneNumber(newNumber);
					co.writeToFile(companyData, recLocation);
					found = true;
					break;
				}
			}
		}
		if (!found)
			throw new NotFoundException();
	}

	public void removeCo(String code) throws NotFoundException, IOException {
		boolean found = false;
		boolean found2 = false;
		PharmaceuticalCo co = null;
		for (CompanyCodeIndex index : codeIndex) {
			if (index.getCompanyCode().equalsIgnoreCase(code)) {
				index.setNotActive();
				Collections.sort(codeIndex);
				found = true;
				for (CompanyNameIndex index2 : nameIndex) {
					if (index.getLocation().equals(index2.getLocation())) {
						index.setNotActive();
						found2 = true;
						Collections.sort(nameIndex);
						break;
					}
				}
				break;
			}
		}
		if (!found)
			throw new NotFoundException();
	}

	public void closeFiles() throws IOException {
		companyData.close();
	}

	public RandomAccessFile getFile() {
		return companyData;
	}

	public ArrayList<CompanyCodeIndex> getCompanyCodeIndexes() {
		return codeIndex;
	}

	public ArrayList<CompanyNameIndex> getCompanyNameIndexes() {
		return nameIndex;
	}

	public void shutdown() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pharmacy.ser"));
		out.writeObject(codeIndex);
		out.writeObject(nameIndex);
		out.close();
	}

}