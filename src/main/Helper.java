package main;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
	public static Scanner sc;
	
	public static void openScanner() {
		sc = new Scanner(System.in);
	}
	
	public static void closeScanner() {
		if (sc != null) sc.close();
	}
	
	
	public static String getText() {
		return sc.nextLine();
	}
	
	public static int getInt() {
		try {
			int n = sc.nextInt();
			sc.nextLine();
			return n;
		} catch (InputMismatchException e) {
			sc.nextLine();
			return -1;
		}
	}
	
	public static boolean isValidPath(String path) {
	    try {
	        Paths.get(path);
	    } catch (InvalidPathException | NullPointerException ex) {
	        return false;
	    }
	    return true;
	}
	
	
}
