package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import exception.ReservationException;
import webservice.Client;
import webservice.Hotel;
import webservice.HotelService;
import webservice.HotelServiceImplService;
import webservice.Reservation;
import webservice.Room;
import webservice.CreditCard;

public class MainFunctions {
	public static Agency HotelAdvisor() {
		HashMap<HotelService, Double> offers = new HashMap<>();
		HashMap<Client, String[]> clients = new HashMap<>();
		try {
			HotelService ritz = new HotelServiceImplService(new URL("http://localhost:8080/ritz?wsdl")).getHotelServiceImplPort();
			HotelService ibis = new HotelServiceImplService(new URL("http://localhost:8080/ibis?wsdl")).getHotelServiceImplPort();
			offers.put(ritz, (double) 10);
			offers.put(ibis, (double) 5);
			Client c1 = new Client("Dubois", "Arnaud", "0658547018", 34);
			Client c2 = new Client("Chad", "Jérémie", "0658547018", 25);
			Client admin = new Client("admin", "admin", "0", 0);
			CreditCard cb1 = new CreditCard(admin, "0", "0", LocalDate.parse("2022-10-20"),1000);
			admin.setCc(cb1);
			String[] log1 = {"ArnaudCs","dub123"};
			String[] log2 = {"GigaChad","gomuscu24"};
			String[] log3 = {"admin","admin"};
			clients.put(admin, log3);
			clients.put(c1, log1);
			clients.put(c2, log2);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("Error while retrieving hotels's info");
			System.exit(1);
		}
		return new Agency("HotelAdvisor", clients, offers);
	}
	
	public static Agency HoteldotNet() {
		Agency agency = null;
		try {
			HashMap<HotelService, Double> offers = new HashMap<>();
			HashMap<Client, String[]> clients = new HashMap<>();
			HotelService ritz = new HotelServiceImplService(new URL("http://localhost:8080/ritz?wsdl")).getHotelServiceImplPort();
			HotelService formule1 = new HotelServiceImplService(new URL("http://localhost:8080/formule1?wsdl")).getHotelServiceImplPort();
			offers.put(ritz, (double) 10);
			offers.put(formule1, (double) 2);
			Client c1 = new Client("Dubois", "Arnaud", "0658547018", 34);
			Client c2 = new Client("Gatien", "Haddad", "0658547018", 25);
			Client admin = new Client("admin", "admin", "0", 0);
			CreditCard cb1 = new CreditCard(admin, "0", "0", LocalDate.parse("2022-10-20"),1000);
			admin.setCc(cb1);
			String[] log1 = {"ArnaudCs","dub123"};
			String[] log2 = {"Leogendra","linkisnotzelda"};
			String[] log3 = {"admin","admin"};
			clients.put(admin, log3);
			clients.put(c1, log1);
			clients.put(c2, log2);
			agency = new Agency("HotelAdvisor", clients, offers);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("Error while retrieving hotels's info");
			System.exit(1);
		}
		return agency;
	}

	public static Client connectClient(Agency agency ) {
		try (Scanner scanner = new Scanner(System.in)) {
			String username = "";
			String pwd = "";
			System.out.println("Please enter your username :\n");
			username = scanner.nextLine();
			System.out.println("Please enter your password :\n");
			pwd = scanner.nextLine();
			Client client = null;
			client = agency.connectClient(username, pwd);
			while(client == null) {
				System.out.println("Wrong credentials!\n");
				System.out.println("Please enter your username :\n");
				username = scanner.nextLine();
				System.out.println("Please enter your password :\n");
				pwd = scanner.nextLine();
				client = agency.connectClient(username, pwd);
			}

			return client;
		}
	}

	public static void hotelFinder(Agency agency, Client client) {
		try (Scanner scanner = new Scanner(System.in)) {
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
			
			ArrayList<Hotel> hotels = research(agency, agency.getOffers(), location, size, in, out, priceMin, priceMax, rating);
			
			if(hotels.isEmpty()) {
				System.err.println("Sorry, no hotels corresponding to your needs.");
				return;
			}
			
			for (int i = 1; i <= hotels.size() ; i++) {
				Hotel hotel = hotels.get(i-1);
				System.out.println(hotel.getName() + " " + hotel.getStars() + "\n" + hotel.getAddress().toString() +"");
				for(int j = 1; j <= hotel.getRooms().size(); j++) {
					Room room = hotel.getRooms().get(j-1);
					System.out.println("N°" + i + "-" + j + " : " + room.toString());
				}					
			}
			System.out.println("Would you like to order one of these ?\n");
			int hotelChoice = -1;
			int roomChoice = 0;
			while(hotelChoice == -1) {
				System.out.println("Hotel number (0 to exit): ");
				hotelChoice = scanner.nextInt();
				if(hotelChoice == 0) {
					System.out.println("Quitting hotel research...");
					return;
				}
				else if(hotelChoice > hotels.size() || hotelChoice <= -1) {
					System.err.println("Impossible choice !");
					hotelChoice = -1;
				}
				else {
					System.out.println("Room number : ");
					roomChoice = scanner.nextInt();					
				}
			}
			LocalDate ind = LocalDate.parse(in) ;
			LocalDate outd = LocalDate.parse(out); 
			try {
				makeReservation(agency, client, ind, outd, hotels.get(hotelChoice-1).getRooms().get(roomChoice-1), hotels.get(hotelChoice-1));
			} catch (ReservationException e) {
				e.printStackTrace();
			}
		}
	}
		
