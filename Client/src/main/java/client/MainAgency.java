package client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import webservice.Client;

public class MainAgency {
	
	public static void main(String[] args) {
		
		boolean debug = true;
		System.out.println("Goolge :\n1. HotelAdvisor.com\n2. Hotel.net\n");
		Scanner scanner = new Scanner(System.in);
		Agency agency = null;
		int agencyChoice = scanner.nextInt();
		
		switch (agencyChoice) {
			case 1 :
				agency = MainFunctions.HotelAdvisor();
				break;
			case 2 :
				agency = MainFunctions.HoteldotNet();
				break;
			default :
				System.err.println("This choice does not exist!\nBye bye");
				System.exit(1);

		}

		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://sql110.epizy.com/epiz_32861716_hotelfinderdb","epiz_32861716","ehIiqX6cItun");  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from 'Client';");  
			while(rs.next())  
				System.out.println(rs.getInt("ID"));  
			con.close();  
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
		  
		
		Client client = null;
		if(!debug) {
			client = MainFunctions.connectClient(agency);		
		}
		else {
			Set<Client> list =agency.getClientInfos().keySet();
			List<Client> stringsList = new ArrayList<>(list);
			client = stringsList.get(0);
		}
		
		
<<<<<<< HEAD
		switch (choice) {
		case 1:
			scanner.nextLine(); 
			System.out.println("Where do you want to go ?\n");
			String location = scanner.nextLine();
			System.out.println("When would you like to go ? (yyyy-MM-dd))\n");
			String in = scanner.nextLine();
			System.out.println("When would you like to leave ? (yyyy-MM-dd))\n");
			String out = scanner.nextLine();
			System.out.println("How many people will be with you ?\n");
			int size = scanner.nextInt();
			System.out.println("Select your range of price\n Price min : ");
			int priceMin = scanner.nextInt();
			System.out.println("Price max : ");
			int priceMax = scanner.nextInt();
			System.out.println("Minimum of rating: ");
			double rating = scanner.nextInt();
			System.out.println("Looking for the best offers...\n");
			
			ArrayList<Hotel> hotels = research(agency, proxy, location, size, in, out, priceMin, priceMax, rating);
			int choice2 = 0;
			if(!hotels.isEmpty()) {
				for (int i = 1; i <= hotels.size() ; i++) {
					Hotel hotel = hotels.get(i-1);
					System.out.println(hotel.getName() + " " + hotel.getStars() + "\n" + hotel.getAddress().toString() +"\n");
					for(int j = 1; j <= hotel.getRooms().size(); j++) {
						Room room = hotel.getRooms().get(j-1);
						System.out.println("N°" + i + "-" + j + " : " + room.toString());
					}					
				}
				System.out.println("Hotel number : ");
				int hotelChoice = scanner.nextInt() - 1;
				LocalDate ind = LocalDate.parse(in) ;
				System.out.println("Room number : ");
				int roomChoice = scanner.nextInt() - 1;
				LocalDate outd = LocalDate.parse(out); 
				try {
					Reservation resa = makeReservation(c, ind, outd, hotels.get(hotelChoice).getRooms().get(roomChoice), hotels);
					hotels.get(hotelChoice).getResa().add(resa);
					System.out.println("Your order have been placed\n Thank you for your purchase.\n");
					resa.getRecipe();
					System.out.println(hotels.get(hotelChoice).searchResa(c, ind));
				} catch (ReservationException e) {
					e.printStackTrace();
				}
				
				//System.out.println(hotels.get(hotelChoice).getResa());
				
			}
			else {
				System.out.println("No hotel applicable to your needs.\n");
				System.out.println("1. Make another research\n2. Back to main menu\n");
				choice2 = scanner.nextInt();
=======
		System.out.println("Welcome to "+ agency.getAgencyName() +" !");
		System.out.println("logged as "+ client.getFirstname()+" "+client.getName());
		
		int choice = -1;
		while(choice != 3) {
			System.out.println("1. Find a hotel \n2. View all available offers \n3. Exit");
			choice = scanner.nextInt();
			switch (choice) {
				case 1 :
					MainFunctions.hotelFinder(agency, client);
					break;
				case 2 :
					MainFunctions.viewAll(agency, client);
					break;
				case 3 :
					break;
				default :
					System.err.println("Option not available !\n");
					break;
>>>>>>> 6a2b5984b8cfbaf79af69ebdf4a87373a768233b
			}
			
		}
		scanner.close();
	}
}
