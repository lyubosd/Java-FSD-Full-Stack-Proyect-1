package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class FileManager implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_PATH = "files.ser";
	private ArrayList<String> files;

	public FileManager() {
		this.files = new ArrayList<String>();
	}

	public void showAllFileNames() {
		if (files.isEmpty()) {
			System.out.println("No registered files :(");
		} else {
			Collections.sort(files);
			for (String name : files) {
				System.out.println(name);
			}
		}
	}

	public void addFile() {
		do {
			System.out.println("File path: (Empty to quit)");
			String path = Helper.getText();
			if (!Helper.isValidPath(path)) {
				System.out.println("Invalid file path");
				continue;
			}
			if(path.length() == 0) {
				break;
			}

			File f = new File(path);
			if (!f.isFile() || f.isDirectory()) {
				System.out.println("Invalid file");
				continue;
			}
			
			files.add(path);
			System.out.println("The file has been added! Do you want to add more? (Y/N)");
			char opt = Character.toLowerCase(Helper.getText().charAt(0));

			if (opt == 'n') {
				break;
			}

		} while (true);
	}

	public void deleteFile() {
		do {
			System.out.println("File path: (Empty to quit)");
			String path = Helper.getText();
			if(path.length() == 0) {
				break;
			}
			if (files.contains(path)) {
				files.remove(path);
				System.out.println("The file has been removed. Do you want to delete more files? (Y/N)");
				char opt = Character.toLowerCase(Helper.getText().charAt(0));

				if (opt == 'n') {
					break;
				}
			} else {
				System.out.println("The file wasn´t found.");
			}

		} while (true);
	}

	public void searchFile() {
		System.out.println("Query string: ");
		String query = Helper.getText();
		boolean found = false;
		for (String name : files) {
			if (name.contains(query)) {
				System.out.println(name);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No file was found!");
		}
	}

	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(SAVE_PATH);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			System.out.println("Error while saving " + e.getMessage());
		}
	}
	
	public void load() {
		try {
	         FileInputStream fileIn = new FileInputStream(SAVE_PATH);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         FileManager manager = (FileManager) in.readObject();
	         this.files = manager.files;
	         in.close();
	         fileIn.close();
	         System.out.println("Files loaded ;)");
	      } catch (Exception e) {
	    	  System.out.println("No previous saves found");
	      }
	}

	public static void readFile(String name) {
		InputStream input = FileManager.class.getResourceAsStream("/resources/" + name);

		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		StringBuffer result = new StringBuffer();
		String str;
		try {
			while ((str = reader.readLine()) != null) {
				result.append(str + "\n");
			}
			System.out.println(result.toString());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
