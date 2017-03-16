package stringBag;

import java.util.Random;

public class StringBag implements StringLogInterface {
	// fin according to assignment!!!!

	private String name;
	private String[] log;
	private int lastIndex = -1;

	public StringBag(String name, int maxSize) {
		log = new String[maxSize];
		this.name = name;
	}

	public StringBag(String name) {
		log = new String[100];
		this.name = name;
	}

	public void insert(String element) {
		lastIndex++;
		log[lastIndex] = element;
	}

	public String remove() {
		Random rand = new Random();
		int num;
		String temp;
		if (lastIndex == 0) {
			temp = log[lastIndex];
		} else {
			num = rand.nextInt(lastIndex);
			temp = log[num];
			log[num] = log[lastIndex];
		}

		log[lastIndex] = null;
		lastIndex--;
		return temp;
	}

	public boolean isEmpty() {
		if (lastIndex <= -1) {
			return true;
		} else
			return false;
	}

	public boolean isFull() {
		if (lastIndex == (log.length - 1))
			return true;
		else
			return false;
	}

	public int size() {
		return (lastIndex + 1);
	}

	public boolean contains(String element) {
		int location = 0;
		while (location <= lastIndex) {
			if (element.equalsIgnoreCase(log[location]))
				return true;
			else
				location++;
		}
		return false;
	}

	public void clear() {
		for (int i = 0; i <= lastIndex; i++) {
			log[i] = null;
		}
		lastIndex = -1;
	}

	public String getName() {
		return name;
	}
	

	public int getLastIndex() {
		return lastIndex;
	}

	public String toString() {
		String logString = "String Bag " + name + ":\n";
		for (int i = 0; i <= lastIndex; i++)
			logString = logString + (i + 1) + ". " + log[i] + "\n";
		return logString;
	}

}
