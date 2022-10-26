package repository;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Hotel;
import model.Position;
import model.Reservation;
import model.Room;

public class Formule1RepositoryImpl implements HotelRepository {

	private Hotel hotel = new Hotel();
	
	public Formule1RepositoryImpl() {
		ArrayList<Room> rooms = new ArrayList<>();
		Room room1 = new Room(1, true, 20.3, 2);
		Room room2 = new Room(2, true, 20.3, 2);
		Room room3 = new Room(3, true, 20, 2);
		Room room4 = new Room(4, true, 21.0, 2);
		Room room5 = new Room(5, true, 25, 3);

		
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		rooms.add(room4);
		rooms.add(room5);

		this.hotel = new Hotel(
				"Formule 1",
				2.5,
				rooms,
				new Position("Toulouse", "France", "Rue du Concorde", 23));
		
		hotel.setResa(new ArrayList<>());
	}
	
	@Override
	public String roomsToString() {
		String res = "";
		for (int index = 0; index < this.hotel.getRooms().size(); index++) {
			res += this.hotel.getRooms().get(index).toString();
		}
		return res;
	}
	
	@Override
	public String toString() {
		return "Hotel "+ this.hotel.getName() +"\n Rating : " + this.hotel.getStars() + "\n" + this.hotel.getAddress() + "\n" + roomsToString();
	}
	
	@Override
	public ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, String inS, String outS) {
		LocalDate in = LocalDate.parse(inS);
		LocalDate out = LocalDate.parse(outS);
		ArrayList<Room> results = new ArrayList<>();
		for (int index = 0; index < this.hotel.getRooms().size(); index++) {
			Room room = this.hotel.getRooms().get(index);
			double roomPrice = room.getPrice();
			int roomSize = room.getSize();
			if(roomPrice >= priceMin && roomPrice <= priceMax && roomSize >= size) {
				boolean isOkay = true;
				for(Reservation res : this.hotel.getResa()) {
					if(res.getRoom().getRoomNumber() == room.getRoomNumber()) {
						if((in.isAfter(res.getIn()) && in.isBefore(res.getOut()))
							|| (out.isAfter(res.getIn()) && out.isBefore(res.getOut()))
							|| ((in.isBefore(res.getIn()) && out.isAfter(res.getOut())))
							|| (in.isAfter(res.getIn()) && out.isBefore(res.getOut()))) {
							isOkay = false;
						}
					}
				}
				if(isOkay) {
					results.add(room);
				}
			}
		}
		return results;
	}
	
	@Override
	public void addReservation(Reservation resa) {
		this.hotel.getResa().add(resa);
	}

	@Override
	public Hotel getHotel() {
		return this.hotel;
	}

}
