package client;

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
//		String dburl = "jdbc:mysql:/sql110.epizy.com/epiz_32861716_hotelfinderdb";
//		String user = "epiz_32861716";
//		String dbpwd = "ehIiqX6cItun";
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection connect = DriverManager.getConnection(dburl,user,dbpwd);
//			Statement statement = connect.createStatement();
//			ResultSet result= statement.executeQuery("select * from Client");
//			while(result.next()) {
//				System.out.println(result.getInt(1));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Client client = null;
		if(!debug) {
			client = MainFunctions.connectClient(agency);		
		}
		else {
			Set<Client> list =agency.getClientInfos().keySet();
			List<Client> stringsList = new ArrayList<>(list);
			client = stringsList.get(0);
		}
		
		
		System.out.println("Welcome to "+ agency.getAgencyName() +" !");
		
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
			}
			
		}
		scanner.close();
	}
}
