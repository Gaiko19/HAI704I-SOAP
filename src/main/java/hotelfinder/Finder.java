package hotelfinder;

import java.time.LocalDate;
import java.util.ArrayList;

import hotel.Client;
import hotel.Hotel;
import hotel.Reservation;
import hotel.Room;

public class Finder {
	
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
	
	public static ArrayList<Hotel> searchHotel(ArrayList<Hotel> hotels, LocalDate in, LocalDate out, int size, float priceMin, float priceMax, String location) {
		ArrayList<Hotel> results = new ArrayList<Hotel>();
		for (Hotel hotel : hotels) {
			if(hotel.getAddress().getCountry().equals(location) || hotel.getAddress().getCity().equals(location)) {
				ArrayList<Room> rooms = hotel.searchRoom(priceMin, priceMax, size, in, out);
				if(rooms != null) {
					hotel.setRooms(rooms);
					results.add(hotel);
				}				
			}
		}
		return results;
	}

	public static void main(String[] args) {
		/*ArrayList<Hotel> hotels = new ArrayList<>();
		
		// Init Hotel 1
		Position pos1 = new Position("Montpellier", "France", "Avenue de Barcelone", 12);
		ArrayList<Room> rooms1 = new ArrayList<Room>();
		Room room11 = new Room(1, true, 150, 4);
		Room room12 = new Room(2, true, 130, 3);
		Room room13 = new Room(3, true, 169, 4);
		rooms1.add(room11); rooms1.add(room12); rooms1.add(room13);
		Hotel hotel1 = new Hotel("Ritz", (float) 4.9, rooms1, pos1);
		
		// Init Hotel 2
		Position pos2 = new Position("Paris", "France", "rue du Concorde", 1);
		ArrayList<Room> rooms2 = new ArrayList<Room>();
		Room room21 = new Room(1, true, 89, 3);
		Room room22 = new Room(2, true, 85, 2);
		Room room23 = new Room(3, true, 83, 2);
		rooms2.add(room21); rooms2.add(room22); rooms2.add(room23);
		Hotel hotel2 = new Hotel("Adagio", (float) 4.6, rooms2, pos2);
		
		hotels.add(hotel1);
		hotels.add(hotel2);
		
		// Init client 1
		Client client1 = new Client("Chad", "Jerem", "061545480807", 34);
		LocalDate expCC1 = LocalDate.of(2035,12,5);
		CreditCard cc1 = new CreditCard("4975 7154 2242 3683", "012", expCC1, 2000); 
		client1.setCc(cc1);
		
		
		System.out.println("Affichage des Hotels et leurs chambres\n");
		System.out.println(hotels);
		
		System.out.println("Press enter to continue");
		try{System.in.read();}
		catch(Exception e){e.printStackTrace();}
		
		System.out.println("Affichage du client : \n");
		System.out.println(client1.toString());
		
		System.out.println("Press enter to continue");
		try{System.in.read();}
		catch(Exception e){e.printStackTrace();}
		
		// Création première résa
		System.out.println("Tentative de résa\n");
		String location = "Montpellier";
		LocalDate in = LocalDate.parse("2022-10-30");
		LocalDate out = LocalDate.parse("2022-11-02");
		float prixMin = 70;
		float prixMax = 150;
		int size = 2;
		
		ArrayList<Hotel> possibleHotels = searchHotel(hotels, in, out, size, prixMin, prixMax, location);
		
		System.out.println("Affichage des hotels possibles :\n");
		System.out.println(possibleHotels);
		
		int choice = 0;
		IHotel choosedHotel = possibleHotels.get(choice);
		Room choosedRoom = choosedHotel.getRooms().get(choice);
		
		
		try {
			Reservation resa = makeReservation(client1, in, out, choosedRoom, possibleHotels);
			choosedHotel.addReservation(resa);
			System.out.println("Réservation enregistrée\n"+ resa.toString());
		} catch (ReservationException e) {
			e.printStackTrace();
		}
		
		
		// Création deuxième résa
		System.out.println("Tentative de résa\n");
		in = LocalDate.parse("2022-11-01");
		out = LocalDate.parse("2022-11-07");
		possibleHotels = searchHotel(hotels, in, out, size, prixMin, prixMax, location);
		System.out.println("Affichage des hotels possibles :\n");
		System.out.println(possibleHotels);
		
		
		try {
			Reservation resa = makeReservation(client1, in, out, choosedRoom, possibleHotels);
			choosedHotel.addReservation(resa);
			System.out.println("Réservation enregistrée\n"+ resa.toString());
		} catch (ReservationException e) {
			e.printStackTrace();
		}
		
		
*/
	}

}
