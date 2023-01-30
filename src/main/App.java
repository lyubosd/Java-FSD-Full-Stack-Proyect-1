package main;

public class App {

	public static void main(String[] args) {
		FileManager manager = new FileManager();
		manager.load();
		FileManager.readFile("Hello.txt");
		Helper.openScanner();
		Menu menu = new Menu(manager);
		menu.start();
		Helper.closeScanner();
		System.out.println("Thank you for using our application!!");
	}
}
