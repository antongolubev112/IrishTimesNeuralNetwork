package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
	private static Menu m = null;
	private int choice;
	

	private Menu() {

	}

	public static Menu getInstance() {
		if (m == null)
			m = new Menu();

		return m;
	}

	public void showMenu() throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*      GMIT - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*             Artificial Intelligence Assignment           *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Train Neural Network");
		System.out.println("(2) Upload test data");
		System.out.println("(2) See Neural Network stats");
		System.out.println("(3) Don't hard-code paths / file fames or bad things will happen");
		System.out.println("(4) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-4]>");
		System.out.println();
		setChoice(in.nextInt());
		processChoice();
		//in.close();
	}

	private void processChoice() throws IOException {
		switch (choice) {
		case 1:
			
		case 2:
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter file name: ");
			String filename = reader.readLine();
			
			System.out.println("File name is:"+ filename);
			break;
		}

	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

}
