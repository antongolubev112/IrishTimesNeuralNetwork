package ie.gmit.sw;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static Menu m = null;
	private int choice;
	private Scanner in = new Scanner(System.in);
	private static NNFactory factory=NNFactory.getInstance();
	private EncogNN nn;
	private Scanner scanner;
	private String filename;
	private int vecSize ;
	private int shingleSize;
	

	private Menu() {

	}

	public static Menu getInstance() {
		if (m == null)
			m = new Menu();

		return m;
	}

	public void showMenu() throws IOException {
		
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*      GMIT - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*             Artificial Intelligence Assignment           *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Upload Training data + Train Network");
		System.out.println("(2) Upload Test Data");
		System.out.println("(3) Classify a Single Headline");
		System.out.println("(4) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-3]>");
		System.out.println();
		choice=in.nextInt();
		processChoice();
		//in.close();
	}

	private void processChoice() throws IOException, InputMismatchException{
		
		switch (choice) {
		case 1:
			scanner= new Scanner(System.in);
			System.out.println("Please enter file name: ");
			filename = scanner.nextLine();
			System.out.println("Please enter the vector size for the inputs: ");
			vecSize = scanner.nextInt();
			System.out.println("Please enter the size of the shingles: ");
			shingleSize = scanner.nextInt();
			
			//create and train neural net
			nn=factory.create(vecSize, shingleSize, filename);
			break;
		
		case 2: 
			if(nn==null) {
				System.out.println("No neural network available, please upload test data");
			}else {
				scanner= new Scanner(System.in);
				System.out.println("Please enter file name: ");
				filename= scanner.nextLine();
				
				//test the neural network
				factory.test(nn, vecSize, shingleSize, filename);
			}
			break;
		
		case 3:
			System.out.println("Please enter a headline in the following format:");
			System.out.println("Date(YYYYMMDD),headline category,headline:");
			System.out.println("EG: 20210101,lifestyle.health-family,\"The art of medical observation\"");
		default:
			System.out.println("Please select a valid option");
		}
		System.out.print("Select Option [1-4]>");
		System.out.println();
		choice=in.nextInt();
		processChoice();
		
		
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

}
