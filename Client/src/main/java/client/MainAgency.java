package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import webservice.Hotel;
import webservice.HotelService;
import webservice.HotelServiceImplService;

public class MainAgency {

	public static void main(String[] args) {
		
		boolean debug = true;
		Agency agency = new Agency();
		HotelService proxy = null;
		try {
			proxy = new HotelServiceImplService(new URL("http://localhost:8080/hotel?wsdl")).getHotelServiceImplPort();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("Error while retrieving hotel's info");
			System.exit(1);
		}
		Scanner scanner = new Scanner(System.in);
		if(!debug) {
			String username = "";
			String pwd = "";
			System.out.println("Please enter your username :\n");
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
		}
		
		System.out.println("Welcome to TripFinder !");
		System.out.println("1. Find a hotel \n2. View all available offers \n3. Make a reservation\n");
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Where do you want to go ?\n");
			String location = "Montpellier";
			System.out.println("When would you like to go ? (yyyy-MM-dd))\n");
			String in = "2022-10-20";
			System.out.println("When would you like to leave ? (yyyy-MM-dd))\n");
			String out = "2022-10-25";
			System.out.println("How many people will be with you ?\n");
			int size = 1;
			System.out.println("Select your range of price\n Price min : ");
			int priceMin = 0;
			System.out.println("Price max : ");
			int priceMax = 150;
			System.out.println("Looking for the best offers...\n");
			ArrayList<Hotel> hotels = new ArrayList<Hotel>();
			Hotel results = agency.searchRoom(proxy, in, out, size, priceMin, priceMax, location);
			hotels.add(results);
			results.getName();
			for(Hotel h : hotels) {
				System.out.println(h.getName() + " " + h.getStars() + "\n" + h.getRooms().toString());
			}
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
