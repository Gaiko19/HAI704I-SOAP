package hotelfinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import hotel.Hotel;

public class MainAgency {

	public static void main(String[] args) {
		
		Agency agency = new Agency();
		
		String username = "";
		String pwd = "";
		System.out.println("Please enter your username :\n");
		Scanner scanner = new Scanner(System.in);
		username = scanner.nextLine();
		System.out.println("Please enter your password :\n");
		pwd = scanner.nextLine();
		
		boolean access = agency.connectClient(username, pwd);
		while(!access) {
			System.out.println("Wrong credentials!\n");
			System.out.println("Please enter your username :\n");
			username = scanner.nextLine();
			System.out.println("Please enter your password :\n");
			pwd = scanner.nextLine();
			access = agency.connectClient(username, pwd);
		}
		System.out.println("Welcome to TripFinder !");
		System.out.println("1. Find a hotel \n2. View all available offers \n3. Make a reservation\n");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Where do you want to go ?\n");
			String location = scanner.nextLine();
			System.out.println("When would you like to go ? (yyyy-MM-dd))\n");
			LocalDate in = LocalDate.parse(scanner.nextLine());
			System.out.println("When would you like to leave ? (yyyy-MM-dd))\n");
			LocalDate out = LocalDate.parse(scanner.nextLine());
			System.out.println("How much person would be with you ?\n");
			int size = scanner.nextInt();
			System.out.println("Select your range of price\n Price min : ");
			int priceMin = scanner.nextInt();
			System.out.println("Price max : ");
			int priceMax = scanner.nextInt();
			System.out.println("Looking for the best offers...\n");
			ArrayList<Hotel> results = agency.searchHotel(in, out, size, priceMin, priceMax, location);
			System.out.println(results);
			break;
		
		case 2:
			
			break;
		
		case 3:
			
			break;

		default:
			scanner.close();
			agency.quitAgency();
		}
		
	}

}