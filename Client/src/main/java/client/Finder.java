package client;

import java.time.LocalDate;
import java.util.ArrayList;

import exception.ReservationException;
import webservice.Client;
import webservice.Hotel;
import webservice.Reservation;
import webservice.Room;



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
	


}
