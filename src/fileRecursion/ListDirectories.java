package fileRecursion;

import java.io.File;

public class ListDirectories {

	public static void fileRecursion(File mainFile, int count) {
		count++;
		for (int i = 0; i < count; i++) {
			System.out.print("\t");
		}
		if (!mainFile.isDirectory()) {
			System.out.println("Name: " + mainFile.getName() + " Size: " + mainFile.length());
		} else {
			System.out.println(mainFile.getName().toUpperCase());
			File[] files = mainFile.listFiles();
			for (File file : files) {
				fileRecursion(file, count);
			}
		}
	}

	public static void printFile(String name) {
		File file = new File(name);
		fileRecursion(file, 0);
	}

	public static void main(String[] args) {
		printFile(System.getProperty("user.home"));
	}
}
