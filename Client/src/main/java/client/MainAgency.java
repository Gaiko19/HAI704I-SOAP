package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import exception.ReservationException;
import webservice.Client;
import webservice.CreditCard;
import webservice.Hotel;
import webservice.HotelService;
import webservice.HotelServiceImplService;
import webservice.Reservation;
import webservice.Room;

public class MainAgency {
	
	
	public static Reservation makeReservation(Client client, LocalDate in, LocalDate out, Room room, ArrayList<Hotel> possibleHotels) throws ReservationException {
		boolean check = false;
		Reservation resa = null;
		for(Hotel hotel : possibleHotels) {
			if(hotel.getRooms().contains(room)) {
				check = true;
			}
		}
		if(check) {
			double creditCradBalance = client.getCc().getAmount();
			if(creditCradBalance >= room.getPrice()) {
					client.subMoney(room.getPrice());
					resa = new Reservation(client, in, out, room);
			}
			else {
				throw new ReservationException("Please verify your account balance");			
			}
		}
		else {
			throw new ReservationException("This room is not available for the selected date");
		}

		return resa;
		
	}
	
	
	public static ArrayList<Hotel> research(Agency agency, HashMap<String, HotelService> proxy, String location, int size, String in, String out, int priceMin, int priceMax, double rating) {
		ArrayList<Hotel> hotels = new ArrayList<>();
		for (Entry<String, HotelService> prox : proxy.entrySet()) {
			HotelService hotel = prox.getValue();
			if((hotel.getHotel().getAddress().getCity().equals(location) || hotel.getHotel().getAddress().getCountry().equals(location))
					&& hotel.getHotel().getStars() >= rating) {
				Hotel results = agency.searchRoom(hotel, in, out, size, priceMin, priceMax);
				if(!results.getRooms().isEmpty()) {
					for (Room room : results.getRooms()) {
						room.setPrice(room.getPrice() - (room.getPrice() / 100 ) * agency.getOffers().get(hotel));
					}
					hotels.add(results);						
				}
			}
		}
		return hotels;
	}

	public static void main(String[] args) {
		
		boolean debug = true;
		Agency agency = new Agency();
		HashMap<String, HotelService> proxy = new HashMap<>();
		
		try {
			HotelService ritz = new HotelServiceImplService(new URL("http://localhost:8080/ritz?wsdl")).getHotelServiceImplPort();
			proxy.put("ritz", ritz);
			HotelService ibis = new HotelServiceImplService(new URL("http://localhost:8080/ibis?wsdl")).getHotelServiceImplPort();
			proxy.put("ibis", ibis);
			agency.getOffers().put(ibis, (double) 10);
			agency.getOffers().put(ritz, (double) 5);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("Error while retrieving hotels's info");
			System.exit(1);
		}
		Client c = new Client("Dubois", "Arnaud", "0658547018", 34);
		LocalDate exp = LocalDate.parse("2035-10-10");
		CreditCard cb = new CreditCard(c, "4759 5130 4796 5822", "123", exp, 2000.0);
		c.setCc(cb);
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
			String location = "France";
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
			System.out.println("Minimum of rating: ");
			double rating = 3;			
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
					System.out.println("Your order have been placed\n Thank you for your purchase.");
				} catch (ReservationException e) {
					e.printStackTrace();
				}
				
				
				System.out.println(hotels.get(hotelChoice).getResa());
				
			}
			else {
				System.out.println("No hotel applicable to your needs.\n");
				System.out.println("1. Make another research\n2. Back to main menu\n");
				choice2 = scanner.nextInt();
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