	public static ArrayList<Hotel> research(Agency agency, HashMap<HotelService, Double> proxy, String location, int size, String in, String out, int priceMin, int priceMax, double rating) {
		ArrayList<Hotel> hotels = new ArrayList<>();
		for (Entry<HotelService, Double> prox : proxy.entrySet()) {
			HotelService hotel = prox.getKey();
			if((hotel.getHotel().getAddress().getCity().equals(location) || hotel.getHotel().getAddress().getCountry().equals(location))
					&& hotel.getHotel().getStars() >= rating) {
				Hotel results = agency.searchRoom(hotel, in, out, size, priceMin, priceMax);
				if(!results.getRooms().isEmpty()) {
					for (Room room : results.getRooms()) {
						room.setPrice(room.getPrice() - (room.getPrice() / 100 ) * agency.getOffers().get(hotel));
						double value =Double.parseDouble(new DecimalFormat("##.##").format(room.getPrice()));
						room.setPrice(value);
					}
					hotels.add(results);						
				}
			}
		}
		return hotels;
	}

	public static void makeReservation(Agency agency, Client client, LocalDate in, LocalDate out, Room room, Hotel hotel) throws ReservationException {
		Reservation resa = null;
		System.out.println("Use your saved payment method ? [y/n]");
		try (Scanner scanner = new Scanner(System.in)) {
			String choice = scanner.nextLine();
			if(choice.equals("y")) {
				CreditCard cc = client.getCc();
				if(cc == null || cc.getCvv() == "000") {
					System.err.println("No payment method found !");
				}
				else {
					double creditCardBalance = client.getCc().getAmount();
					double price = room.getPrice() - (room.getPrice() / 100) * agency.getOffers().get(hotel);
					if(creditCardBalance >= price) {
							client.subMoney(price);
							resa = new Reservation(client, in, out, room);
							hotel.getResa().add(resa);
							System.out.println("Your order have been placed. Thank you for your purchase !\n");
							getRecipe(hotel, client, resa);
					}
					else {
						System.err.println("Please verify your account balance.");
						System.err.println("Problem during your reservation please try again.");
						return;
					}
				}
			}
			else {
				System.out.println("Card number : ");
				String num = scanner.nextLine();
				System.out.println("CVV number : ");
				String cvv = scanner.nextLine();
				System.out.println("Expiration date (yyyy-mm-dd) : ");
				LocalDate exp = LocalDate.parse(scanner.nextLine());
				resa = new Reservation(client, in, out, room);
				hotel.getResa().add(resa);
				System.out.println("Your order have been placed. Thank you for your purchase !\n");
				getRecipe(hotel, client, resa);
				
			}
		}
	}

	public static void viewAll(Agency agency, Client client) {
		System.out.println("Not yet working. Please try again in the next days.");
	}
	
	public static void getRecipe(Hotel hotel, Client client, Reservation resa) {
		int size = 31;  
		System.out.println(Reservation.formRecipe(size, "Client Infos"));
		System.out.println("|                             |");
		System.out.println(Reservation.adaptiveDisplay("info", client.getFirstname(), size));
		System.out.println(Reservation.adaptiveDisplay("info", client.getName(), size));
		System.out.println(Reservation.adaptiveDisplay("info", client.getTelNumber(), size));
		System.out.println("|                             |");
		System.out.println(Reservation.formRecipe(size, "Reservation Infos"));
		System.out.println("|                             |");
		System.out.println(Reservation.adaptiveDisplay("hotelName", hotel.getName(), size));
		System.out.println(Reservation.adaptiveDisplay("room", String.valueOf(resa.getRoom().getRoomNumber()), size));
		System.out.println(Reservation.adaptiveDisplay("datein", String.valueOf(resa.getIn()), size));
		System.out.println(Reservation.adaptiveDisplay("dateout", String.valueOf(resa.getOut()), size));
		System.out.println(Reservation.adaptiveDisplay("price", String.valueOf(resa.getRoom().getPrice())+"€", size));
		System.out.println(Reservation.formRecipe(size, "footer"));
	}

}
