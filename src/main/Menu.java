package main;

public class Menu {
	FileManager manager;

	public Menu(FileManager manager) {
		this.manager = manager;
	}

	public void start() {
		boolean stop = false;
		do {
			showMainMenu();
			int option = Helper.getInt();
			switch (option) {
			case 1:
				manager.showAllFileNames();
				break;
			case 2:
				startManagerMenu();
				break;
			case 3:
				stop = true;
			}
		} while (!stop);
	}

	public void showMainMenu() {
		FileManager.readFile("mainMenu.txt");
	}

	public void startManagerMenu() {
		boolean stop = false;
		do {
			FileManager.readFile("managerMenu.txt");
			int option = Helper.getInt();
			switch (option) {
			case 1:
				manager.addFile();
				manager.save();
				break;
			case 2:
				manager.deleteFile();
				manager.save();
				break;
			case 3:
				manager.searchFile();
				break;
			case 4:
				stop = true;
			}
		} while (!stop);
	}

}
